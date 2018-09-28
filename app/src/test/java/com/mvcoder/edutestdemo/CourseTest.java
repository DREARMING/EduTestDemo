package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.Course;
import com.mvcoder.edutestdemo.utils.GsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTest {

    @Test
    public void testCourse(){
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        String result = gson.toJson(getCourse());
        System.out.println(result);
    }

    private String[] courseNames = new String[]{"大学英语","高等数学","毛泽东思想","Java","Android"};

    private List<Course> getCourse(){
        List<Course> courseList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Course course = new Course();
            course.setCourseId(i+1);
            course.setCourseName(courseNames[i]);
            course.setHours(30);
            course.setLevel(0);
            course.setLevelName("大一");
            course.setType(0);
            course.setTypeName("公共必修课");
            courseList.add(course);
        }
        return courseList;
    }

}
