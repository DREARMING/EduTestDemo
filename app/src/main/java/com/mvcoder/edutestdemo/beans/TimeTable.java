package com.mvcoder.edutestdemo.beans;

import java.util.Date;
import java.util.List;

/**
 * 作息表，描述某学期的上课时间安排、包括学期的始末、周末
 */
public class TimeTable {

    private int timeTableId;
    private String tableName;
    //学期id
    private int termId;
    private String termName;
    //指每日课节数
    private int lessionNums;
    //一学期多少周
    private int weekNums;
    //学期开始时间
    private Date startDate;
    private Date endDate;

    //每一节课的时间安排
    private List<LessionTimeInfo> lessionTimeList;
}
