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

 Date: 07/04/2021 00:40:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user_role`;
CREATE TABLE `t_admin_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_operator_role_operator_1`(`uid`) USING BTREE,
  INDEX `fk_operator_role_role_1`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_user_role
-- ----------------------------
INSERT INTO `t_admin_user_role` VALUES (40, 24, 2);
INSERT INTO `t_admin_user_role` VALUES (63, 3, 2);
INSERT INTO `t_admin_user_role` VALUES (64, 1, 1);
INSERT INTO `t_admin_user_role` VALUES (67, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
