-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: finance_tracker
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `balance` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3652 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (152,'Free Time',200,151),(203,'Car parts',500,202),(354,'Car parts',500,355),(404,'Car parts',500,405),(454,'Car parts',500,455),(601,'Free Time',200,602),(702,'Free Time',200,701),(753,'Car parts',500,752),(803,'Car parts',500,802),(853,'Car parts',500,852),(1002,'Free Time',200,1001);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `sum` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `repeat_type_type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_budget_repeat_type1_idx` (`repeat_type_type`),
  KEY `fk_budget_user1` (`user_id`),
  CONSTRAINT `fk_budget_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type` FOREIGN KEY (`repeat_type_type`) REFERENCES `repeat_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget_has_account`
--

DROP TABLE IF EXISTS `budget_has_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budget_has_account` (
  `budget_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`budget_id`,`account_id`),
  KEY `fk_budget_has_account_account1_idx` (`account_id`),
  KEY `fk_budget_has_account_budget1_idx` (`budget_id`),
  CONSTRAINT `fk_budget_has_account_account1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_budget_has_account_budget1` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget_has_account`
--

LOCK TABLES `budget_has_account` WRITE;
/*!40000 ALTER TABLE `budget_has_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `budget_has_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4802 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (201,'Airplane'),(251,'Airplane'),(301,'Airplane'),(751,'Airplane'),(1201,'Airplane');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `name` varchar(3) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES ('BGN');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance_operation`
--

DROP TABLE IF EXISTS `finance_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sum` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(400) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `repeat_type_type` varchar(10) NOT NULL,
  `finance_operation_type_type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_finance_operation_category1_idx` (`category_id`),
  KEY `fk_finance_operation_account1_idx` (`account_id`),
  KEY `fk_finance_operation_repeat_type1_idx` (`repeat_type_type`),
  KEY `fk_finance_operation_finance_operation_type1_idx` (`finance_operation_type_type`),
  CONSTRAINT `FK_finance_operation_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_finance_operation_account1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finance_operation_finance_operation_type1` FOREIGN KEY (`finance_operation_type_type`) REFERENCES `finance_operation_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finance_operation_repeat_type1` FOREIGN KEY (`repeat_type_type`) REFERENCES `repeat_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3052 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_operation`
--

LOCK TABLES `finance_operation` WRITE;
/*!40000 ALTER TABLE `finance_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `finance_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance_operation_has_tag`
--

DROP TABLE IF EXISTS `finance_operation_has_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance_operation_has_tag` (
  `finance_operation_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`finance_operation_id`,`tag_id`),
  KEY `fk_finance_operation_has_tag_finance_operation1_idx` (`finance_operation_id`),
  KEY `FK_finance_operation_has_tag_tag_ID` (`tag_id`),
  CONSTRAINT `FK_finance_operation_has_tag_tag_ID` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `fk_finance_operation_has_tag_finance_operation_id` FOREIGN KEY (`finance_operation_id`) REFERENCES `finance_operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_operation_has_tag`
--

LOCK TABLES `finance_operation_has_tag` WRITE;
/*!40000 ALTER TABLE `finance_operation_has_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `finance_operation_has_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance_operation_type`
--

DROP TABLE IF EXISTS `finance_operation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance_operation_type` (
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_operation_type`
--

LOCK TABLES `finance_operation_type` WRITE;
/*!40000 ALTER TABLE `finance_operation_type` DISABLE KEYS */;
INSERT INTO `finance_operation_type` VALUES ('EXPENCES');
/*!40000 ALTER TABLE `finance_operation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repeat_type`
--

DROP TABLE IF EXISTS `repeat_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repeat_type` (
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repeat_type`
--

LOCK TABLES `repeat_type` WRITE;
/*!40000 ALTER TABLE `repeat_type` DISABLE KEYS */;
INSERT INTO `repeat_type` VALUES ('MONTHLY');
/*!40000 ALTER TABLE `repeat_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',1500);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category_id_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TAG_category_id_category` (`category_id_category`),
  CONSTRAINT `FK_TAG_category_id_category` FOREIGN KEY (`category_id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3403 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (204,'Something',201),(252,'Something',251),(302,'Something',301),(754,'Something',751);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(30) NOT NULL,
  `start_day` date DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `currency_name` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `name_idx` (`currency_name`),
  CONSTRAINT `fk_name` FOREIGN KEY (`currency_name`) REFERENCES `currency` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4803 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (51,'Sasa','sasko','iiI12l','petata2@abv.bg','2016-03-28',0,'BGN'),(151,'Sasa','sasko','iiI12l','petata3@abv.bg','2016-03-28',0,'BGN'),(202,'Luis','Suarez','aqqqW9a','luizaa.s11ez@gmail.com',NULL,0,'BGN'),(355,'Luis','Suarez','aqqqW9a','luiaazaa.s11eaajjz@gmail.com',NULL,0,'BGN'),(405,'Luis','Suarez','aqqqW9a','see@gmail.com',NULL,0,'BGN'),(455,'Luis','Suarez','aqqqW9a','see1@gmail.com',NULL,0,'BGN'),(501,'Sasa','sasko','iiI12l','petata19@abv.bg','2016-03-28',0,'BGN'),(602,'Sasa','sasko','iiI12l','petnnata19@abv.bg','2016-03-28',0,'BGN'),(701,'Sasa','sasko','iiI12l','pnetnnata19@abv.bg','2016-03-28',0,'BGN'),(752,'Luis','Suarez','aqqqW9a','see2@gmail.com',NULL,0,'BGN'),(802,'Luis','Suarez','aqqqW9a','see3@gmail.com',NULL,0,'BGN'),(852,'Luis','Suarez','aqqqW9a','see4@gmail.com',NULL,0,'BGN'),(901,'Stanio','Hakera','AaA123','peshoto_hakercheto@abv.bg','2016-03-28',0,'BGN'),(1001,'Sasa','sasko','iiI12l','19@abv.bg','2016-03-28',0,'BGN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-28  1:51:31
