package com.mvcoder.edutestdemo.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Course {

    //学科d
    @Id
    private long courseId;
    private String courseName;
    //科目类型：将来有可能存在公共必修，选修
    private int type;
    private String typeName;
    //学时
    private int hours;
    //大一、大二
    private int level;
    private String levelName;

    @Index
    private long lastModified;

    @Transient
    private int state;

    @Generated(hash = 503028059)
    public Course(long courseId, String courseName, int type, String typeName,
            int hours, int level, String levelName, long lastModified) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.type = type;
        this.typeName = typeName;
        this.hours = hours;
        this.level = level;
        this.levelName = levelName;
        this.lastModified = lastModified;
    }
    @Generated(hash = 1355838961)
    public Course() {
    }
    public long getCourseId() {
        return this.courseId;
    }
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public int getHours() {
        return this.hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getLevelName() {
        return this.levelName;
    }
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public long getLastModified() {
        return this.lastModified;
    }
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    @Keep
    public int getState() {
        return state;
    }

    @Keep
    public void setState(int state) {
        this.state = state;
    }
}
