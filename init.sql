-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host 127.0.0.1    Database users
-- ------------------------------------------------------
-- Server version	8.0.37

!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT ;
!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS ;
!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION ;
!50503 SET NAMES utf8 ;
!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE ;
!40103 SET TIME_ZONE='+0000' ;
!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 ;
!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ;
!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' ;
!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 ;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
!40101 SET @saved_cs_client     = @@character_set_client ;
!50503 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `section_id` int NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
!40101 SET character_set_client = @saved_cs_client ;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
!40000 ALTER TABLE `products` DISABLE KEYS ;
INSERT INTO `products` VALUES (1,'Черника',2,0),(2,'Помидор',1,1);
!40000 ALTER TABLE `products` ENABLE KEYS ;
UNLOCK TABLES;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
!40101 SET @saved_cs_client     = @@character_set_client ;
!50503 SET character_set_client = utf8mb4 ;
CREATE TABLE `sections` (
  `id_section` int NOT NULL AUTO_INCREMENT,
  `section_name` varchar(255) NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_section`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
!40101 SET character_set_client = @saved_cs_client ;

--
-- Dumping data for table `sections`
--

LOCK TABLES `sections` WRITE;
!40000 ALTER TABLE `sections` DISABLE KEYS ;
INSERT INTO `sections` VALUES (1,'Ягоды',0),(2,'Овощи',1),(3,'Фрукты',0);
!40000 ALTER TABLE `sections` ENABLE KEYS ;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
!40101 SET @saved_cs_client     = @@character_set_client ;
!50503 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
!40101 SET character_set_client = @saved_cs_client ;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
!40000 ALTER TABLE `user` DISABLE KEYS ;
INSERT INTO `user` VALUES (1,'admin','$2a$10$UQJVlhVpJuTyI7Bta5B77uRtt5LTzaLtLTkUNavASKOvID..qgIWy','admin@mail.ru','ADMIN'),(2,'user1','$2a$10$YETrAl37VykxSnBFJneGXreb7Crpaki8UCjZsZ6UgCmdcWKsZC','user1@mail.ru','USER'),(3,'user5','user5','user5@mail.ru','USER'),(4,'admin2','admin2','admin2@mail.ru','ADMIN'),(5,'admin3','admin3','admin3@mail.ru','ADMIN'),(6,'admin4','admin4','admin4@mail.ru','ADMIN'),(7,'admin5','$2a$10$4.5Z2PBiUv2aNXzdNAiqCujiv2ee7D.1DVgn.z2sDYYkKHifMsYyq','admin5@mail.ru','ADMIN');
!40000 ALTER TABLE `user` ENABLE KEYS ;
UNLOCK TABLES;
!40103 SET TIME_ZONE=@OLD_TIME_ZONE ;

!40101 SET SQL_MODE=@OLD_SQL_MODE ;
!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS ;
!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT ;
!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS ;
!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION ;
!40111 SET SQL_NOTES=@OLD_SQL_NOTES ;

-- Dump completed on 2025-06-03 201633
