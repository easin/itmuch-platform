/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.6.21 : Database - dh_platform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dh_platform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dh_platform`;

/*Table structure for table `f_area` */

DROP TABLE IF EXISTS `f_area`;

CREATE TABLE `f_area` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `code` varchar(20) NOT NULL DEFAULT '00' COMMENT '区域编码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '区域类型',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建者id',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新者id',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='区域表';

/*Data for the table `f_area` */

LOCK TABLES `f_area` WRITE;

insert  into `f_area`(`id`,`parent_id`,`name`,`sort`,`code`,`type`,`create_id`,`create_time`,`update_id`,`update_time`,`remark`,`del_flag`) values (1,0,'江苏省',0,'320',2,1,'2015-08-20 16:53:15',0,'0000-00-00 00:00:00','江苏省',0),(2,0,'南京市',0,'320100',3,1,'2015-08-20 16:53:35',1,'2015-08-20 16:53:55','南京市',0);

UNLOCK TABLES;

/*Table structure for table `f_menu` */

DROP TABLE IF EXISTS `f_menu`;

CREATE TABLE `f_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `href` varchar(255) NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(10) NOT NULL DEFAULT '' COMMENT '目标',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `show_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否在菜单中显示 0:否 1:是',
  `permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限标识',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `f_menu` */

LOCK TABLES `f_menu` WRITE;

insert  into `f_menu`(`id`,`parent_id`,`name`,`sort`,`href`,`target`,`icon`,`show_flag`,`permission`,`create_id`,`create_time`,`update_id`,`update_time`,`remark`,`del_flag`) values (1,0,'管理',0,'','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(2,0,'监控',0,'','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(3,0,'演示',0,'/cxf','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(10,1,'用户管理',0,'user','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(11,10,'新增用户',0,'','','',0,'sys:user:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(12,10,'删除用户',0,'','','',0,'sys:user:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(13,10,'编辑用户',0,'','','',0,'sys:user:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(14,10,'查看用户',0,'','','',0,'sys:user:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(20,1,'菜单管理',0,'menu','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(21,20,'添加菜单',0,'','','',0,'sys:menu:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(22,20,'删除菜单',0,'','','',0,'sys:menu:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(23,20,'修改菜单',0,'','','',0,'sys:menu:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(24,20,'查看菜单',0,'','','',0,'sys:menu:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(30,1,'机构管理',0,'office','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(31,30,'添加机构',0,'','','',0,'sys:office:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(32,30,'删除机构',0,'','','',0,'sys:office:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(33,30,'修改机构',0,'','','',0,'sys:office:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(34,30,'查看机构',0,'','','',0,'sys:office:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(40,1,'角色管理',0,'role','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(41,40,'添加角色',0,'','','',0,'sys:role:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(42,40,'删除角色',0,'','','',0,'sys:role:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(43,40,'修改角色',0,'','','',0,'sys:role:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(44,40,'查看角色',0,'','','',0,'sys:role:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(50,2,'日志管理(MongoDB)',0,'log','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(51,50,'添加日志',0,'','','',0,'sys:log:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(52,50,'删除日志',0,'','','',0,'sys:log:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(53,50,'修改日志',0,'','','',0,'sys:log:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(54,50,'查看日志',0,'','','',0,'sys:log:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(60,1,'地区管理',0,'area','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(61,60,'添加地区',0,'','','',0,'sys:area:insert',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(62,60,'删除地区',0,'','','',0,'sys:area:del',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(63,60,'修改地区',0,'','','',0,'sys:area:edit',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(64,60,'查看地区',0,'','','',0,'sys:area:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(70,3,'webservice演示',0,'/cxf','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(71,3,'soap 演示',0,'/cxf/soap/user?wsdl','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(72,3,'jaxrs 演示 xml',0,'/cxf/jaxrs/user/1.xml','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(73,3,'jaxrs 演示 soap',0,'/cxf/jaxrs/user/1.json','','',1,'',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(100,2,'数据源监控(Druid)',0,'/druid','','',1,'sys:datasource:view',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(101,2,'项目监控(JavaMelody)',0,'/monitoring','','',1,'sys:monitoring:view',0,'0000-00-00 00:00:00',1,'2015-08-20 15:35:57','',0);

UNLOCK TABLES;

/*Table structure for table `f_office` */

DROP TABLE IF EXISTS `f_office`;

CREATE TABLE `f_office` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级编号',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '名称',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '归属区域',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '区域编码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '机构类型 1:公司 2:部门 3:小组 4:其他',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '联系地址',
  `zip_code` varchar(10) NOT NULL DEFAULT '' COMMENT '邮政编码',
  `master` varchar(40) NOT NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '电话',
  `fax` varchar(15) NOT NULL DEFAULT '' COMMENT '传真',
  `email` varchar(60) NOT NULL DEFAULT '' COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1:正常 0:无用',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`),
  KEY `sys_office_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

/*Data for the table `f_office` */

LOCK TABLES `f_office` WRITE;

UNLOCK TABLES;

/*Table structure for table `f_role` */

DROP TABLE IF EXISTS `f_role`;

CREATE TABLE `f_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` int(11) NOT NULL DEFAULT '0' COMMENT '归属机构',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '角色名称',
  `enname` varchar(60) NOT NULL DEFAULT '' COMMENT '英文名称',
  `role_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '角色类型',
  `sys_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否系统数据 0否 1是',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0:可用 1:禁用',
  `create_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_id` int(11) NOT NULL DEFAULT '0' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`enname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `f_role` */

LOCK TABLES `f_role` WRITE;

UNLOCK TABLES;

/*Table structure for table `f_role_menu` */

DROP TABLE IF EXISTS `f_role_menu`;

CREATE TABLE `f_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

/*Data for the table `f_role_menu` */

LOCK TABLES `f_role_menu` WRITE;

UNLOCK TABLES;

/*Table structure for table `f_role_office` */

DROP TABLE IF EXISTS `f_role_office`;

CREATE TABLE `f_role_office` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `office_id` int(11) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

/*Data for the table `f_role_office` */

LOCK TABLES `f_role_office` WRITE;

UNLOCK TABLES;

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

/*Table structure for table `f_user_role` */

DROP TABLE IF EXISTS `f_user_role`;

CREATE TABLE `f_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `f_user_role` */

LOCK TABLES `f_user_role` WRITE;

insert  into `f_user_role`(`user_id`,`role_id`) values (6,5);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
