package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.User;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testUser(){
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        MResponse<List<User>> mResponse = new MResponse<>();
        mResponse.setCode(200);
        mResponse.setData(getUserList());
        String result = gson.toJson(mResponse);
        System.out.println(result);
    }

    private User getUser(){
        User user = new User();
        user.setUserId(1);
        user.setUsername("edu_guolin");
        user.setAvartar("");
        user.setSex(1);
        user.setEmail("1258233333@qq.com");
        user.setType(2);
        user.setNickname("郭霖");
        user.setPhone("13111111111");
        user.setPost("副院长");
        user.setJobTitle("Android高级讲师");
        user.setNumber("10086");
        return user;
    }

    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserId(1);
        user.setUsername("edu_guolin");
        user.setAvartar("");
        user.setSex(1);
        user.setEmail("1258233333@qq.com");
        user.setType(2);
        user.setNickname("郭霖");
        user.setPhone("13111111111");
        user.setPost("副院长");
        user.setJobTitle("Android高级讲师");
        user.setNumber("10086");
        userList.add(user);

        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("edu_hongyang");
        user2.setAvartar("");
        user2.setSex(1);
        user2.setEmail("1258233332@qq.com");
        user2.setType(2);
        user2.setNickname("鸿洋");
        user2.setPhone("13111111112");
        user2.setPost("副院长");
        user2.setJobTitle("Android高级讲师");
        user2.setNumber("10010");
        userList.add(user2);

        User user3 = new User();
        user3.setUserId(3);
        user3.setUsername("edu_xiaocang");
        user3.setAvartar("");
        user3.setSex(2);
        user3.setEmail("1258233331@qq.com");
        user3.setType(2);
        user3.setNickname("小苍");
        user3.setPhone("13111111113");
        user3.setPost("教授");
        user3.setJobTitle("毛概讲师");
        user3.setNumber("10000");
        userList.add(user3);

        return userList;
    }
}
