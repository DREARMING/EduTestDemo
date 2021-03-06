package com.mvcoder.edutestdemo.api;

import com.mvcoder.edutestdemo.utils.MResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FileApi {

    @Multipart
    @POST("/upload/room_{roomId}")
    Observable<MResponse> uploadFile(@Path("roomId") int roomId, @Part MultipartBody.Part file);

    @GET("/download/room_{roomId}/{filename}")
    Call<ResponseBody> downloadFile(@Path("roomId") int roomId,@Path("filename") String filename);

}
