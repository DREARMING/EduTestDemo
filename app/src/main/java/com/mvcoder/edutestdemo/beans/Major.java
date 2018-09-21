package com.mvcoder.edutestdemo.beans;

import java.util.List;

/**
 * 专业信息
 */
public class Major {
    private int majorId;
    private String majorName;
    private College college;
    private Department department;

    //专业下面的年级
    private List<Grade> gradeList;
    //学年
    private int studyYears;



}
