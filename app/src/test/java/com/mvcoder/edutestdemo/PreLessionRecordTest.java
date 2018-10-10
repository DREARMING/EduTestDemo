package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.PreLessionRecord;
import com.mvcoder.edutestdemo.beans.User;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PreLessionRecordTest {

    @Test
    public void testPreLession(){

        Gson gson =  GsonUtil.getInstance().prettyPrintGson();

        MResponse<PreLessionRecord> response = new MResponse<>();
        response.setCode(200);
        PreLessionRecord record = new PreLessionRecord();
        record.setRecordId(10010);
        response.setData(record);

        MResponse<String> temp = new MResponse<>();
        temp.setCode(200);
        temp.setData("rtmp://192.168.1.110:3000/1hbuoq");

        MResponse<List<PreLessionRecord>> listMResponse = new MResponse<>();
        listMResponse.setCode(200);
        listMResponse.setData(getRecordList());

        String result = gson.toJson(listMResponse);
        System.out.println(result);

    }

    private PreLessionRecord getRecord(){
        PreLessionRecord record = new PreLessionRecord();
        record.setPublisherId(1);
        record.setPublisherName("edu_guolin");
        record.setCreatTime(Calendar.getInstance().getTime());
        record.setAvatar("");
        record.setLessionId(1);
        record.setCourseId(1);
        record.setCourseName("大学英语");
        Calendar preCalendar = Calendar.getInstance();
        preCalendar.set(Calendar.HOUR_OF_DAY,20);
        preCalendar.set(Calendar.MINUTE,30);
        record.setPreparedTime(preCalendar.getTime());
        record.setState(0);
        record.setLiveUrl("rtmp://192.168.1.110/kskjdhfkhsj");
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("edu_guolin");
        user1.setAvartar("xx");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("edu_hongyang");
        user2.setAvartar("xx");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        record.setMemberList(userList);

        return record;
    }

    private List<PreLessionRecord> getRecordList(){
        List<PreLessionRecord> list = new ArrayList<>();

        PreLessionRecord record = new PreLessionRecord();
        record.setPublisherId(1);
        record.setPublisherName("edu_guolin");
        record.setCreatTime(Calendar.getInstance().getTime());
        record.setAvatar("");
        record.setLessionId(1);
        record.setCourseId(1);
        record.setCourseName("大学英语");
        Calendar preCalendar = Calendar.getInstance();
        preCalendar.set(Calendar.HOUR_OF_DAY,20);
        preCalendar.set(Calendar.MINUTE,30);
        record.setPreparedTime(preCalendar.getTime());
        record.setState(0);
        record.setLiveUrl("rtmp://192.168.1.110/kskjdhfkhsj");
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("edu_guolin");
        user1.setAvartar("xx");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("edu_hongyang");
        user2.setAvartar("xx");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        record.setMemberList(userList);
        list.add(record);

        PreLessionRecord record2 = new PreLessionRecord();
        record2.setPublisherId(2);
        record2.setPublisherName("edu_hongyang");
        record2.setCreatTime(Calendar.getInstance().getTime());
        record2.setAvatar("");
        record2.setLessionId(3);
        record2.setCourseId(4);
        record2.setCourseName("Java语言学习");
        Calendar preCalendar2 = Calendar.getInstance();
        preCalendar2.set(Calendar.HOUR_OF_DAY,21);
        preCalendar2.set(Calendar.MINUTE,30);
        record2.setPreparedTime(preCalendar2.getTime());
        record2.setState(0);
        record2.setLiveUrl("rtmp://192.168.1.110/1hbuoq");
        User user3 = new User();
        user3.setUserId(1);
        user3.setUsername("edu_guolin");
        user3.setAvartar("xx");

        User user4 = new User();
        user4.setUserId(2);
        user4.setUsername("edu_hongyang");
        user4.setAvartar("xx");

        List<User> userList2 = new ArrayList<>();
        userList2.add(user3);
        userList2.add(user4);

        record2.setMemberList(userList2);

        list.add(record2);

        return list;
    }
}
