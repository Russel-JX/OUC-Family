CREATE DATABASE  IF NOT EXISTS `izone` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `izone`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: izone
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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(50) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `description` longtext,
  `activityType` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_comment`
--

DROP TABLE IF EXISTS `activity_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityID` int(11) DEFAULT NULL,
  `replyID` int(11) DEFAULT NULL,
  `commentator` int(11) DEFAULT NULL,
  `commentContent` varchar(255) DEFAULT NULL,
  `commentDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_comment`
--

LOCK TABLES `activity_comment` WRITE;
/*!40000 ALTER TABLE `activity_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_sharing`
--

DROP TABLE IF EXISTS `activity_sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_sharing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityID` int(11) DEFAULT NULL,
  `sharedTo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_sharing`
--

LOCK TABLES `activity_sharing` WRITE;
/*!40000 ALTER TABLE `activity_sharing` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_sharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `albumName` varchar(255) DEFAULT NULL,
  `albumDescription` varchar(255) DEFAULT NULL,
  `belongTo` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'uuu',NULL,NULL,NULL),(2,'ooo',NULL,NULL,NULL);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empID` varchar(20) DEFAULT NULL,
  `emp_CN_name` varchar(30) DEFAULT NULL,
  `emp_EN_name` varchar(80) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `engageDate` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `birthPlace` varchar(50) DEFAULT NULL,
  `constellation` varchar(50) DEFAULT NULL,
  `bloodType` varchar(10) DEFAULT NULL,
  `personalMail` varchar(50) DEFAULT NULL,
  `companyMail` varchar(50) DEFAULT NULL,
  `favourite` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `deleteFlag` int(1) DEFAULT NULL,
  `emp_head_portrait` varchar(200) DEFAULT NULL,
  `empType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'yz816343','周毅程','IanCheng','96e79218965eb72c92a549dd5a330112','2013-10-21',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebConten','adminstration'),(23,'yq120004','yqqqyq','yqyqyq','96e79218965eb72c92a549dd5a330112','2014-01-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(50,'asdf1000','sdfasdf','adfas','96e79218965eb72c92a549dd5a330112','2014-01-03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','recruiment'),(18,'yq111119','yq','yq','96e79218965eb72c92a549dd5a330112','2014-01-16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','adminstration'),(19,'yq120001','sdfgh','asdfasd','96e79218965eb72c92a549dd5a330112','2014-01-22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','finace'),(20,'yq120001','sdfgh','asdfasd','96e79218965eb72c92a549dd5a330112','2014-01-22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','finace'),(21,'yq120002','yqq','yqq','96e79218965eb72c92a549dd5a330112','2014-01-02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','hr'),(22,'yq120003','yqqy','yqqy','96e79218965eb72c92a549dd5a330112','2014-01-01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(24,'yw120005','yw','yw','96e79218965eb72c92a549dd5a330112','2014-01-04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(25,'yw120007','ywyw','yww','96e79218965eb72c92a549dd5a330112','2014-01-15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(26,'yw120008','ywwy','ywwy','96e79218965eb72c92a549dd5a330112','2014-01-07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','it'),(27,'yww120010','yww','ywww','96e79218965eb72c92a549dd5a330112','2014-01-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(28,'yew120015','yew','yew','96e79218965eb72c92a549dd5a330112','2014-01-30',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(29,'yww120016','rrr','rrr','96e79218965eb72c92a549dd5a330112','2014-01-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(30,'yww120018','y问问','yww','96e79218965eb72c92a549dd5a330112','2014-01-17',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','normal'),(46,'yz100100','帅气的老高0','Smart old gao0','96e79218965eb72c92a549dd5a330112','2013-09-21',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','Normal'),(47,'yy100101','帅气的老高1','Smart old gao1','96e79218965eb72c92a549dd5a330112','2013-09-22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','Normal'),(48,'yx100102','帅气的老高2','Smart old gao2','96e79218965eb72c92a549dd5a330112','2013-09-23',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','HR'),(49,'ya100103','帅气的老高3','Smart old gao3','96e79218965eb72c92a549dd5a330112','2013-09-24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','Finance'),(39,'ttt120026','天天听说','ttts','96e79218965eb72c92a549dd5a330112','2014-01-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','hr'),(53,'123456','周毅成','YiChengZhou','96e79218965eb72c92a549dd5a330112','2014-01-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg','0'),(54,'555555555555','','','96e79218965eb72c92a549dd5a330112','2014-01-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'C:/Users/yz816343/EclipseWorkspace/workspace/iZone/WebContent/resource/img/head_portrait3.jpg','0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `favouriteName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_sharing`
--

DROP TABLE IF EXISTS `file_sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_sharing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) DEFAULT NULL,
  `fileType` varchar(50) DEFAULT NULL,
  `uploadedDate` date DEFAULT NULL,
  `fileFrom` int(11) DEFAULT NULL,
  `fileDescription` varchar(255) DEFAULT NULL,
  `fileRealName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_sharing`
--

LOCK TABLES `file_sharing` WRITE;
/*!40000 ALTER TABLE `file_sharing` DISABLE KEYS */;
INSERT INTO `file_sharing` VALUES (21,'10631391516114.xlsx','video','2014-04-24',5,'山东省地方放','地方各分'),(22,'10631392477428.doc','audio','2014-04-24',5,'abc','Business_Requirement_Document_with_Use_Cases_Unified.doc'),(68,'28545659556694.jpg','pic','2014-04-24',5,'abc','22905404958651.jpg'),(69,'28546542170312.xlsx','pic','2014-04-24',5,'abc','ADD_Employee Benefit_China_Data_Field_Mapping 2014.03.11_v1.1 (1).xlsx'),(70,'28551443838539.xlsx','pic','2014-04-24',5,'abc','ADD_Employee Benefit_China_Data_Field_Mapping 2014.03.11_v1.1.xlsx'),(78,'29328331303512.jpg','docs','2014-04-24',5,'<script>alert(\"ok\")</script>','22905404958651.jpg'),(79,'29328332638653.xlsx','docs','2014-04-24',5,'“的所发生的”','ADD_Employee Benefit_China_Data_Field_Mapping 2014.03.11_v1.1 (1).xlsx'),(80,'34944558669088.zip','docs','2014-04-24',5,'abc《》<>&& and select * ','1.zip'),(81,'34948083879093.doc','docs','2014-04-24',5,'abc','2014年4月PMP政府补贴培训班-高博教育.doc'),(82,'78268484605679.xlsx','docs','2014-05-05',5,'abc','ADD_Employee Benefit_China_Data_Field_Mapping 2014.03.11_v1.1.xlsx'),(83,'78383339927145.doc','docs','2014-05-05',5,'abc','Business_Requirement_Document_without_Use_Cases_Unified.doc'),(84,'78383342666429.jpg','docs','2014-05-05',5,'abc','Chrysanthemum.jpg'),(86,'78945340908659.doc','docs','2014-05-05',5,'abc','2014年4月PMP政府补贴培训班-高博教育.doc'),(87,'81801081561491.txt','docs','2014-05-05',5,'abc','看js的call方法.txt'),(88,'81875786650905.txt','docs','2014-05-05',5,'abc','note.txt'),(89,'82406707439015.xlsx','docs','2014-05-05',5,'abc','817306_Xun Jiang.xlsx'),(90,'82444698331394.pptx','docs','2014-05-05',5,'abc','User Register and PW Reset.pptx');
/*!40000 ALTER TABLE `file_sharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `albumID` int(11) DEFAULT NULL,
  `photoName` varchar(255) DEFAULT NULL,
  `photoDescription` varchar(255) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionName` varchar(50) DEFAULT NULL,
  `answerID` int(11) DEFAULT NULL,
  `questionDescription` varchar(255) DEFAULT NULL,
  `questionFrom` int(11) DEFAULT NULL,
  `questionTo` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `raiseDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer`
--

DROP TABLE IF EXISTS `question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standardQuestionName` varchar(50) DEFAULT NULL,
  `answerContent` varchar(255) DEFAULT NULL,
  `answerDate` date DEFAULT NULL,
  `answer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer`
--

LOCK TABLES `question_answer` WRITE;
/*!40000 ALTER TABLE `question_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportName` varchar(50) DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `reportDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'re101',301,'2014-04-10','第一个报告');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_detail`
--

DROP TABLE IF EXISTS `report_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `columnName` varchar(50) DEFAULT NULL,
  `columnData` varchar(255) DEFAULT NULL,
  `columnNumber` int(11) DEFAULT NULL,
  `reportID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_detail`
--

LOCK TABLES `report_detail` WRITE;
/*!40000 ALTER TABLE `report_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_sharing`
--

DROP TABLE IF EXISTS `report_sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_sharing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportID` int(11) DEFAULT NULL,
  `sharedTo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_sharing`
--

LOCK TABLES `report_sharing` WRITE;
/*!40000 ALTER TABLE `report_sharing` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_sharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soso`
--

DROP TABLE IF EXISTS `soso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sosoContent` varchar(255) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soso`
--

LOCK TABLES `soso` WRITE;
/*!40000 ALTER TABLE `soso` DISABLE KEYS */;
/*!40000 ALTER TABLE `soso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-11 10:18:36
