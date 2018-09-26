package com.mvcoder.edutestdemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mvcoder.edutestdemo.greendao.DaoMaster;
import com.mvcoder.edutestdemo.greendao.DaoSession;

public class DBUtils {

    private DaoMaster.DevOpenHelper helper;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    private static volatile DBUtils instance;

    public static DBUtils getInstance(){
        if(instance == null){
            synchronized (DBUtils.class){
                if(instance == null){
                    instance = new DBUtils();
                }
            }
        }
        return instance;
    }


    public void init(Context context, String dbname){
        helper = new DaoMaster.DevOpenHelper(context,dbname);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

}
