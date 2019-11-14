/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - kellte
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kellte` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kellte`;

/*Table structure for table `reserve_datasourceconfig` */

DROP TABLE IF EXISTS `reserve_datasourceconfig`;

CREATE TABLE `reserve_datasourceconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据源配置主键ID',
  `dsName` varchar(255) DEFAULT NULL COMMENT '数据源名称',
  `dataBaseType` varchar(10) DEFAULT NULL COMMENT '数据源类型',
  `address` varchar(255) DEFAULT NULL COMMENT '数据源地址',
  `userName` varchar(255) DEFAULT NULL COMMENT '数据源登录用户名',
  `userPass` varchar(255) DEFAULT NULL COMMENT '数据源登录密码',
  `dsPort` varchar(255) DEFAULT NULL COMMENT '数据库连接端口',
  `dataBaseName` varchar(255) DEFAULT NULL COMMENT '数据库库名',
  `validStatus` varchar(32) NOT NULL COMMENT '有效状态，1：有效，0：无效',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `updateUserCode` varchar(32) DEFAULT NULL COMMENT '更新用户code',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserCode` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NAME` (`dsName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `reserve_datasourceconfig` */

insert  into `reserve_datasourceconfig`(`id`,`dsName`,`dataBaseType`,`address`,`userName`,`userPass`,`dsPort`,`dataBaseName`,`validStatus`,`updateTime`,`updateUserCode`,`CreateTime`,`createUserCode`) values (1,'admin','mysql','127.0.0.1','root','a123321','3306','test','1','2019-02-27 16:09:22','00000000','2019-02-27 16:09:35','00000000');

/*Table structure for table `reserve_extract_config` */

DROP TABLE IF EXISTS `reserve_extract_config`;

CREATE TABLE `reserve_extract_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exName` varchar(100) NOT NULL COMMENT '名称',
  `extractType` varchar(2) DEFAULT NULL COMMENT '类型(预留)',
  `tableName` varchar(100) NOT NULL COMMENT '需要操作的表名',
  `extractSql` text NOT NULL COMMENT '查询sql',
  `updatelookup` varchar(1000) NOT NULL COMMENT '更新表字段名（格式:abc,abc,abc...）',
  `updateStream` varchar(1000) NOT NULL COMMENT '查询sql结果字段名（格式:abc,abc,abc...）',
  `keyCondition` varchar(1000) NOT NULL COMMENT '关系符号：=、= ~NULL、<>、<、<=、>、>=、LIKE、BETWEEN、IS NULL、IS NOT NULL',
  `updateOrNot` varchar(1000) NOT NULL COMMENT '是否更新：true/false',
  `ktrUrl` varchar(100) NOT NULL COMMENT '存放地址',
  `ktrName` varchar(100) NOT NULL COMMENT '文件名称',
  `validStatus` varchar(2) NOT NULL COMMENT '状态：1有效，0无效',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createUserCode` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updateUserCode` varchar(100) DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_name` (`exName`),
  UNIQUE KEY `UNIQUE_ktrUrlANDktrName` (`ktrUrl`,`ktrName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='抽取配置表';

/*Data for the table `reserve_extract_config` */

insert  into `reserve_extract_config`(`id`,`exName`,`extractType`,`tableName`,`extractSql`,`updatelookup`,`updateStream`,`keyCondition`,`updateOrNot`,`ktrUrl`,`ktrName`,`validStatus`,`createTime`,`createUserCode`,`updateTime`,`updateUserCode`) values (1,'test',NULL,'test','select uname, upass, ucode from test','uname,upass,ucode','uname,upass,ucode','=,=,=','true,true,true','D:/ktr','test.ktr','1',NULL,NULL,NULL,NULL);

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `uname` varchar(255) DEFAULT NULL,
  `upass` varchar(255) DEFAULT NULL,
  `ucode` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
