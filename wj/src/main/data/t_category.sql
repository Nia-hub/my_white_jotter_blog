/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50610
 Source Host           : localhost:3306
 Source Schema         : white_jotter

 Target Server Type    : MySQL
 Target Server Version : 50610
 File Encoding         : 65001

 Date: 05/04/2021 00:41:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '文学');
INSERT INTO `t_category` VALUES (2, '流行');
INSERT INTO `t_category` VALUES (3, '文化');
INSERT INTO `t_category` VALUES (4, '生活');
INSERT INTO `t_category` VALUES (5, '经管');
INSERT INTO `t_category` VALUES (6, '科技');

SET FOREIGN_KEY_CHECKS = 1;
