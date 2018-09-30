package com.mvcoder.edutestdemo.apis;

import com.mvcoder.edutestdemo.beans.User;
import com.mvcoder.edutestdemo.utils.MResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    @FormUrlEncoded
    @POST("/login")
    Observable<MResponse<User>> login(@Field("number") String number,@Field("password") String password);

    @FormUrlEncoded
    @POST("/logout")
    Observable<MResponse> login(@Field("userId") int userId);
}
