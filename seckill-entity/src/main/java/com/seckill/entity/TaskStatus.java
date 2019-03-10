package com.seckill.entity;

/**
 * Created by Administrator on 2019/3/10.
 */
public enum TaskStatus {
    WAIT_RUN((byte)1,"�ȴ�ִ��"),
    RUNNING((byte)2,"����ִ��"),
    RUN_SUCCESS((byte)3,"ִ�гɹ�"),
    RUN_FAIL((byte)4,"ִ��ʧ��"),
    WAIT_RETRY((byte)5,"�ȴ�����"),
    RETRY((byte)6,"��������");
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
