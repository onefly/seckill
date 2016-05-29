package com.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by caozhifei on 2016/5/29.
 */
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisDao(String ip,int port) {
        this.jedisPool = new JedisPool(ip,port);
    }
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId){
        //redis 操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = getCacheKey(seckillId);
                //并没有实现内部序列化操作
                //采用自定义序列化
                byte[] data = jedis.get(key.getBytes());
                if(data != null){
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(data,seckill,schema);
                    //seckill 反序列化完成
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {

        }
        return null;
    }
    public String putSeckill(Seckill seckill){
        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String key = getCacheKey(seckill.getId());
                //并没有实现内部序列化操作
                //采用自定义序列化
                byte[] data =ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 2*60;
                String result = jedis.setex(key.getBytes(),timeout,data);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    private String getCacheKey(long seckillId){
        return "seckill:"+seckillId;
    }
}
