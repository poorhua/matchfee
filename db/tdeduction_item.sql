/*
Navicat MySQL Data Transfer

Source Server         : jeesite
Source Server Version : 50613
Source Host           : ::1:3306
Source Database       : matchfee

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2017-12-04 23:07:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tdeduction_item
-- ----------------------------
DROP TABLE IF EXISTS `tdeduction_item`;
CREATE TABLE `tdeduction_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdeduction_item
-- ----------------------------
INSERT INTO `tdeduction_item` VALUES ('1', '幼儿园', '1', '2017-12-04 20:41:07', '1', '2017-12-04 20:41:07', '', '0');
INSERT INTO `tdeduction_item` VALUES ('2', '社区卫生服务站', '1', '2017-12-04 20:41:20', '1', '2017-12-04 20:41:20', '', '0');
INSERT INTO `tdeduction_item` VALUES ('3', '文化活动用房', '1', '2017-12-04 20:41:31', '1', '2017-12-04 20:41:31', '', '0');
INSERT INTO `tdeduction_item` VALUES ('4', '居家养老服务用房', '1', '2017-12-04 23:05:41', '1', '2017-12-04 23:05:41', '', '0');
INSERT INTO `tdeduction_item` VALUES ('5', '物业管理用房', '1', '2017-12-04 23:05:56', '1', '2017-12-04 23:05:56', '', '0');
INSERT INTO `tdeduction_item` VALUES ('6', '社居委用房', '1', '2017-12-04 23:06:11', '1', '2017-12-04 23:06:11', '', '0');
INSERT INTO `tdeduction_item` VALUES ('7', '公厕', '1', '2017-12-04 23:06:22', '1', '2017-12-04 23:06:22', '', '0');
INSERT INTO `tdeduction_item` VALUES ('8', '自行车停车库', '1', '2017-12-04 23:07:25', '1', '2017-12-04 23:07:25', '', '0');
