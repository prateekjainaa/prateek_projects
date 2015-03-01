/** to accomodate vvip, default general **/
 alter table members MODIFY is_vip varchar(1) NOT NULL default '1';
 
 alter table members ADD work_category varchar(1) NOT NULL default '4';
 
INSERT INTO work_type_category VALUES (1, 'Corporate');
INSERT INTO work_type_category VALUES (2, 'Medical');
INSERT INTO work_type_category VALUES (3, 'Business');
INSERT INTO work_type_category VALUES (4, 'Others');