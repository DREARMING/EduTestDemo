package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.LiveVideo;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LiveVideoTest {

    @Test
    public void testLiveVideo() {
        Gson gson = GsonUtil.getInstance().prettyPrintGson();
        MResponse<List<LiveVideo>> response = new MResponse<>();
        response.setCode(200);
        response.setData(getLiveVideo());
        System.out.println(gson.toJson(response));
    }

    private List<LiveVideo> getLiveVideo() {
        List<LiveVideo> list = new ArrayList<>();

        LiveVideo video = new LiveVideo();
        video.setVideoId(1);
        video.setCourseId(1);
        video.setClassRoomId(4);
        video.setTeacherId(1);
        video.setTeacherName("郭霖");
        video.setThumbUrl("");
        video.setVideoTitle("趣味大学英语");
        video.setVideoUrl("rtmp://192.168.1.110");
        video.setViewerNums(2);
        video.setVideoAbstract("该课程由xxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        list.add(video);

        LiveVideo video2 = new LiveVideo();
        video2.setVideoId(2);
        video2.setCourseId(2);
        video2.setClassRoomId(5);
        video2.setTeacherId(2);
        video2.setTeacherName("鸿洋");
        video2.setThumbUrl("");
        video2.setVideoTitle("趣味大学数学");
        video2.setVideoUrl("rtmp://192.168.1.110");
        video2.setViewerNums(2);
        video2.setVideoAbstract("该课程由xxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        list.add(video2);

        return list;
    }

}
