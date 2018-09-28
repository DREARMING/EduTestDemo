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

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public int getStudyYears() {
        return studyYears;
    }

    public void setStudyYears(int studyYears) {
        this.studyYears = studyYears;
    }
}
