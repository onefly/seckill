--秒杀执行存储过程
DELIMITER $$ --console;转换为 $$
--定义存储过程
-- row_count(); 返回上一条修改类型sql影响的行数

CREATE PROCEDURE seckill.execute_seckill
  (in v_seckill_id bigint,in v_phone bigint, in v_kill_time TIMESTAMP ,in v_state tinyint, out r_result int)
  BEGIN
 DECLARE  insert_count int DEFAULT 0;
    START TRANSACTION ;

    INSERT ignore into success_killed
      (seckill_id,user_phone,state,created)
      VALUES (v_seckill_id,v_phone,v_state,v_kill_time);

      SELECT row_count() into insert_count;

      IF (insert_count = 0) THEN
        ROLLBACK;
        set r_result = -1;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK;
        set r_result = -2;
      ELSE

        update seckill
        set stock = stock - 1
        where id = v_seckill_id
        and end_time > v_kill_time
        and start_time < v_kill_time
        and stock > 0;

        select row_count() into insert_count;

        IF(insert_count = 0) THEN
          ROLLBACK ;
          set r_result = 0;
        ELSEIF (insert_count < 0) THEN
          ROLLBACK ;
          set r_result = -2;
        ELSE
          COMMIT;
          set r_result = 1;
        END IF;
      END IF;
   END;
$$
--存储过程定义完成

DELIMITER ;
set @r_result = -3;
--执行存储过程
call execute_seckill(1,18888888888,now(),1,@r_result);

--获取结果
SELECT @r_result;
