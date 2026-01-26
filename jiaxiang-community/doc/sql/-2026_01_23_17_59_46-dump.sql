-- MySQL dump 10.13  Distrib 9.5.0, for macos15.7 (arm64)
--
-- Host: 1.13.158.205    Database: community_springcloud
-- ------------------------------------------------------
-- Server version	8.4.6

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,1,'星火阅读·好书推荐官”风采展示活动','嘉祥街道嘉和社区党群服务中心二楼','2024-10-19 09:00:00',NULL,'团县委、嘉祥街道嘉和社区、县实验小学嘉和校区',20,'10名“星火阅读·好书推荐官”及家长，共计20人。','2025-10-20 01:20:27','2025-10-20 01:20:27'),(3,1,'嘉祥县“祥城慧老”健身气功八段锦','嘉祥街道嘉和社区党群服务中心一楼大厅','2024-11-13 09:00:00','2024-11-13 10:00:00','社区居委会',200,'居民','2025-10-20 01:23:24','2025-10-20 01:23:24'),(4,1,'抵制高价彩礼 倡树婚俗新风','嘉祥县嘉祥街道嘉和社区党群服务中心一楼大厅','2024-09-30 15:00:00','2024-09-30 17:00:00','团县委、嘉祥街道嘉和社区、县实验小学嘉和校区',20,'10名“星火阅读·好书推荐官”及家长，共计20人。','2025-10-20 01:33:31','2025-10-20 01:33:31'),(5,1,'红色贴珠画 共抒爱国心','嘉祥县嘉祥街道嘉和社区党群服务中心二楼同行坊','2024-09-25 09:00:00','2024-09-25 10:00:00','社区居委会',200,'居民','2025-10-20 01:34:53','2025-10-20 01:34:53'),(6,1,'绘出多彩世界凝聚社区温情','嘉祥县嘉祥街道嘉和社区党群服务中心二楼童乐坊','2025-02-16 09:00:00','2025-02-16 11:00:00','社区居委会',200,'居民','2025-10-20 01:44:09','2025-10-20 01:44:09'),(7,1,'“圾”时分类，绿色“童”行','嘉和社区党群服务中心二楼童乐坊','2024-11-30 14:30:00','2024-11-30 16:00:00','社区居委会',200,'居民','2025-10-20 01:49:29','2025-10-20 01:49:29'),(8,1,'饺香暖冬','嘉祥街道嘉和社区党群服务中心二楼同心坊','2024-11-07 16:00:00','2024-11-07 18:00:00','社区居委会',200,'居民','2025-10-20 01:53:19','2025-10-20 01:53:19'),(9,1,'镂刻新岁，雕艺焕彩','嘉祥县嘉祥街道嘉和社区党群服务中心二楼童乐坊','2025-01-22 09:00:00','2025-01-22 11:00:00','社区居委会',200,'居民','2025-10-20 02:02:21','2025-10-20 02:02:21'),(10,1,'巧手编织，扮靓生活','嘉祥县嘉祥街道嘉和社区党群服务中心二楼童乐坊','2024-09-26 09:00:00','2024-09-26 10:00:00','社区居委会',200,'居民','2025-10-20 02:06:08','2025-10-20 02:06:08'),(11,1,'巧手做月饼，送出社区情','嘉祥县嘉祥街道嘉和社区党群服务中心二楼同行坊、辖区内小区','2024-09-12 18:00:00','2024-09-13 09:00:00','社区居委会',200,'居民','2025-10-20 02:11:39','2025-10-20 02:11:39'),(12,1,'正双姿，写未来','嘉和社区党群服务中心一楼大厅','2024-12-15 15:00:00','2024-12-15 17:00:00','社区居委会',200,'居民','2025-10-20 02:18:07','2025-10-20 02:18:07'),(13,1,'健康义诊进社区','嘉祥街道嘉和社区党群服务中心二楼同行坊','2025-10-24 09:00:00','2025-10-24 10:00:00','社区居委会',200,'居民','2025-10-20 02:20:59','2025-10-20 02:20:59'),(14,1,'老年学堂音乐课堂','嘉和社区党群服务中心二楼同乐坊','2024-12-04 15:30:00','2024-12-04 17:00:00','社区居委会',200,'居民','2025-10-20 02:24:18','2025-10-20 02:24:18'),(15,1,'品元宵叙党情奔赴新征程','嘉祥县嘉祥街道嘉和社区党群服务中心一楼爱上嘉','2025-02-12 09:00:00','2025-02-12 11:00:00','社区居委会',200,'居民','2025-10-20 02:27:00','2025-10-20 02:27:00'),(16,1,'悦享夕阳老年声乐课堂','嘉祥县嘉祥街道嘉和社区党群服务中心二楼同悦坊','2025-01-07 14:00:00','2025-01-07 16:00:00','社区居委会',200,'居民','2025-10-20 02:29:44','2025-10-20 02:29:44'),(17,1,'巧绘年画，欢喜迎春','嘉祥县嘉祥街道嘉和社区党群服务中心二楼童乐坊','2025-01-22 09:00:00','2025-01-22 11:00:00','社区居委会',200,'居民','2025-10-20 02:31:51','2025-10-20 02:31:51');
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
  UNIQUE KEY `activity_detail_pk_2` (`id`),
  UNIQUE KEY `activity_detail_pk` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_detail`
--

LOCK TABLES `activity_detail` WRITE;
/*!40000 ALTER TABLE `activity_detail` DISABLE KEYS */;
INSERT INTO `activity_detail` VALUES (2,2,'星火阅读','星火阅读·好书推荐官”风采展示活动','思想引领环节：“青春社区”志愿者开展“移风易俗”主题宣讲，引导未成年人结合自身日常生活，积极践行崇尚科学、尊老爱幼、男女平等、绿色环保等美德。\n “星火阅读·好书推荐官”风采展示环节。 \n 颁发活动证书并合影。','2025-10-20 01:20:27','2025-10-20 01:24:33'),(3,3,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','嘉和社区负责布置活动场地、招募居民、宣传报道\n老年大学专业老师现场教学','2025-10-20 01:23:24','2025-10-20 01:23:24'),(4,4,'低价高彩礼','星火阅读·好书推荐官”风采展示活动','确定好活动时间和地点以及活动流程，给居民做好通知工作；在活动当天提前布置现场，由社区工作人员做好人员引导就座；活动开始社区工作人员进行现场秩序维持；活动结束后，对活动进行记录和总结。','2025-10-20 01:33:31','2025-10-20 01:33:31'),(5,5,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动开始，由社区工作人员进行手工制作讲解，介绍制作方法。\n活动中，对居民进行制作指导和帮助。活动结束后，对活动进行记录和总结。','2025-10-20 01:34:53','2025-10-20 01:34:53'),(6,6,'社区温情','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程，提前在居民群里发布公告，确定活动人数，采购好活动材料。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动开始由老师指导孩子们绘画，社区工作人员进行现场秩序维持。\n活动结束后，对活动进行记录和总结。','2025-10-20 01:44:09','2025-10-20 01:44:09'),(7,7,'社区温情','一年一度的社区运动会，欢迎大家积极参与！','活动前两天在居民群内发布活动公告，由家长扫码报名，截止报名时间，有工作人员电话通知报名是否成功。活动前一天，由工作人员现场布置。\n活动开始首先用开展低碳环保小能手评比、垃圾分类实践、塑料污染防治科普等绿色实践活动，积极营造健康、文明、向上的校园生态文化。 通过宣讲模式开展绿色环保主题教育活动，推动生态文明理念入眼入耳、入脑入心，教育青少年从小事做起，养成绿色、环保、低碳、适度、健康的行为习惯和消费方式。\n活动结束后，做好活动总结和宣传。','2025-10-20 01:49:29','2025-10-20 01:49:29'),(8,8,'社区温情','一年一度的社区运动会，欢迎大家积极参与！','儿童活动前一天在居民群里发布活动通知，引导居民登记报名表，由工作人员布置好场地。\n活动当天上午采购好，活动用到的食材和工具，在一楼做好活动居民信息登记，并有工作人员引导上二楼安排人员参加活动。\n活动中，大家一起动手做饺子。活动结束，由工作人员送居民离开。\n并做好活动记录，及时汇总、宣传报道。','2025-10-20 01:53:19','2025-10-20 01:53:19'),(9,9,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程，在居民中招募参加对象，提前报名，确定人数，采购好活动材料。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动开始社区工作人员进行现场秩序维持。\n活动结束后，对活动进行记录和总结。','2025-10-20 02:02:21','2025-10-20 02:02:21'),(10,10,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程，给居民做好通知工作。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动现场，钩织老师耐心地为居民们讲解毛线钩织的基本方法和技巧。从如何拿钩针、如何起针，到如何编织不同的花纹图案，老师一步一步地进行示范，居民们则聚精会神地听讲，不时地提出问题。活动中，对居民进行制作指导和帮助。\n活动结束后，对活动进行记录和总结。','2025-10-20 02:06:08','2025-10-20 02:06:08'),(11,11,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程。\n在活动当天提前布置现场，由社区工作人员做好月饼，第二天送给值守在岗位的值班人员。\n活动结束后，做好活动记录和总结。','2025-10-20 02:11:39','2025-10-20 02:11:39'),(12,12,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','活动前联系专业书法老师，确定好活动时间，提前三天在小区居民群发布活动预告，由家长扫码登记报名，活动前一天截止报名，工作人员依次电话通知时间地点，活动当天有家长带来孩子一起参加活动。\n活动开始一楼工作人员进行人员签到，引导居民到活动场地按座次牌就座，由专业老师进行授课，工作人员做好活动支持，维持现场秩序。\n活动结束后，引导居民秩序离开，并做好活动总结和宣传。','2025-10-20 02:18:07','2025-10-20 02:18:07'),(13,13,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','活动前一天联系好社区医院医生，约定好活动时间，并在居民群里发布义诊通知，引导居民报名，并布置好场地。活动当天在一楼做好活动居民信息登记，并有工作人员引导上二楼安排排队就座。\n活动中，由社区医院工作人员进行为老人检查身体，黄威、劲松现场维持秩序，协助老年人量体重、脱衣服等等。\n活动结束，由工作人员送居民离开。并做好活动记录，及时汇总、宣传报道。','2025-10-20 02:20:59','2025-10-20 02:20:59'),(14,14,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','活动前联系嘉祥县老年大学音乐老师确定好活动时间；在居民中宣传活动，登记报名人员。\n活动开始一楼工作人员进行人员签到，引导居民到二楼参加活动场地，二楼工作人员进行现场秩序维持，并做好活动支持。\n活动结束后，引导居民秩序离开，并做好活动总结和宣传。','2025-10-20 02:24:18','2025-10-20 02:24:18'),(15,15,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程，提前通知居民，确定人数，采购好活动材料。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动开始社区工作人员进行现场秩序维持。\n活动结束后，对活动进行记录和总结。','2025-10-20 02:27:00','2025-10-20 02:27:00'),(16,16,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，联系老年大学专业老师确定好活动时间和地点以及活动流程，在居民中招募参加对象，提前报名，确定人数，提前布置活动场地。\n。在活动当天，由社区工作人员做好人员引导就座。活动开始社区工作人员进行现场秩序维持。\n活动结束后，对活动进行记录和总结。','2025-10-20 02:29:44','2025-10-20 02:29:44'),(17,17,'健康生活','一年一度的社区运动会，欢迎大家积极参与！','在活动开始前，确定好活动时间和地点以及活动流程，在居民中招募参加对象，提前报名，确定人数，采购好活动材料。\n在活动当天提前布置现场，由社区工作人员做好人员引导就座。\n活动开始社区工作人员进行现场秩序维持。\n活动结束后，对活动进行记录和总结。','2025-10-20 02:31:51','2025-10-20 02:31:51');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动所需图片/附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_file`
--

LOCK TABLES `activity_file` WRITE;
/*!40000 ALTER TABLE `activity_file` DISABLE KEYS */;
INSERT INTO `activity_file` VALUES (1,2,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png','image/png',1,'星火阅读·好书推荐官”风采展示活动','2025-10-20 01:20:28','2025-10-20 01:20:28'),(2,2,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片1.jpg','image/jpeg',0,'星火阅读·好书推荐官”风采展示活动','2025-10-20 01:20:28','2025-10-20 01:20:28'),(3,3,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image2.png','image/jpeg',1,'嘉祥县“祥城慧老”健身气功八段锦','2025-10-20 01:28:15','2025-10-20 01:42:01'),(4,3,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片2.jpg','image/jpeg',0,'嘉祥县“祥城慧老”健身气功八段锦','2025-10-20 01:28:15','2025-10-20 01:28:15'),(5,4,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image3.png','image/png',1,'抵制高价彩礼 倡树婚俗新风','2025-10-20 01:33:31','2025-10-20 01:41:55'),(6,4,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片3.jpg','image/jpeg',0,'抵制高价彩礼 倡树婚俗新风','2025-10-20 01:33:31','2025-10-20 01:33:31'),(7,5,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image4.png','image/png',1,'红色贴珠画 共抒爱国心','2025-10-20 01:38:48','2025-10-20 01:38:48'),(8,5,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片4.jpg','image/jpeg',0,'红色贴珠画 共抒爱国心','2025-10-20 01:38:48','2025-10-20 01:40:49'),(9,6,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image5.png','image/png',1,'绘出多彩世界凝聚社区温情','2025-10-20 01:46:52','2025-10-20 01:46:52'),(10,6,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片5.png','image/png',0,'绘出多彩世界凝聚社区温情','2025-10-20 01:46:52','2025-10-20 01:46:52'),(11,7,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片6.jpg','image/jpeg',0,'垃圾分类','2025-10-20 01:51:55','2025-10-20 01:51:55'),(12,7,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image6.png','image/png',1,'l阿吉分类','2025-10-20 01:51:55','2025-10-20 01:51:55'),(13,8,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片7.jpg','image/jpeg',0,'jiao自耦','2025-10-20 01:55:04','2025-10-20 01:55:20'),(14,8,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image7.png','image/png',1,'j饺子','2025-10-20 01:55:04','2025-10-20 01:55:20'),(15,9,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image8.png','image/png',1,'镂刻新岁，雕艺焕彩','2025-10-20 02:04:29','2025-10-20 02:04:29'),(16,9,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片8.jpg','image/jpeg',0,'镂刻新岁，雕艺焕彩','2025-10-20 02:04:29','2025-10-20 02:04:29'),(17,10,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image9.png','image/png',1,'巧手编织，扮靓生活','2025-10-20 02:09:56','2025-10-20 02:09:56'),(18,10,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片9.jpg','image/jpeg',0,'巧手编织，扮靓生活','2025-10-20 02:09:56','2025-10-20 02:09:56'),(19,11,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image10.png','image/png',1,'巧手做月饼，送出社区情','2025-10-20 02:14:53','2025-10-20 02:14:53'),(20,11,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片10.jpg','image/jpeg',0,'巧手做月饼，送出社区情','2025-10-20 02:14:53','2025-10-20 02:14:53'),(21,12,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image11.png','image/png',1,'正双姿，写未来','2025-10-20 02:19:34','2025-10-20 02:19:34'),(22,12,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片11.jpg','image/jpeg',0,'正双姿，写未来','2025-10-20 02:19:34','2025-10-20 02:19:34'),(23,13,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image12.png','image/png',1,'健康义诊进社区','2025-10-20 02:22:58','2025-10-20 02:22:58'),(24,13,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片12.jpg','image/jpeg',0,'健康义诊进社区','2025-10-20 02:22:58','2025-10-20 02:22:58'),(25,14,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image13.png','image/png',1,'老年学堂音乐课堂','2025-10-20 02:25:41','2025-10-20 02:25:41'),(26,14,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片13.jpg','image/jpeg',0,'老年学堂音乐课堂','2025-10-20 02:25:41','2025-10-20 02:25:41'),(27,15,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image14.png','image/png',1,'品元宵叙党情奔赴新征程','2025-10-20 02:28:14','2025-10-20 02:28:14'),(28,15,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片14.jpg','image/jpeg',0,'品元宵叙党情奔赴新征程','2025-10-20 02:28:14','2025-10-20 02:28:14'),(29,16,'http://1.13.158.205:9000/jiaxiang/2025/10/20/image15.png','image/png',1,'悦享夕阳老年声乐课堂','2025-10-20 02:30:41','2025-10-20 02:30:41'),(30,16,'http://1.13.158.205:9000/jiaxiang/2025/10/20/图片15.jpg','image/jpeg',0,'悦享夕阳老年声乐课堂','2025-10-20 02:30:41','2025-10-20 02:30:41');
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
INSERT INTO `article_profile` VALUES (1,NULL,1,1,'社区新动态',1,0,0,NULL,'2025-04-10 19:20:58','2025-09-25 15:32:28');
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
INSERT INTO `article_profile_content` VALUES (1,1,'本次更新包括了多项优化232','2025-04-10 19:46:16','2025-10-13 18:59:30');
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
  `honor` varchar(255) DEFAULT NULL,
  `honor_detail` text,
  `honor_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `community_honor_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community_honor`
--

LOCK TABLES `community_honor` WRITE;
/*!40000 ALTER TABLE `community_honor` DISABLE KEYS */;
INSERT INTO `community_honor` VALUES (1,1,'荣耀1','2018年，荣获“全国文明社区”称号。','http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png'),(2,1,'荣耀2','2019年，被评为“优秀基层党组织','http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png'),(3,1,'荣耀3','2020年，获得“社区治理创新奖','http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png'),(4,1,'荣耀4','2021年，荣获\"绿色环保社区\"称号。\n\n','http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png'),(5,1,'荣耀5','2022年，被评为\"最具幸福感社区\"。','http://1.13.158.205:9000/jiaxiang/2025/10/20/image1.png');
/*!40000 ALTER TABLE `community_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good_order`
--

DROP TABLE IF EXISTS `good_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `good_order` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint unsigned NOT NULL COMMENT '下单的用户id',
  `good_id` bigint unsigned NOT NULL COMMENT '购买的物品id',
  `pay_type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '支付方式 1：余额支付；2：支付宝；3：微信',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '订单状态，1：未支付；2：已支付；3：派送中 4：已送达；5：已取消；6：退款中；7：已退款',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `use_time` datetime DEFAULT NULL COMMENT '送达时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good_order`
--

LOCK TABLES `good_order` WRITE;
/*!40000 ALTER TABLE `good_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `good_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '商品名',
  `pay_value` decimal(10,2) NOT NULL COMMENT '秒杀价格',
  `actual_value` decimal(10,2) NOT NULL COMMENT '原价格',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0：普通商品， 1：秒杀商品',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1：上架， 2：下架, 3：过期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
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
  `fileName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guide_category_pk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指导清单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guide_category`
--

LOCK TABLES `guide_category` WRITE;
/*!40000 ALTER TABLE `guide_category` DISABLE KEYS */;
INSERT INTO `guide_category` VALUES (5,1,'老年人补助申请事项清单.md','1. **提交申请**符合条件的老年人需提前一个月向户口所在地的村（居）委会提出书面申请。可以由本人或委托他人提交，需提交户口簿、身份证原件和复印件，以及近期一寸彩色照片。行动不便的老人可以委托近亲属或所在敬老院、福利院代为申请‌.2. **村（居）委会初审‌**。村（居）委会初审‌：村（居）委会在收到申请后，会对申请材料进行核实，包括入户调查、邻里访问等，核实无误后签署意见并上报乡镇（街道）民政部门‌3. **乡镇（街道）复审‌**。乡镇（街道）民政部门在收到材料后进行复审，必要时会重新组织调查核实，无误后签署意见并上报县（市、区）民政局‌4. **县（市、区）民政局审批‌**。县（市、区）民政局对上报的材料进行审批，符合条件的批准享受补贴待遇，不符合条件的退回材料并说明理由‌。','老年人补助申请事项清单.md','http://1.13.158.205:9000/jiaxiang/2026/01/23/老年人补助申请事项清单.md'),(6,1,'社会救助政策宣传单-孤儿基本生活费.md','1. **保障范围及标准：**失去父母、查找不到生父母，未满18周岁的成年人，每人每月1987元。2. **申请程序：**- 申请：由孤儿监护人向户籍所在地街道办事处或镇人民政府提出申请。- 审核：街道办事处或镇人民政府应在10个工作日内，对申请人和社会散居孤儿情况进行初步核实。符合申请条件的，由申请人填写《孤儿基本生活申请表》，连同有关证明材料一并上报县级民政部门。- 审批。县级民政部门认真审查申请材料，符合条件的，在10个工作日内作出审批决定。民政部门与社会散居孤儿的监护人签订孤儿养育协议，- 资金发放。县级部门认定为符合孤儿救助条件的，次月通过涉农补贴“一本通”系统向孤儿发放孤儿基本生活费。3. **申请程序：**   个人申请→镇街审核→县级审批→资金发放。4. **需提报的材料**：   - 申请书（孤儿基本情况及其父母各自的基本情况）   - 孤儿身份证、户口本复印件，监护人身份证复印件：   - 孤儿《医学出生证明》、父母《结婚证》原件及复印件；   - 孤儿父母死亡或者失踪证明。','社会救助政策宣传单-孤儿基本生活费.md','http://1.13.158.205:9000/jiaxiang/2026/01/23/社会救助政策宣传单-孤儿基本生活费.md'),(7,1,'退役军人服务一件事.md','1. **涉及事项**   1. 退役报到   2. 自主就业一次性经济补助金的给付；    3. 自主就业职业技能培训和就业创业的指导；    4. 户口登记（退役军人恢复户口）；    5. 居民身份证申领；    6. 社会保险登记；    7. 军地养老保险关系转移接续；    8. 社会保障卡（含电子社保卡）申领；    9. 基本医疗保险参保和变更登记；    10. 基本医疗保险关系转移接续；    11. 预备役登记。2. **实施机构**   县退役军人事务局牵头，县武装部、县公安局、县司法局、县人力资源和社会保障局、县医疗保障局等部门配合。3. **适用范围**   当年度经部队批准退出现役，已办理退役手续，到嘉祥县安置的自主就业退役士兵、逐月领取退役金的退役军官和军士，以及复员军官4. **办理方式**   - 现场申请。申请人向嘉祥县政务服务中心或退役军人服务中心退役军人服务“一件事”窗口提出申请。    - 线上申请。线上在“爱山东”政务服务平台高效办成一件事专区“退役军人服务‘一件事’”模块申请。5. **申报条件**   当年度依法取得退出现役证件和退出现役行政介绍信的嘉祥县内落户安置的退役军人均可申请6. **办理流程**   1. 当年度退役军人在网上进行申报或到线下窗口进行退役报到，提交材料符合要求的，予以审核通过。   2. 办理自主就业退役士兵一次性经济补助申领登记和自主就业退役士兵教育培训报名登记事项的，由退役军人事务局现场审核办理。7. **办理地点**   嘉祥县政务服务中心（嘉祥县呈祥大道3号）主楼一楼C106退役军人服务“一件事”窗口8. **办理时间**​		工作日周一至周五上午8:30-12:00,下午13:30-17:00（冬令时，法定节假日除外) 工作日周一至周五上午8:30-12:00,下午14:00-17:30（夏令时，法定节假日除外)9. **法定办结时限**​		30个工作日10. **承诺办结时限**​		10个工作日11. **办理费用**​		免费12. **快递物流**​		社保卡和居民身份证可通过快递物流送达。','退役军人服务一件事.md','http://1.13.158.205:9000/jiaxiang/2026/01/23/退役军人服务一件事.md'),(8,1,'新生儿出生一件事.md','1. **涉及事项**   1. 《出生医学证明》办理(首签)；    2. 预防接种证办理；    3. 本县户口登记(申报出生登记)1岁以下婚内本县生育；    4. 社会保障卡申领；    5. 生育医疗费用报销（免申即享）；    6. 办理居民医保登记；    7. 科学育儿指导服务登记（免申即享）。2. **实施机构**​		嘉祥县卫生健康局、 嘉祥县公安局、 嘉祥县人力资源和社会保障局、 嘉祥县行政审批局 、嘉祥县医疗保障局3. **适用范围**​		嘉祥县内符合网上联办条件的新生儿4. **办理方式** 		线下到所属嘉祥县助产机构或政务服务中心办理；线上可通过登录“爱山东”APP“高效办成一件事”专区，或政务服务网“高效办成一件事”专区，进行网上办理。5. **申报条件** 在嘉祥县域内助产机构出生的新生儿，夫妻至少一方为我县户籍、随在嘉祥县一方父或母落户、父母婚姻关系为存续状态，已取好姓名且确定民族的婚生新生儿。6. **办理流程**   1. 现场办理：新生儿“出生一件事”办理可至嘉祥县辖区内各助产机构或政务服务中心“一件事”服务窗口提交申请，办理除“户口登记”事项外的所有事项，“户口登记”事项办理，需在新生儿落户地派出所现场办理并领取户口簿。    2. 网上办理：可通过登录“爱山东”APP“高效办成一件事”专区，或政务服务网“高效办成一件事”专区（http://www.shandong.gov.cn/jcfw），进行网上办理。群众可在两种办理方式任选其一：      1. .对有邮寄意愿的新生儿父母，网上提交申请后，由助产机构将出生医学证明（正副页）邮寄至户籍地派出所，派出所在收到出生医学证明（正副页）后，查验、拆切出生医学证明副页，同时根据联办系统推送的信息，为申请人办理落户业务（打印索引页及新生儿户籍页）。户籍地派出所通过邮寄到付形式，将新生儿出生医学证明正页、户口索引页及新生儿户籍页邮寄至新生儿父母，并将结果推送至社保、医保办理。此流程全程网办，群众缴纳两次邮费，无需跑腿；      2. .对无邮寄意愿的新生儿父母，网上提交申请后，由各助产机构妇产科医务人员、出生医学证明办理窗口人员将申请联办业务群众的出生医学证明电子版，采取微信、邮箱等方式先行推送至群众落户地派出所，群众择时携带出生医学证明（正副页）、户口簿到落户地派出所线下办理，并将结果推送至社保、医保办理。此流程无需群众缴纳邮费，跑腿1次。7. **办理地点**   - 助产机构：      1. 嘉祥县迎风路188号嘉祥县人民医院7号楼二楼东出生医学证明办理室；      1. 嘉祥县呈祥大道与嘉汇路交叉口嘉祥县妇女儿童医院二楼东出生医学证明办理室； 政务服务中心： 嘉祥县政务服务中心（嘉祥县呈祥大道3号）主楼3楼A103窗口8. **办理时间** 	现场办理“户口登记”的工作时间为：工作日周一至周五上午8:30-12:00,下午13:30-17:00(冬令时，法定节假日除外) 工作日周一至周五上午8:30-12:00,下午14:00-17:30(夏令时，法定节假日除外)9. **法定办结时限**​		32个工作日10. **承诺办结时限**​		4个工作日11. **办理费用**​		办理过程中，因邮寄材料产生的费用，按快递部门收费标准计费收取。12. **快递物流**​		纸质“出生医学证明”正副页、社保卡将通过邮寄方式送达。','新生儿出生一件事.md','http://1.13.158.205:9000/jiaxiang/2026/01/23/新生儿出生一件事.md');
/*!40000 ALTER TABLE `guide_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `cn_name` varchar(30) DEFAULT NULL COMMENT '中文名',
  `en_name` varchar(30) DEFAULT NULL COMMENT '英文名',
  `is_parent` tinyint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL COMMENT '前端路由',
  `component` varchar(100) DEFAULT NULL COMMENT '前段页面路径',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识(user:add)',
  `type` tinyint DEFAULT NULL COMMENT '菜单类型(0.目录 1.菜单 2.按钮)',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int DEFAULT '0' COMMENT '排序字段',
  `is_visible` tinyint DEFAULT NULL COMMENT '是否显示(0.隐藏 1.显示)',
  `status` tinyint DEFAULT '1' COMMENT '是否启用(0 禁用 1 启用)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proof_documents`
--

DROP TABLE IF EXISTS `proof_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proof_documents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `communityId` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `proof_documents_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proof_documents`
--

LOCK TABLES `proof_documents` WRITE;
/*!40000 ALTER TABLE `proof_documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `proof_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名(管理员 用户等)',
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_menu_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_user_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seckill_goods`
--

DROP TABLE IF EXISTS `seckill_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seckill_goods` (
  `goods_id` bigint unsigned NOT NULL COMMENT '关联的商品id',
  `stock` int NOT NULL COMMENT '库存',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `begin_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seckill_goods`
--

LOCK TABLES `seckill_goods` WRITE;
/*!40000 ALTER TABLE `seckill_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `seckill_goods` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='为人民服务详细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serve_people_info`
--

LOCK TABLES `serve_people_info` WRITE;
/*!40000 ALTER TABLE `serve_people_info` DISABLE KEYS */;
INSERT INTO `serve_people_info` VALUES (1,'暖心嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin2.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','暖心嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n',NULL,NULL,'2025-04-11 21:12:08','2025-10-20 02:42:32'),(2,'和睦嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin3.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','和睦嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-04-11 21:17:25','2025-10-20 02:42:32'),(3,'和美嘉\n                       ','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin4.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','和美嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n                       ',NULL,NULL,'2025-04-11 21:17:25','2025-10-20 02:42:32'),(4,'聆听嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin5.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','聆听嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(5,'爱上嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin6.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','爱上嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(6,'敬业嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin7.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','敬业嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(7,'邻里童乐坊','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin8.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','邻里童乐坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(8,'邻里同心坊','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin9.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','邻里同心坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(9,'邻里同行坊','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin10.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','邻里同行坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(10,'邻里同悦坊','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin11.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','邻里同悦坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-14 19:55:58','2025-10-20 02:42:32'),(11,'幸福学堂','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin12.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','幸福学堂是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。',NULL,NULL,'2025-06-15 19:47:59','2025-10-20 02:42:32'),(12,'暖心嘉','http://1.13.158.205:9000/jiaxiang/2025/10/20/weimin2.jpg','0550-1234567','工作日08:00-17:30','丰山街道社区服务中心3楼','暖心嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n','包括个体咨询、团体辅导、心理评估等。','需提前预约，每人每周限一次。','2025-07-24 16:38:16','2025-10-20 02:48:35');
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
  `name` varchar(100) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
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
INSERT INTO `staff_info` VALUES (1,'于燕\n','社区党委书记 居委会主任','http://1.13.158.205:9000/jiaxiang/2025/10/20/yuyan.png','管理社区所有小区','','2025-04-11 20:48:07','2025-10-20 02:39:45'),(2,'郭亚芳','社区书记助理 社区两委成员','http://1.13.158.205:9000/jiaxiang/2025/10/20/guoyafang.png','管理红旗花园小区','协助书记处理社区日常事务，确保社区工作的顺利进行。\n\n在书记的指导下，负责社区重要项目的推进和实施。\n\n协调社区内各部门之间的沟通，确保信息畅通。\n\n参与社区重要会议，提出建设性意见。\n\n负责社区居民的反馈收集，及时向书记汇报。','2025-04-11 20:48:57','2025-10-20 02:39:45'),(3,'郭振南','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/guozhennan.png','管理祥曾园小区','负责社区组织建设，制定组织工作计划并组织实施。\n\n组织社区党员和居民开展各类活动，增强社区凝聚力。\n\n负责社区党员的教育和管理，提高党员素质。\n\n协调社区内外部资源，推动社区组织发展。\n\n完成上级组织交办的其他任务。','2025-04-11 20:49:22','2025-10-20 02:39:45'),(4,'黄威','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/huangwei.png','管理嘉和苑西区','负责社区宣传工作，制定宣传计划并组织实施。\n\n通过多种渠道宣传社区活动和政策，提高居民知晓率。\n\n负责社区宣传材料的制作和分发，确保宣传效果。\n\n组织社区居民开展宣传活动，提升社区形象。\n\n完成上级宣传部门交办的其他任务。','2025-04-11 20:49:44','2025-10-20 02:39:45'),(5,'江婷婷','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/jiangtingting.png','管理财苑小区、碧城华园小区','负责社区纪律检查工作，确保社区工作廉洁高效。\n\n监督社区工作人员遵守纪律，防止违规行为。\n\n处理居民投诉和举报，维护社区良好秩序。\n\n定期开展廉政教育活动，提高社区工作人员廉洁意识。\n\n完成上级纪检部门交办的其他任务。','2025-04-11 20:50:07','2025-10-20 02:39:44'),(6,'蒋彦臣','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/jiangyanchen.png','管理金都花苑小区','负责社区青年工作，制定青年工作计划并组织实施。\n\n组织青年开展各类活动，促进青年成长和发展。\n\n关注青年需求，为青年提供就业创业指导和服务。\n\n引导青年参与社区建设，发挥青年积极作用。\n\n完成上级青年组织交办的其他任务。','2025-04-11 20:50:54','2025-10-20 02:39:45'),(7,'孟如','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/mengru.png.png','管理国际商贸城小区','负责社区妇女工作，制定妇女工作计划并组织实施。\n\n组织妇女开展各类活动，提升妇女素质和能力。\n\n维护妇女合法权益，关注妇女需求。\n\n引导妇女参与社区建设，发挥妇女积极作用。\n\n完成上级妇女组织交办的其他任务。','2025-04-11 20:51:18','2025-10-20 02:39:44'),(8,'张传祥','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/zhangchuanxiang.png','管理红旗花园小区','负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。','2025-04-11 20:51:45','2025-10-20 02:39:44'),(9,'赵劲松','一般社区工作人员','http://1.13.158.205:9000/jiaxiang/2025/10/20/zhaojinsong.png','管理博悦城小区','负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。','2025-04-11 20:52:11','2025-10-20 02:39:44');
/*!40000 ALTER TABLE `staff_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint DEFAULT NULL COMMENT '性别（0女 1男）',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `account_expired` tinyint(1) DEFAULT '0' COMMENT '是否过期（0.未过期 1.过期）',
  `credentials_expired` tinyint(1) DEFAULT '0' COMMENT '凭证是否过期(0 未过期 1 过期)',
  `account_locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定(0. 未锁定 1.锁定)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$cdYg9n0Mr3Ei7OYvb04A1e2IBCwNOAK9Nfl56iGD/rxLJ1GKhdZ3S','系统管理员',NULL,NULL,NULL,NULL,1,0,0,0,'2025-06-27 20:59:02','2025-07-01 14:30:43'),(2,'user','$2a$10$cdYg9n0Mr3Ei7OYvb04A1e2IBCwNOAK9Nfl56iGD/rxLJ1GKhdZ3S','普通用户',NULL,NULL,NULL,NULL,1,0,0,0,'2025-06-27 20:59:02','2025-07-01 14:30:46');
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

-- Dump completed on 2026-01-23 18:00:02
