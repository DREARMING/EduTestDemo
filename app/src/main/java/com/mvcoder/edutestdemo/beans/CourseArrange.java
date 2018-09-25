package com.mvcoder.edutestdemo.beans;

/**
 * 课程信息安排
 */
public class CourseArrange {

    private int arrageId;
    //课节信息id
    private int lessionInfoId;
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

    /*enum DAYS{

        MONDAY(0, "周一"),THUESDAY(1, "周二"),WEDSDAY(2, "周三");

        private String day;
        private int index;

        @Override
        public String toString() {
            return day;
        }

        DAYS(int index, String name){
            this.day = name;
            this.index = index;
        }
    }*/

}
