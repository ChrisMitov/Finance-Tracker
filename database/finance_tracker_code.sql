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
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `balance` int(11) NOT NULL,
  `user_id_users` int(11) NOT NULL,
  PRIMARY KEY (`id_account`),
  KEY `fk_account_user1_idx` (`user_id_users`),
  CONSTRAINT `fk_account_user1` FOREIGN KEY (`user_id_users`) REFERENCES `user` (`id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budget` (
  `id_budget` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `sum` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `user_id_users` int(11) NOT NULL,
  `repeat_type_idrepeat_type` int(11) NOT NULL,
  PRIMARY KEY (`id_budget`),
  KEY `fk_budget_user1_idx` (`user_id_users`),
  KEY `fk_budget_repeat_type1_idx` (`repeat_type_idrepeat_type`),
  CONSTRAINT `fk_budget_repeat_type1` FOREIGN KEY (`repeat_type_idrepeat_type`) REFERENCES `repeat_type` (`idrepeat_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_budget_user1` FOREIGN KEY (`user_id_users`) REFERENCES `user` (`id_users`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `budget_id_budget` int(11) NOT NULL,
  `account_id_account` int(11) NOT NULL,
  PRIMARY KEY (`budget_id_budget`,`account_id_account`),
  KEY `fk_budget_has_account_account1_idx` (`account_id_account`),
  KEY `fk_budget_has_account_budget1_idx` (`budget_id_budget`),
  CONSTRAINT `fk_budget_has_account_account1` FOREIGN KEY (`account_id_account`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_budget_has_account_budget1` FOREIGN KEY (`budget_id_budget`) REFERENCES `budget` (`id_budget`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id_currency` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance_operation`
--

DROP TABLE IF EXISTS `finance_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance_operation` (
  `id_operation` int(11) NOT NULL AUTO_INCREMENT,
  `sum` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(400) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `account_id_account` int(11) NOT NULL,
  `repeat_type_idrepeat_type` int(11) NOT NULL,
  `category_id_category` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `fk_finance_operation_account1_idx` (`account_id_account`),
  KEY `fk_finance_operation_repeat_type1_idx` (`repeat_type_idrepeat_type`),
  KEY `fk_finance_operation_category1_idx` (`category_id_category`),
  CONSTRAINT `fk_finance_operation_account1` FOREIGN KEY (`account_id_account`) REFERENCES `account` (`id_account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finance_operation_category1` FOREIGN KEY (`category_id_category`) REFERENCES `category` (`id_category`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finance_operation_repeat_type1` FOREIGN KEY (`repeat_type_idrepeat_type`) REFERENCES `repeat_type` (`idrepeat_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `finance_operation_id_operation` int(11) NOT NULL,
  `tag_id_tag` int(11) NOT NULL,
  PRIMARY KEY (`finance_operation_id_operation`,`tag_id_tag`),
  KEY `fk_finance_operation_has_tag_tag1_idx` (`tag_id_tag`),
  KEY `fk_finance_operation_has_tag_finance_operation1_idx` (`finance_operation_id_operation`),
  CONSTRAINT `fk_finance_operation_has_tag_finance_operation1` FOREIGN KEY (`finance_operation_id_operation`) REFERENCES `finance_operation` (`id_operation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finance_operation_has_tag_tag1` FOREIGN KEY (`tag_id_tag`) REFERENCES `tag` (`id_tag`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `repeat_type`
--

DROP TABLE IF EXISTS `repeat_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repeat_type` (
  `idrepeat_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idrepeat_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repeat_type`
--

LOCK TABLES `repeat_type` WRITE;
/*!40000 ALTER TABLE `repeat_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `repeat_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category_id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_tag`),
  KEY `fk_tag_category_idx` (`category_id_category`),
  CONSTRAINT `fk_tag_category` FOREIGN KEY (`category_id_category`) REFERENCES `category` (`id_category`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_users` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(30) NOT NULL,
  `start_day` date DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `currency_id_currency` int(11) NOT NULL,
  PRIMARY KEY (`id_users`),
  KEY `fk_user_currency1_idx` (`currency_id_currency`),
  CONSTRAINT `fk_user_currency1` FOREIGN KEY (`currency_id_currency`) REFERENCES `currency` (`id_currency`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2016-03-23 18:56:10
