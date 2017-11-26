-- ----------------------------
-- Table structure for tdeduction_item
-- ----------------------------
DROP TABLE IF EXISTS `tdeduction_item`;
CREATE TABLE `tdeduction_item` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tcharge
-- ----------------------------
DROP TABLE IF EXISTS `tcharge`;
CREATE TABLE `tcharge` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `prj_num` varchar(64) NOT NULL COMMENT '项目代码',
  `prj_name` varchar(256) NOT NULL COMMENT '项目名称',
  `prj_address` varchar(256) NOT NULL COMMENT '项目地址',
  `report_staff` varchar(256) NOT NULL COMMENT '申报人',
  `report_entity` varchar(256) NOT NULL COMMENT '申报单位',
  `report_date` datetime NULL COMMENT '申报时间',
  `cal_staff` varchar(256) NOT NULL COMMENT '测算人',
  `cal_date` datetime DEFAULT NULL COMMENT '测算时间',
  `approve_staff` varchar(256) NOT NULL COMMENT '审核人',
  `approve_date` datetime NULL COMMENT '审核时间',
  `confirm_staff` varchar(256) NOT NULL COMMENT '确认人',
  `confirm_date` datetime NULL COMMENT '确认时间',
  `pay_ticket_path` varchar(128)  NULL COMMENT '付款凭证保存路径',
  `cal_money` decimal(10,2) NULL COMMENT '测算金额',
  `pay_money` decimal(10,2) NULL COMMENT '付款金额',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topinion_book
-- ----------------------------
DROP TABLE IF EXISTS `topinion_book`;
CREATE TABLE `topinion_book` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `prj_num` varchar(64) NOT NULL COMMENT '项目代码',
  `document_no` varchar(8) NOT NULL COMMENT '文件编号',
  `document_type` varchar(8) NOT NULL COMMENT '文件类型',
  `path` varchar(128) NOT NULL COMMENT '保存路径',
  `document_date` datetime NOT NULL COMMENT '文档日期',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topinion_book_item
-- ----------------------------
DROP TABLE IF EXISTS `topinion_book_item`;
CREATE TABLE `topinion_book_item` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `doc_id` varchar(64) NOT NULL COMMENT '文档代码',
  `item_id` varchar(64) NOT NULL COMMENT '抵扣项代码',
  `area` decimal(10,2) NOT NULL COMMENT '面积（平米）',
  `money` decimal(10,2) NOT NULL COMMENT '金额（元）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  UNIQUE(`doc_id`, `item_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tdeduction_doc
-- ----------------------------
DROP TABLE IF EXISTS `tdeduction_doc`;
CREATE TABLE `tdeduction_doc` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `charge_id` int(11) NOT NULL COMMENT '征收代码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `prj_num` varchar(64) NOT NULL COMMENT '项目代码',
  `document_no` varchar(8) NOT NULL COMMENT '文件编号',
  `document_type` varchar(8) NOT NULL COMMENT '文件类型',
  `path` varchar(128) NOT NULL COMMENT '保存路径',
  `document_date` datetime NOT NULL COMMENT '文档日期',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  UNIQUE(`name`, prj_num),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tdeduction_doc_item
-- ----------------------------
DROP TABLE IF EXISTS `tdeduction_doc_item`;
CREATE TABLE `tdeduction_doc_item` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `doc_id` varchar(64) NOT NULL COMMENT '文档代码',
  `item_id` varchar(64) NOT NULL COMMENT '抵扣项代码',
  `area` decimal(10,2) NOT NULL COMMENT '面积（平米）',
  `money` decimal(10,2) NOT NULL COMMENT '金额（元）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  UNIQUE(`doc_id`, `item_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tproject_license
-- ----------------------------
DROP TABLE IF EXISTS `tproject_license`;
CREATE TABLE `tproject_license` (
  `id` int(11) NOT NULL auto_increment COMMENT '代码',
  `charge_id` int(11) NOT NULL COMMENT '征收代码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `prj_num` varchar(64) NOT NULL COMMENT '项目代码',
  `document_no` varchar(8) NOT NULL COMMENT '文件编号',
  `path` varchar(128) NOT NULL COMMENT '保存路径',
  `document_date` datetime NOT NULL COMMENT '文档日期',
  `up_area` decimal(10,2) NOT NULL COMMENT '地上面积（平米）',
  `down_area` decimal(10,2) NOT NULL COMMENT '地下面积（平米）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  UNIQUE(`name`, prj_num),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;