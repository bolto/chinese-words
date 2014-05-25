CREATE DATABASE  IF NOT EXISTS `chinesewords` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `chinesewords`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: chinesewords
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `pingying_character`
--

DROP TABLE IF EXISTS `pingying_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pingying_character` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pingying_character`
--

LOCK TABLES `pingying_character` WRITE;
/*!40000 ALTER TABLE `pingying_character` DISABLE KEYS */;
INSERT INTO `pingying_character` VALUES (1,'2014-01-11 20:11:23','ㄅ',1,'2013-08-25 14:44:03'),(2,'2014-01-11 20:11:23','ㄆ',1,'2013-09-06 11:12:59'),(3,'2014-01-11 20:11:23','ㄇ',1,'2013-09-06 11:13:09'),(4,'2014-01-11 20:11:23','ㄈ',1,'2013-09-06 11:13:17'),(5,'2014-01-11 20:11:23','ㄉ',1,'2013-09-06 11:13:27'),(6,'2014-01-11 20:11:23','ㄊ',1,'2013-09-06 11:19:42'),(7,'2014-01-11 20:11:23','ㄋ',1,'2013-09-06 11:19:52'),(8,'2014-01-11 20:11:23','ㄌ',1,'2013-09-06 11:20:02'),(9,'2014-01-11 20:11:23','ㄍ',1,'2013-09-06 11:20:14'),(10,'2014-01-11 20:11:23','ㄎ',1,'2013-09-06 11:20:39'),(11,'2014-01-11 20:11:23','ㄏ',1,'2013-09-06 11:20:52'),(12,'2014-01-11 20:11:23','ㄐ',1,'2013-09-06 11:21:01'),(13,'2014-01-11 20:11:23','ㄑ',1,'2013-09-06 11:21:12'),(14,'2014-01-11 20:11:23','ㄒ',1,'2013-09-06 11:21:22'),(15,'2014-01-11 20:11:23','ㄓ',1,'2013-09-06 11:21:32'),(16,'2014-01-11 20:11:23','ㄔ',1,'2013-09-06 11:21:42'),(17,'2014-01-11 20:11:23','ㄕ',1,'2013-09-06 11:22:58'),(18,'2014-01-11 20:11:23','ㄖ',1,'2013-09-06 11:24:05'),(19,'2014-01-11 20:11:23','ㄗ',1,'2013-09-06 11:24:15'),(20,'2014-01-11 20:11:23','ㄘ',1,'2013-09-06 11:24:25'),(21,'2014-01-11 20:11:23','ㄙ',1,'2013-09-06 11:24:53'),(22,'2014-01-11 20:11:23','ㄧ',2,'2013-09-06 11:25:05'),(23,'2014-01-11 20:11:23','ㄨ',2,'2013-09-06 11:25:16'),(24,'2014-01-11 20:11:23','ㄩ',2,'2013-09-06 11:25:27'),(25,'2014-01-11 20:11:23','ㄚ',3,'2013-09-06 11:25:38'),(26,'2014-01-11 20:11:23','ㄛ',3,'2013-09-06 11:25:51'),(27,'2014-01-11 20:11:23','ㄜ',3,'2013-09-06 11:26:01'),(28,'2014-01-11 20:11:23','ㄝ',3,'2013-09-06 11:26:21'),(29,'2014-01-11 20:11:23','ㄞ',3,'2013-09-06 11:26:32'),(30,'2014-01-11 20:11:23','ㄟ',3,'2013-09-06 11:26:47'),(31,'2014-01-11 20:11:23','ㄠ',3,'2013-09-06 11:27:01'),(32,'2014-01-11 20:11:23','ㄡ',3,'2013-09-06 11:27:12'),(33,'2014-01-11 20:11:23','ㄢ',3,'2013-09-06 11:27:23'),(34,'2014-01-11 20:11:23','ㄣ',3,'2013-09-06 11:27:37'),(35,'2014-01-11 20:11:23','ㄤ',3,'2013-09-06 11:27:46'),(36,'2014-01-11 20:11:23','ㄥ',3,'2013-09-06 11:27:57'),(37,'2014-01-11 20:11:23','ㄦ',3,'2013-09-06 11:28:06'),(38,'2014-01-14 10:26:47','0',0,'2013-09-15 07:55:40');
/*!40000 ALTER TABLE `pingying_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tone`
--

DROP TABLE IF EXISTS `tone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tone`
--

LOCK TABLES `tone` WRITE;
/*!40000 ALTER TABLE `tone` DISABLE KEYS */;
INSERT INTO `tone` VALUES (1,'2014-01-17 01:04:15',' ','2014-01-17 01:04:15'),(2,'2014-01-17 01:04:15','ˊ','2014-01-17 01:04:15'),(3,'2014-01-17 01:04:15','ˇ','2014-01-17 01:04:15'),(4,'2014-01-17 01:04:15','ˋ','2014-01-17 01:04:15'),(5,'2014-01-17 01:04:15','˙','2014-01-17 01:04:15');
/*!40000 ALTER TABLE `tone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-19 12:59:17
