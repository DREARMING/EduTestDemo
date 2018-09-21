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

}
