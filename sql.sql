/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.9 : Database - asha_worker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`asha_worker` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `asha_worker`;

/*Table structure for table `ashaworker` */

DROP TABLE IF EXISTS `ashaworker`;

CREATE TABLE `ashaworker` (
  `worker_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`worker_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `ashaworker` */

insert  into `ashaworker`(`worker_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`) values (1,2,'Health','Workers','erkm','9078543216','qwe@gmail.com'),(2,3,'Anna','Philip','ktym','9078543216','qwe@gmail.com'),(3,4,'Sruthi','Malu','Malapuram','9078543216','sruthimalu@gmail.com');

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `attendance` */

insert  into `attendance`(`attendance_id`,`worker_id`,`date`) values (1,3,'2022-01-14'),(2,2,'2022-01-14');

/*Table structure for table `enquiry` */

DROP TABLE IF EXISTS `enquiry`;

CREATE TABLE `enquiry` (
  `enquiry_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `worker_id` int(11) DEFAULT NULL,
  `enquiry` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enquiry_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `enquiry` */

insert  into `enquiry`(`enquiry_id`,`user_id`,`worker_id`,`enquiry`,`reply`,`date`) values (1,1,3,'assdddf','kkk','2022-01-14'),(2,2,2,'fhui','pending','2022-01-14');

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `event` */

insert  into `event`(`event_id`,`event`,`date`) values (1,'WWW','2022-01-28'),(2,'WEE','2022-01-31'),(3,'mnmnn','2022-02-04');

/*Table structure for table `health` */

DROP TABLE IF EXISTS `health`;

CREATE TABLE `health` (
  `health_id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` int(11) DEFAULT NULL,
  `healthdetails` varchar(100) DEFAULT NULL,
  `files` varchar(1000) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`health_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `health` */

insert  into `health`(`health_id`,`worker_id`,`healthdetails`,`files`,`date`,`status`) values (1,2,'bbb','static/6cd089c6-9ff7-46d7-9ddd-6805b205f3edabc.jpg','2022-01-14','accept'),(2,3,'dfgghj','static/efe700f9-0536-4454-8f28-a272c0eae384abc.jpg','2022-01-14','pending'),(3,1,'ydfydyf','static/159f502b-d5ed-4e2b-a33c-6abd3caa0105abc.jpg','2022-01-14','accept'),(4,1,'mmmmm','static/d8226ffa-99e2-49fa-a019-d366fb84d83dabc.jpg','2022-01-14','pending');

/*Table structure for table `immunization` */

DROP TABLE IF EXISTS `immunization`;

CREATE TABLE `immunization` (
  `immunization_id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`immunization_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `immunization` */

insert  into `immunization`(`immunization_id`,`worker_id`,`date`,`status`) values (1,2,'2022-01-14','pending'),(2,3,'2022-01-14','accept');

/*Table structure for table `inspection` */

DROP TABLE IF EXISTS `inspection`;

CREATE TABLE `inspection` (
  `inspection_id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` int(11) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `files` varchar(1000) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`inspection_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `inspection` */

insert  into `inspection`(`inspection_id`,`worker_id`,`details`,`files`,`status`) values (1,2,'hhhh','static/ee667175-47f9-4036-b1fe-9cc4a328cdf4abc.jpg','accept'),(2,3,'bbbb','static/0e75dd77-4a9b-417a-95e0-790683769e7cabc.jpg','pending');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usertype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values (1,'admin','admin','admin'),(2,'worker','worker','ashaworker'),(3,'asha','asha','ashaworker'),(4,'sruthi','sruthi','ashaworker'),(6,'user','user','user'),(7,'new','new','user');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`) values (1,6,'Annnnaa','Thomas','ktym','9085741236','ertyuu@gmail.com'),(2,7,'Qwert','yuiop','asdf','3541269870','cvbn@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
