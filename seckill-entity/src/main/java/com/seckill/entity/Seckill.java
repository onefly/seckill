package com.seckill.entity;

import java.util.Date;

/**
 * Created by caozhifei on 2016/5/15.
 */
public class Seckill {
    private long id;
    private String name;
    private int stock;
    private Date startTime;
    private Date endTime;
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", created=" + created +
                '}';
    }
}
