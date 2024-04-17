-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: expense_manager
-- ------------------------------------------------------
-- Server version	11.4.0-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'food'),(2,'transport'),(3,'entertainment'),(4,'clothes'),(5,'health'),(6,'education'),(7,'technology'),(8,'sport'),(9,'travel'),(10,'insurance'),(11,'other');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `payment_method_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'cash'),(2,'card'),(3,'blik'),(4,'online payment');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `payment_method_id` bigint(20) NOT NULL,
  `type` enum('income','outcome') DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `expense_user_FK` (`user_id`),
  KEY `expense_category_FK` (`category_id`),
  KEY `expense_payment_method_FK` (`payment_method_id`),
  CONSTRAINT `expense_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `expense_payment_method_FK` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`payment_method_id`),
  CONSTRAINT `expense_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (8,5000,'2024-01-14','Wyplata',14,11,1,'income'),(9,1000,'2024-01-14','zakupy',14,4,2,'outcome'),(10,450,'2024-01-14','Kino',14,3,1,'outcome'),(11,6000,'2024-01-14','komornik',14,11,2,'outcome'),(12,1000,'2024-01-12','dsadasd',14,3,2,'outcome'),(13,123,'2024-01-14','dsadas',15,3,2,'outcome'),(14,1000,'2024-01-15','wyplata',14,4,4,'income'),(15,1000,'2024-01-15','wyplata',14,4,4,'income'),(16,1000,'2024-01-15','wyplata',14,4,4,'outcome');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `balance` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,'jarek','$2a$10$VWSu0Ms7jTyDAXefNm568.ReLt1Km0XCYEt92mt7dGb1Zf05wDPte',-2450),(15,'szymon','$2a$10$AYFkSeryH2AnVEsKBKV4Q.yKizPKss1f4p2qcGBa8Pn3mMg3Yv8rW',-123),(16,'maciek','$2a$10$68HLghEEmcm42Vz/j15v0e2/rx.RxKGeBY2velgqN3tu/BhUlftmG',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'expense_manager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-15  0:02:10
