package com.seckill.entity;

/**
 * Created by Administrator on 2017/9/10.
 */
public class SplitTable {
    /**
     * 拆分ID
     */
    private long splitId;
    /**
     * 拆分表后缀
     */
    protected String tableSuffix;
    /**
     * 表数量
     */
    protected int tableNum = 1;

    public long getSplitId() {
        return splitId;
    }

    public void setSplitId(long splitId) {
        this.splitId = splitId;
    }

    public String getTableSuffix() {
        return String.valueOf(splitId % tableNum);
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}
