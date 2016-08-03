CREATE DATABASE  IF NOT EXISTS `brilliance` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `brilliance`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: brilliance
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address_review_info`
--

DROP TABLE IF EXISTS `address_review_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_review_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUGGESTID` varchar(20) DEFAULT NULL COMMENT '用户建议地址ID',
  `REVIEWER` varchar(20) DEFAULT NULL COMMENT '审核人ID',
  `REVIEWTIME` datetime DEFAULT NULL COMMENT '审核时间',
  `STATUS` int(11) DEFAULT NULL COMMENT '审核状态:1.决绝;2.通过;',
  `REVIEWMEMO` varchar(200) DEFAULT NULL COMMENT '审核理由',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户推荐快递地址信息审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_review_info`
--

LOCK TABLES `address_review_info` WRITE;
/*!40000 ALTER TABLE `address_review_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `address_review_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_sugg_info`
--

DROP TABLE IF EXISTS `address_sugg_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_sugg_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ADDRESSID` varchar(20) DEFAULT NULL COMMENT '自定义地址ID',
  `PARENTID` varchar(20) DEFAULT NULL COMMENT '所属的门店地址ID',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `HOTNAME` varchar(50) DEFAULT NULL COMMENT '关键词',
  `CONTACT` varchar(20) DEFAULT NULL COMMENT '联系人',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `OFFICENO` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `PROVINCEID` varchar(20) DEFAULT NULL COMMENT '省份ID',
  `CITYID` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `AREAID` varchar(20) DEFAULT NULL COMMENT '区县ID',
  `STATUS` int(11) DEFAULT NULL COMMENT '审核状态:0.未开始;1.决绝;2.通过;',
  `DELETEFLAG` int(11) DEFAULT NULL COMMENT '记录是否激活:0.无效;1.有效;',
  `SOURCE` int(11) DEFAULT NULL COMMENT '数据源：1.电脑端;2.手机端;3,数据库导入;4,文件导入',
  `DATATYPE` int(11) DEFAULT NULL COMMENT '数据类型：1.门店地址信息;2.配送范围地址信息',
  `TAILADDRESS` varchar(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` varchar(500) DEFAULT NULL COMMENT '完整地址信息',
  `LONGITUDE` varchar(500) DEFAULT NULL COMMENT '地图经度坐标值',
  `LATITUDE` varchar(500) DEFAULT NULL COMMENT '地图纬度坐标值',
  `CREATEDBY` varchar(20) DEFAULT NULL COMMENT '创建者ID',
  `MEMO` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户推荐快递地址信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_sugg_info`
--

LOCK TABLES `address_sugg_info` WRITE;
/*!40000 ALTER TABLE `address_sugg_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `address_sugg_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ACCOUNTID` varchar(11) NOT NULL COMMENT '账号id',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名字',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATEBY` int(11) DEFAULT NULL COMMENT '操作人',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态,0/1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT=' 管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

LOCK TABLES `admin_info` WRITE;
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES (1,'Admin','Admin','1234',NULL,'2016-03-29 03:57:38',NULL,1);
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas_info`
--

DROP TABLE IF EXISTS `areas_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areas_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREAID` varchar(20) NOT NULL,
  `AREA` varchar(50) NOT NULL,
  `CITYID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2959 DEFAULT CHARSET=utf8 COMMENT='行政区域县区信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas_info`
--

LOCK TABLES `areas_info` WRITE;
/*!40000 ALTER TABLE `areas_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `areas_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities_info`
--

DROP TABLE IF EXISTS `cities_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITYID` varchar(20) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `PROVINCEID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=390 DEFAULT CHARSET=utf8 COMMENT='行政区域地州市信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities_info`
--

LOCK TABLES `cities_info` WRITE;
/*!40000 ALTER TABLE `cities_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `cities_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customization_addressinfo`
--

DROP TABLE IF EXISTS `customization_addressinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customization_addressinfo` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ADDRESSID` varchar(20) DEFAULT NULL COMMENT '自定义地址ID',
  `PARENTID` varchar(20) DEFAULT NULL COMMENT '所属的门店地址ID',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `HOTNAME` varchar(50) DEFAULT NULL COMMENT '关键词',
  `CONTACT` varchar(20) DEFAULT NULL COMMENT '联系人',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `OFFICENO` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `PROVINCEID` varchar(20) DEFAULT NULL COMMENT '省份ID',
  `CITYID` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `AREAID` varchar(20) DEFAULT NULL COMMENT '区县ID',
  `SOURCE` int(11) DEFAULT NULL COMMENT '数据源：1.电脑端;2.手机端;3,数据库导入;4,文件导入',
  `DATATYPE` int(11) DEFAULT NULL COMMENT '数据类型：1.门店地址信息;2.配送范围地址信息',
  `ARCHIVEFLAG` int(11) DEFAULT NULL COMMENT '是否已归档：1.已归档;2.未归档',
  `TAILADDRESS` varchar(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` varchar(500) DEFAULT NULL COMMENT '完整地址信息',
  `LONGITUDE` varchar(500) DEFAULT NULL COMMENT '地图经度坐标值',
  `LATITUDE` varchar(500) DEFAULT NULL COMMENT '地图纬度坐标值',
  `CREATEDBY` varchar(20) DEFAULT NULL COMMENT '创建者ID',
  `MEMO` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义快递地址信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customization_addressinfo`
--

LOCK TABLES `customization_addressinfo` WRITE;
/*!40000 ALTER TABLE `customization_addressinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `customization_addressinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliver_address_info`
--

DROP TABLE IF EXISTS `deliver_address_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliver_address_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `DELIVERNAME` varchar(50) DEFAULT NULL COMMENT '收件人姓名',
  `COMPANYNAME` varchar(200) DEFAULT NULL COMMENT '公司/个人名称',
  `PROVINCE` varchar(20) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(20) DEFAULT NULL COMMENT '城市',
  `AREA` varchar(20) DEFAULT NULL COMMENT '地区',
  `STREETNAME` varchar(100) DEFAULT NULL COMMENT '街道名',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `TELEPHONE` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `TAILADDRESS` varchar(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` varchar(500) DEFAULT NULL COMMENT '完整的地址信息',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` int(11) DEFAULT '1' COMMENT '0（不可用）/1(可用)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收件地址信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliver_address_info`
--

LOCK TABLES `deliver_address_info` WRITE;
/*!40000 ALTER TABLE `deliver_address_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliver_address_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliver_areas`
--

DROP TABLE IF EXISTS `deliver_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliver_areas` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `PROVINCEID` varchar(20) DEFAULT NULL COMMENT '省ID',
  `PROVINCE` varchar(100) DEFAULT NULL COMMENT '省名称',
  `CITYID` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `CITY` varchar(100) DEFAULT NULL COMMENT '城市名',
  `AREAID` varchar(20) DEFAULT NULL COMMENT '市县ID',
  `AREA` varchar(100) DEFAULT NULL COMMENT '市县名',
  `TOWNID` varchar(20) DEFAULT NULL COMMENT '区镇ID',
  `TOWN` varchar(100) DEFAULT NULL COMMENT '区镇',
  `OFFICENUMBER` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `SUBEXPRESSNAME` varchar(200) DEFAULT NULL COMMENT '子公司名称',
  `SUBEXPRESSADDRESS` varchar(500) DEFAULT NULL COMMENT '子公司地址',
  `MOBILE` varchar(100) DEFAULT NULL COMMENT '移动电话',
  `DELIVERAREAS` varchar(2000) DEFAULT NULL COMMENT '配送地点',
  `NONDELIVERAREAS` varchar(2000) DEFAULT NULL COMMENT '不派送地点',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态,0/1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司派送范围明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliver_areas`
--

LOCK TABLES `deliver_areas` WRITE;
/*!40000 ALTER TABLE `deliver_areas` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliver_areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary_info`
--

DROP TABLE IF EXISTS `diary_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diary_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `DIARYTYPE` int(11) DEFAULT NULL COMMENT '日记类型:0.日记;1.新闻;2.漫画;3.其他',
  `DELETEFLAG` int(11) DEFAULT NULL COMMENT '记录是否激活:0.无效;1.有效;',
  `DIARYTITLE` varchar(50) DEFAULT NULL COMMENT '标题',
  `SOURCE` varchar(50) DEFAULT NULL COMMENT '来源',
  `AUTHOR` varchar(20) DEFAULT NULL COMMENT '作者',
  `DIARYDATE` datetime DEFAULT NULL COMMENT '日记日期',
  `CONTENT` longblob COMMENT '内容',
  `LOVE` int(11) DEFAULT NULL COMMENT '喜欢（次数）',
  `DISLOVE` int(11) DEFAULT NULL COMMENT '不喜欢',
  `MEMO` varchar(200) DEFAULT NULL COMMENT '备注或描述',
  `CREATEDBY` varchar(20) DEFAULT NULL COMMENT '创建者ID',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATEDBY` varchar(20) DEFAULT NULL COMMENT '修改者ID',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='戴宗日记';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary_info`
--

LOCK TABLES `diary_info` WRITE;
/*!40000 ALTER TABLE `diary_info` DISABLE KEYS */;
INSERT INTO `diary_info` VALUES (1,0,1,'开心每一天','路边社2222','jx','2016-04-06 00:00:00','<p>????????????????????????????2????3?? ??&nbsp;</p>',NULL,NULL,NULL,'Admin','2016-04-06 15:31:33','Admin','2016-04-06 07:31:33');
/*!40000 ALTER TABLE `diary_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_deliver_detail`
--

DROP TABLE IF EXISTS `express_deliver_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_deliver_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DELIVERCODE` varchar(20) NOT NULL COMMENT '编码ID，便于被其他表引用',
  `EXPRESSCODE` varchar(20) NOT NULL COMMENT '快递公司ID',
  `CHARGE` varchar(50) DEFAULT NULL COMMENT '费用',
  `DELIVERDAYS` int(11) DEFAULT NULL COMMENT '送达天数',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '描述',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费标准及送达天数基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_deliver_detail`
--

LOCK TABLES `express_deliver_detail` WRITE;
/*!40000 ALTER TABLE `express_deliver_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_deliver_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_deliver_info`
--

DROP TABLE IF EXISTS `express_deliver_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_deliver_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DELIVERCODE` varchar(20) NOT NULL COMMENT '编码ID，便于被其他表引用',
  `F_PROVINCEID` varchar(20) NOT NULL COMMENT '始发地--省份ID',
  `FROMADDR` varchar(50) NOT NULL COMMENT '始发地',
  `T_PROVINCEID` varchar(20) NOT NULL COMMENT '目的地--省份ID',
  `TOADDR` varchar(50) NOT NULL COMMENT '目的地',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递数据派送省份信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_deliver_info`
--

LOCK TABLES `express_deliver_info` WRITE;
/*!40000 ALTER TABLE `express_deliver_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_deliver_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_evaluation`
--

DROP TABLE IF EXISTS `express_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_evaluation` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ADDRESSID` varchar(20) DEFAULT NULL COMMENT '常用地址ID',
  `IMAGEURL` varchar(20) DEFAULT NULL COMMENT '图片URL',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `ORDERCODE` varchar(20) DEFAULT NULL COMMENT '订单ID',
  `SCORE` decimal(20,0) DEFAULT NULL COMMENT '评价分数',
  `EXTRAINFO` varchar(2000) DEFAULT NULL COMMENT '备注信息',
  `CREATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_evaluation`
--

LOCK TABLES `express_evaluation` WRITE;
/*!40000 ALTER TABLE `express_evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_info`
--

DROP TABLE IF EXISTS `express_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL COMMENT '名字',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `POSTID` varchar(20) DEFAULT NULL COMMENT '快递公司查询代号',
  `LOGOURL` varchar(200) DEFAULT NULL COMMENT '快递公司logo地址',
  `TELEPHONE` varchar(20) DEFAULT NULL COMMENT '座机',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机',
  `SERVICELINE` varchar(20) DEFAULT NULL COMMENT '客服电话',
  `EVALUATION` float DEFAULT NULL COMMENT '评分',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATEBY` varchar(50) DEFAULT NULL COMMENT '操作人',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态,0/1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='快递公司基本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_info`
--

LOCK TABLES `express_info` WRITE;
/*!40000 ALTER TABLE `express_info` DISABLE KEYS */;
INSERT INTO `express_info` VALUES (1,'德邦物流','debang','001',NULL,NULL,NULL,NULL,NULL,NULL,'2016-04-06 06:31:52',NULL,1),(2,'申通物流','shentong','002',NULL,NULL,NULL,NULL,NULL,NULL,'2016-04-06 06:32:44',NULL,1),(3,'天天快递','tiantian','003',NULL,NULL,NULL,NULL,NULL,NULL,'2016-04-06 06:32:44',NULL,1);
/*!40000 ALTER TABLE `express_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite_address`
--

DROP TABLE IF EXISTS `favourite_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite_address` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ADDRESSID` varchar(20) DEFAULT NULL COMMENT '自定义地址ID',
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `SOURCE` int(11) DEFAULT NULL COMMENT '来源。1，自己新增；2，收藏',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常用快递信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite_address`
--

LOCK TABLES `favourite_address` WRITE;
/*!40000 ALTER TABLE `favourite_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourite_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `ORDERCODE` varchar(20) DEFAULT NULL COMMENT '订单ID',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `OPERATORID` varchar(20) DEFAULT NULL COMMENT '操作员ID',
  `EXPRESSSERIALNO` varchar(20) DEFAULT NULL COMMENT '快递单号',
  `AMOUNT` decimal(20,0) DEFAULT NULL COMMENT '费用',
  `SOURCE` int(11) DEFAULT NULL COMMENT '来源，1手工录入,2通过系统下单',
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT '1' COMMENT '1新增单，2处理中，3配送中，4已收货',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `FROM_ADDR` varchar(200) DEFAULT NULL COMMENT '发货地的省市区',
  `F_STREETNAME` varchar(200) DEFAULT NULL COMMENT '发货人街道名',
  `F_CONTACT` varchar(50) DEFAULT NULL COMMENT '发货人',
  `F_COMPANYNAME` varchar(200) DEFAULT NULL COMMENT '发货人公司/个人名称',
  `F_PROVINCEID` varchar(50) DEFAULT NULL COMMENT '发货人省份ID',
  `F_CITYID` varchar(50) DEFAULT NULL COMMENT '发货人城市ID',
  `F_AREAID` varchar(50) DEFAULT NULL COMMENT '发货人区县ID',
  `F_HOUSENO` varchar(200) DEFAULT NULL COMMENT '发货人具体地址',
  `F_MOBILENO` varchar(50) DEFAULT NULL COMMENT '发货人手机号码',
  `F_SUBEXPRESS` varchar(200) DEFAULT NULL COMMENT '发货网点',
  `T_CONTACT` varchar(50) DEFAULT NULL COMMENT '收货人',
  `T_COMPANYNAME` varchar(200) DEFAULT NULL COMMENT '收货人公司/个人名称',
  `T_PROVINCEID` varchar(50) DEFAULT NULL COMMENT '收货人省份ID',
  `T_CITYID` varchar(50) DEFAULT NULL COMMENT '收货人城市ID',
  `T_AREAID` varchar(50) DEFAULT NULL COMMENT '收货人区县ID',
  `TO_ADDR` varchar(200) DEFAULT NULL COMMENT '收货人地址',
  `T_STREETNAME` varchar(200) DEFAULT NULL COMMENT '收货人街道名',
  `T_HOUSENO` varchar(200) DEFAULT NULL COMMENT '收货人具体地址',
  `T_MOBILENO` varchar(50) DEFAULT NULL COMMENT '收货人手机号码',
  `T_OFFICENO` varchar(50) DEFAULT NULL COMMENT '收货人电话号码',
  `CARGONAME` varchar(100) DEFAULT NULL COMMENT '货物名称',
  `COMMENT` varchar(1000) DEFAULT NULL COMMENT '备注',
  `DELIVERDAYS` int(11) DEFAULT NULL COMMENT '送达天数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_progress`
--

DROP TABLE IF EXISTS `order_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_progress` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `EXPRESSNO` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代码',
  `SOURCE` int(11) DEFAULT NULL COMMENT '来源，1,手机端 2,电脑端',
  `LATESTSTATUSINFO` varchar(200) DEFAULT NULL COMMENT '单据的最新进度信息',
  `CREATIONDATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='快递单据进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_progress`
--

LOCK TABLES `order_progress` WRITE;
/*!40000 ALTER TABLE `order_progress` DISABLE KEYS */;
INSERT INTO `order_progress` VALUES (1,'','2343424','',2,'','2016-04-06 07:31:33'),(2,'','2332','',2,'','2016-04-06 07:31:33'),(3,'','3323','',2,'','2016-04-06 07:31:33');
/*!40000 ALTER TABLE `order_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_address_info`
--

DROP TABLE IF EXISTS `post_address_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_address_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `EXPRESSCODE` varchar(20) DEFAULT NULL COMMENT '快递公司代号',
  `POSTNAME` varchar(50) DEFAULT NULL COMMENT '发件人姓名',
  `COMPANYNAME` varchar(200) DEFAULT NULL COMMENT '公司/个人名称',
  `PROVINCE` varchar(20) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(20) DEFAULT NULL COMMENT '城市',
  `AREA` varchar(20) DEFAULT NULL COMMENT '地区',
  `STREETNAME` varchar(100) DEFAULT NULL COMMENT '街道名',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `TAILADDRESS` varchar(200) DEFAULT NULL COMMENT '末尾地址信息',
  `ADDRESSDETAIL` varchar(500) DEFAULT NULL COMMENT '完整的地址信息',
  `CREATETIME` datetime DEFAULT NULL,
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` int(11) DEFAULT '1' COMMENT '0（不可用）/1(可用)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发件地址信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_address_info`
--

LOCK TABLES `post_address_info` WRITE;
/*!40000 ALTER TABLE `post_address_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_address_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces_info`
--

DROP TABLE IF EXISTS `provinces_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provinces_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROVINCEID` varchar(20) NOT NULL,
  `PROVINCE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='省份信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces_info`
--

LOCK TABLES `provinces_info` WRITE;
/*!40000 ALTER TABLE `provinces_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `provinces_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommend_info`
--

DROP TABLE IF EXISTS `recommend_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommend_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '推荐人ID',
  `NOMINEEID` varchar(20) DEFAULT NULL COMMENT '被推荐人ID',
  `SOURCE` int(11) DEFAULT NULL COMMENT '推荐来源,1,新浪微博，2,qq空间3,其他',
  `COMPLETEDATE` datetime DEFAULT NULL COMMENT '推荐完成时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommend_info`
--

LOCK TABLES `recommend_info` WRITE;
/*!40000 ALTER TABLE `recommend_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `recommend_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reward_score`
--

DROP TABLE IF EXISTS `reward_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reward_score` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `REWARDTYPE` int(5) DEFAULT NULL COMMENT '积分类型:1,新用户注册积分;2,扫描快递积分;3,评价快递员积分;4,提交常用快递数据积分',
  `SCORE` decimal(20,0) DEFAULT NULL COMMENT 'link类型1.推荐2.找回密码3.其他',
  `CREATIONTIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='URL信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reward_score`
--

LOCK TABLES `reward_score` WRITE;
/*!40000 ALTER TABLE `reward_score` DISABLE KEYS */;
INSERT INTO `reward_score` VALUES (1,'',2,10,'2016-04-06 15:31:33'),(2,'',2,10,'2016-04-06 15:31:33'),(3,'',2,10,'2016-04-06 15:31:33');
/*!40000 ALTER TABLE `reward_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_params`
--

DROP TABLE IF EXISTS `sys_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_params` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CONSTANTKEY` varchar(20) DEFAULT NULL COMMENT '常量ID',
  `VALUE` varchar(20) DEFAULT NULL COMMENT '取值',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  `EFFECTNOW` varchar(200) DEFAULT NULL COMMENT '是否立即生效:0,需要重启生效;1,立即生效',
  `CREATETIME` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_params`
--

LOCK TABLES `sys_params` WRITE;
/*!40000 ALTER TABLE `sys_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `url_info`
--

DROP TABLE IF EXISTS `url_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) DEFAULT NULL COMMENT '推荐人ID',
  `URL` varchar(500) DEFAULT NULL COMMENT '推荐的URL',
  `TYPE` int(11) DEFAULT NULL COMMENT 'link类型1.推荐2.找回密码3.其他',
  `STARTTIME` datetime DEFAULT NULL COMMENT '开始时间',
  `EXPIRETIME` datetime DEFAULT NULL COMMENT '推荐截止时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='URL信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `url_info`
--

LOCK TABLES `url_info` WRITE;
/*!40000 ALTER TABLE `url_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `url_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` varchar(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL COMMENT '名字',
  `MOBILENO` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮件地址',
  `PROVINCEID` varchar(20) DEFAULT NULL COMMENT '省份ID',
  `CITYID` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `AREAID` varchar(20) DEFAULT NULL COMMENT '区县ID',
  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '地址',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `LOGTIMES` int(11) DEFAULT NULL COMMENT '登陆次数',
  `CREATETIME` datetime DEFAULT NULL,
  `CREDITS` int(11) DEFAULT NULL COMMENT '积分',
  `LASTUPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `BIRTHDAY` date DEFAULT NULL,
  `GENDER` varchar(2) DEFAULT NULL,
  `UPDATEBY` int(11) DEFAULT NULL COMMENT '操作人',
  `STATUS` int(11) DEFAULT '1' COMMENT '状态,0/1',
  `LOGINTIMES` int(11) DEFAULT NULL,
  `LONGITUDE` varchar(50) DEFAULT NULL,
  `LATITUDE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'1','Russell','18932334628','jxoucjj@126.com',NULL,NULL,NULL,'上海市','1234',90,NULL,1000,'2016-04-05 09:11:00',NULL,'1',NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zipcode_info`
--

DROP TABLE IF EXISTS `zipcode_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zipcode_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREAID` varchar(20) NOT NULL,
  `ZIP` varchar(20) NOT NULL,
  `CODE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3145 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zipcode_info`
--

LOCK TABLES `zipcode_info` WRITE;
/*!40000 ALTER TABLE `zipcode_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `zipcode_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-06 16:25:28
