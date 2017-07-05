/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.6.21 : Database - dh_cas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dh_cas` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dh_cas`;

/*Table structure for table `f_user` */

DROP TABLE IF EXISTS `f_user`;

CREATE TABLE `f_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` int(11) NOT NULL DEFAULT '0' COMMENT '归属公司',
  `office_id` int(11) NOT NULL DEFAULT '0' COMMENT '归属部门',
  `username` varchar(40) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(80) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(64) NOT NULL DEFAULT '' COMMENT '盐',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '姓名',
  `email` varchar(60) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '电话',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机',
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型 0普通用户 1管理员',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像',
  `last_ip` varchar(15) NOT NULL DEFAULT '' COMMENT '最后登陆IP',
  `last_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后登陆时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态 0:正常 1:查封 2:待审核',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`username`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_time`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `f_user` */

LOCK TABLES `f_user` WRITE;

insert  into `f_user`(`id`,`company_id`,`office_id`,`username`,`password`,`salt`,`name`,`email`,`phone`,`mobile`,`user_type`,`photo`,`last_ip`,`last_time`,`status`,`create_id`,`create_time`,`update_id`,`update_time`,`remark`,`del_flag`) values (1,0,0,'admin','1a6dc0b974ebf951d684453cb99267aa','fbc5d00a625883c0','admin','itmuch0000@126.com','15151816012','15151816012',1,'','','0000-00-00 00:00:00',0,0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(6,2,2,'eacdy','cf40fde8ebefd750afcd111c0e39ecff','78777995e30d8e48','eacdy','eacdy0000@126.com','15850770072','15850770072',1,'','','0000-00-00 00:00:00',0,1,'2015-08-20 16:14:54',0,'0000-00-00 00:00:00','test',0);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
