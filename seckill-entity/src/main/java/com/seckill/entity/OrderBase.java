package com.seckill.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/10.
 */
public class OrderBase {
    private Long id;
    private String json;
    private Byte status;
    private String workerIp;
    private Date created;
    private Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getWorkerIp() {
        return workerIp;
    }

    public void setWorkerIp(String workerIp) {
        this.workerIp = workerIp;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderBase{");
        sb.append("id=").append(id);
        sb.append(", json='").append(json).append('\'');
        sb.append(", status=").append(status);
        sb.append(", workerIp='").append(workerIp).append('\'');
        sb.append(", created=").append(created);
        sb.append(", modified=").append(modified);
        sb.append('}');
        return sb.toString();
    }
}
