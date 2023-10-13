/*
 Navicat MySQL Data Transfer

 Source Server         : Class
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : shoppingdb

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 23/08/2022 14:34:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_admin`;
CREATE TABLE `t_admin_admin`  (
  `adminid` int NOT NULL AUTO_INCREMENT,
  `admingroupid` int NULL DEFAULT NULL,
  `adminname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `adminpwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `regtime` datetime NULL DEFAULT NULL,
  `modifytime` datetime NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`adminid`) USING BTREE,
  INDEX `FK_Reference_8`(`admingroupid`) USING BTREE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`admingroupid`) REFERENCES `t_admin_group` (`admingroupid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_admin
-- ----------------------------
INSERT INTO `t_admin_admin` VALUES (1, 1, '123', '1230', '男', '2022-08-16 16:21:49', '2022-08-16 16:21:47', 'life');
INSERT INTO `t_admin_admin` VALUES (2, 1, '145', '546', '男', '2022-08-17 15:54:54', '2022-08-17 15:54:57', '545646');
INSERT INTO `t_admin_admin` VALUES (3, 1, 'dasd', 'sadasd', '男', '2022-08-17 16:03:13', '2022-08-17 16:03:16', 'dasdas');
INSERT INTO `t_admin_admin` VALUES (4, 1, 'das', 'asda', '男', NULL, NULL, '');
INSERT INTO `t_admin_admin` VALUES (6, 1, 'a', 'aa', '男', NULL, NULL, '');
INSERT INTO `t_admin_admin` VALUES (7, 1, 'ddd', 'ddd', '男', NULL, NULL, '');
INSERT INTO `t_admin_admin` VALUES (8, 1, '2312', '12312', '男', NULL, NULL, '');
INSERT INTO `t_admin_admin` VALUES (9, 1, '111', '1111', '男', NULL, NULL, '');
INSERT INTO `t_admin_admin` VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, '我的评价是：摆');
INSERT INTO `t_admin_admin` VALUES (18, 1, 'dasd', 'ddddddd', '男', NULL, NULL, '');

-- ----------------------------
-- Table structure for t_admin_group
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_group`;
CREATE TABLE `t_admin_group`  (
  `admingroupid` int NOT NULL AUTO_INCREMENT,
  `admingroupname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admingroupid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_group
-- ----------------------------
INSERT INTO `t_admin_group` VALUES (1, '开发部');
INSERT INTO `t_admin_group` VALUES (2, '总监部');
INSERT INTO `t_admin_group` VALUES (3, '美工部');
INSERT INTO `t_admin_group` VALUES (7, '测试部');

-- ----------------------------
-- Table structure for t_news_class
-- ----------------------------
DROP TABLE IF EXISTS `t_news_class`;
CREATE TABLE `t_news_class`  (
  `classId` int NOT NULL AUTO_INCREMENT,
  `className` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news_class
-- ----------------------------

-- ----------------------------
-- Table structure for t_news_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news_news`;
CREATE TABLE `t_news_news`  (
  `newsId` int NOT NULL AUTO_INCREMENT,
  `classId` int NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `datetime` datetime NULL DEFAULT NULL,
  `publisher` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`newsId`) USING BTREE,
  INDEX `FK_Reference_16`(`classId`) USING BTREE,
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`classId`) REFERENCES `t_news_class` (`classId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news_news
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_order_cart`;
CREATE TABLE `t_order_cart`  (
  `cartid` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userid` int NULL DEFAULT NULL,
  `productid` int NULL DEFAULT NULL,
  `productPrice` double NULL DEFAULT NULL,
  `productNum` int NULL DEFAULT NULL,
  `buytime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`cartid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_cart
-- ----------------------------
INSERT INTO `t_order_cart` VALUES (17, 'mi12', 3, 33, 333, 333, NULL);
INSERT INTO `t_order_cart` VALUES (18, 'mi9', 3, 6, 222, 1, NULL);
INSERT INTO `t_order_cart` VALUES (21, 'mi9', 4, 1, 222, 1, NULL);
INSERT INTO `t_order_cart` VALUES (27, 'Redmi K50', 14, 17, 222, 1, NULL);

-- ----------------------------
-- Table structure for t_order_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order_order`;
CREATE TABLE `t_order_order`  (
  `orderid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` int NULL DEFAULT NULL,
  `receivename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ordersum` double NULL DEFAULT NULL,
  `ordertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `FK_Reference_5`(`userid`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`userid`) REFERENCES `t_user_user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_order
-- ----------------------------
INSERT INTO `t_order_order` VALUES ('-1983469746', 9, '测试员', '地球', 'XX市XX区XX小区', '10083', 11994, '2022-08-23 09:30:10');
INSERT INTO `t_order_order` VALUES ('-2129686076', 9, '测试员', '地球', 'XX市XX区XX小区', '10083', 1999, '2022-08-22 23:27:47');
INSERT INTO `t_order_order` VALUES ('-814001493', 9, '测试员', '甘肃省', 'XX市XX区XX小区', '10086', 7897, '2022-08-22 16:24:12');
INSERT INTO `t_order_order` VALUES ('227890093', 9, '测试员', '地球', 'XX市XX区XX小区', '10083', 4399, '2022-08-22 16:27:27');
INSERT INTO `t_order_order` VALUES ('254627244', 9, '测试员', '地球', 'XX市XX区XX小区', '10083', 20193, '2022-08-22 22:30:23');

-- ----------------------------
-- Table structure for t_order_orderitems
-- ----------------------------
DROP TABLE IF EXISTS `t_order_orderitems`;
CREATE TABLE `t_order_orderitems`  (
  `orderitemId` int NOT NULL AUTO_INCREMENT,
  `orderid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `productid` int NULL DEFAULT NULL,
  `productName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `productPrice` double NULL DEFAULT NULL,
  `buynum` int NULL DEFAULT NULL,
  PRIMARY KEY (`orderitemId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_orderitems
-- ----------------------------
INSERT INTO `t_order_orderitems` VALUES (3, '-814001493', 1, 'mi9', 7897, 1);
INSERT INTO `t_order_orderitems` VALUES (4, '227890093', 5, 'mi12', 4399, 1);
INSERT INTO `t_order_orderitems` VALUES (5, '344786436', 9, 'mi12', 4399, 1);
INSERT INTO `t_order_orderitems` VALUES (6, '254627244', 6, 'mi12', 4399, 1);
INSERT INTO `t_order_orderitems` VALUES (7, '254627244', 1, 'mi9', 7897, 2);
INSERT INTO `t_order_orderitems` VALUES (8, '-2129686076', 27, '测试', 1999, 1);
INSERT INTO `t_order_orderitems` VALUES (9, '-1983469746', 24, 'Mi12', 1999, 1);
INSERT INTO `t_order_orderitems` VALUES (10, '-1983469746', 17, 'Redmi K50', 1999, 2);
INSERT INTO `t_order_orderitems` VALUES (11, '-1983469746', 25, 'Mi12', 1999, 1);
INSERT INTO `t_order_orderitems` VALUES (12, '-1983469746', 30, '测试', 1999, 1);
INSERT INTO `t_order_orderitems` VALUES (13, '-1983469746', 17, 'Redmi K50', 1999, 1);

-- ----------------------------
-- Table structure for t_product_brand
-- ----------------------------
DROP TABLE IF EXISTS `t_product_brand`;
CREATE TABLE `t_product_brand`  (
  `brandid` int NOT NULL AUTO_INCREMENT,
  `brandname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`brandid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product_brand
-- ----------------------------
INSERT INTO `t_product_brand` VALUES (1, '小米');
INSERT INTO `t_product_brand` VALUES (2, '华为');
INSERT INTO `t_product_brand` VALUES (3, '苹果');

-- ----------------------------
-- Table structure for t_product_class
-- ----------------------------
DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class`  (
  `classid` int NOT NULL AUTO_INCREMENT,
  `classname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product_class
-- ----------------------------
INSERT INTO `t_product_class` VALUES (1, '手机');
INSERT INTO `t_product_class` VALUES (2, 'ipad');
INSERT INTO `t_product_class` VALUES (5, 'watch');

-- ----------------------------
-- Table structure for t_product_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product_product`;
CREATE TABLE `t_product_product`  (
  `productid` int NOT NULL AUTO_INCREMENT,
  `classid` int NULL DEFAULT NULL,
  `brandid` int NULL DEFAULT NULL,
  `productname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `smallimg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bigimg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`productid`) USING BTREE,
  INDEX `FK_Reference_3`(`classid`) USING BTREE,
  INDEX `FK_Reference_4`(`brandid`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`classid`) REFERENCES `t_product_class` (`classid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`brandid`) REFERENCES `t_product_brand` (`brandid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product_product
-- ----------------------------
INSERT INTO `t_product_product` VALUES (11, 1, 1, 'Redmi K50', '8838cc3f40cf4e9796ae3b97fdfec7c4.png', '7eb9cfceaac744ffbe1bdc38884e1514.png', 1999, 'Redmi于2022年发布的手机产品\r\nRedmi K50是Redmi旗下的一款手机，于2022年3月17日发布；3月22日上市开售。 [1]  [6]  [16] \r\nRedmi K50屏幕尺寸6.67英寸，长度163.1mm；宽度76.15mm；厚度8.48mm；重量201g，有“墨羽”“幽芒”“银迹”“幻镜”四种配色。 [13] \r\nRedmi K50搭载天玑8100处理器，采用三星2K直屏，内置5500mAh电池，支持67W闪充，采用4800万像素光学防抖相机、VC液冷散热，支持杜比视界全景声。 [14] ');
INSERT INTO `t_product_product` VALUES (17, 1, 1, 'Redmi K50', '8838cc3f40cf4e9796ae3b97fdfec7c4.png', '7eb9cfceaac744ffbe1bdc38884e1514.png', 1999, 'Redmi于2022年发布的手机产品\r\nRedmi K50是Redmi旗下的一款手机，于2022年3月17日发布；3月22日上市开售。 [1]  [6]  [16] \r\nRedmi K50屏幕尺寸6.67英寸，长度163.1mm；宽度76.15mm；厚度8.48mm；重量201g，有“墨羽”“幽芒”“银迹”“幻镜”四种配色。 [13] \r\nRedmi K50搭载天玑8100处理器，采用三星2K直屏，内置5500mAh电池，支持67W闪充，采用4800万像素光学防抖相机、VC液冷散热，支持杜比视界全景声。 [14] ');
INSERT INTO `t_product_product` VALUES (21, 1, 2, '华为MatePad 2022款平板', '349919311f5b443faf9a999def91f466.jpg', 'dcd825c221304a6fb92914ab4c654fc4.jpg', 1999, '这款平板搭载鸿蒙系统，能够和其他的华为鸿蒙设备，如手机、笔记本等实现跨屏互动，轻松互联。屏幕采用10.4英寸2K全面屏，通过莱茵低蓝光、无频闪双重认证，可以很好的保护双眼。\r\n\r\n采用沉浸式立体音效，四大振幅扬声器，哈曼卡顿调音。内置7250mAh大容量电池，持久续航。一直关注的小伙伴可以入手一个。');
INSERT INTO `t_product_product` VALUES (22, 5, 2, '华为WATCH GT 3', '4aed925503e94cf08457712a738bf8e8.png', '1e7f65b1789e46b19cde3b0b6a5ff890.png', 3999, '从设计之初，华为就一直坚持“watch is watch”理念，长续航、健康管理、生态全面发力，不断为用户带来新价值，引领长续航智能手表赛道。WATCH系列和GT系列虽然有功能上重合，但侧重的用户需求点不同，我们回到用户场景拆开来看。');
INSERT INTO `t_product_product` VALUES (23, 5, 2, '华为WATCH GT 3', 'bfffc25848b140578bc009ab2f411adb.png', 'e549cebc09c247d48f89f463c214845e.png', 4399, '');
INSERT INTO `t_product_product` VALUES (24, 1, 1, 'Mi12', '8b96fbd443e842b6a86ecf0c22a3dcec.png', '1535e305cfe24818916136041af715a2.png', 1999, '小米公司于2021年12月28日发布的手机\r\nXiaomi 12是小米公司于2021年12月28日发布的手机产品。 [5-6] \r\nXiaomi 12采用6.28英寸微曲柔性屏幕；配有黑色、蓝色、紫色、原野绿四种颜色；黑色、蓝色、紫色高度约152.7毫米，宽度约69.85毫米，厚度约8.16毫米，重量约180克；原野绿高度约152.7毫米，宽度约69.85毫米，厚度约8.66毫米，重量约179克。 [5-6] \r\nXiaomi 12搭载高通骁龙8 Gen1八核处理器；预装基于Android 12的MIUI 13操作系统；后置5000万像素主镜头+1300万像素超广角镜头+500万像素微距镜头三摄，支持OIS光学防抖、快速追焦等拍照功能，前置3200万像素摄像头；搭载4500毫安时容量不可拆卸电池；为5G全网通手机。 [5-6] ');
INSERT INTO `t_product_product` VALUES (25, 1, 1, 'Mi12', '248d2c97fd614c2bbceeeb88fe154bd5.png', '85e9283f77b34f9dac74c941e9f2db94.png', 1999, '小米公司于2021年12月28日发布的手机\r\nXiaomi 12是小米公司于2021年12月28日发布的手机产品。 [5-6] \r\nXiaomi 12采用6.28英寸微曲柔性屏幕；配有黑色、蓝色、紫色、原野绿四种颜色；黑色、蓝色、紫色高度约152.7毫米，宽度约69.85毫米，厚度约8.16毫米，重量约180克；原野绿高度约152.7毫米，宽度约69.85毫米，厚度约8.66毫米，重量约179克。 [5-6] \r\nXiaomi 12搭载高通骁龙8 Gen1八核处理器；预装基于Android 12的MIUI 13操作系统；后置5000万像素主镜头+1300万像素超广角镜头+500万像素微距镜头三摄，支持OIS光学防抖、快速追焦等拍照功能，前置3200万像素摄像头；搭载4500毫安时容量不可拆卸电池；为5G全网通手机。 [5-6] ');
INSERT INTO `t_product_product` VALUES (26, 2, 2, '华为MatePad 2022款平板', 'a370f58f156847098d1c35078f56f31f.jpg', '38465e3d5d3440bda9d413b578e69e1b.jpg', 1999, '小米公司于2021年12月28日发布的手机\r\nXiaomi 12是小米公司于2021年12月28日发布的手机产品。 [5-6] \r\nXiaomi 12采用6.28英寸微曲柔性屏幕；配有黑色、蓝色、紫色、原野绿四种颜色；黑色、蓝色、紫色高度约152.7毫米，宽度约69.85毫米，厚度约8.16毫米，重量约180克；原野绿高度约152.7毫米，宽度约69.85毫米，厚度约8.66毫米，重量约179克。 [5-6] \r\nXiaomi 12搭载高通骁龙8 Gen1八核处理器；预装基于Android 12的MIUI 13操作系统；后置5000万像素主镜头+1300万像素超广角镜头+500万像素微距镜头三摄，支持OIS光学防抖、快速追焦等拍照功能，前置3200万像素摄像头；搭载4500毫安时容量不可拆卸电池；为5G全网通手机。 [5-6] ');
INSERT INTO `t_product_product` VALUES (28, 1, 2, '华为WATCH GT 3', '929094bb265143da9edb6e44552f392c.png', 'e4706c95da3f4728948ceb608e6daa56.png', 3999, '华为WATCH GT 3');
INSERT INTO `t_product_product` VALUES (30, 1, 1, '测试', 'a7e0a2e42aef4395be67a9cbbf360a6b.jpg', 'df576717972b487c8f95d40a8aa93ed3.jpg', 1999, '');

-- ----------------------------
-- Table structure for t_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group`  (
  `usergroupid` int NOT NULL AUTO_INCREMENT,
  `usergroupname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`usergroupid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES (1, '普通用户');
INSERT INTO `t_user_group` VALUES (2, '高级用户');

-- ----------------------------
-- Table structure for t_user_receive
-- ----------------------------
DROP TABLE IF EXISTS `t_user_receive`;
CREATE TABLE `t_user_receive`  (
  `receiveid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `receivename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isdefault` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`receiveid`) USING BTREE,
  INDEX `FK_Reference_2`(`userid`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`userid`) REFERENCES `t_user_user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_receive
-- ----------------------------
INSERT INTO `t_user_receive` VALUES (1, 9, '测试员', '地球', '10083', '1', 'XX市XX区XX小区');
INSERT INTO `t_user_receive` VALUES (3, 11, '阿拉丁', '火星', '10001', '1', '翻斗大街');
INSERT INTO `t_user_receive` VALUES (6, 4, '测试修改', '火星', '10000', NULL, '翻斗大街');

-- ----------------------------
-- Table structure for t_user_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user_user`;
CREATE TABLE `t_user_user`  (
  `userid` int NOT NULL AUTO_INCREMENT,
  `usergroupid` int NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userpwd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `regtime` datetime NULL DEFAULT NULL,
  `modifytime` datetime NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `FK_Reference_1`(`usergroupid`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`usergroupid`) REFERENCES `t_user_group` (`usergroupid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_user
-- ----------------------------
INSERT INTO `t_user_user` VALUES (4, 1, '张三', '546', '男', '2022-08-20 10:08:58', '2022-08-20 10:08:58', '564');
INSERT INTO `t_user_user` VALUES (6, 1, '李四', '222', '男', '2022-08-20 10:10:39', '2022-08-20 10:10:39', 'sadasd');
INSERT INTO `t_user_user` VALUES (7, 1, '王五', '22', '男', '2022-08-20 10:10:56', '2022-08-20 10:10:56', '222');
INSERT INTO `t_user_user` VALUES (9, 2, '123', '1230', '男', '2022-08-21 08:29:54', '2022-08-21 08:29:54', 'Hlmove');
INSERT INTO `t_user_user` VALUES (10, 2, '张麻子', '111', '男', NULL, NULL, 'ads');
INSERT INTO `t_user_user` VALUES (11, 1, '六子', 'sadasd', '女', '2022-08-21 08:32:01', '2022-08-21 08:32:01', '');
INSERT INTO `t_user_user` VALUES (14, 2, '123', '132110', '男', NULL, NULL, '');
INSERT INTO `t_user_user` VALUES (15, 1, '963887', '123', '男', NULL, NULL, '');
INSERT INTO `t_user_user` VALUES (16, 1, '16719310957', 'dsa', '男', '2022-08-22 22:07:54', '2022-08-22 22:07:54', '');
INSERT INTO `t_user_user` VALUES (17, 1, '963887', 'asas', '男', '2022-08-22 22:10:19', '2022-08-22 22:10:19', 'dasdas');
INSERT INTO `t_user_user` VALUES (18, 1, 'dasd', 'asda', '男', '2022-08-23 09:11:24', '2022-08-23 09:11:24', '');
INSERT INTO `t_user_user` VALUES (19, 2, '963887', 'dasd', '男', NULL, NULL, '');
INSERT INTO `t_user_user` VALUES (20, 1, '963887', 'asas', '男', '2022-08-23 09:28:55', '2022-08-23 09:28:55', 'as');

-- ----------------------------
-- View structure for v_product_product
-- ----------------------------
DROP VIEW IF EXISTS `v_product_product`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_product_product` AS select `a`.`productid` AS `productid`,`a`.`classid` AS `classid`,`a`.`brandid` AS `brandid`,`a`.`productname` AS `productname`,`a`.`smallimg` AS `smallimg`,`a`.`bigimg` AS `bigimg`,`a`.`price` AS `price`,`a`.`description` AS `description`,`b`.`brandname` AS `brandname`,`c`.`classname` AS `classname` from ((`t_product_product` `a` join `t_product_brand` `b` on((`a`.`brandid` = `b`.`brandid`))) join `t_product_class` `c` on((`a`.`classid` = `c`.`classid`)));

SET FOREIGN_KEY_CHECKS = 1;
