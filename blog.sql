-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_admin` (
  `user` varchar(30) NOT NULL,
  `password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES ('1593623458@qq.com','c11655e95e2fd66fd6294f53150f5f3f');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog`
--

DROP TABLE IF EXISTS `t_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `release_date` datetime DEFAULT NULL,
  `title` text NOT NULL,
  `appreciate` bit(1) NOT NULL,
  `image_address` text NOT NULL,
  `views` int NOT NULL DEFAULT '0',
  `content` longtext NOT NULL,
  `s_id` int NOT NULL,
  `published` bit(1) NOT NULL,
  `comment` bit(1) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_top` bit(1) NOT NULL DEFAULT b'0',
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_blog_t_sort_id_fk` (`s_id`),
  CONSTRAINT `t_blog_t_sort_id_fk` FOREIGN KEY (`s_id`) REFERENCES `t_sort` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog`
--

LOCK TABLES `t_blog` WRITE;
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
INSERT INTO `t_blog` VALUES (11,'2021-05-05 16:30:00','uniapp学习过程',_binary '\0','11',293,'# heading 1\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\n\n## heading 2\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\n\n### heading 3\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\n\n## heading 2\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\n\n### heading 3\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\ncontentcontentcontent\n',7,_binary '',_binary '','2021-08-13 11:47:00',_binary '\0','test1'),(13,'2021-06-08 17:26:00','Java知识体系最强总结',_binary '','11',62,'### Test\n|tag|标签|数量|\n|-|-|-|\n|11|2|3|\n\n:innocent:\n',2,_binary '',_binary '','2021-08-14 10:48:00',_binary '','test2'),(14,'2019-06-08 17:36:00','C语言入门',_binary '','11',141,'#### C语言一经出现就以其功能丰富、表达能力强、灵活方便、应用面广等特点迅速在全世界普及和推广。\nC语言不但执行效率高而且可移植性好，可以用来开发应用软件、驱动、操作系统等。C语言也是其它众多高级语言的鼻祖语言，所以说学习C语言是进入编程世界的必修课。\n``` c\n #include<stdio.h> \n int main()\n {\n   /*在双引号中间输入Hello World*/ \n   printf(\"Hello World\");\n   return 0; \n }\n```\n:::tip\nHello\n:::',9,_binary '',_binary '','2021-08-14 10:47:00',_binary '','test3');
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog_tag`
--

DROP TABLE IF EXISTS `t_blog_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_blog_tag` (
  `b_id` int DEFAULT NULL,
  `t_id` int DEFAULT NULL,
  KEY `t_blog_tag_t_blog_id_fk` (`b_id`),
  KEY `t_blog_tag_t_tag_id_fk` (`t_id`),
  CONSTRAINT `t_blog_tag_t_blog_id_fk` FOREIGN KEY (`b_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_blog_tag_t_tag_id_fk` FOREIGN KEY (`t_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog_tag`
--

LOCK TABLES `t_blog_tag` WRITE;
/*!40000 ALTER TABLE `t_blog_tag` DISABLE KEYS */;
INSERT INTO `t_blog_tag` VALUES (11,2),(13,2),(14,2);
/*!40000 ALTER TABLE `t_blog_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_comment` (
  `name` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `c_date` datetime NOT NULL,
  `r_name` varchar(20) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `b_id` int NOT NULL,
  `parent_id` int DEFAULT '-1',
  `content` text,
  `published` bit(1) NOT NULL DEFAULT b'0',
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_comment_id_uindex` (`id`),
  KEY `t_comment_t_blog_id_fk` (`b_id`),
  CONSTRAINT `t_comment_t_blog_id_fk` FOREIGN KEY (`b_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES ('Heaven','1593623458@qq.com','2021-08-16 09:07:13','',15,11,-1,'测试回复',_binary '',_binary ''),('test1','test1@qq.com','2021-08-19 19:30:00','',17,13,-1,'test1',_binary '',_binary '\0'),('111','1593623458@qq.com','2021-08-19 19:56:00','Heaven',20,11,15,'测试子回复',_binary '',_binary '\0');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_friendlink`
--

DROP TABLE IF EXISTS `t_friendlink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_friendlink` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img_link` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL,
  `to_link` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_friendLink_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_friendlink`
--

LOCK TABLES `t_friendlink` WRITE;
/*!40000 ALTER TABLE `t_friendlink` DISABLE KEYS */;
INSERT INTO `t_friendlink` VALUES (1,'https://www.oyohyee.com/static/img/logo.svg','test1','https://www.oyohyee.com/','善用搜索'),(2,'https://p.vczyh.com/blog/avatar.jpg','test2','https://zhangeek.com/','一点点');
/*!40000 ALTER TABLE `t_friendlink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_info`
--

DROP TABLE IF EXISTS `t_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_info` (
  `record_num` text,
  `say` text,
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_info`
--

LOCK TABLES `t_info` WRITE;
/*!40000 ALTER TABLE `t_info` DISABLE KEYS */;
INSERT INTO `t_info` VALUES ('粤123455','你好吗',1);
/*!40000 ALTER TABLE `t_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sort`
--

DROP TABLE IF EXISTS `t_sort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sort` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sort`
--

LOCK TABLES `t_sort` WRITE;
/*!40000 ALTER TABLE `t_sort` DISABLE KEYS */;
INSERT INTO `t_sort` VALUES (1,'SpringBoot','#0BEC91'),(2,'Java','#2FD523'),(4,'Mybatis','#230577'),(5,'Java反射','#CC00FF'),(7,'uniapp',NULL),(9,'C语言',NULL),(14,'SpringMVC','#0BABD3');
/*!40000 ALTER TABLE `t_sort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
INSERT INTO `t_tag` VALUES (2,'Java学习过程','#62E213'),(4,'test1','#15C27C');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-25 14:58:43
