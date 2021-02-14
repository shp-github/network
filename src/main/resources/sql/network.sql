/*
 Navicat Premium Data Transfer

 Source Server         : 47.92.213.36
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 47.92.213.36:3306
 Source Schema         : network

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 14/02/2021 20:42:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for tb_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '按钮id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `has_third` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮地址',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父级id',
  `is_del` int(0) NULL DEFAULT 0 COMMENT '0 未删除   1已删除',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '排序字段',
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
  `sql_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ' sql名称',
  `sql_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'sql语句',
  PRIMARY KEY (`sql_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'sql表' ROW_FORMAT = Dynamic;

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
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `operate_date` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operate_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字，用于关键字搜索',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性介绍，该网页的描述',
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '向页面输出的页面新消息。',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嵌入网页的地址',
  `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认为当前时间',
  `is_del` int(0) NULL DEFAULT NULL COMMENT '0 不删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
