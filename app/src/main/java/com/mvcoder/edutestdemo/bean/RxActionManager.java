package com.mvcoder.edutestdemo.bean;

/**
 * Created by mvcoder on 2017/9/28.
 */

/**
 * 管理订阅的接口类
 * @param <T>
 */
public interface RxActionManager<T> {

    void add(T tag, BaseSubscriber subscriber);
    void remove(T tag);
    void cancel(T tag);
    void cancelAll();

    //新版本
    void add(T prefix, T tag, BaseSubscriber subscriber);
    void remove(T prefix, T tag);

    /**
     *
     * @param prefix
     * @param tag  tag == null时，清除该前缀所有订阅
     */
    void cancel(T prefix, T tag);

}