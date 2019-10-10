/*
 Navicat Premium Data Transfer

 Source Server         : gordianknot
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.56.101:3306
 Source Schema         : ds_0

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/09/2019 11:11:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction_message
-- ----------------------------
DROP TABLE IF EXISTS `transaction_message`;
CREATE TABLE `transaction_message` (
  `id` bigint(64) NOT NULL,
  `message` varchar(1000) NOT NULL COMMENT '消息內容',
  `queue` varchar(50) NOT NULL COMMENT '隊列名稱',
  `send_system` varchar(20) NOT NULL COMMENT '發送消息的系統',
  `send_count` int(4) NOT NULL DEFAULT '0' COMMENT '重復發送消息次數',
  `c_date` datetime NOT NULL COMMENT '創建時間',
  `send_date` datetime DEFAULT NULL COMMENT '最近發送消息時間',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '狀態：0等待消費  1已消費  2已死亡',
  `die_count` int(4) NOT NULL DEFAULT '0' COMMENT '死亡次數條件，由使用方決定，默認為發送10次還沒被消費則標記死亡,人工介入',
  `customer_date` datetime DEFAULT NULL COMMENT '消費時間',
  `customer_system` varchar(50) DEFAULT NULL COMMENT '消費系統',
  `die_date` datetime DEFAULT NULL COMMENT '死亡時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
