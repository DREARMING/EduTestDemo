package com.mvcoder.edutestdemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.mvcoder.edutestdemo.greendao.ClassBuildingDao;
import com.mvcoder.edutestdemo.greendao.DaoMaster;
import com.mvcoder.edutestdemo.greendao.FloorDao;
import com.mvcoder.edutestdemo.greendao.RoomDao;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, ClassBuildingDao.class, FloorDao.class, RoomDao.class);
    }



}
