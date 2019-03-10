package com.seckill.entity;

/**
 * Created by Administrator on 2017/9/10.
 */
public class SplitTable {
    /**
     * ���ID
     */
    private long splitId;
    /**
     * ��ֱ��׺
     */
    protected String tableSuffix;
    /**
     * ������
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
