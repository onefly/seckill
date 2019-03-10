package com.seckill.entity;

/**
 * Created by Administrator on 2019/3/10.
 */
public enum TaskStatus {
    WAIT_RUN((byte)1,"等待执行"),
    RUNNING((byte)2,"正在执行"),
    RUN_SUCCESS((byte)3,"执行成功"),
    RUN_FAIL((byte)4,"执行失败"),
    WAIT_RETRY((byte)5,"等待重试"),
    RETRY((byte)6,"正在重试");
    private byte value;
    private String desc;

    TaskStatus(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
