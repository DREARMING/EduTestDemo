package com.mvcoder.edutestdemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.mvcoder.edutestdemo.utils.LogUtil;

public class TestDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LogUtil.init(BuildConfig.DEBUG, TestDemoApp.class.getSimpleName());
    }
}
