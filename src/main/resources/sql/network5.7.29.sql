/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.33
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 192.168.1.33:3306
 Source Schema         : network

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 19/03/2021 14:17:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('MyScheduler', 'synchronizedRedis', 'group2', '0 0/1 * * * ? ', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('MyScheduler', 'synchronizedRedis', 'group2', NULL, 'com.shp.dev.network.common.util.quartz.SchedulingInstance', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('MyScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('MyScheduler', 'synchronizedRedis', 'group2', 'synchronizedRedis', 'group2', NULL, 1616134680000, 1616134620000, 5, 'WAITING', 'CRON', 1615099903000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for TB_VIDEO
-- ----------------------------
DROP TABLE IF EXISTS `TB_VIDEO`;
CREATE TABLE `TB_VIDEO`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `v_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频流地址',
  `v_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频类型',
  `v_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频标题',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后操作的时间',
  `is_del` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '0表示已经删除  1表示存在',
  `channel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of TB_VIDEO
-- ----------------------------
INSERT INTO `TB_VIDEO` VALUES (1, 'rtmp://58.200.131.2:1935/livetv/cctv1', 'CCTV', 'CCTV1', '2020-03-11 00:00:00', '0', 'CCTV1');
INSERT INTO `TB_VIDEO` VALUES (2, 'rtmp://58.200.131.2:1935/livetv/cctv2', 'CCTV', 'CCTV2', '2020-03-11 00:00:00', '0', 'CCTV2');
INSERT INTO `TB_VIDEO` VALUES (3, 'rtmp://58.200.131.2:1935/livetv/cctv3', 'CCTV', 'CCTV3', '2020-03-11 00:00:00', '0', 'CCTV3');
INSERT INTO `TB_VIDEO` VALUES (4, 'rtmp://58.200.131.2:1935/livetv/cctv4', 'CCTV', 'CCTV4', '2020-03-11 00:00:00', '0', 'CCTV4');
INSERT INTO `TB_VIDEO` VALUES (5, 'rtmp://58.200.131.2:1935/livetv/cctv5', 'CCTV', 'CCTV5', '2020-03-11 00:00:00', '0', 'CCTV5');
INSERT INTO `TB_VIDEO` VALUES (6, 'rtmp://58.200.131.2:1935/livetv/cctv6', 'CCTV', 'CCTV6', '2020-03-11 00:00:00', '1', 'CCTV6');
INSERT INTO `TB_VIDEO` VALUES (7, 'rtmp://58.200.131.2:1935/livetv/cctv7', 'CCTV', 'CCTV7', '2020-03-11 00:00:00', '0', 'CCTV7');
INSERT INTO `TB_VIDEO` VALUES (8, 'rtmp://58.200.131.2:1935/livetv/cctv8', 'CCTV', 'CCTV8', '2020-03-11 00:00:00', '0', 'CCTV8');
INSERT INTO `TB_VIDEO` VALUES (9, 'rtmp://58.200.131.2:1935/livetv/cctv9', 'CCTV', 'CCTV9', '2020-03-11 00:00:00', '0', 'CCTV9');
INSERT INTO `TB_VIDEO` VALUES (10, 'rtmp://58.200.131.2:1935/livetv/cctv10', 'CCTV', 'CCTV10', '2020-03-11 00:00:00', '0', 'CCTV10');
INSERT INTO `TB_VIDEO` VALUES (11, 'rtmp://58.200.131.2:1935/livetv/cctv11', 'CCTV', 'CCTV11', '2020-03-11 00:00:00', '0', 'CCTV11');
INSERT INTO `TB_VIDEO` VALUES (12, 'rtmp://58.200.131.2:1935/livetv/cctv12', 'CCTV', 'CCTV12', '2020-03-11 00:00:00', '0', 'CCTV12');
INSERT INTO `TB_VIDEO` VALUES (13, 'rtmp://58.200.131.2:1935/livetv/cctv13', 'CCTV', 'CCTV13', '2020-03-11 00:00:00', '0', 'CCTV13');
INSERT INTO `TB_VIDEO` VALUES (14, 'rtmp://58.200.131.2:1935/livetv/cctv14', 'CCTV', 'CCTV14', '2020-03-11 00:00:00', '0', 'CCTV14');
INSERT INTO `TB_VIDEO` VALUES (15, 'rtmp://58.200.131.2:1935/livetv/cctv15', 'CCTV', 'CCTV15', '2020-03-11 00:00:00', '0', 'CCTV15');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for tb_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '按钮id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `has_third` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮地址',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `is_del` int(11) NULL DEFAULT 0 COMMENT '0 未删除   1已删除',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_menu
-- ----------------------------
INSERT INTO `tb_sys_menu` VALUES (1, 1, 'li-icon-xiangmuguanli', '基础管理', NULL, NULL, NULL, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (2, 11, 'icon-cat-skuQuery', '商品管理', 'N', 'goods/Goods', 1, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (3, 2, 'li-icon-dingdanguanli', '订单管理', NULL, '', NULL, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (4, 21, 'icon-order-manage', '交易订单', 'N', 'pay/Order', 2, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (5, 3, 'icon-order-manage', '系统管理', NULL, NULL, NULL, 0, 2);
INSERT INTO `tb_sys_menu` VALUES (6, 31, 'icon-cus-manage', '用户管理', 'N', 'system/user', 3, 0, NULL);
INSERT INTO `tb_sys_menu` VALUES (7, 32, 'icon-cms-manage', '菜单管理', 'N', 'system/Module', 3, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (8, 33, 'icon-news-manage', '角色管理', 'N', 'system/Role', 3, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (9, 34, 'icon-cs-manage', '公司管理', 'N', 'system/Dept', 3, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (10, 35, 'icon-promotion-manage', '系统环境变量', 'N', 'system/Variable', 3, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (11, 36, 'icon-cms-manage', '权限管理', 'N', 'system/Permission', 3, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (12, 4, 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91', '支付管理', NULL, NULL, NULL, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (13, 41, 'icon-provider-manage', '支付配置信息', 'N', 'machine/MachineConfig', 4, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (14, 42, 'icon-provider-manage', '支付配置', 'N', 'pay/Config', 4, 1, NULL);
INSERT INTO `tb_sys_menu` VALUES (15, 5, 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91', '数据展示', NULL, NULL, NULL, 0, NULL);
INSERT INTO `tb_sys_menu` VALUES (16, 51, 'icon-provider-manage', '数据可视化', 'N', 'charts/statistics', 5, 0, 2);
INSERT INTO `tb_sys_menu` VALUES (17, 32, 'icon-cs-manage', '输出页面', 'N', 'system/write', 3, 0, 1);
INSERT INTO `tb_sys_menu` VALUES (18, 33, 'icon-order-manage', 'sql管理', 'N', 'system/sql', 3, 0, 3);
INSERT INTO `tb_sys_menu` VALUES (19, 34, 'li-icon-dingdanguanli', '音乐管理', NULL, 'system/music', 3, 0, 4);

-- ----------------------------
-- Table structure for tb_sys_sql
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_sql`;
CREATE TABLE `tb_sys_sql`  (
  `sql_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' sql名称',
  `sql_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sql语句',
  PRIMARY KEY (`sql_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sql表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_sql
-- ----------------------------
INSERT INTO `tb_sys_sql` VALUES ('addSysSql', 'INSERT INTO sys_sql(sql_name,sql_text) VALUES(?,?)');
INSERT INTO `tb_sys_sql` VALUES ('queryAllSysMenu', 'select * from sys_menu');
INSERT INTO `tb_sys_sql` VALUES ('queryAllSysUser', 'select * from sys_user');
INSERT INTO `tb_sys_sql` VALUES ('queryAllSysWrite', 'select * from sys_write');
INSERT INTO `tb_sys_sql` VALUES ('queryPageBySysWrite', 'select * from sys_write');
INSERT INTO `tb_sys_sql` VALUES ('querySysUserById', 'select * from sys_user where id =?');

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `operate_date` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operate_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
INSERT INTO `tb_sys_user` VALUES (1, 'shp', '202cb962ac59075b964b07152d234b70', '16630013666', '男', '22', '河北', '2020-09-24 16:17:32', 'shp');
INSERT INTO `tb_sys_user` VALUES (2, 'admin', '202cb962ac59075b964b07152d234b70', '16630013666', '女', '20', '河北', '2020-09-24 16:17:34', 'shp');
INSERT INTO `tb_sys_user` VALUES (3, 'aaa', '202cb962ac59075b964b07152d234b70', '15530013666', '女', '21', '河北', '2020-07-26 00:36:07', 'shp');
INSERT INTO `tb_sys_user` VALUES (4, 'xxx', '202cb962ac59075b964b07152d234b70', '15530013666', '男', '20', '陕西', '2020-10-31 17:20:14', 'shp');
INSERT INTO `tb_sys_user` VALUES (5, 'bbb', '202cb962ac59075b964b07152d234b70', '16630013666', '女', '18', '山西', '2020-10-31 17:20:39', 'admin');

-- ----------------------------
-- Table structure for tb_sys_write
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_write`;
CREATE TABLE `tb_sys_write`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字，用于关键字搜索',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性介绍，该网页的描述',
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '向页面输出的页面新消息。',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嵌入网页的地址',
  `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认为当前时间',
  `is_del` int(11) NULL DEFAULT NULL COMMENT '0 不删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sys_write
-- ----------------------------
INSERT INTO `tb_sys_write` VALUES (1, 'music', '音乐播放器', '<!DOCTYPE html>\r\n<html lang=\"zh\">\r\n	<head>\r\n		<meta charset=\"UTF-8\">\r\n		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n		<script src=\"https://cdn.bootcss.com/js-polyfills/0.1.42/polyfill.min.js\"></script>\r\n		<script src=\"https://cdnjs.cloudflare.com/ajax/libs/color-thief/2.3.0/color-thief.umd.js\"></script>\r\n		<script src=\"https://cdn.bootcss.com/hls.js/8.0.0-beta.3/hls.min.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n		<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.css\">\r\n		<script src=\"https://cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.js\"></script>\r\n		<script src=\"https://cdn.jsdelivr.net/npm/meting@2/dist/Meting.min.js\"></script>\r\n		<style type=\"text/css\">\r\n			*{background: none;\r\n				margin: 0;\r\n				padding: 0;\r\n				box-sizing: border-box;\r\n				overflow: hidden;\r\n				border: none;}\r\n		</style>\r\n		<script type=\"text/javascript\">\r\n			var $$_ = (function() {\r\n				var url = window.document.location.href.toString(); \r\n				var u = url.split(\"?\");\r\n				if (typeof(u[1]) == \"string\") {\r\n					u = u[1].split(\"&\");\r\n					var get = {};\r\n					for (var i in u) {\r\n						var j = u[i].split(\"=\");\r\n						get[j[0]] = j[1];\r\n					}\r\n					return get;\r\n				} else {\r\n					return {};\r\n				}\r\n			})();\r\n			var getConfigs = {\r\n				\"id\": $$_[\'id\'] || \'2844270533\',\r\n				\"server\": $$_[\'server\'] || \'netease\',\r\n				\"type\": $$_[\'type\'] || \'playlist\',\r\n				\"auto\": $$_[\'auto\'] || \'true\',\r\n				\"fixed\": $$_[\'fixed\'] || \'false\',\r\n				\"mini\": $$_[\'mini\'] || \'false\',\r\n				\"autoplay\": $$_[\'autoplay\'] || \'false\',\r\n				\"theme\": $$_[\'theme\'] || \'#2980b9\',\r\n				\"loop\": $$_[\'loop\'] || \'all\',\r\n				\"order\": $$_[\'order\'] || \'list\',\r\n				\"preload\": $$_[\'preload\'] || \'auto\',\r\n				\"volume\": $$_[\'volume\'] || \'0.7\',\r\n				\"mutex\": $$_[\'mutex\'] || \'true\',\r\n				\"lrc-type\": $$_[\'lrc-type\'] || \'0\',\r\n				\"list-folded\": $$_[\'list-folded\'] || \'false\',\r\n				\"list-max-height\": $$_[\'list-max-height\'] || \'340px\',\r\n				\"storage-name\": $$_[\'storage-name\'] || \'cosher-player\',\r\n			}\r\n		</script>\r\n	</head>\r\n	<body>\r\n		<div id=\"app\">\r\n		</div>\r\n		<script type=\"text/javascript\">\r\n			var musicPlayer=document.createElement(\"meting-js\")\r\n			for (k in getConfigs) {\r\n				musicPlayer.setAttribute(k,getConfigs[k])\r\n			}\r\n			document.getElementById(\'app\').appendChild(musicPlayer)\r\n		</script>\r\n	</body>\r\n</html>', NULL, '2020-10-01 13:40:30', 0);
INSERT INTO `tb_sys_write` VALUES (2, '404', '404页面', '<!doctype html>\r\n<html>\r\n<head>\r\n<meta charset=\"utf-8\">\r\n<title>页面不翼而飞！</title>\r\n\r\n<style>\r\nhtml,body {\r\n	margin:0;\r\n	padding:0;\r\n}\r\ncanvas {\r\n	display:block;\r\n}\r\n</style>\r\n</head>\r\n<body>\r\n\r\n<div></div>\r\n\r\n<script>\r\n\r\nvar canvas = document.createElement(\'canvas\');\r\n      var height = canvas.height = window.innerHeight;\r\n      var width = canvas.width = window.innerWidth;\r\n      var ctx = canvas.getContext(\'2d\');\r\n      document.body.appendChild(canvas);\r\n\r\n      function random(min,max)\r\n      {\r\n          return Math.random()*(max-min+1)+min;\r\n      }\r\n\r\n      function range_map(value,in_min, in_max, out_min, out_max) {\r\n        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;\r\n      }\r\n\r\n      var word_arr = [];\r\n      var txt_min_size = 5;\r\n      var txt_max_size = 25;\r\n      var keypress = false;\r\n      var acclerate = 2;\r\n      for (var i = 0; i < 25; i++) {\r\n        word_arr.push({\r\n          x : random(0,width),\r\n          y : random(0,height),\r\n          text : \'出大问题！\',\r\n          size : random(txt_min_size,txt_max_size)\r\n        });\r\n\r\n        word_arr.push({\r\n          x : random(0,width),\r\n          y : random(0,height),\r\n          text : \'页面\',\r\n          size : random(txt_min_size,txt_max_size)\r\n        });\r\n\r\n\r\n        word_arr.push({\r\n          x : random(0,width),\r\n          y : random(0,height),\r\n          text : \'找不到了！\',\r\n          size : random(txt_min_size,txt_max_size)\r\n        });\r\n\r\n        word_arr.push({\r\n          x : random(0,width),\r\n          y : random(0,height),\r\n          text : \'404\',\r\n          size : Math.floor(random(txt_min_size,txt_max_size))\r\n        });\r\n      }\r\n\r\n      function render()\r\n      {\r\n        ctx.fillStyle = \"rgba(0,0,0,1)\";\r\n        ctx.fillRect(0,0,width,height);\r\n\r\n        ctx.fillStyle = \"#fff\";\r\n        for (var i = 0; i < word_arr.length; i++) {\r\n          ctx.font = word_arr[i].size+\"px sans-serif\";\r\n          var w = ctx.measureText(word_arr[i].text);\r\n          ctx.fillText(word_arr[i].text,word_arr[i].x,word_arr[i].y);\r\n\r\n          if(keypress)\r\n          {\r\n            word_arr[i].x += range_map(word_arr[i].size,txt_min_size,txt_max_size,2,4) * acclerate;\r\n          }\r\n          else {\r\n            word_arr[i].x += range_map(word_arr[i].size,txt_min_size,txt_max_size,2,3);\r\n          }\r\n\r\n          if(word_arr[i].x >= width)\r\n          {\r\n            word_arr[i].x = -w.width*2;\r\n            word_arr[i].y = random(0,height);\r\n            word_arr[i].size =  Math.floor(random(txt_min_size,txt_max_size));\r\n\r\n          }\r\n        }\r\n\r\n        ctx.fill();\r\n\r\n        requestAnimationFrame(render);\r\n      }\r\n\r\n      render();\r\n\r\n      window.addEventListener(\'keydown\',function(){\r\n        keypress = true;\r\n      },true);\r\n      window.addEventListener(\'keyup\',function(){\r\n        keypress = false;\r\n      },true);</script>\r\n\r\n</body>\r\n</html>\r\n', NULL, '2020-10-01 13:40:30', 0);
INSERT INTO `tb_sys_write` VALUES (14, 'ifrom', 'ifrom blibli', '<!DOCTYPE html>\r\n<html lang=\"zh\">\r\n	<head>\r\n	</head>\r\n	<body  bgcolor=\"#FFFFCC\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n		<iframe \r\n		  id=\"iframeId\"\r\n		  scrolling=\"no\"\r\n		  framespacing=\"0\"\r\n		  vspace=\"0\"\r\n		  hspace=\"0\" \r\n		  frameborder=\"0\"\r\n		  width=\"100%\" \r\n		  height=\"3000px\"\r\n		  src=\"https://www.bilibili.com/\">\r\n		</iframe>\r\n	</body>\r\n</html>', NULL, '2020-10-01 13:40:30', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
