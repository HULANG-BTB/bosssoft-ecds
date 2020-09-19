CREATE DATABASE  IF NOT EXISTS `ecds_financial` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ecds_financial`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 39.97.123.56    Database: ecds_financial
-- ------------------------------------------------------
-- Server version	5.7.30

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
-- Table structure for table `uaa_print_template`
--

DROP TABLE IF EXISTS `uaa_print_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uaa_print_template` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_rgn_code` varchar(2) DEFAULT NULL COMMENT '区划编码',
  `f_sort_id` varchar(2) DEFAULT NULL COMMENT '种类编码',
  `f_type_id` varchar(2) DEFAULT NULL COMMENT '分类编码',
  `f_agen_id_code` varchar(50) DEFAULT NULL COMMENT '单位识别码',
  `f_place_id` varchar(32) DEFAULT NULL COMMENT '开票点ID',
  `f_name` varchar(200) DEFAULT NULL COMMENT '模板名称',
  `f_template` text COMMENT '模板文本',
  `f_memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `f_priority` varchar(32) DEFAULT NULL COMMENT '优先级，默认0。1为默认模板',
  `f_version` bigint(11) DEFAULT NULL COMMENT '乐观锁',
  `f_operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `f_operator_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `f_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `f_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='打印模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaa_print_template`
--

LOCK TABLES `uaa_print_template` WRITE;
/*!40000 ALTER TABLE `uaa_print_template` DISABLE KEYS */;
INSERT INTO `uaa_print_template` VALUES (5,'12','56','34',NULL,NULL,'一张非税票据的模板','<!DOCTYPE html>\r\n<html lang=\"en\">\r\n\r\n<head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n    <title>Title</title>\r\n</head>\r\n\r\n<body style=\"font-family: SimSun\">\r\n    <table style=\"border-collapse:collapse;\">\r\n        <tr style=\"height: 0px;\">\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 110px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 130px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"9\" rowspan=\"1\" style=\"text-align: center;font-size: 20px;height: 35px;\" valign=\"center\">中央非税收入统一票据（电子）</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">票据代码：</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.billCode)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">校验码：</td>\r\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checkCode)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">票据号码：</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.serialCode)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">缴款人：</td>\r\n            <td colspan=\"5\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payerName)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">开票日期：</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.date)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">项目编码</td>\r\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">项目名称</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">单位</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">数量</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">标准</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">金额（元）</td>\r\n        </tr>\r\n        <#list billDTO.items as item>\r\n            <tr>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">${(item.itemCode)!\' \'}</td>\r\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.itemName)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.units)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.quantity)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.standardName)!\' \'}</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.amount)!\' \'}</td>\r\n            </tr>\r\n        </#list>\r\n        <tr>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（小写）</td>\r\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmount)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（大写）</td>\r\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmountCapital)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">备注：</td>\r\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.remark)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">附加说明：</td>\r\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.addition)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">收款单位（公章）：</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.agenName)!\' \'}</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">复核人：</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checker)!\' \'}</td>\r\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">收款人（公章）：</td>\r\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payee)!\' \'}</td>\r\n        </tr>\r\n    </table>\r\n\r\n</body>\r\n\r\n</html>','备注',NULL,0,NULL,NULL,NULL,'2020-08-25 06:23:33'),(14,'45','98','67',NULL,NULL,'一个小模版啦','<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF­8\"/>\r\n    <title>Title</title>\r\n</head>\r\n<body style=\"font-family: SimSun\">\r\n<div>\r\n    hello\r\n</div>\r\n</body>\r\n</html>','一个小模版啦',NULL,0,NULL,NULL,NULL,'2020-08-26 08:39:44'),(16,'22','22','22',NULL,NULL,'Excel模板','<!DOCTYPE html>\n<html lang=\"en\">\n\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n    <title>Title</title>\n</head>\n\n<body style=\"font-family: SimSun\">\n    <table style=\"border-collapse:collapse;margin: 0 auto;\">\n        <tr style=\"height: 0px;\">\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 110px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 130px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n        </tr>\n        <tr>\n            <td colspan=\"9\" rowspan=\"1\" style=\"text-align: center;font-size: 20px;height: 35px;\" valign=\"center\">中央非税收入统一票据（电子）</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">票据代码：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.billCode)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">校验码：</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checkCode)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">票据号码：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.serialCode)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">缴款人：</td>\n            <td colspan=\"5\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payerName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">开票日期：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.date)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">项目编码</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">项目名称</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">单位</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">数量</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">标准</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">金额（元）</td>\n        </tr>\n        <#list billDTO.items as item>\n            <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">${(item.itemCode)!\' \'}</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.itemName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.units)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.quantity)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.standardName)!\' \'}</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.amount)!\' \'}</td>\n            </tr>\n        </#list>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（小写）</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmount)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（大写）</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmountCapital)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">备注：</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.remark)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">附加说明：</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.addition)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">收款单位（公章）：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.agenName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">复核人：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checker)!\' \'}</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">收款人（公章）：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payee)!\' \'}</td>\n        </tr>\n    </table>\n\n</body>\n\n</html>','Excel模板',NULL,0,NULL,NULL,NULL,NULL),(32,'01','02','16',NULL,NULL,'中央非税收入统一票据','<!DOCTYPE html>\n<html lang=\"en\">\n\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n    <title>Title</title>\n</head>\n\n<body style=\"font-family: SimSun\">\n    <table style=\"border-collapse:collapse;margin: 0 auto;\">\n        <tr style=\"height: 0px;\">\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 110px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 130px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left; width: 95px;\"></td>\n        </tr>\n        <tr>\n            <td colspan=\"9\" rowspan=\"1\" style=\"text-align: center;font-size: 20px;height: 35px;\" valign=\"center\">中央非税收入统一票据（电子）</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">票据代码：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.billCode)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">校验码：</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checkCode)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">票据号码：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.serialCode)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">缴款人：</td>\n            <td colspan=\"5\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payerName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">开票日期：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.date)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">项目编码</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">项目名称</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">单位</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">数量</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">标准</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">金额（元）</td>\n        </tr>\n        <#list billDTO.items as item>\n            <tr>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">${(item.itemCode)!\' \'}</td>\n            <td colspan=\"3\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.itemName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.units)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.quantity)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.standardName)!\' \'}</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: center;font-size: 16px;border:2px solid black;\" valign=\"center\">${(item.amount)!\' \'}</td>\n            </tr>\n        </#list>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（小写）</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmount)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">金额合计（大写）</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.totalAmountCapital)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">备注：</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.remark)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;border:2px solid black;\" valign=\"center\">附加说明：</td>\n            <td colspan=\"7\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;border:2px solid black;\" valign=\"center\">${(billDTO.addition)!\' \'}</td>\n        </tr>\n        <tr>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;height: 35px;\" valign=\"center\">收款单位（公章）：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.agenName)!\' \'}</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">复核人：</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.checker)!\' \'}</td>\n            <td colspan=\"2\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">收款人（公章）：</td>\n            <td colspan=\"1\" rowspan=\"1\" style=\"text-align: left;font-size: 16px;\" valign=\"center\">${(billDTO.payee)!\' \'}</td>\n        </tr>\n    </table>\n\n</body>\n\n</html>','正式的模板文件，不要删除了！！',NULL,0,NULL,NULL,'2020-08-24 11:54:24','2020-08-24 11:54:24'),(35,'01','02','16',NULL,NULL,'红色的非税票据','<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n    <title>Title</title>\r\n    <style>\r\n        .color{\r\n            color: #d32a0f;\r\n        }\r\n        .table-border{\r\n            border-color: #d32a0f;\r\n            border-style: solid;\r\n            border-width: medium;\r\n            border-collapse: collapse;\r\n            width: 100%;\r\n        }\r\n        .title{\r\n            text-align: center;\r\n            font-size: 30px;\r\n        }\r\n        .col1{\r\n            width: 20%;\r\n            height: 5px;\r\n            display: inline-block;\r\n        }\r\n        td{\r\n            border-color: #d32a0f;\r\n        }\r\n    </style>\r\n</head>\r\n<body style=\"font-family: SimSun\">\r\n<div class=\"color\">\r\n    <p class=\"title\">中央非税收入统一票据（电子）</p>\r\n    <table width=\"100%\">\r\n        <tr>\r\n            <td width=\"25%\">票据代码：${(billDTO.billCode)!\' \'}</td>\r\n            <td width=\"50%\">校验码：${(billDTO.checkCode)!\' \'}</td>\r\n            <td width=\"25%\">票据号码：${(billDTO.serialCode)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td width=\"75%\" colspan=\"2\">交款人：${(billDTO.payerName)!\'\'}</td>\r\n            <td width=\"25%\">开票日期：${(billDTO.date)!\' \'}</td>\r\n        </tr>\r\n    </table>\r\n</div>\r\n<div class=\"color\">\r\n    <table border=\"1\" cellpadding=\"10\" cellspacing=\"0\" class=\"table-border\">\r\n        <tr align=\"center\" >\r\n            <td width=\"15%\">项目编码</td>\r\n            <td width=\"37%\">项目名称</td>\r\n            <td width=\"8%\">单位</td>\r\n            <td width=\"10%\">数量</td>\r\n            <td width=\"10%\">标准</td>\r\n            <td width=\"20%\">金 额（元）</td>\r\n        </tr>\r\n        <#if (billDTO.items)??>\r\n            <#list billDTO.items as item>\r\n                <tr>\r\n                    <td>${(item.itemCode)!\' \'}</td>\r\n                    <td>${(item.itemName)!\' \'}</td>\r\n                    <td>${(item.units)!\' \'}</td>\r\n                    <td>${(item.quantity)!\' \'}</td>\r\n                    <td>${(item.standardName)!\' \'}</td>\r\n                    <td>${(item.amount)!\' \'}</td>\r\n                </tr>\r\n            </#list>\r\n            <#if (billDTO.items?size) < 5 >\r\n                <#list 1..5-(billDTO.items?size) as i>\r\n                    <tr>\r\n                        <td>&nbsp;</td>\r\n                        <td>&nbsp;</td>\r\n                        <td>&nbsp;</td>\r\n                        <td>&nbsp;</td>\r\n                        <td>&nbsp;</td>\r\n                        <td>&nbsp;</td>\r\n                    </tr>\r\n                </#list>\r\n            </#if>\r\n        <#else>\r\n            <#list 1..5 as i>\r\n                <tr>\r\n                    <td>&nbsp;</td>\r\n                    <td>&nbsp;</td>\r\n                    <td>&nbsp;</td>\r\n                    <td>&nbsp;</td>\r\n                    <td>&nbsp;</td>\r\n                    <td>&nbsp;</td>\r\n                </tr>\r\n            </#list>\r\n        </#if>\r\n        <tr>\r\n            <td colspan=\"5\">金额合计（小写）${(billDTO.totalAmount)!\' \'}</td>\r\n            <td></td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"6\">金额合计（大写）${(billDTO.totalAmountCapital)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"6\">备&nbsp;&nbsp;&nbsp;注${(billDTO.remark)!\' \'}</td>\r\n        </tr>\r\n        <tr>\r\n            <td colspan=\"6\">附加说明${(billDTO.addition)!\' \'}</td>\r\n        </tr>\r\n    </table>\r\n</div>\r\n<div class=\"color\">\r\n    <table width=\"100%\">\r\n        <tr>\r\n            <td width=\"33%\">收款单位（公章）：${(billDTO.agenName)!\' \'}</td>\r\n            <td width=\"33%\">复核人：${(billDTO.checker)!\' \'}</td>\r\n            <td width=\"33%\">收款人（盖章）：${(billDTO.payee)!\' \'}</td>\r\n        </tr>\r\n    </table>\r\n</div>\r\n</body>\r\n</html>','红色的非税票据',NULL,0,NULL,NULL,'2020-08-25 01:17:37','2020-08-25 01:55:53');
/*!40000 ALTER TABLE `uaa_print_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uba_template_default`
--

DROP TABLE IF EXISTS `uba_template_default`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uba_template_default` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_billcode` varchar(8) DEFAULT NULL COMMENT '6位的票据代码（前6位，没有年度编码）',
  `f_agen_id_code` varchar(50) DEFAULT NULL COMMENT '单位识别码',
  `f_type` varchar(10) DEFAULT NULL COMMENT '默认模板的类型。打印模板是"print"',
  `f_default_id` bigint(20) DEFAULT NULL COMMENT '储存模板表的主键，比如打印模板的id',
  `f_version` bigint(11) DEFAULT NULL COMMENT '乐观锁',
  `f_operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `f_operator_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `f_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `f_update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='默认模板表，保存默认的模板编号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uba_template_default`
--

LOCK TABLES `uba_template_default` WRITE;
/*!40000 ALTER TABLE `uba_template_default` DISABLE KEYS */;
INSERT INTO `uba_template_default` VALUES (3,'123456',NULL,'print',5,NULL,NULL,NULL,'2020-08-24 12:26:26','2020-08-24 12:26:26'),(4,'011602',NULL,'print',32,NULL,NULL,NULL,'2020-08-25 06:21:08','2020-08-25 06:21:08'),(5,'456798',NULL,'print',14,NULL,NULL,NULL,'2020-08-25 06:16:28','2020-08-25 06:16:28');
/*!40000 ALTER TABLE `uba_template_default` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-29 19:52:47