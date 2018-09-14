package com.mvcoder.edutestdemo.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by mvcoder on 2017/1/16.
 * Log日志工具，封装logger
 */
public class LogUtil {

    /**
     * 初始化log工具，在app入口处调用
     *
     * @param isLogEnable 是否打印log
     */
    public static void init(final boolean isLogEnable,String TAG) {
        PrettyFormatStrategy formatStrategy =  PrettyFormatStrategy.newBuilder().tag(TAG)
                .build();
       Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
           @Override
           public boolean isLoggable(int priority, String tag) {
               return isLogEnable;
           }
       });
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
