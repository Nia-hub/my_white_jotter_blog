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

 Date: 07/04/2021 00:40:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_permission`;
CREATE TABLE `t_admin_role_permission`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rid` int(20) NULL DEFAULT NULL,
  `pid` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_permission_role_1`(`rid`) USING BTREE,
  INDEX `fk_role_permission_permission_1`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role_permission
-- ----------------------------
INSERT INTO `t_admin_role_permission` VALUES (83, 5, 3);
INSERT INTO `t_admin_role_permission` VALUES (108, 1, 1);
INSERT INTO `t_admin_role_permission` VALUES (109, 1, 2);
INSERT INTO `t_admin_role_permission` VALUES (110, 1, 3);
INSERT INTO `t_admin_role_permission` VALUES (139, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
