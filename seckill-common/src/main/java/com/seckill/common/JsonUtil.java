package com.seckill.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/27.
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static  <T> Object fromJson(String json,Class<T> type){
        try {
            return objectMapper.readValue(json,type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        for(int i=0;i<50;i++){
            jsonMap.put("key-"+i,"value-"+i);
        }
        String json = toJson(jsonMap);
        System.out.println("json = [" + json + "]");
        long t1 = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            Map<String,Object> map = (Map<String, Object>) fromJson(json,Map.class);
            if(map.containsKey("\"key-20\":\"value-20\"")){
                System.out.println("fromJson = [" + i + "]");
            }
        }
        long t2 = System.currentTimeMillis();
        long t3 = t2 - t1;


        long t4 = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            if(json.contains("\"key-20\":\"value-20\"")){
                System.out.println("contains = [" + i + "]");
            }
        }
        long t5 = System.currentTimeMillis();
        long t6 = t5 - t4;
        System.out.println("t3 = [" + t3 + "]");
        System.out.println("t6 = [" + t6 + "]");
    }
}
