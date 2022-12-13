CREATE SCHEMA `hsm` ;

CREATE TABLE `hsm`.`user` (
                              `Uid` INT NOT NULL AUTO_INCREMENT,
                              `UserPass` VARCHAR(45) NOT NULL,
                              `UserType` ENUM('manager', 'reception', 'client') NOT NULL,
                              PRIMARY KEY (`Uid`));

CREATE TABLE `hsm`.`type` (
                              `Tid` INT NOT NULL AUTO_INCREMENT,
                              `TypeName` VARCHAR(45) NOT NULL,
                              `TypePrice` DECIMAL(10,2) NOT NULL,
                              PRIMARY KEY (`Tid`));

CREATE TABLE `hsm`.`room` (
                              `Rid` INT NOT NULL AUTO_INCREMENT,
                              `Floor` VARCHAR(45) NULL,
                              `Feature` VARCHAR(45) NULL,
                              `Orientation` VARCHAR(45) NULL,
                              `Tid` INT NOT NULL,
                              PRIMARY KEY (`Rid`),
                              INDEX `Tid_idx` (`Tid` ASC) VISIBLE,
                              CONSTRAINT `Tid`
                                  FOREIGN KEY (`Tid`)
                                      REFERENCES `hsm`.`type` (`Tid`)
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION);

CREATE TABLE `hsm`.`book` (
                              `Bid` INT NOT NULL,
                              `Rid` INT NOT NULL,
                              `Uid` INT NOT NULL,
                              `StartTime` DATE NOT NULL,
                              `EndTime` DATE NOT NULL,
                              `Price` DECIMAL NOT NULL,
                              PRIMARY KEY (`Bid`),
                              INDEX `Rid_idx` (`Rid` ASC) VISIBLE,
                              INDEX `Uid_idx` (`Uid` ASC) VISIBLE,
                              CONSTRAINT `Rid`
                                  FOREIGN KEY (`Rid`)
                                      REFERENCES `hsm`.`room` (`Rid`)
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION,
                              CONSTRAINT `Uid`
                                  FOREIGN KEY (`Uid`)
                                      REFERENCES `hsm`.`user` (`Uid`)
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION);

ALTER TABLE `hsm`.`user`
    ADD COLUMN `UserCode` VARCHAR(45) NOT NULL AFTER `UserType`;

ALTER TABLE `hsm`.`book`
DROP FOREIGN KEY `Uid`;
ALTER TABLE `hsm`.`book`
    CHANGE COLUMN `Uid` `UserCode` VARCHAR(45) NOT NULL ,
DROP INDEX `Uid_idx` ;
;

ALTER TABLE `hsm`.`user`
    CHANGE COLUMN `UserCode` `UserCode` VARCHAR(45) NOT NULL AFTER `Uid`;

ALTER TABLE `hsm`.`book`
    ADD COLUMN `Checked` ENUM("yes", "no") NOT NULL DEFAULT 'no' AFTER `Price`,
    ADD COLUMN `Paid` ENUM("yes", "no") NOT NULL DEFAULT 'no' AFTER `Checked`;

CREATE
ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `room_type` AS
SELECT
    `room`.`Rid` AS `Rid`,
    `room`.`Floor` AS `Floor`,
    `room`.`Feature` AS `Feature`,
    `room`.`Orientation` AS `Orientation`,
    `room`.`Tid` AS `Tid`,
    `type`.`TypeName` AS `TypeName`,
    `type`.`TypePrice` AS `TypePrice`
FROM
    (`room`
        LEFT JOIN `type` ON ((`type`.`Tid` = `room`.`Tid`)))

ALTER TABLE `hsm`.`book`
    CHANGE COLUMN `Bid` `Bid` INT NOT NULL AUTO_INCREMENT ;

CREATE
ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `new_view` AS
SELECT
    `room_type`.`Rid` AS `Rid`,
    `room_type`.`Floor` AS `Floor`,
    `room_type`.`Feature` AS `Feature`,
    `room_type`.`Orientation` AS `Orientation`,
    `room_type`.`Tid` AS `Tid`,
    `room_type`.`TypeName` AS `TypeName`,
    `room_type`.`TypePrice` AS `TypePrice`
FROM
    `room_type`
WHERE
        `room_type`.`Rid` IN (SELECT
                                  `book`.`Rid`
                              FROM
                                  `book`
                              WHERE
                                  (NOW() BETWEEN `book`.`StartTime` AND `book`.`EndTime`))
        IS FALSE

DROP VIEW IF EXISTS `hsm`.`new_view` ;
USE `hsm`;
CREATE
OR REPLACE ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `room_empty` AS
SELECT
    `room_type`.`Rid` AS `Rid`,
    `room_type`.`Floor` AS `Floor`,
    `room_type`.`Feature` AS `Feature`,
    `room_type`.`Orientation` AS `Orientation`,
    `room_type`.`Tid` AS `Tid`,
    `room_type`.`TypeName` AS `TypeName`,
    `room_type`.`TypePrice` AS `TypePrice`
FROM
    `room_type`
WHERE
        `room_type`.`Rid` IN (SELECT
                                  `book`.`Rid`
                              FROM
                                  `book`
                              WHERE
                                  (NOW() BETWEEN `book`.`StartTime` AND `book`.`EndTime`))
        IS FALSE;

DROP TRIGGER IF EXISTS `hsm`.`book_BEFORE_INSERT`;

DELIMITER $$
USE `hsm`$$
CREATE DEFINER = CURRENT_USER TRIGGER `hsm`.`book_BEFORE_INSERT` BEFORE INSERT ON `book` FOR EACH ROW
BEGIN
	set @price = (select TypePrice from hsm.room_type where room_type.Rid = new.Rid);
    set new.price = @price * DATEDIFF(new.EndTime, new.StartTime);
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS `hsm`.`book_BEFORE_UPDATE`;

DELIMITER $$
USE `hsm`$$
CREATE DEFINER = CURRENT_USER TRIGGER `hsm`.`book_BEFORE_UPDATE` BEFORE UPDATE ON `book` FOR EACH ROW
BEGIN
	if (new.EndTIme != old.EndTIme) then
		set @price = (select TypePrice from hsm.room_type where room_type.Rid = new.Rid);
		set new.Price = @Price * DATEDIFF(new.EndTime, old.EndTime) +old.Price;
end if;
END$$
DELIMITER ;
