-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: favshare
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `create_date` datetime(6) NOT NULL,
  `is_modify` bit(1) NOT NULL,
  `pop_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6md77o1v0qoclryr5ld13da1i` (`pop_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK6md77o1v0qoclryr5ld13da1i` FOREIGN KEY (`pop_id`) REFERENCES `pop` (`id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (19,'댓글1','2022-08-01 04:17:29.919000',_binary '\0',1,1),(20,'댓글2','2022-08-01 04:17:36.894000',_binary '\0',1,2),(21,'댓글3','2022-08-01 04:17:40.928000',_binary '\0',1,3),(22,'댓글','2022-08-01 04:17:56.879000',_binary '\0',2,4),(23,'댓글','2022-08-01 04:17:59.274000',_binary '\0',2,5),(24,'댓글','2022-08-01 04:18:03.352000',_binary '\0',3,1),(25,'댓글','2022-08-01 04:18:05.655000',_binary '\0',3,2),(26,'댓글','2022-08-01 04:18:10.935000',_binary '\0',4,2),(27,'댓글','2022-08-01 04:18:13.649000',_binary '\0',4,5),(28,'댓글','2022-08-01 04:18:17.889000',_binary '\0',5,1),(29,'댓글','2022-08-01 04:18:20.331000',_binary '\0',5,2),(30,'댓글','2022-08-01 04:18:23.835000',_binary '\0',5,4),(31,'댓글','2022-08-01 04:18:28.177000',_binary '\0',6,4),(32,'댓글','2022-08-01 04:18:32.546000',_binary '\0',6,5),(33,'댓글','2022-08-01 04:18:37.533000',_binary '\0',7,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `id` int NOT NULL,
  `feed_image_url` varchar(255) DEFAULT NULL,
  `is_first` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeupe1ba7u2e7sr6r3fa4dhdo7` (`user_id`),
  CONSTRAINT `FKeupe1ba7u2e7sr6r3fa4dhdo7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (1,NULL,_binary '','user1피드',1),(2,NULL,_binary '\0','user1피드',1),(3,NULL,_binary '','user2피드',2),(4,NULL,_binary '\0','user2피드',2),(5,NULL,_binary '\0','user2피드',2),(6,NULL,_binary '','user3피드',3),(7,NULL,_binary '','user4피드',4),(8,NULL,_binary '\0','user4피드',4),(9,NULL,_binary '','user5피드',5),(10,NULL,_binary '\0','user5피드',5),(11,NULL,_binary '\0','user5피드',5),(12,NULL,_binary '\0','user5피드',5);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL,
  `from_user_id` int NOT NULL,
  `to_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKepp5qc1696afiyipw8jhk6et7` (`from_user_id`),
  KEY `FKbo8snnjqnckmjhm2d71j3bc84` (`to_user_id`),
  CONSTRAINT `FKbo8snnjqnckmjhm2d71j3bc84` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKepp5qc1696afiyipw8jhk6et7` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,1,5),(2,3,4),(3,4,3),(16,2,1),(17,2,5),(18,4,2);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (48);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idol`
--

DROP TABLE IF EXISTS `idol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idol` (
  `id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `idol_image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idol`
--

LOCK TABLES `idol` WRITE;
/*!40000 ALTER TABLE `idol` DISABLE KEYS */;
INSERT INTO `idol` VALUES (1,'아이브 설명입니다~','iveUrl','아이브'),(2,'트와이스 설명입니다~','twiceUrl','트와이스'),(3,'세븐틴 설명입니다~','seventeenUrl','세븐틴'),(4,'방탄소년단 설명입니다~','btsUrl','방탄소년단'),(5,'아이들 설명입니다~','idleUrl','아이들');
/*!40000 ALTER TABLE `idol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idol_member`
--

DROP TABLE IF EXISTS `idol_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idol_member` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `idol_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfksx1wuoxbewcmoukbp7cis43` (`idol_id`),
  CONSTRAINT `FKfksx1wuoxbewcmoukbp7cis43` FOREIGN KEY (`idol_id`) REFERENCES `idol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idol_member`
--

LOCK TABLES `idol_member` WRITE;
/*!40000 ALTER TABLE `idol_member` DISABLE KEYS */;
INSERT INTO `idol_member` VALUES (1,'안유진',1),(2,'가을',1),(3,'레이',1),(4,'장원영',1),(5,'리즈',1),(6,'이서',1),(7,'나연',2),(8,'정연',2),(9,'모모',2),(10,'사나',2),(11,'지효',2),(12,'미나',2),(13,'다현',2),(14,'채영',2),(15,'쯔위',2),(16,'원우',3),(17,'에스쿱스',3),(18,'디에잇',3),(19,'정한',3),(20,'민규',3),(21,'조슈아',3),(22,'도겸',3),(23,'준',3),(24,'승관',3),(25,'호시',3),(26,'버논',3),(27,'디노',3),(28,'우지',3),(29,'진',4),(30,'슈가',4),(31,'제이홉',4),(32,'RM',4),(33,'지민',4),(34,'뷔',4),(35,'정국',4),(36,'미연',5),(37,'민니',5),(38,'소연',5),(39,'우기',5),(40,'슈화',5);
/*!40000 ALTER TABLE `idol_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest_idol`
--

DROP TABLE IF EXISTS `interest_idol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest_idol` (
  `id` int NOT NULL,
  `idol_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe90d9haohsjwkv700vkb5gne1` (`idol_id`),
  KEY `FKorx015dc964te4vcx6kur2fpv` (`user_id`),
  CONSTRAINT `FKe90d9haohsjwkv700vkb5gne1` FOREIGN KEY (`idol_id`) REFERENCES `idol` (`id`),
  CONSTRAINT `FKorx015dc964te4vcx6kur2fpv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest_idol`
--

LOCK TABLES `interest_idol` WRITE;
/*!40000 ALTER TABLE `interest_idol` DISABLE KEYS */;
INSERT INTO `interest_idol` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5);
/*!40000 ALTER TABLE `interest_idol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest_song`
--

DROP TABLE IF EXISTS `interest_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest_song` (
  `id` int NOT NULL,
  `song_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkb7br2sfxtpfi8be0sotafewv` (`song_id`),
  KEY `FK46nowdmxb8aagskv5l2gxf162` (`user_id`),
  CONSTRAINT `FK46nowdmxb8aagskv5l2gxf162` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkb7br2sfxtpfi8be0sotafewv` FOREIGN KEY (`song_id`) REFERENCES `song` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest_song`
--

LOCK TABLES `interest_song` WRITE;
/*!40000 ALTER TABLE `interest_song` DISABLE KEYS */;
INSERT INTO `interest_song` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,3),(6,6,3),(7,7,4),(8,8,4),(9,9,5),(10,10,5);
/*!40000 ALTER TABLE `interest_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_comment`
--

DROP TABLE IF EXISTS `like_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_comment` (
  `id` int NOT NULL,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0r3rvwkfrav930797rs2d9y1` (`comment_id`),
  KEY `FKda02ibmlxln75b1lato8698nv` (`user_id`),
  CONSTRAINT `FKda02ibmlxln75b1lato8698nv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKh0r3rvwkfrav930797rs2d9y1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_comment`
--

LOCK TABLES `like_comment` WRITE;
/*!40000 ALTER TABLE `like_comment` DISABLE KEYS */;
INSERT INTO `like_comment` VALUES (34,19,1),(35,19,2),(36,20,1),(37,20,4),(38,20,5),(39,22,4),(40,22,2);
/*!40000 ALTER TABLE `like_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_pop`
--

DROP TABLE IF EXISTS `like_pop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_pop` (
  `id` int NOT NULL,
  `pop_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeany7445arso7tpi88d0mt4ia` (`pop_id`),
  KEY `FKncsbdv8dna800hgjfvf6m9u5n` (`user_id`),
  CONSTRAINT `FKeany7445arso7tpi88d0mt4ia` FOREIGN KEY (`pop_id`) REFERENCES `pop` (`id`),
  CONSTRAINT `FKncsbdv8dna800hgjfvf6m9u5n` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_pop`
--

LOCK TABLES `like_pop` WRITE;
/*!40000 ALTER TABLE `like_pop` DISABLE KEYS */;
INSERT INTO `like_pop` VALUES (41,1,1),(42,1,2),(43,3,2),(44,3,4),(45,7,2),(46,7,5),(47,7,1);
/*!40000 ALTER TABLE `like_pop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pop`
--

DROP TABLE IF EXISTS `pop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pop` (
  `id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) NOT NULL,
  `end_second` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_second` int NOT NULL,
  `views` int NOT NULL,
  `user_id` int NOT NULL,
  `youtube_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlh88nyaaj5hgoxc6eumyufym` (`user_id`),
  KEY `FKt40fhl5yl6eka5xfyad19txcd` (`youtube_id`),
  CONSTRAINT `FKlh88nyaaj5hgoxc6eumyufym` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt40fhl5yl6eka5xfyad19txcd` FOREIGN KEY (`youtube_id`) REFERENCES `youtube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pop`
--

LOCK TABLES `pop` WRITE;
/*!40000 ALTER TABLE `pop` DISABLE KEYS */;
INSERT INTO `pop` VALUES (1,'pop입니다','2012-01-01 00:00:00.000000',111,'pop1',11,0,1,1),(2,'pop입니다','2018-11-02 00:00:00.000000',67,'pop2',22,0,1,2),(3,'pop입니다','2012-01-01 00:00:00.000000',23,'pop3',11,0,2,3),(4,'pop입니다','2019-04-30 00:00:00.000000',56,'pop4',12,0,3,5),(5,'pop입니다','2020-01-01 00:00:00.000000',78,'pop5',14,0,4,7),(6,'pop입니다','2012-03-02 00:00:00.000000',90,'pop6',56,0,5,9),(7,'pop입니다','2017-01-29 00:00:00.000000',67,'pop7',45,0,5,10);
/*!40000 ALTER TABLE `pop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pop_in_feed`
--

DROP TABLE IF EXISTS `pop_in_feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pop_in_feed` (
  `id` int NOT NULL,
  `feed_id` int NOT NULL,
  `pop_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK88rpynawii3e37v57pf6vhlir` (`feed_id`),
  KEY `FKo70okb67ywhj03n6jy0ty0x99` (`pop_id`),
  CONSTRAINT `FK88rpynawii3e37v57pf6vhlir` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`),
  CONSTRAINT `FKo70okb67ywhj03n6jy0ty0x99` FOREIGN KEY (`pop_id`) REFERENCES `pop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pop_in_feed`
--

LOCK TABLES `pop_in_feed` WRITE;
/*!40000 ALTER TABLE `pop_in_feed` DISABLE KEYS */;
INSERT INTO `pop_in_feed` VALUES (1,1,1),(2,1,2),(3,2,1),(4,3,3),(5,4,3),(6,5,3),(7,4,6),(8,7,5),(9,8,5),(10,9,6),(11,10,6),(12,11,7),(13,12,7);
/*!40000 ALTER TABLE `pop_in_feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_pop`
--

DROP TABLE IF EXISTS `show_pop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_pop` (
  `id` int NOT NULL,
  `pop_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23ywldpr02idot5quenf1a247` (`pop_id`),
  KEY `FK9ur7ylncdoufvmewsks20fmsk` (`user_id`),
  CONSTRAINT `FK23ywldpr02idot5quenf1a247` FOREIGN KEY (`pop_id`) REFERENCES `pop` (`id`),
  CONSTRAINT `FK9ur7ylncdoufvmewsks20fmsk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_pop`
--

LOCK TABLES `show_pop` WRITE;
/*!40000 ALTER TABLE `show_pop` DISABLE KEYS */;
INSERT INTO `show_pop` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5);
/*!40000 ALTER TABLE `show_pop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_youtube`
--

DROP TABLE IF EXISTS `show_youtube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_youtube` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `youtube_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1347tpcc82e8sm3mi5hlh617j` (`user_id`),
  KEY `FKnm833t0sxxt54wai9nmkekjea` (`youtube_id`),
  CONSTRAINT `FK1347tpcc82e8sm3mi5hlh617j` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnm833t0sxxt54wai9nmkekjea` FOREIGN KEY (`youtube_id`) REFERENCES `youtube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_youtube`
--

LOCK TABLES `show_youtube` WRITE;
/*!40000 ALTER TABLE `show_youtube` DISABLE KEYS */;
INSERT INTO `show_youtube` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,3,5),(6,3,6),(7,4,7),(8,4,8),(9,5,9),(10,5,10);
/*!40000 ALTER TABLE `show_youtube` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'아이브노래1','love dive'),(2,'아이브노래2','eleven'),(3,'트와이스노래1','TT'),(4,'트와이슨노래2','Alcohol Free'),(5,'세븐틴노래1','박수'),(6,'세븐틴노래2','만세'),(7,'방탄노래1','피땀눈물'),(8,'방탄노래2','작은 것들을 위한 시'),(9,'아이들노래1','Tomboy'),(10,'아이들노래2','LATATA');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_youtube`
--

DROP TABLE IF EXISTS `store_youtube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_youtube` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `youtube_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaqrvm5rdnacjqw3ksy391bftq` (`user_id`),
  KEY `FKsjrrr8b4xy1dvc7g7jxqcq1gh` (`youtube_id`),
  CONSTRAINT `FKaqrvm5rdnacjqw3ksy391bftq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsjrrr8b4xy1dvc7g7jxqcq1gh` FOREIGN KEY (`youtube_id`) REFERENCES `youtube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_youtube`
--

LOCK TABLES `store_youtube` WRITE;
/*!40000 ALTER TABLE `store_youtube` DISABLE KEYS */;
INSERT INTO `store_youtube` VALUES (1,1,1),(2,2,3),(3,3,5),(4,4,7),(5,5,9);
/*!40000 ALTER TABLE `store_youtube` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2010-01-01 00:00:00.000000','안녕하세요~','abcd1234@naver.com','김싸피','아이브 팬1','abcd1234!','01012345678','url1'),(2,'2008-12-22 00:00:00.000000','반갑습니다!','aaaa@naver.com','홍길동','트와이스 팬2','aaaa1234!','01011111111','url2'),(3,'2005-10-11 00:00:00.000000','안녕!','jjjj@naver.com','김주원','세븐틴 팬3','jjjj1234!','01022223333','url3'),(4,'2007-04-21 00:00:00.000000','안녕~~','nnnn123@naver.com','길라임','방탄 팬4','nnnn1234!','01033334444','url4'),(5,'1987-05-06 00:00:00.000000','반가워!','zxcv123@naver.com','이싸피','아이들 팬5','zxcv1234!','01099999999','url5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `youtube`
--

DROP TABLE IF EXISTS `youtube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `youtube` (
  `id` int NOT NULL,
  `url` varchar(255) NOT NULL,
  `idol_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9g566px0shl58b7od5q2albk5` (`idol_id`),
  CONSTRAINT `FK9g566px0shl58b7od5q2albk5` FOREIGN KEY (`idol_id`) REFERENCES `idol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `youtube`
--

LOCK TABLES `youtube` WRITE;
/*!40000 ALTER TABLE `youtube` DISABLE KEYS */;
INSERT INTO `youtube` VALUES (1,'url주소',1),(2,'url주소',1),(3,'url주소',2),(4,'url주소',2),(5,'url주소',3),(6,'url주소',3),(7,'url주소',4),(8,'url주소',4),(9,'url주소',5),(10,'url주소',5);
/*!40000 ALTER TABLE `youtube` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-01 13:29:08
