package com.mvcoder.edutestdemo.api;

import com.mvcoder.edutestdemo.utils.MResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChatRoomApi {

    @FormUrlEncoded
    @POST("chatroom/create")
    Observable<MResponse> createChatRoom(@Field("chatRoomId") int chatRoomId, @Field("members") String ids);

    @FormUrlEncoded
    @POST("chatroom/delete")
    Observable<MResponse> deleteChatRoom(@Field("chatRoomId") int chatRoomId);

    @FormUrlEncoded
    @POST("chatroom/update")
    Observable<MResponse> updateChatRoom(@Field("chatRoomId") int chatRoomId, @Field("members") String ids);

}
