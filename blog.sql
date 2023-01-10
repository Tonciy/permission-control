/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 10/01/2023 17:15:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_permission`;
CREATE TABLE `blog_permission`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `api_identify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '此API的唯一标识符',
  `api_method_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '此API接口方法/请求的类型',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '此API接口的请求地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_permission
-- ----------------------------
INSERT INTO `blog_permission` VALUES ('1607655705689583616', 'getMethod', '获取', 'OneController-ONE', 'GET', '/one');
INSERT INTO `blog_permission` VALUES ('1607656390900445184', 'postMethod', '新增', 'OneController-TWO', 'POST', '/one');
INSERT INTO `blog_permission` VALUES ('1607656390908833792', 'putMethod', '修改', 'OneController-THREE', 'PUT', '/one');
INSERT INTO `blog_permission` VALUES ('1607656390913028096', 'deleteMethod', '删除', 'OneController-FOUR', 'DELETE', '/one');
INSERT INTO `blog_permission` VALUES ('1607656390921416704', 'getMethod', '获取', 'TwoController-ONE', 'GET', '/two');
INSERT INTO `blog_permission` VALUES ('1607656390929805312', 'postMethod', '新增', 'TwoController-TWO', 'POST', '/two');
INSERT INTO `blog_permission` VALUES ('1607656390938193920', 'putMethod', '修改', 'TwoController-THREE', 'PUT', '/two');
INSERT INTO `blog_permission` VALUES ('1607656390946582528', 'deleteMethod', '删除', 'TwoController-FOUR', 'DELETE', '/two');

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_role
-- ----------------------------
INSERT INTO `blog_role` VALUES ('1607654976895700992', 'Admin', '超级管理员');
INSERT INTO `blog_role` VALUES ('1607655382203760640', 'Common', '普通用户');

-- ----------------------------
-- Table structure for blog_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_permission`;
CREATE TABLE `blog_role_permission`  (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_role_permission
-- ----------------------------
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607655705689583616');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390900445184');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390908833792');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390913028096');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390921416704');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390929805312');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390938193920');
INSERT INTO `blog_role_permission` VALUES ('1607654976895700992', '1607656390946582528');
INSERT INTO `blog_role_permission` VALUES ('1607655382203760640', '1607655705689583616');
INSERT INTO `blog_role_permission` VALUES ('1607655382203760640', '1607656390900445184');
INSERT INTO `blog_role_permission` VALUES ('1607655382203760640', '1607656390908833792');
INSERT INTO `blog_role_permission` VALUES ('1607655382203760640', '1607656390913028096');

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES ('1607653708047421440', 'zhangsan', '123456');
INSERT INTO `blog_user` VALUES ('1607654823249911808', 'lisi', '123456');

-- ----------------------------
-- Table structure for blog_user_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_role`;
CREATE TABLE `blog_user_role`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user_role
-- ----------------------------
INSERT INTO `blog_user_role` VALUES ('1607653708047421440', '1607654976895700992');
INSERT INTO `blog_user_role` VALUES ('1607654823249911808', '1607655382203760640');

SET FOREIGN_KEY_CHECKS = 1;
