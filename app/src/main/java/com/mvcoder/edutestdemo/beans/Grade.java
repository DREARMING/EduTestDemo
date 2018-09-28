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

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public List<SchoolClass> getClassList() {
        return classList;
    }

    public void setClassList(List<SchoolClass> classList) {
        this.classList = classList;
    }
}
