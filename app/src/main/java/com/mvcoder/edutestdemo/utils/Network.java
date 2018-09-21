package com.mvcoder.edutestdemo.utils;

import android.content.Context;

//import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvcoder.edutestdemo.api.ChatRoomApi;
import com.mvcoder.edutestdemo.api.FileApi;
import com.mvcoder.edutestdemo.bean.BaseSubscriber;
import com.mvcoder.edutestdemo.bean.RxActionManager;
import com.mvcoder.edutestdemo.rxbus.RxBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mvcoder on 2017/4/10.
 */

public class Network implements RxActionManager<String> {

    private Map<String,List<BaseSubscriber>> subscriptionManager;

    private Map<String,List<BaseSubscriber>> newSubscriptionManger = new HashMap<>();
    private Map<String,HashSet<String>> prefixRelations;

    private static final int DEFAULT_SIZE = 20 * 1024 * 1024;

    private Retrofit retrofit;

    private OkHttpClient client;

    private static Network network;

    private Network(){
        subscriptionManager = new HashMap<>();
        newSubscriptionManger = new HashMap<>();
        prefixRelations = new HashMap<>();
    }

    public void init(Context context){
        initOkhttpClient(context);
        retrofit = createRetrofit();
    }

    public static Network getInstance(){
        if(network == null){
            synchronized (Network.class){
                if(network == null){
                    network = new Network();
                }
            }
        }
        return network;
    }


    public Retrofit getRetrofit(){
        return retrofit;
    }

    private Retrofit createRetrofit(){
        // String url = PreferenceUtil.IPSetting.getIP();
        String url = "192.168.1.67";
        int port = 3001;
        Gson gson = new GsonBuilder().setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://" + url + ":" + port+"/")
                .client(client)
                .build();
        return retrofit;
    }

    private OkHttpClient initOkhttpClient(Context context){
        File file = new File(context.getCacheDir(), Constants.cacheFileName);
        Cache cache = new Cache(file,DEFAULT_SIZE);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                //.addInterceptor(new HttpInfoInterceptor())
                //网络请求和响应 日志打印
                //.addInterceptor(new HttpLoggingInterceptor(new HttpLogger()))
                //.addInterceptor(new TokenInterceptor())
                // .cookieJar(new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(context)))
                .build();
        return client;
    }

    private HashMap<String,Object> map = new HashMap<>();

    private  <T> T Api(Class<T> clazz){
        Object obj =  map.get(clazz.getSimpleName());
        if(obj == null){
            T api = retrofit.create(clazz);
            map.put(clazz.getSimpleName(),api);
        }
        return (T) map.get(clazz.getSimpleName());
    }

    public ChatRoomApi chatRoomApi(){
        return Api(ChatRoomApi.class);
    }

    public FileApi fileApi(){
        return Api(FileApi.class);
    }

 /*   public LoginApi userApi(){
        return Api(LoginApi.class);
    }


    public DeviceApi deviceApi(){
        return Api(DeviceApi.class);
    }

    public GroupApi groupApi(){return Api(GroupApi.class);}

    public PCApi pcApi(){return Api(PCApi.class);}*/

    /*
    public StreamApi streamApi(){
        return Api(StreamApi.class);}

 */

    public void release(){
        map.clear();
        map = null;
        client = null;
        retrofit = null;
        network = null;
        RxBus.getDefault().unRegister(this);
    }


    public void changeServerIP(){
        LogUtil.d("changeServerIP");
        map.clear();
        retrofit = createRetrofit();
    }

    /**
     *
     * @param tag tag的标签形式： 页面tag:方法tag 比如IRStudyActivity:requestIR
     * @param subscriber
     */
    @Override
    public void add(String tag, BaseSubscriber subscriber) {
        List<BaseSubscriber> list = subscriptionManager.get(tag);
        if(list == null){
            list = new ArrayList<>();
            subscriptionManager.put(tag,list);
        }
        list.add(subscriber);
        /*String[] tags = tag.split(":");
        if(tags != null && tags.length == 2){
            List<BaseSubscriber> pageList = subscriptionManager.get(tags[0]);
            if(pageList == null){
                pageList = new ArrayList<>();
                subscriptionManager.put(tags[0],pageList);
            }
            pageList.add(subscriber);
        }*/
    }

    @Override
    public void remove(String tag) {
        if(subscriptionManager.get(tag) != null){
            subscriptionManager.remove(tag);
        }
    }

    /**
     *
     * @param tag
     */
    @Override
    public void cancel(String tag) {
        List<BaseSubscriber> list = subscriptionManager.get(tag);
        if(list != null && list.size() > 0){
            for(BaseSubscriber subscriber : list){
                Disposable disposable = subscriber.getDisposable();
                if(disposable != null && !disposable.isDisposed()){
                    disposable.dispose();
                }
                //MaterialDialog dialog = subscriber.getProgressDialog();
               // if(dialog != null && dialog.isShowing()){
                //    dialog.dismiss();
               // }
            }
            subscriptionManager.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        for(String key : subscriptionManager.keySet()){
            cancel(key);
        }

        //新版本
        for(String key : newSubscriptionManger.keySet()){
            cancelTag(key);
        }
        prefixRelations.clear();
    }

    @Override
    public void add(String prefix, String tag, BaseSubscriber subscriber) {
        String label = prefix + ":" + tag;
        List<BaseSubscriber> list = newSubscriptionManger.get(label);
        if(list == null){
            list = new ArrayList<>();
            newSubscriptionManger.put(label,list);
        }
        list.add(subscriber);
        HashSet<String> childTags = prefixRelations.get(prefix);
        if(childTags == null){
            childTags = new HashSet<>();
            prefixRelations.put(prefix,childTags);
        }
        childTags.add(tag);
    }

    @Override
    public void remove(String prefix, String tag) {
        String label = prefix + ":" + tag;
        if(newSubscriptionManger.get(label) != null){
            newSubscriptionManger.remove(label);
        }
        HashSet<String> childTags = prefixRelations.get(prefix);
        if(childTags != null){
            childTags.remove(tag);
            if(childTags.size() == 0){
                prefixRelations.remove(prefix);
            }
        }
    }

    @Override
    public void cancel(String prefix, String tag) {
        if(tag == null){
            HashSet<String> childTags = prefixRelations.get(prefix);
            if(childTags != null && childTags.size() > 0){
                for(String childTag : childTags){
                    String lable = prefix + childTag;
                    cancelTag(lable);
                }
            }
            prefixRelations.remove(prefix);
        }else{
            String lable = prefix + ":" + tag;
            cancelTag(lable);
            HashSet<String> childTags = prefixRelations.get(prefix);
            if(childTags != null && childTags.size() > 0){
                childTags.remove(tag);
                if(childTags.size() == 0){
                    prefixRelations.remove(prefix);
                }
            }
        }
    }

    private void cancelTag(String tag){
        List<BaseSubscriber> list = newSubscriptionManger.get(tag);
        if(list != null && list.size() > 0){
            for(BaseSubscriber subscriber : list){
                Disposable disposable = subscriber.getDisposable();
                if(disposable != null && !disposable.isDisposed()){
                    disposable.dispose();
                }
                /*MaterialDialog dialog = subscriber.getProgressDialog();
                if(dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }*/
            }
            newSubscriptionManger.remove(tag);
        }
    }
}
