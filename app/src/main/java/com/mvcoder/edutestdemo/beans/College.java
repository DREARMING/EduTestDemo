package com.mvcoder.edutestdemo.beans;

import java.util.List;

public class College {

    private int collegeId;
    private String collegeName;

    //学院下面的专业，有可能空，暂时以下面哥参数为准，即以系为准
    private List<Major> majorList;
    private List<Department> departmentList;
}
