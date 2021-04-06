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

 Date: 07/04/2021 00:40:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NULL DEFAULT NULL,
  `mid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES (19, 4, 1);
INSERT INTO `t_admin_role_menu` VALUES (20, 4, 2);
INSERT INTO `t_admin_role_menu` VALUES (21, 3, 1);
INSERT INTO `t_admin_role_menu` VALUES (22, 3, 2);
INSERT INTO `t_admin_role_menu` VALUES (23, 9, 1);
INSERT INTO `t_admin_role_menu` VALUES (24, 9, 2);
INSERT INTO `t_admin_role_menu` VALUES (121, 1, 1);
INSERT INTO `t_admin_role_menu` VALUES (122, 1, 2);
INSERT INTO `t_admin_role_menu` VALUES (123, 1, 3);
INSERT INTO `t_admin_role_menu` VALUES (124, 1, 6);
INSERT INTO `t_admin_role_menu` VALUES (125, 1, 7);
INSERT INTO `t_admin_role_menu` VALUES (126, 1, 4);
INSERT INTO `t_admin_role_menu` VALUES (127, 1, 8);
INSERT INTO `t_admin_role_menu` VALUES (128, 1, 9);
INSERT INTO `t_admin_role_menu` VALUES (129, 1, 10);
INSERT INTO `t_admin_role_menu` VALUES (130, 1, 5);
INSERT INTO `t_admin_role_menu` VALUES (188, 2, 1);
INSERT INTO `t_admin_role_menu` VALUES (189, 2, 2);
INSERT INTO `t_admin_role_menu` VALUES (190, 2, 4);
INSERT INTO `t_admin_role_menu` VALUES (191, 2, 8);
INSERT INTO `t_admin_role_menu` VALUES (192, 2, 9);
INSERT INTO `t_admin_role_menu` VALUES (193, 2, 10);

SET FOREIGN_KEY_CHECKS = 1;
