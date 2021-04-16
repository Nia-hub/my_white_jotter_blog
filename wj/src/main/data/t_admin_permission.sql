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

 Date: 07/04/2021 00:41:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_permission`;
CREATE TABLE `t_admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_permission
-- ----------------------------
INSERT INTO `t_admin_permission` VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `t_admin_permission` VALUES (2, 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `t_admin_permission` VALUES (3, 'content_management', '内容管理', '/api/admin/content');

SET FOREIGN_KEY_CHECKS = 1;
