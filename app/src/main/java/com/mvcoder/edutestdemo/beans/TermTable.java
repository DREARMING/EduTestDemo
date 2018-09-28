package com.mvcoder.edutestdemo.beans;

import org.litepal.annotation.Column;

import java.util.Date;

public class TermTable {
    //学期id
    private int termId;
    //学期名字
    private String termName;

    //学期起始日期
    private Date startDate;

    //学期天数
    private int days;

    private int weekNums;

    //学期结束日期
    private Date endDate;

    private int timeTableId;

    @Column(ignore = true)
    private TimeTable timeTable;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getWeekNums() {
        return weekNums;
    }

    public void setWeekNums(int weekNums) {
        this.weekNums = weekNums;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
