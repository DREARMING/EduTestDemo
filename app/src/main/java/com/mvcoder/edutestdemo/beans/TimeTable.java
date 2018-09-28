package com.mvcoder.edutestdemo.beans;

import java.util.List;

/**
 * 作息表，描述某学期的上课时间安排、包括学期的始末、周末
 */
public class TimeTable {

    private int timeTableId;
    private String tableName;

    private int lessionNums;


    //每一节课的时间安排
    private List<LessionTimeInfo> lessionTimeList;

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public int getLessionNums() {
        return lessionNums;
    }

    public void setLessionNums(int lessionNums) {
        this.lessionNums = lessionNums;
    }


    public List<LessionTimeInfo> getLessionTimeList() {
        return lessionTimeList;
    }

    public void setLessionTimeList(List<LessionTimeInfo> lessionTimeList) {
        this.lessionTimeList = lessionTimeList;
    }
}
