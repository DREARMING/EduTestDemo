package com.mvcoder.edutestdemo.beans;

/**
 * 课程信息安排
 */
public class CourseArrange {

    private int arrageId;
    //课节信息id
    private int lessionInfoId;
    //学期id
    private int termId;
    //周几
    private int day;
    //课室id
    private int roomId;
    //科目id
    private int courseId;
    //开始周
    private int startWeek;
    //结束周
    private int endWeek;
    //任课老师id
    private int teacherId;
    //班级id
    private int schoolClassId;

    transient private LessionTimeInfo lessionTimeInfo;
    transient Room room;
    transient Course course;

    /*个人选修和通识课的查表问题，尚未解决*/

    public int getArrageId() {
        return arrageId;
    }

    public void setArrageId(int arrageId) {
        this.arrageId = arrageId;
    }

    public int getLessionInfoId() {
        return lessionInfoId;
    }

    public void setLessionInfoId(int lessionInfoId) {
        this.lessionInfoId = lessionInfoId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public LessionTimeInfo getLessionTimeInfo() {
        return lessionTimeInfo;
    }

    public void setLessionTimeInfo(LessionTimeInfo lessionTimeInfo) {
        this.lessionTimeInfo = lessionTimeInfo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}
