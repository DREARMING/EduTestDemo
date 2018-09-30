package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.Grade;
import com.mvcoder.edutestdemo.utils.GsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GradeTest {

    @Test
    public void testGrade(){
        Gson gson = GsonUtil.getInstance().fieldsGson(true,true,"baseObjId");
        List<Grade> gradeList = getGradeList();
        String result = gson.toJson(gradeList);
        System.out.println(result);
    }

    private String[] gradeNames = new String[]{
            "2017级","2018级","2019级","2020级"
    };

    private Date[] dates = new Date[]{
            new Date(2017 - 1900,8,1),
            new Date(2018 - 1900,8,1),
            new Date(2019 - 1900,8,1),
            new Date(2020 - 1900,8,1)
    };

    private List<Grade> getGradeList(){
        int gradeNum = 4;
        int gradeIdStep = 1;

        List<Grade> gradeList = new ArrayList<>();
        for(int n = 0; n < gradeNum; n++){
            Grade grade = new Grade();
            grade.setGradeId(gradeIdStep++);
            int gradeIndex = (int) ((grade.getGradeId() - 1) % 4);
            grade.setGradeName(gradeNames[gradeIndex]);
            grade.setEnrolDate(dates[gradeIndex]);
            gradeList.add(grade);
        }
        return null;
    }
}
