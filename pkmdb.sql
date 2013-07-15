/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : pkmdb

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2013-05-23 19:05:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '111111', 'a');

-- ----------------------------
-- Table structure for `sh_dairy`
-- ----------------------------
DROP TABLE IF EXISTS `sh_dairy`;
CREATE TABLE `sh_dairy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `time` varchar(50) NOT NULL,
  `etime` bigint(11) NOT NULL,
  `check` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sh_dairy
-- ----------------------------
INSERT INTO `sh_dairy` VALUES ('1', '1', '测试共享笔记一', '这是测试共享笔记一，已经通过审核，可以供用户查看', '2013-5-16 / 17:15', '1368695459449', '1');
INSERT INTO `sh_dairy` VALUES ('2', '1', '测试共享笔记二', '测试共享笔记二，。wesgasdgsdgsdgasgasgdasdgasgasdgasgsg', '2013-5-16 / 17:17', '1368695469449', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `dairy_count` int(11) NOT NULL DEFAULT '0',
  `sh_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '123', '0', '0');

-- ----------------------------
-- Table structure for `user_dairy`
-- ----------------------------
DROP TABLE IF EXISTS `user_dairy`;
CREATE TABLE `user_dairy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `target` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `etime` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_dairy
-- ----------------------------
