-- MySQL dump 10.13  Distrib 9.2.0, for macos15.2 (arm64)
--
-- Host: 127.0.0.1    Database: community_springcloud
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `community_id` bigint NOT NULL COMMENT '社区id',
  `title` varchar(255) DEFAULT NULL COMMENT '活动标题',
  `location` varchar(255) DEFAULT NULL COMMENT '活动地点',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `organizer` varchar(255) DEFAULT NULL COMMENT '主办方(多个逗号隔开)',
  `participant_count` int DEFAULT NULL COMMENT '预计人数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'星火阅读·好书推荐官”风采展示活动（第二期）','嘉祥街道嘉和社区党群服务中心二楼','2024-10-19 09:00:00',NULL,'团县委、嘉祥街道嘉和社区、县实验小学嘉和校区',20,'2025-04-09 21:20:56','2025-04-09 21:20:56'),(2,1,'嘉祥县“祥城慧老”健身气功八段锦公益讲堂活动\r','嘉祥街道嘉和社区党群服务中心一楼大厅','2024-11-13 09:00:00','2024-11-13 10:00:00',NULL,20,'2025-04-09 21:26:33','2025-04-09 21:26:33');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_detail`
--

DROP TABLE IF EXISTS `activity_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint DEFAULT NULL,
  `theme` text COMMENT '活动主题',
  `Introduction` text COMMENT '活动介绍',
  `content` text COMMENT '活动安排，具体流程',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_detail_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_detail`
--

LOCK TABLES `activity_detail` WRITE;
/*!40000 ALTER TABLE `activity_detail` DISABLE KEYS */;
INSERT INTO `activity_detail` VALUES (1,1,NULL,'为进一步深化“星火阅读”活动品牌，激励引导少年儿童爱读书、读好书、善读书，营造全民阅读的浓厚氛围，团县委、县少工委推出“星火阅读·好书推荐官”风采展示活动，通过开展读书分享活动，引领广大少先队员从阅读中汲取智慧力量，在阅读中坚定理想信念。','1、思想引领环节：“青春社区”志愿者开展“移风易俗”主题宣讲，引导未成年人结合自身日常生活，积极践行崇尚科学、尊老爱幼、男女平等、绿色环保等美德。\r2、“星火阅读·好书推荐官”风采展示环节\r3、颁发活动证书并合影\r嘉祥街道嘉和社区负责布置活动场地，并安排工作人员负责活动具体实施（签到、主持、宣讲、拍照等）。\r','2025-04-09 21:21:57','2025-04-09 21:21:57'),(2,2,'“祥城慧老”健身气功八段锦课堂','为推动全民健身工作，提高全民健康素质，营造全民健身的氛围，老年大学联合嘉和社区开展“八段锦”健身公益讲堂活动，旨在为居民们带来身心的放松和愉悦，更有助于居民探索健康生活的新路径。','1.嘉和社区负责布置活动场地、招募居民、宣传报道\n2.老年大学专业老师现场教学\n','2025-04-09 21:27:22','2025-04-09 21:27:40');
/*!40000 ALTER TABLE `activity_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_file`
--

DROP TABLE IF EXISTS `activity_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL,
  `path_url` varchar(255) DEFAULT NULL COMMENT '文件存储地址',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_file_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动所需图片/附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_file`
--

LOCK TABLES `activity_file` WRITE;
/*!40000 ALTER TABLE `activity_file` DISABLE KEYS */;
INSERT INTO `activity_file` VALUES (1,1,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo1.jpg','image/jpeg','星火阅读·好书推荐官活动图片','2025-04-09 21:23:17','2025-04-09 21:23:40'),(2,1,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo2.jpg','image/jpeg','星火阅读·好书推荐官活动图片','2025-04-09 21:23:17','2025-04-09 21:23:40'),(4,2,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin1.jpg','image/jpeg','嘉祥县“祥城慧老”健身气功八段锦活动图片','2025-04-09 21:30:06','2025-04-09 21:30:06'),(5,2,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin2.jpg','image/jpeg','嘉祥县“祥城慧老”健身气功八段锦活动图片','2025-04-09 21:30:06','2025-04-09 21:30:06');
/*!40000 ALTER TABLE `activity_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_participant`
--

DROP TABLE IF EXISTS `activity_participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_participant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL COMMENT '家长，居民，志愿者',
  `contact_info` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_participant_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动参加人员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_participant`
--

LOCK TABLES `activity_participant` WRITE;
/*!40000 ALTER TABLE `activity_participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_profile`
--

DROP TABLE IF EXISTS `article_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author_id` int DEFAULT NULL COMMENT '发表人id',
  `community_id` int NOT NULL COMMENT '社区id',
  `article_type_id` int DEFAULT NULL COMMENT '文章类型',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `author_name` int DEFAULT NULL COMMENT '发表人名称',
  `layout` tinyint DEFAULT NULL COMMENT '无图文章、单图文章、多图文章',
  `images` varchar(255) DEFAULT NULL COMMENT '文章图片的url，多张图片逗号间隔,封面默认第一张',
  `comment_nums` int DEFAULT NULL COMMENT '评论数量',
  `views_nums` int DEFAULT NULL COMMENT '浏览数量',
  `publish_time` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_profile_pk_2` (`id`),
  UNIQUE KEY `community_profile_pk_3` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile`
--

LOCK TABLES `article_profile` WRITE;
/*!40000 ALTER TABLE `article_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_profile_config`
--

DROP TABLE IF EXISTS `article_profile_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_profile_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_profile_id` bigint NOT NULL,
  `is_comment` tinyint DEFAULT '0' COMMENT '是否可评论(0/1)，默认0不可以',
  `is_show` int DEFAULT '1' COMMENT '是否展示（0/1），默认1 展示(不展示相当于草稿)',
  `is_delete` int DEFAULT '0' COMMENT '是否删除（默认0未删除）',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_profile_config_pk_2` (`id`),
  UNIQUE KEY `community_profile_config_pk_3` (`article_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile_config`
--

LOCK TABLES `article_profile_config` WRITE;
/*!40000 ALTER TABLE `article_profile_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_profile_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_profile_content`
--

DROP TABLE IF EXISTS `article_profile_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_profile_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_profile_id` bigint NOT NULL,
  `content` longtext COMMENT '社区简介内容',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_profile_content_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile_content`
--

LOCK TABLES `article_profile_content` WRITE;
/*!40000 ALTER TABLE `article_profile_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_profile_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_type`
--

DROP TABLE IF EXISTS `article_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(500) DEFAULT NULL COMMENT '文章所属类型',
  `description` varchar(500) DEFAULT NULL COMMENT '中文描述',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_type_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章类型，比如社区活动简介，个人荣誉简介，社区简介等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_type`
--

LOCK TABLES `article_type` WRITE;
/*!40000 ALTER TABLE `article_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `community`
--

DROP TABLE IF EXISTS `community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `community` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '社区名字',
  `description` varchar(500) DEFAULT NULL COMMENT '简单社区描述',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_name_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社区表 标注有多少个社区';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community`
--

LOCK TABLES `community` WRITE;
/*!40000 ALTER TABLE `community` DISABLE KEYS */;
INSERT INTO `community` VALUES (1,'jia_he_community','嘉和社区','2025-04-09 20:17:43','2025-04-09 20:18:04');
/*!40000 ALTER TABLE `community` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-09 21:40:39
