package com.mvcoder.edutestdemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.mvcoder.edutestdemo.utils.DBUtil;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.utils.Network;

import org.litepal.LitePal;

public class TestDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        DBUtil.getInstance().init(this,"db_edu.db");
        //DBUtils.getInstance().init(this,"db_edu");
        Utils.init(this);
        Network.getInstance().init(this);
        LogUtil.init(BuildConfig.DEBUG, TestDemoApp.class.getSimpleName());
    }
}
