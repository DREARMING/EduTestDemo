package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.CourseArrange;
import com.mvcoder.edutestdemo.utils.GsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseArrageTest {

    @Test
    public void testCourseArrage(){
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        String result = gson.toJson(getArrage());
        System.out.println(result);
    }

    private List<CourseArrange> getArrage(){
        List<CourseArrange> list = new ArrayList<>();
        list.addAll(getGuolinArrage());
        list.addAll(getHongyangArrage());
        list.addAll(getXiaoCangArrage());
        return list;
    }

    int arrageId = 1;

    private List<CourseArrange> getGuolinArrage(){
        List<CourseArrange> list = new ArrayList<>();

        CourseArrange arrange1 = getArrage(1,1,1,27,4,1,1,18,1);
        list.add(arrange1);

        CourseArrange arrange2 = getArrage(1,1,1,
                27,4,2,1,18,1);
        list.add(arrange2);

        CourseArrange arrange3 = getArrage(1,1,1,28,4,1,1,18,1);
        list.add(arrange3);

        CourseArrange arrange4 = getArrage(1,1,1,
                28,4,2,1,18,1);
        list.add(arrange4);

        //------------------------------------------------------------

        CourseArrange arrange5 = getArrage(1,1,3,
                27,8,1,2,19,5);
        list.add(arrange5);

        CourseArrange arrange6 = getArrage(1,1,3,
                27,8,2,2,19,5);
        list.add(arrange6);

        CourseArrange arrange7 = getArrage(1,1,3,
                28,8,3,2,19,5);
        list.add(arrange7);

        CourseArrange arrange8 = getArrage(1,1,3,
                28,8,4,2,19,5);
        list.add(arrange8);

        //--------------------------------------------------
        CourseArrange arrange9 = getArrage(1,1,3,
                28,5,5,1,18,2);
        list.add(arrange9);

        CourseArrange arrange10 = getArrage(1,1,3,
                28,5,6,1,18,2);
        list.add(arrange10);

        CourseArrange arrange11 = getArrage(1,1,2,
                27,3,3,1,18,2);
        list.add(arrange11);

        CourseArrange arrange12 = getArrage(1,1,2,
                27,3,4,1,18,2);
        list.add(arrange12);


        return list;
    }


    private List<CourseArrange> getHongyangArrage() {
        List<CourseArrange> list = new ArrayList<>();
        CourseArrange arrange1 = getArrage(1,2,2,
                27,2,3,1,18,4);
        list.add(arrange1);

        CourseArrange arrange2 = getArrage(1,2,2,
                27,2,4,1,18,4);
        list.add(arrange2);

        CourseArrange arrange3 = getArrage(1,2,3,
                28,1,7,1,18,4);
        list.add(arrange3);

        CourseArrange arrange4 = getArrage(1,2,3,
                28,1,8,1,18,4);
        list.add(arrange4);


        CourseArrange arrange5 = getArrage(1,2,5,
                27,5,1,1,18,2);
        list.add(arrange5);

        CourseArrange arrange6 = getArrage(1,2,5,
                27,5,2,1,18,2);
        list.add(arrange6);

        CourseArrange arrange7 = getArrage(1,2,5,
                28,5,3,1,18,2);
        list.add(arrange7);

        CourseArrange arrange8 = getArrage(1,2,5,
                28,5,4,1,18,2);
        list.add(arrange8);

        return list;
    }


    private List<CourseArrange> getXiaoCangArrage() {
        List<CourseArrange> list = new ArrayList<>();
        CourseArrange arrange1 = getArrage(1,3,4,
                27,8,1,1,18,3);
        list.add(arrange1);

        CourseArrange arrange2 = getArrage(1,3,4,
                27,8,2,1,18,3);
        list.add(arrange2);

        CourseArrange arrange3 = getArrage(1,3,4,
                28,8,3,1,18,3);
        list.add(arrange3);

        CourseArrange arrange4 = getArrage(1,3,4,
                28,8,4,1,18,3);
        list.add(arrange4);

        return list;
    }

    private CourseArrange getArrage(int termId,int tearchId, int day, int schoolClassId,
                                    int roomId,int lessionId, int startWeek, int endWeek,
                                    int courseId){
        CourseArrange arrange2 = new CourseArrange();
        arrange2.setArrageId(arrageId++);
        arrange2.setCourseId(courseId);
        arrange2.setDay(day);
        arrange2.setStartWeek(startWeek);
        arrange2.setEndWeek(endWeek);
        arrange2.setRoomId(roomId);
        arrange2.setSchoolClassId(schoolClassId);
        arrange2.setTermId(termId);
        arrange2.setTeacherId(tearchId);
        arrange2.setLessionInfoId(lessionId);
        return arrange2;
    }

}
