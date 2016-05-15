
CREATE TABLE seckill{
'id' bigint not null AUTO_INCREMENT comment 'ID',
'name' varchar(120) not null comment '商品名称',
'stock' int not null comment '库存',
'start_time' TIMESTAMP  not null comment '开始时间',
'end_time' TIMESTAMP not null comment '结束时间',
'created' TIMESTAMP not null DEFAULT  CURRENT_TIME COMMIT '创建时间',
PRIMARY KEY(seckill_id)
}ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT='秒杀库存表';


INSERT INTO
  seckill(name,stock,start_time,end_time,created)
  VALUES
  ('1000元秒杀iphone6',100,'2016-05-16 00:00:00','2016-05-17 00:00:00',now()),
  ('200元秒杀小米4',200,'2016-05-16 00:00:00','2016-05-17 00:00:00',now()),
  ('100元秒杀ipad',300,'2016-05-16 00:00:00','2016-05-17 00:00:00',now()),
  ('10元秒杀红米',400,'2016-05-16 00:00:00','2016-05-17 00:00:00',now());

 create table success_killed(
 'seckill_id' bigint not null comment '秒杀商品ID',
 'user_phone' bigint not null comment '用户手机号',
 'state' tinyint not null DEFAULT 0 comment '秒杀状态：0成功',
 'created' TIMESTAMP not null comment '创建时间',
 PRIMARY KEY (seckill_id,user_phone)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='秒杀成功明细';