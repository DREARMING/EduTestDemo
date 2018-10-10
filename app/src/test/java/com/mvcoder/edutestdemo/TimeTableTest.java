package com.mvcoder.edutestdemo;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvcoder.edutestdemo.beans.LessionTimeInfo;
import com.mvcoder.edutestdemo.beans.TimeTable;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeTableTest {

    @Test
    public void testTimeTable(){
        Gson gson = fieldsGson(true,true,"baseObjId");
        String  result = gson.toJson(getTimeTable());
        MResponse<TimeTable> mResponse = new MResponse<>();
        mResponse.setCode(200);
        mResponse.setData(getTimeTable());
        System.out.println(gson.toJson(mResponse));
    }

    private Gson fieldsGson(boolean isPrettyPrint, final boolean isExclusive, final String... fields){
        GsonBuilder builder = new GsonBuilder().setDateFormat("HH:mm");
        builder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                for (int i = 0 ; i < fields.length ; i++){
                    if(f.getName().equals(fields[i])) {
                        if(isExclusive) return true;
                        else return false;
                    }
                }
                return !isExclusive;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        if(isPrettyPrint)
            builder.setPrettyPrinting();
        return builder.create();
    }



    private String[] lessionTimes = new String[]{
            "8:00-8:50",
            "9:00-9:50",
            "10:00-10:50",
            "11:00-11:50",
            "13:30-14:20",
            "14:30-15:20",
            "15:30-16:20",
            "16:30-17:20"
    };

    private TimeTable getTimeTable(){
        TimeTable timeTable = new TimeTable();
        timeTable.setLessionNums(8);
        timeTable.setTimeTableId(1);
        timeTable.setTableName("xx大学作息安排表");
        List<LessionTimeInfo> lessionTimeInfos = new ArrayList<>();
        for(int i = 0; i < timeTable.getLessionNums(); i++){
            LessionTimeInfo info = new LessionTimeInfo();
            info.setIndex(i);
            info.setLessionId(i+1);
            info.setTimeTableId(timeTable.getTimeTableId());
            info.setLessionName("第" + (i+1) + "节");
            String[] lessTimes = lessionTimes[i].split("-");
            Date startTime = convertToDate(lessTimes[0]);
            Date endTime = convertToDate(lessTimes[1]);
            info.setStartTime(startTime);
            info.setEndTime(endTime);
            lessionTimeInfos.add(info);
        }
        timeTable.setLessionTimeList(lessionTimeInfos);
        return timeTable;
    }

    private Date convertToDate(String time){
        if(time ==null || !time.contains(":")) return null;
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.HOUR,hour);
        calendar.set(Calendar.MINUTE,minutes);
        return calendar.getTime();
    }

}
