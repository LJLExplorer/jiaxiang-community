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
  `participant_type` varchar(255) DEFAULT NULL COMMENT '参与角色（推荐官，家长等',
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
INSERT INTO `activity` VALUES (1,1,'星火阅读·好书推荐官”风采展示活动（第二期）','嘉祥街道嘉和社区党群服务中心二楼','2024-10-19 09:00:00',NULL,'团县委、嘉祥街道嘉和社区、县实验小学嘉和校区',20,'星火阅读·好书推荐官10人和家长10人','2025-04-09 21:20:56','2025-04-10 18:57:45'),(2,1,'嘉祥县“祥城慧老”健身气功八段锦公益讲堂活动\r','嘉祥街道嘉和社区党群服务中心一楼大厅','2024-11-13 09:00:00','2024-11-13 10:00:00',NULL,20,'居民','2025-04-09 21:26:33','2025-04-10 18:57:25');
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
  `introduction` text COMMENT '活动介绍',
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
  `is_cover` tinyint(1) DEFAULT '0' COMMENT '是否是封面图片',
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
INSERT INTO `activity_file` VALUES (1,1,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo1.jpg','image/jpeg',1,'星火阅读·好书推荐官活动图片','2025-04-09 21:23:17','2025-04-10 15:35:34'),(2,1,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo2.jpg','image/jpeg',0,'星火阅读·好书推荐官活动图片','2025-04-09 21:23:17','2025-04-09 21:23:40'),(4,2,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin1.jpg','image/jpeg',1,'嘉祥县“祥城慧老”健身气功八段锦活动图片','2025-04-09 21:30:06','2025-04-10 15:35:34'),(5,2,'http:/127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin2.jpg','image/jpeg',0,'嘉祥县“祥城慧老”健身气功八段锦活动图片','2025-04-09 21:30:06','2025-04-09 21:30:06');
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
-- Table structure for table `article_file`
--

DROP TABLE IF EXISTS `article_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `path_url` varchar(255) DEFAULT NULL COMMENT '文件存储地址',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `is_cover` tinyint(1) DEFAULT '0' COMMENT '是否是封面图片',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_file_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动所需图片/附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_file`
--

LOCK TABLES `article_file` WRITE;
/*!40000 ALTER TABLE `article_file` DISABLE KEYS */;
INSERT INTO `article_file` VALUES (1,1,'http://127.0.0.1:9000/jiaxiang/2025/04/10/community_gate.jpg','image/jpeg',0,'社区简介图片','2025-04-10 19:24:00','2025-04-10 19:24:00');
/*!40000 ALTER TABLE `article_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_profile`
--

DROP TABLE IF EXISTS `article_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author_id` bigint DEFAULT NULL COMMENT '发表人id',
  `community_id` bigint NOT NULL COMMENT '社区id',
  `article_type_id` bigint DEFAULT NULL COMMENT '文章类型',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `layout` tinyint DEFAULT NULL COMMENT '无图文章、单图文章、多图文章(0,1,2)',
  `comment_nums` int DEFAULT '0' COMMENT '评论数量',
  `views_nums` int DEFAULT '0' COMMENT '浏览数量',
  `publish_time` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_profile_pk_2` (`id`),
  UNIQUE KEY `community_profile_pk_3` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile`
--

LOCK TABLES `article_profile` WRITE;
/*!40000 ALTER TABLE `article_profile` DISABLE KEYS */;
INSERT INTO `article_profile` VALUES (1,NULL,1,1,'嘉和社区\n',1,0,0,NULL,'2025-04-10 19:20:58','2025-04-10 19:24:16');
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
  `is_show` tinyint(1) DEFAULT '1' COMMENT '是否展示（0/1），默认1 展示(不展示相当于草稿)',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除（默认0未删除）',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_profile_config_pk_2` (`id`),
  UNIQUE KEY `community_profile_config_pk_3` (`article_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile_config`
--

LOCK TABLES `article_profile_config` WRITE;
/*!40000 ALTER TABLE `article_profile_config` DISABLE KEYS */;
INSERT INTO `article_profile_config` VALUES (1,1,0,1,0,'2025-04-10 19:45:21','2025-04-10 19:45:21');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_profile_content`
--

LOCK TABLES `article_profile_content` WRITE;
/*!40000 ALTER TABLE `article_profile_content` DISABLE KEYS */;
INSERT INTO `article_profile_content` VALUES (1,1,'嘉和社区地处嘉祥县城北，管理范围为东至武路、西至老嘉梁路，南至曾子大道，北至嘉北路。嘉和社区下辖财苑小区、博悦城、碧城华园、曾子园、金都花苑、红旗花园、国际商贸城、嘉和苑西区、圣贤世家等9个小区，居民3333333户、972人，党员259名，商铺532家，拥有嘉和园幼儿园、嘉祥县实验小学、嘉祥县第四中学3所学校，教育和商贸资源丰富。\\n 嘉和社区党群中心位于演武路与嘉北路交叉口的临街位置，于2024年8月建成投入使用，服务范围广、功能全，阵地面积1500余平方米。社区立足便民利民、务实管用，科学设置和睦嘉议事厅、暖新嘉驿站、邻里同行坊等12处特色功能阵地，构建一站式社区生活“服务圈”。目前累计开展各类服务活动180余次，服务居民1.2万余人次。\\n 嘉和社区坚持“党建为核、治理为基、服务为本”，积极整合社区资源、搭建治理平台、凝聚社会力量，打造了“幸福嘉和邻里”的社区品牌，引导各方力量共同参与社区治理，不断提升居民在社区生活的获得感、幸福感。\\n 党建赋能，领航“幸福舫”。完善“社区大党委一网格（小区）党支部一楼栋党小组一党员中心户”四级组织架构，开展“支部进小区、党员进楼栋”行动，构建横向到边、纵向到底的组织体系。持续推进智慧社区建设，完善数据采集、信息发布、网上议事、志愿服务等应用场景，提升服务居民的能力和水平，打造网上服务群众平台。\\n 居民参与，启航“幸福舫”。秉承“把居民当家人、把社区当家建”的初衷，打造开放式、互动式办公区域，为居民提供事项办理、政策咨询等服务。充分利用党组织的政治优势和组织优势，引导居民参与社区治理，启航“幸福舫” \\n 多方协同，护航“幸福舫”。建立社区“红色合伙人”资源库，招募嘉祥口腔医院、银泰商圈等“红色合伙人”10个，领资源、服务项目24个。定期邀请驻区单位、社会组织、志愿者等参与社区服务，多方协同，护航“幸福舫”. \\n','2025-04-10 19:46:16','2025-04-10 19:46:16');
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
  `type` varchar(100) DEFAULT NULL COMMENT '文章所属类型',
  `description` varchar(255) DEFAULT NULL COMMENT '中文描述',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_type_pk_2` (`id`),
  UNIQUE KEY `article_type_pk` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章类型，比如社区活动简介，个人荣誉简介，社区简介等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_type`
--

LOCK TABLES `article_type` WRITE;
/*!40000 ALTER TABLE `article_type` DISABLE KEYS */;
INSERT INTO `article_type` VALUES (1,'community_profile','社区简介','2025-04-10 19:15:57','2025-04-11 15:30:10');
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
  `name_en` varchar(100) DEFAULT NULL COMMENT '社区名字',
  `name_cn` varchar(100) DEFAULT NULL COMMENT '中文名',
  `icon` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL COMMENT '简单社区描述',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_name_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社区表 标注有多少个社区';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community`
--

LOCK TABLES `community` WRITE;
/*!40000 ALTER TABLE `community` DISABLE KEYS */;
INSERT INTO `community` VALUES (1,'jia_he_community','嘉和社区','','嘉和社区是一个充满温暖与活力的居住区域，致力于为居民提供舒适、安全、便捷的生活环境。 社区定期举办各类活动，包括健康讲座、体育赛事、环保行动等，以增强邻里关系，促进社区文化建设。','2025-04-09 20:17:43','2025-04-12 10:21:02'),(2,'xiang_cheng_bei_yuan_community','祥城北苑社区','','祥城北苑社区是一个充满温暖与活力的居住区域，致力于为居民提供舒适、安全、便捷的生活环境。 社区定期举办各类活动，包括健康讲座、体育赛事、环保行动等，以增强邻里关系，促进社区文化建设。','2025-04-12 09:51:11','2025-04-12 10:21:02');
/*!40000 ALTER TABLE `community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `community_honor`
--

DROP TABLE IF EXISTS `community_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `community_honor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `communityId` bigint DEFAULT NULL,
  `honor_detail` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_honor_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community_honor`
--

LOCK TABLES `community_honor` WRITE;
/*!40000 ALTER TABLE `community_honor` DISABLE KEYS */;
INSERT INTO `community_honor` VALUES (1,1,'2018年，荣获“全国文明社区”称号。'),(2,1,'2019年，被评为“优秀基层党组织'),(3,1,'2020年，获得“社区治理创新奖');
/*!40000 ALTER TABLE `community_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guide_category`
--

DROP TABLE IF EXISTS `guide_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guide_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `communityId` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guide_category_pk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指导清单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guide_category`
--

LOCK TABLES `guide_category` WRITE;
/*!40000 ALTER TABLE `guide_category` DISABLE KEYS */;
INSERT INTO `guide_category` VALUES (1,1,'村（农村社区）依法履职事项指导清单','内容'),(2,1,'城市社区依法履职事项指导清单','内容'),(3,1,'村（农村社区）依法履职事项指导清单','内容'),(4,1,'城市社区依法履职事项指导清单','内容'),(5,2,'城市社区依法履职事项指导清单','内容');
/*!40000 ALTER TABLE `guide_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serve_people_info`
--

DROP TABLE IF EXISTS `serve_people_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serve_people_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `images` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `open_days_and_time` varchar(50) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `serviceIntro` varchar(522) DEFAULT NULL,
  `serviceContent` varchar(255) DEFAULT NULL,
  `serviceRules` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serve_people_info_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='为人民服务详细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serve_people_info`
--

LOCK TABLES `serve_people_info` WRITE;
/*!40000 ALTER TABLE `serve_people_info` DISABLE KEYS */;
INSERT INTO `serve_people_info` VALUES (1,'暖心嘉','http:/127.0.0.1:9000/jiaxiang/2025/04/11/weimin2.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','暖心嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n',NULL,NULL,'2025-04-11 21:12:08','2025-04-11 21:16:05'),(2,'和睦嘉','http:/127.0.0.1:9000/jiaxiang/2025/04/11/weimin3.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','和睦嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-04-11 21:17:25','2025-04-11 21:19:15'),(3,'和美嘉\n                       ','http:/127.0.0.1:9000/jiaxiang/2025/04/11/weimin4.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','和美嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n                       ',NULL,NULL,'2025-04-11 21:17:25','2025-04-11 21:22:48');
/*!40000 ALTER TABLE `serve_people_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_info`
--

DROP TABLE IF EXISTS `staff_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nameAndPosition` varchar(100) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `duty` varchar(100) DEFAULT NULL,
  `dutyDescription` text,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_info_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='两委成员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_info`
--

LOCK TABLES `staff_info` WRITE;
/*!40000 ALTER TABLE `staff_info` DISABLE KEYS */;
INSERT INTO `staff_info` VALUES (1,'于燕 - 社区党委书记 居委会主任\n','http:/127.0.0.1:9000/jiaxiang/2025/04/11/yuyan','管理社区所有小区','','2025-04-11 20:48:07','2025-04-11 20:54:44'),(2,'郭亚芳 - 社区书记助理 社区两委成员\n','http:/127.0.0.1:9000/jiaxiang/2025/04/11/guoyafang.png','管理红旗花园小区','协助书记处理社区日常事务，确保社区工作的顺利进行。\n\n在书记的指导下，负责社区重要项目的推进和实施。\n\n协调社区内各部门之间的沟通，确保信息畅通。\n\n参与社区重要会议，提出建设性意见。\n\n负责社区居民的反馈收集，及时向书记汇报。','2025-04-11 20:48:57','2025-04-11 21:03:02'),(3,'郭振南 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/guozhennan.png','管理祥曾园小区','负责社区组织建设，制定组织工作计划并组织实施。\n\n组织社区党员和居民开展各类活动，增强社区凝聚力。\n\n负责社区党员的教育和管理，提高党员素质。\n\n协调社区内外部资源，推动社区组织发展。\n\n完成上级组织交办的其他任务。','2025-04-11 20:49:22','2025-04-11 21:03:02'),(4,'黄威 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/huangwei.png','管理嘉和苑西区','负责社区宣传工作，制定宣传计划并组织实施。\n\n通过多种渠道宣传社区活动和政策，提高居民知晓率。\n\n负责社区宣传材料的制作和分发，确保宣传效果。\n\n组织社区居民开展宣传活动，提升社区形象。\n\n完成上级宣传部门交办的其他任务。','2025-04-11 20:49:44','2025-04-11 21:03:02'),(5,'江婷婷 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/jiangtingting.png','管理财苑小区、碧城华园小区','负责社区纪律检查工作，确保社区工作廉洁高效。\n\n监督社区工作人员遵守纪律，防止违规行为。\n\n处理居民投诉和举报，维护社区良好秩序。\n\n定期开展廉政教育活动，提高社区工作人员廉洁意识。\n\n完成上级纪检部门交办的其他任务。','2025-04-11 20:50:07','2025-04-11 21:03:02'),(6,'蒋彦臣 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/jiangyanchen.png','管理金都花苑小区','负责社区青年工作，制定青年工作计划并组织实施。\n\n组织青年开展各类活动，促进青年成长和发展。\n\n关注青年需求，为青年提供就业创业指导和服务。\n\n引导青年参与社区建设，发挥青年积极作用。\n\n完成上级青年组织交办的其他任务。','2025-04-11 20:50:54','2025-04-11 21:03:02'),(7,'孟如 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/mengru.png.png','管理国际商贸城小区','负责社区妇女工作，制定妇女工作计划并组织实施。\n\n组织妇女开展各类活动，提升妇女素质和能力。\n\n维护妇女合法权益，关注妇女需求。\n\n引导妇女参与社区建设，发挥妇女积极作用。\n\n完成上级妇女组织交办的其他任务。','2025-04-11 20:51:18','2025-04-11 21:03:02'),(8,'张传祥 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/zhangchuanxiang.png','管理红旗花园小区','负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。','2025-04-11 20:51:45','2025-04-11 21:03:02'),(9,'赵劲松 - 一般社区工作人员','http:/127.0.0.1:9000/jiaxiang/2025/04/11/zhaojinsong.png','管理博悦城小区','负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。','2025-04-11 20:52:11','2025-04-11 21:03:02');
/*!40000 ALTER TABLE `staff_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-13 12:58:46
