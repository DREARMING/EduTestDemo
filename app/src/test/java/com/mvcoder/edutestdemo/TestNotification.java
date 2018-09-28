package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.Notification;
import com.mvcoder.edutestdemo.utils.GsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestNotification {

    @Test
    public void testNotification(){
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        String result = gson.toJson(getNotifications());
        System.out.println(result);
    }

    private List<Notification> getNotifications(){
        List<Notification> notificationList = new ArrayList<>();
        for(int i = 1; i < 3;i ++){
            Notification notification = new Notification();
            notification.setNoticeId(i);
            notification.setType(1);
            notification.setTitle("文字公告" + i);
            notification.setContent("文字公告 " + i + " 内容");
            notification.setSchoolId(1);
            notification.setPublishDate(Calendar.getInstance().getTime());
            notification.setWebUrl("http://www.baidu.com");
            notificationList.add(notification);
        }

        for(int j=3; j < 5; j++){
            Notification notification = new Notification();
            notification.setNoticeId(j);
            notification.setType(8);
            notification.setTitle("图片公告" + j);
            notification.setContent("图片公告 " + j + " 内容");
            notification.setSchoolId(1);
            notification.setPublishDate(Calendar.getInstance().getTime());
            notification.setWebUrl("http://www.baidu.com");
            notificationList.add(notification);
        }
        return notificationList;
    }
}
