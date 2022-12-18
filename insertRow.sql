INSERT INTO `hsm`.`user` (`Uid`, `UserPass`, `UserType`, `UserCode`) VALUES ('1', '123456', 'manager', '1');
INSERT INTO `hsm`.`user` (`UserPass`, `UserType`, `UserCode`) VALUES ('123456', 'reception', '2');
INSERT INTO `hsm`.`user` (`UserPass`, `UserType`, `UserCode`) VALUES ('123456', 'client', '3');

INSERT INTO `hsm`.`type` (`TypeName`, `TypePrice`) VALUES ('单人间', '100');
INSERT INTO `hsm`.`type` (`TypeName`, `TypePrice`) VALUES ('大床间', '180');
INSERT INTO `hsm`.`type` (`TypeName`, `TypePrice`) VALUES ('双床间', '220');
INSERT INTO `hsm`.`type` (`TypeName`, `TypePrice`) VALUES ('三床间', '300');
INSERT INTO `hsm`.`type` (`TypeName`, `TypePrice`) VALUES ('多床间', '400');

INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1001', '舒适单人间，楼层低出行方便', '北', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1002', '舒适单人间，楼层低出行方便', '东', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1003', '双人间，夫妻旅行首选，楼层低出行方便', '东', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1004', '双人间，夫妻旅行首选，楼层低出行方便', '南', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1005', '双人间，工作出差的优质选择，楼层低出行方便', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1006', '双人间，工作出差的优质选择，楼层低出行方便', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1007', '家庭旅行的优质之选楼层低出行方便', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1008', '大家庭也能家庭旅行的优质之选楼层低出行方便容纳，楼层低出行方便', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('1009', '大家庭也能容纳，楼层低出行方便', '南', '5');

INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2001', '舒适单人间，楼层低出行方便', '北', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2002', '舒适单人间，楼层低出行方便', '东', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2003', '双人间，夫妻旅行首选，楼层低出行方便', '东', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2004', '双人间，夫妻旅行首选，楼层低出行方便', '南', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2005', '双人间，工作出差的优质选择，楼层低出行方便', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2006', '双人间，工作出差的优质选择，楼层低出行方便', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2007', '家庭旅行的优质之选楼层低出行方便', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2008', '大家庭也能家庭旅行的优质之选楼层低出行方便容纳，楼层低出行方便', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('2009', '大家庭也能容纳，楼层低出行方便', '南', '5');

INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3001', '舒适单人间，中高楼层风景秀丽', '北', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3002', '舒适单人间，中高楼层风景秀丽', '东', '1');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3003', '双人间，夫妻旅行首选，中高楼层风景秀丽', '东', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3004', '双人间，夫妻旅行首选，中高楼层风景秀丽', '南', '2');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3005', '双人间，工作出差的优质选择，中高楼层风景秀丽', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3006', '双人间，工作出差的优质选择，中高楼层风景秀丽', '南', '3');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3007', '家庭旅行的优质之选中高楼层风景秀丽', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3008', '大家庭也能家庭旅行的优质之选楼层低出行方便容纳，中高楼层风景秀丽', '西', '4');
INSERT INTO `hsm`.`room` (`Floor`, `Feature`, `Orientation`, `Tid`) VALUES ('3009', '大家庭也能容纳，中高楼层风景秀丽', '南', '5');

INSERT INTO `hsm`.`book` (`Rid`, `UserCode`, `StartTime`, `EndTime`, `Price`) VALUES ('1', '3', '2022-12-12', '2022-12-20', '800');

INSERT INTO `hsm`.`check_people` (`Uid`, `CheckName`, `CheckCode`, `CheckPhone`) VALUES ('1', '入住用户1', '1', '123');
INSERT INTO `hsm`.`check_people` (`Uid`, `CheckName`, `CheckCode`, `CheckPhone`) VALUES ('2', '入住用户2', '2', '123');
INSERT INTO `hsm`.`check_people` (`Uid`, `CheckName`, `CheckCode`, `CheckPhone`) VALUES ('1', '入住用户3', '1', '123');

UPDATE `hsm`.`check_people` SET `Uid` = '3' WHERE (`Cid` = '1');
UPDATE `hsm`.`check_people` SET `Uid` = '4' WHERE (`Cid` = '2');
UPDATE `hsm`.`check_people` SET `Uid` = '3' WHERE (`Cid` = '3');
INSERT INTO `hsm`.`check_people` ( `CheckName`, `CheckCode`, `CheckPhone`) VALUES ( '入住用户4', '6', '123');

