/*
 Navicat Premium Data Transfer

 Source Server         : msyql5
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3307
 Source Schema         : article_system

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 23/11/2022 00:56:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for as_article
-- ----------------------------
DROP TABLE IF EXISTS `as_article`;
CREATE TABLE `as_article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_user_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `article_is_comment` int(11) NULL DEFAULT 1,
  `article_create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `article_summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '摘要',
  `article_thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `article_is_comment`(`article_is_comment`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 568 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_article_category_ref
-- ----------------------------
DROP TABLE IF EXISTS `as_article_category_ref`;
CREATE TABLE `as_article_category_ref`  (
  `article_id` int(11) NOT NULL COMMENT '文章ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`article_id`, `category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_category
-- ----------------------------
DROP TABLE IF EXISTS `as_category`;
CREATE TABLE `as_category`  (
  `category_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `category_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000015 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_comment
-- ----------------------------
DROP TABLE IF EXISTS `as_comment`;
CREATE TABLE `as_comment`  (
  `comment_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_article_id` int(11) UNSIGNED NOT NULL COMMENT '文章ID',
  `comment_author_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人名称',
  `comment_author_avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人头像',
  `comment_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `comment_create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_user
-- ----------------------------
DROP TABLE IF EXISTS `as_user`;
CREATE TABLE `as_user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_pass` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `user_phone_number` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户电话名',
  `user_role` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'user' COMMENT '用户角色',
  `user_avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
