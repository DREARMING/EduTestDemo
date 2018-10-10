package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.MResource;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MResourceTest {

    @Test
    public void getResource() {
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        MResponse<List<MResource>> response = new MResponse<>();
        response.setCode(200);
        response.setData(getResourceList());
        String result = gson.toJson(response);
        System.out.println(result);
    }

    private List<MResource> getResourceList() {
        List<MResource> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            MResource resource = new MResource();
            resource.setResId(i);
            resource.setResName("视频资源Title" + i);
            resource.setType(0); //暂定0是视频资源,1是文档资源
            resource.setDonwloadNums(2);
            resource.setReadNums(2);
            resource.setStar(i % 2 == 1);
            resource.setStarNums(2);
            resource.setPublishDate(Calendar.getInstance().getTime());
            resource.setResAbstract("视频资源简介............");
            resource.setPublishName("admin");
            resource.setPublishId(1);
            resource.setResUrl("http://videourl");
            list.add(resource);
        }

        for (int j = 3; j < 5; j++) {
            MResource resource = new MResource();
            resource.setResId(j);
            resource.setResName("文档资源Title" + (j - 2));
            resource.setType(1); //暂定0是视频资源
            resource.setDonwloadNums(2);
            resource.setReadNums(2);
            resource.setStar(j % 2 == 1);
            resource.setStarNums(2);
            resource.setPublishDate(Calendar.getInstance().getTime());
            resource.setResAbstract("文档资源简介............");
            resource.setPublishName("admin");
            resource.setPublishId(1);
            resource.setResUrl("http://documentdonwloadurl");
            list.add(resource);
        }
        return list;
    }
}
