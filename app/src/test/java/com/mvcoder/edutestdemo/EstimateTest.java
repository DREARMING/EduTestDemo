package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.CourseEstimate;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EstimateTest {

    @Test
    public void testEstimate() {
        Gson gson = GsonUtil.getInstance().prettyPrintGson();
        MResponse<List<CourseEstimate>> mResponse = new MResponse<>();
        mResponse.setCode(200);
        mResponse.setData(getEsimates());
        String result = gson.toJson(mResponse);
        System.out.println(result);
    }

    private List<CourseEstimate> getEsimates() {
        List<CourseEstimate> list = new ArrayList<>();
        CourseEstimate estimate = new CourseEstimate();
        estimate.setVideoId(1);
        estimate.setUserId(1);
        estimate.setUserName("郭霖");
        estimate.setEstimateId(1);
        estimate.setContent("讲得很nice!!");
        estimate.setType(0);
        estimate.setScore(5);
        list.add(estimate);

        CourseEstimate estimate2 = new CourseEstimate();
        estimate2.setVideoId(1);
        estimate2.setUserId(3);
        estimate2.setUserName("小苍");
        estimate2.setEstimateId(2);
        estimate2.setContent("wonderful!!");
        estimate2.setType(0);
        estimate2.setScore(4);
        list.add(estimate2);

        return list;
    }

}
