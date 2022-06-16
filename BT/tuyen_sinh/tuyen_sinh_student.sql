CREATE DATABASE  IF NOT EXISTS `tuyen_sinh` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tuyen_sinh`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: tuyen_sinh
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `idstudent` varchar(50) NOT NULL,
  `class` varchar(45) DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `school` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `noi_sinh` varchar(45) DEFAULT NULL,
  `dan_toc` varchar(45) DEFAULT NULL,
  `ho_khau` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `point1` double DEFAULT NULL,
  `point2` double DEFAULT NULL,
  `point3` double DEFAULT NULL,
  `point4` double DEFAULT NULL,
  `point5` double DEFAULT NULL,
  `priority_points` double DEFAULT NULL,
  `total5year` double DEFAULT NULL,
  `total_point` double DEFAULT NULL,
  PRIMARY KEY (`idstudent`),
  UNIQUE KEY `idstudent_UNIQUE` (`idstudent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('01170\nANHU','5A7','Đặng Mỹ Kiều ','Cầu Giấy','Dịch Vọng B','2011-10-15','Nữ','Hà Nội','Kinh','Tổ 3, Quan Hoa, Cầu Giấy, Hà Nội','0976494648','Đủ điều kiện dự thi vòng 2',20,20,30,50,50,0,170,170),('01176\nANHU','5A7','Đặng Mỹ Kiều Trinh','Cầu Giấy','Dịch Vọng B','2011-10-15','Nữ','Hà Nội','Kinh','Tổ 3, Quan Hoa, Cầu Giấy, Hà Nội','0976494648','Đủ điều kiện dự thi vòng 2',20,20,30,50,50,0,170,170),('01177\nANHU','6B','Đặng Mỹ Kiều ','Cầu Giấy','A','2011-10-15','Nam','HN','Kinh','Tổ 3, Quan Hoa, Cầu Giấy, Hà Nội','0976494648','',0,0,0,0,0,0,0,0),('01178\nANHU','5A7','Đặng Kiều Trinh','Cầu Giấy','Dịch Vọng B','2011-10-15','Nữ','Hà Nội','Kinh','Tổ 3, Quan Hoa, Cầu Giấy, Hà Nội','0976494648','Đủ điều kiện dự thi vòng 2',20,20,30,50,50,0,170,170);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-13 23:25:53
