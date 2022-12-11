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
                              `End` DATE NOT NULL,
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

