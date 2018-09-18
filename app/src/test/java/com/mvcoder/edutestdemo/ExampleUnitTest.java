package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        /*TextMessage txtmsg = new TextMessage();
        txtmsg.setSenderId(1);
        txtmsg.setChatRoomId(1);
        txtmsg.setSendTime(System.currentTimeMillis());
        txtmsg.setContent("hello world");
        Gson gson = new Gson();
        String info = gson.toJson(txtmsg);
        System.out.println(info);*/

        Gson gson = GsonUtil.getInstance().prettyPrintGson();

        MResponse mResponse = new MResponse();
        mResponse.setCode(200);
        mResponse.setMsg("创建ChatRoom成功");
        mResponse.setData("");

        System.out.println(gson.toJson(mResponse));

        mResponse.setCode(-1);
        mResponse.setMsg("该chatRoom已经被创建");
        System.out.println(gson.toJson(mResponse));


    }
}