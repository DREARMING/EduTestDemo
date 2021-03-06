package com.mvcoder.edutestdemo.rxbus;


/**
 * 线程处理类
 * @author wzg 2016/9/21
 */
public enum ThreadMode {

    /**
     * current thread
     */
    CURRENT_THREAD,

    /**
     * COMPULATION THREAD
     */
    COMPULATION_THREAD,

    /**
     * android main thread
     */
    MAIN,


    /**
     * new thread
     */
    NEW_THREAD,

    /**
     * io
     */
    IO

}
