/*
 Navicat Premium Data Transfer

 Source Server         : 我本地的
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost:3306
 Source Schema         : cjb

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : 65001

 Date: 03/01/2020 18:01:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lcn_business_t
-- ----------------------------
DROP TABLE IF EXISTS `lcn_business_t`;
CREATE TABLE `lcn_business_t`  (
  `id` int(11) NOT NULL,
  `business_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lcn_mq_msg_t
-- ----------------------------
DROP TABLE IF EXISTS `lcn_mq_msg_t`;
CREATE TABLE `lcn_mq_msg_t`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `message_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息ID',
  `message_body` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容json',
  `message_send_num` int(11) NULL DEFAULT NULL COMMENT '消息发送次数',
  `message_status` tinyint(1) NULL DEFAULT NULL COMMENT '1 发送中 2 发送完成',
  `queue_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '队列名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lcn_order_t
-- ----------------------------
DROP TABLE IF EXISTS `lcn_order_t`;
CREATE TABLE `lcn_order_t`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lcn_warehouse_t
-- ----------------------------
DROP TABLE IF EXISTS `lcn_warehouse_t`;
CREATE TABLE `lcn_warehouse_t`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


