package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.Friend;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FriendTest {

    @Test
    public void testFriend(){
        Gson gson = GsonUtil.getInstance().fieldsGson(true,true,"userId");

        MResponse<List<Friend>> response = new MResponse<>();
        response.setCode(200);
        response.setData(getFriends());

        MResponse<Friend> friendMResponse = new MResponse<>();
        friendMResponse.setCode(200);
        friendMResponse.setData(getFriend());

        String result = gson.toJson(friendMResponse);
        System.out.println(result);

    }

    private Friend getFriend(){
        Friend friend1 = new Friend();
        friend1.setFriendId(2);
        friend1.setFriendName("edu_hongyang");
        friend1.setNickname("鸿洋");
        friend1.setAvatar("xxx");
        return friend1;
    }


    private List<Friend> getFriends(){
        List<Friend> list = new ArrayList<>();

        Friend friend1 = new Friend();
        friend1.setFriendId(2);
        friend1.setFriendName("edu_hongyang");
        friend1.setNickname("鸿洋");
        friend1.setAvatar("xxx");
        list.add(friend1);



        Friend friend2 = new Friend();
        friend2.setFriendId(3);
        friend2.setFriendName("edu_xiaocang");
        friend2.setNickname("小苍");
        friend2.setAvatar("xxx");
        list.add(friend2);

        return list;
    }

}
