package com.mvcoder.edutestdemo.beans;

import java.util.Date;

/**
 * 课节信息
 */
public class LessionTimeInfo {

    private int lessionId;
    //第几节/下标
    private int index;
    //课节名称，举例：第一节
    private String lessionName;
    //课节上课时间
    private Date startTime;
    //课节结束时间
    private Date endTime;
    private TimeTable timeTable;

    public int getLessionId() {
        return lessionId;
    }

    public void setLessionId(int lessionId) {
        this.lessionId = lessionId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLessionName() {
        return lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
