INSERT INTO `patients` (`created`,`description`,`email`,`first_name`,`last_name`,`pesel`,`phone`,`updated`) VALUES ('2021-04-21 20:01:41.961967','gshfgsdjgfhsdg\r\nsdgjidhfghdfghdfhgdfg\r\nsdghfjshdghfdfsghjdfgjfdhgj','nowaks@wp.pl','Stefan','Nowak','67011105111','456789234',NULL);
INSERT INTO `patients` (`created`,`description`,`email`,`first_name`,`last_name`,`pesel`,`phone`,`updated`) VALUES ('2021-04-21 20:03:51.481579','hdsfgudfhgjhdfg\r\naessdjkfdjhsgjdfjgd\r\ngjbwejgjwhgjkhdskjfhdkxgvd\r\nsdjhfbdshgbjdsbjdbzfjsbdfjsdjfb','akowalska@gmail.com','Anna','Kowalska','00271164810','4575676888',NULL);
INSERT INTO `patients` (`created`,`description`,`email`,`first_name`,`last_name`,`pesel`,`phone`,`updated`) VALUES ('2021-04-21 20:05:02.978524','sdghdjkhfgjhdfjghdf\r\nsfjhsdjfhdsjhgjdhfgjhdfg\r\nsadhsgdfhgdshgfdsjghjdsgjjdg','cymbal@poczta.pl','Antoni','Antoni','00311138515','123456782',NULL);

INSERT INTO users(username, email, password, enabled) values('user', 'user@user.user', '$2y$04$xeupe1xeGug9Z6A.0LyB6.t7/sp3Ztq1atUuUr7H.213QDddoojwG', 1);
INSERT INTO users(username, email, password, enabled) values('admin', 'admin@admin.admin', '$2y$04$gNsgG4oBku4OZ3rqblAyQO4JcFORUrOr9nImUp0Fk2/DAsGiHERHC', 1);

INSERT INTO authorities(authority) values('ADMIN');
INSERT INTO authorities(authority) values('USER');

INSERT INTO users_authorities(users_id, authorities_id) values(1, 2);
INSERT INTO users_authorities(users_id, authorities_id) values(2, 1);
INSERT INTO users_authorities(users_id, authorities_id) values(2, 2);

INSERT INTO `therapist_relief`.`sessions` (`created`,`date`,`notes`,`payment_actual`,`payment_due`,`time`,`users_id`)VALUES('2021-04-21 20:01:41.961967','2021-05-04','dsfdf drgdfgfdgdcg',0.00,150.00,'15:00',1);
INSERT INTO `therapist_relief`.`sessions` (`created`,`date`,`notes`,`payment_actual`,`payment_due`,`time`,`users_id`)VALUES('2021-04-21 20:01:41.961967','2021-05-18','dsfdf drgdfgfdgdcg',70.00,150.00,'16:00',1);
INSERT INTO `therapist_relief`.`sessions` (`created`,`date`,`notes`,`payment_actual`,`payment_due`,`time`,`users_id`)VALUES('2021-04-21 20:01:41.961967','2021-05-04','dsfdf drgdfgfdgdcg',150.00,150.00,'17:00',1);
INSERT INTO `therapist_relief`.`sessions` (`created`,`date`,`notes`,`payment_actual`,`payment_due`,`time`,`users_id`)VALUES('2021-04-21 20:01:41.961967','2021-05-20','sdgdfg',0.00,150.00,'11:00',2);
INSERT INTO `therapist_relief`.`sessions` (`created`,`date`,`notes`,`payment_actual`,`payment_due`,`time`,`users_id`)VALUES('2021-04-21 20:01:41.961967','2021-05-20','erdgdfhhfgh dfchdfghghfgh \n sdfdsfdfgdfgfdg',150.00,150.00,'13:00',2);


INSERT INTO patients_sessions(patients_id, sessions_id) values(1,1);
INSERT INTO patients_sessions(patients_id, sessions_id) values(3,1);
INSERT INTO patients_sessions(patients_id, sessions_id) values(1,2);
INSERT INTO patients_sessions(patients_id, sessions_id) values(3,3);
INSERT INTO patients_sessions(patients_id, sessions_id) values(2,4);
INSERT INTO patients_sessions(patients_id, sessions_id) values(2,5);