package com.mvcoder.edutestdemo.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mvcoder on 2017/11/20.
 */

public class TokenInterceptor implements Interceptor {

    public volatile static int keyCode = 0;
    public volatile static String token = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder()
                .addHeader("platform".intern(),"Android Platform".intern());
        if(token != null) {
            builder.addHeader("token", token)
                    .build();
        }
        Request request = builder.build();
        return chain.proceed(request);


    }
}
