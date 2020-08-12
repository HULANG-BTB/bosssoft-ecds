/*
Navicat MySQL Data Transfer

Source Server         : nontax_wcy
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nontax_wzzzc

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-08-10 16:02:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fab_agen_bill
-- ----------------------------
DROP TABLE IF EXISTS `fab_agen_bill`;
CREATE TABLE `fab_agen_bill` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '单位票据表_主键',
  `f_bill_code` varchar(18) NOT NULL COMMENT '票据编码',
  `f_bill_precode` varchar(8) NOT NULL COMMENT '票据代码',
  `f_bill_id` varchar(10) NOT NULL COMMENT '票据ID',
  `f_warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `f_bill_name` varchar(64) NOT NULL COMMENT '票据名称',
  `f_eff_date` datetime NOT NULL COMMENT '生效日期（默认为第二天）',
  `f_exp_date` datetime NOT NULL COMMENT '失效日期（默认为1年）',
  `f_oper_id` bigint(20) unsigned NOT NULL COMMENT '经办人ID',
  `f_operator` varchar(200) NOT NULL COMMENT '经办人姓名',
  `f_ope_date` datetime NOT NULL COMMENT '经办日期',
  `f_is_return` int(2) NOT NULL DEFAULT '0' COMMENT '是否退票',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_version` int(11) NOT NULL COMMENT '版本号（乐观锁）',
  `f_rgn_code` varchar(2) NOT NULL COMMENT '区划编码',
  `f_hx_status` int(2) NOT NULL DEFAULT '0' COMMENT '核销状态（0未核销1需要核销2不需核销）',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fab_agen_bill
-- ----------------------------

-- ----------------------------
-- Table structure for fab_finan_bill
-- ----------------------------
DROP TABLE IF EXISTS `fab_finan_bill`;
CREATE TABLE `fab_finan_bill` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '财政票据表_主键',
  `f_bill_code` varchar(18) NOT NULL COMMENT '票据编码（18位）',
  `f_bill_precode` varchar(8) NOT NULL COMMENT '票据代码（8位）',
  `f_bill_id` varchar(10) NOT NULL COMMENT '票据ID（10）',
  `f_warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `f_bill_name` varchar(64) NOT NULL COMMENT '票据名称',
  `f_agen_code` bigint(20) unsigned DEFAULT NULL COMMENT '单位编码',
  `f_agen_name` varchar(200) DEFAULT NULL COMMENT '单位名称',
  `f_eff_date` datetime NOT NULL COMMENT '生效日期',
  `f_exp_date` datetime NOT NULL COMMENT '失效日期',
  `f_oper_id` bigint(20) unsigned NOT NULL COMMENT '经办人ID',
  `f_operator` varchar(32) NOT NULL COMMENT '经办人',
  `f_ope_date` datetime NOT NULL COMMENT '经办日期',
  `f_is_putout` int(2) NOT NULL DEFAULT '0' COMMENT '是否发放',
  `f_is_return` int(2) NOT NULL DEFAULT '0' COMMENT '是否退票',
  `f_is_invalid` int(2) NOT NULL DEFAULT '0' COMMENT '是否作废',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_version` int(11) NOT NULL COMMENT '版本号（乐观锁）',
  `f_rgn_code` varchar(2) NOT NULL COMMENT '区划编码',
  `f_hx_status` int(2) NOT NULL DEFAULT '0' COMMENT '核销状态（0未核销1需要核销2不需核销）',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fab_finan_bill
-- ----------------------------

-- ----------------------------
-- Table structure for fab_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `fab_warehouse`;
CREATE TABLE `fab_warehouse` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '仓库表_主键',
  `f_warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `f_warehouse_name` varchar(64) NOT NULL COMMENT '仓库name',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fab_warehouse
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_check_record
-- ----------------------------
DROP TABLE IF EXISTS `fbe_check_record`;
CREATE TABLE `fbe_check_record` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '查验记录表_主键',
  `f_check_id` bigint(20) unsigned NOT NULL COMMENT '查验业务ID',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_check_type` varchar(32) NOT NULL COMMENT '查验类别',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `f_operator` varchar(32) NOT NULL COMMENT '操作者（查验人）',
  `f_bill_code` varchar(18) NOT NULL COMMENT '票据编码',
  `f_result` int(2) NOT NULL COMMENT '结果（0伪1真）',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除（0未删1删除）',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_check_record
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_financeapply_change
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_financeapply_change`;
CREATE TABLE `fbe_stock_financeapply_change` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '申领表_主键',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '单号',
  `f_rgn_code` varchar(2) NOT NULL COMMENT '区域编码',
  `f_code` bigint(20) unsigned NOT NULL COMMENT '单位编码',
  `f_name` varchar(200) NOT NULL COMMENT '单位名称',
  `f_kind_name` varchar(32) NOT NULL COMMENT '单位类型',
  `f_bill_code` varchar(8) NOT NULL COMMENT '票据代码',
  `f_batch_num` int(9) NOT NULL COMMENT '批次数量',
  `f_link_man` varchar(32) NOT NULL COMMENT '联系人',
  `f_link_tel` varchar(200) NOT NULL COMMENT '联系方式',
  `f_link_addr` varchar(200) NOT NULL COMMENT '联系地址',
  `f_author` varchar(32) NOT NULL COMMENT '编制人',
  `f_memo` varchar(200) NOT NULL DEFAULT '无' COMMENT '备注',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_abstract` varchar(200) NOT NULL DEFAULT '无' COMMENT '摘要',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `f_change_date` datetime DEFAULT NULL COMMENT '审核日期',
  `f_change_man` varchar(32) DEFAULT NULL COMMENT '审核人',
  `f_change_situ` varchar(200) DEFAULT NULL COMMENT '审核意见',
  `f_status` int(2) NOT NULL DEFAULT '0' COMMENT '状态（入库与否）',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除（0未删1删除）',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_financeapply_change
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_in
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_in`;
CREATE TABLE `fbe_stock_in` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '票据入库表_主键',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '单号（业务单号）',
  `f_warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `f_author` varchar(32) NOT NULL COMMENT '编制人',
  `f_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日期',
  `f_memo` varchar(200) NOT NULL DEFAULT '无' COMMENT '备注',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_bill_source` varchar(200) NOT NULL COMMENT '票据来源',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间（初始等于创建时间）',
  `f_change_state` int(2) NOT NULL DEFAULT '0' COMMENT '审核状态（0未审核1通过2退回）',
  `f_change_date` datetime DEFAULT NULL COMMENT '审核日期',
  `f_change_man` varchar(32) DEFAULT NULL COMMENT '审核人',
  `f_change_situ` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `f_status` int(2) NOT NULL DEFAULT '0' COMMENT '状态（入库与否）',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除（0未删1删除）',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_in
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_in_change
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_in_change`;
CREATE TABLE `fbe_stock_in_change` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '入库变动表_主键',
  `f_buss_id` bigint(20) unsigned NOT NULL COMMENT '业务ID',
  `f_change_state` int(2) NOT NULL COMMENT '变更状态',
  `f_change_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '变更日期',
  `f_change_man` varchar(32) NOT NULL COMMENT '变更人',
  `f_change_situ` varchar(200) NOT NULL DEFAULT '无' COMMENT '变更情况',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_altercode` int(2) NOT NULL COMMENT '1新增　2修改   3 删除',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_in_change
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_in_item
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_in_item`;
CREATE TABLE `fbe_stock_in_item` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '票据入库明细表_主键',
  `f_pid` bigint(20) unsigned NOT NULL COMMENT '入库表id',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '序号',
  `f_bill_code` varchar(8) NOT NULL COMMENT '票据代码（8位）',
  `f_bill_name` varchar(200) NOT NULL COMMENT '票据名称',
  `f_number` int(9) NOT NULL COMMENT '数量',
  `f_bill_no1` varchar(10) NOT NULL COMMENT '起始号',
  `f_bill_no2` varchar(10) NOT NULL COMMENT '终止号',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除（0未删1删除）',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_in_item
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_outnotice
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_outnotice`;
CREATE TABLE `fbe_stock_outnotice` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '业务单号',
  `f_rgn_code` varchar(2) NOT NULL COMMENT '区划编码',
  `f_warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `f_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请日期',
  `f_use_man` varchar(64) NOT NULL COMMENT '领用人',
  `f_link_tel` varchar(32) NOT NULL COMMENT '联系电话',
  `f_link_address` varchar(200) NOT NULL COMMENT '联系地址',
  `f_author` varchar(64) NOT NULL COMMENT '编制人',
  `f_memo` varchar(200) NOT NULL DEFAULT '无' COMMENT '备注',
  `f_version` tinyint(1) NOT NULL COMMENT '版本号',
  `f_abstact` varchar(200) NOT NULL DEFAULT '无' COMMENT '摘要',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除(0未删除1删除)',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_outnotice
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_outnotice_change
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_outnotice_change`;
CREATE TABLE `fbe_stock_outnotice_change` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_buss_id` bigint(20) unsigned NOT NULL COMMENT '业务ID',
  `f_change_state` int(2) NOT NULL COMMENT '变更状态',
  `f_change_date` datetime NOT NULL COMMENT '变更日期',
  `f_change_man` varchar(32) NOT NULL COMMENT '变更人',
  `f_change_situ` varchar(200) NOT NULL DEFAULT '无' COMMENT '变更情况',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_altercode` int(2) NOT NULL COMMENT '1新增　2修改   3 删除',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_outnotice_change
-- ----------------------------

-- ----------------------------
-- Table structure for fbe_stock_outnotice_item
-- ----------------------------
DROP TABLE IF EXISTS `fbe_stock_outnotice_item`;
CREATE TABLE `fbe_stock_outnotice_item` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '票据出库明细表_主键',
  `f_pid` bigint(20) unsigned NOT NULL COMMENT '出库表id',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '序号',
  `f_bill_code` varchar(8) NOT NULL COMMENT '票据代码（8位）',
  `f_bill_name` varchar(200) NOT NULL COMMENT '票据名称',
  `f_number` int(9) NOT NULL COMMENT '数量',
  `f_bill_no1` varchar(10) NOT NULL COMMENT '起始号',
  `f_bill_no2` varchar(10) NOT NULL COMMENT '终止号',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_logic_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除（0未删1删除）',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fbe_stock_outnotice_item
-- ----------------------------

-- ----------------------------
-- Table structure for ube_stock_return
-- ----------------------------
DROP TABLE IF EXISTS `ube_stock_return`;
CREATE TABLE `ube_stock_return` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '退票主表_主键',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '业务单号',
  `f_rgn_code` varchar(2) NOT NULL COMMENT '区划编码',
  `f_agen_id_code` varchar(32) NOT NULL COMMENT '单位识别码',
  `f_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '编制日期',
  `f_return_reason` varchar(200) NOT NULL DEFAULT '无' COMMENT '退票原因',
  `f_returner` varchar(32) NOT NULL COMMENT '退票人',
  `f_author` varchar(32) NOT NULL COMMENT '编制人',
  `f_memo` varchar(200) NOT NULL DEFAULT '无' COMMENT '备注',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `f_change_state` int(11) NOT NULL COMMENT '审核状态',
  `f_change_date` datetime NOT NULL COMMENT '审核日期',
  `f_change_man` varchar(32) NOT NULL COMMENT '经办人',
  `f_change_situ` varchar(200) NOT NULL COMMENT '审核意见',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ube_stock_return
-- ----------------------------

-- ----------------------------
-- Table structure for ube_stock_return_item
-- ----------------------------
DROP TABLE IF EXISTS `ube_stock_return_item`;
CREATE TABLE `ube_stock_return_item` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_pid` bigint(20) unsigned NOT NULL COMMENT '父级ID',
  `f_no` bigint(20) unsigned NOT NULL COMMENT '序号',
  `f_bill_code` varchar(8) NOT NULL COMMENT '票据代码',
  `f_number` int(11) NOT NULL COMMENT '数量',
  `f_bill_no1` varchar(10) NOT NULL COMMENT '起始票号',
  `f_bill_no2` varchar(10) NOT NULL COMMENT '终止票号',
  `f_version` int(11) NOT NULL COMMENT '版本号',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ube_stock_return_item
-- ----------------------------
