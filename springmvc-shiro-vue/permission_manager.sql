/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : permission_manager

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 05/07/2021 15:38:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(0) NULL DEFAULT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `COMPONENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应路由组件component',
  `ORDER_NUM` double(20, 0) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `module` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '首页管理', 'home', 1, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES (2, 0, '权限管理', 'permission', 1, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES (3, 1, '首页', 'home', 1, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES (4, 2, '用户管理', 'user-manage', 1, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES (5, 2, '角色管理', 'role-manage', 2, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES (6, 2, '菜单管理', 'menu-manage', 3, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '1');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `ID` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(0) NOT NULL,
  `MENU_ID` bigint(0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1, 2);
INSERT INTO `t_role_menu` VALUES (3, 1, 3);
INSERT INTO `t_role_menu` VALUES (4, 1, 4);
INSERT INTO `t_role_menu` VALUES (5, 1, 5);
INSERT INTO `t_role_menu` VALUES (6, 1, 6);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最近访问时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, NULL, 'admin', 'admin', '2974@qq.com', '18683446913', '1', '2021-07-03 11:26:21', '2021-07-03 11:26:21', '2021-07-03 11:26:21');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `ID` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(0) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
