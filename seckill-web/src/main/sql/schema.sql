-- ----------------------------
-- Table structure for `seckill`
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) DEFAULT '' COMMENT '商品名称',
  `stock` int(10) unsigned DEFAULT NULL COMMENT '商品库存',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1', '1000元秒杀iphone6', '98', '2016-05-15 00:00:00', '2016-05-17 00:00:00', '2016-05-15 12:23:04');
INSERT INTO `seckill` VALUES ('2', '200元秒杀小米4', '200', '2016-05-16 00:00:00', '2016-05-17 00:00:00', '2016-05-15 12:23:04');
INSERT INTO `seckill` VALUES ('3', '100元秒杀ipad', '300', '2016-05-16 00:00:00', '2016-05-17 00:00:00', '2016-05-15 12:23:04');
INSERT INTO `seckill` VALUES ('4', '10元秒杀红米', '400', '2016-05-16 00:00:00', '2016-05-17 00:00:00', '2016-05-15 12:23:04');

-- ----------------------------
-- Table structure for `success_killed`
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL COMMENT '状态 ',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1', '15099999999', '1', '2016-05-15 15:44:13');
INSERT INTO `success_killed` VALUES ('1', '15888888888', '1', '2016-05-15 20:47:43');
