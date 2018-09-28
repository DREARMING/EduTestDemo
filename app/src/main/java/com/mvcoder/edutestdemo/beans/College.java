package com.mvcoder.edutestdemo.beans;

import java.util.List;

public class College {

    private int collegeId;
    private String collegeName;

    //学院下面的专业，有可能空，暂时以下面哥参数为准，即以系为准
    private List<Major> majorList;

    private List<Department> departmentList;

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
