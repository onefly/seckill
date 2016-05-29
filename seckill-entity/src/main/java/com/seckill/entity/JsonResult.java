package com.seckill.entity;

/**
 * json 数据封装对象
 * Created by caozhifei on 2016/5/28.
 */
public class JsonResult<T> {
    private boolean success;
    private T data;
    private String message;

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
