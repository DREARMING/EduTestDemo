package com.mvcoder.edutestdemo.beans;

import java.util.List;

/**
 * 系别信息
 */
public class Department {
    private int departMentId;
    private String departmentName;
    private College college;
    //系别下面的专业
    private List<Major> majorList;
}
