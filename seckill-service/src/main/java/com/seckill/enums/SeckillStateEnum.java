package com.seckill.enums;

/**
 * 秒杀结果枚举
 * Created by caozhifei on 2016/5/15.
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"C重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");
    private int state;
    private  String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    public static SeckillStateEnum stateOf(int index){
        for(SeckillStateEnum stateEnum : values()){
            if(stateEnum.getState() == index){
                return stateEnum;
            }
        }
        return null;
    }
}
