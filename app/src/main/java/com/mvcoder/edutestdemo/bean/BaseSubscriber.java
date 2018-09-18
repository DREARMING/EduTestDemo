package com.mvcoder.edutestdemo.bean;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.mvcoder.edutestdemo.rxbus.RxBus;
import com.mvcoder.edutestdemo.utils.Constants;
import com.mvcoder.edutestdemo.utils.ExceptionHandle;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.utils.MResponse;
import com.mvcoder.edutestdemo.utils.Network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by mvcoder on 2017/9/28.
 */

public abstract class BaseSubscriber<T> implements Observer<T> {

    private Context context;
    private String progressTitle;
    private String progressContent;

    //取消订阅的页面标签
    private String prefix;

    //取消订阅的标签
    private String TAG;
   // private MaterialDialog progressDialog;
    private Disposable disposable;
    private static final int delayTime = 1000;
    private boolean shouldShowDialog = true;

    public BaseSubscriber(String TAG, Context progressBarContext, String title, String content){
        initData(null,TAG,progressBarContext,title,content);
    }

    public BaseSubscriber(String prefix, String TAG, Context progressBarContext, String title, String content){
        initData(prefix,TAG,progressBarContext,title,content);
    }

    public BaseSubscriber(Context progressBarContext, String title, String content){
        initData(null,null,progressBarContext,title,content);
    }

    public BaseSubscriber(Context progressBarContext){
        initData(null,null,progressBarContext,null,null);
    }

    public BaseSubscriber(String TAG, Context progressBarContext){
        initData(null,TAG,progressBarContext,null,null);
    }

    public BaseSubscriber(String prefix, String TAG, Context progressBarContext){
        initData(prefix,TAG,progressBarContext,null,null);
    }

    public BaseSubscriber(String TAG){
        initData(null ,TAG, null,null,null);
    }

    public BaseSubscriber(String prefixt, String TAG){
        initData(prefixt, TAG,null,null,null);
    }

    public BaseSubscriber(){}

    private void initData(String prefix, String TAG, Context progressBarContext, String title, String content){
        this.context = progressBarContext;
        this.progressTitle = title;
        this.progressContent = content;
        this.TAG = TAG;
        this.prefix = prefix;
    }

    @Override
    public void onSubscribe(Disposable s) {
        //网络不通时，直接返回
        if(!NetworkUtils.isConnected()){
            ToastUtils.showShort(Constants.NETWORK_UNAVILABLE);
            //手动取消订阅，避免后续的onError事件发生，因为手动调用onComplete方法无法阻止以后的事件
            if(!s.isDisposed()) s.dispose();
            return;
        }

        if(prefix == null) {
            if (TAG != null) {
                disposable = s;
                //管理订阅
                Network.getInstance().add(TAG, this);
            }
        }else{
            if(TAG != null){
                disposable = s;
                Network.getInstance().add(prefix,TAG,this);
            }
        }

        if(context != null){
           // showDialog();
        }

    }

    @Override
    public void onComplete() {
        //关闭Dialog
        //closeDialog();
        //移除订阅
        if(prefix == null) {
            if (TAG != null)
                Network.getInstance().remove(TAG);
        }else{
            if(TAG != null){
                Network.getInstance().remove(prefix,TAG);
            }
        }
    }

    public Disposable getDisposable(){
        return disposable;
    }
/*
    public MaterialDialog getProgressDialog(){
        //return progressDialog;
        return null;
    }*/

    /*private void showDialog(){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if(!TextUtils.isEmpty(progressTitle)){
            builder.title(progressTitle);
        }
        if(!TextUtils.isEmpty(progressContent)){
            builder.content(progressContent);
        }else{
            builder.content("Please wait...".intern());
        }
        builder.canceledOnTouchOutside(false);
        builder.progress(true,0);
        progressDialog = builder.show();
    }

    private void closeDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }*/

    @Override
    public void onError(Throwable t) {
        //打印Log
        if(t.getMessage() != null)
        LogUtil.d(t.getMessage());

        //移除订阅
        if(prefix == null) {
            if (TAG != null)
                Network.getInstance().remove(TAG);
        }else{
            if(TAG != null){
                Network.getInstance().remove(prefix,TAG);
            }
        }

        //如果dialog开启的话，关闭dialog
       // closeDialog();

        onError(ExceptionHandle.handleException(t));
    }

    @Override
    public void onNext(T value) {
        if(value instanceof MResponse){
            MResponse temp = (MResponse) value;
            if(!TextUtils.isEmpty(temp.msg) && temp.msg.contains("未登录".intern())){
                RxBus.getDefault().post(Constants.RxCode.LOGIN_STATE,-999);
                ((MResponse) value).setMsg("服务器开小差了，请稍后重试！".intern());
            }
        }
        onSuccess(value);
    }

    public abstract void onSuccess(T mResponse);

    public abstract void onError(ExceptionHandle.ResponeThrowable throwable);
}
