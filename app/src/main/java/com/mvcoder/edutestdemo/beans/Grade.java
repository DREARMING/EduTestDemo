package com.mvcoder.edutestdemo.beans;

import java.util.Date;
import java.util.List;

/**
 * 年级信息
 */
public class Grade {
    private int gradeId;
    private String gradeName;
    //开学时间
    private Date enrolDate;

    //班级信息
    private List<SchoolClass> classList;

}
