package com.mvcoder.edutestdemo.manager;

import android.text.TextUtils;

import com.mvcoder.edutestdemo.bean.BaseSubscriber;
import com.mvcoder.edutestdemo.rxbus.RxBus;
import com.mvcoder.edutestdemo.utils.Constants;
import com.mvcoder.edutestdemo.utils.ExceptionHandle;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.utils.MResponse;
import com.mvcoder.edutestdemo.utils.Network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mvcoder.edutestdemo.utils.Constants.RXCODE_DOWNLOAD_FILE;
import static com.mvcoder.edutestdemo.utils.Constants.RXCODE_UPLOAD_FILE;

public class FileManager {

   public void downloadFile(int roomId, String filename, String savePath, int recordId) {
        if (TextUtils.isEmpty(filename)) throw new IllegalArgumentException("downloadUrl不能为Null");
        Call<ResponseBody> call = Network.getInstance().fileApi().downloadFile(roomId,filename);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean flag =  writeFileToDisk(savePath, response.body());
                    if(flag) {
                        RxBus.getDefault().post(Constants.RXCODE_DOWNLOAD_FILE,new FileInfo(recordId, true));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RxBus.getDefault().post(RXCODE_DOWNLOAD_FILE, new FileInfo(recordId,false));
            }
        });
    }

   public  void uploadFile(int roomId, String filepath, int recordId) {
        if (TextUtils.isEmpty(filepath)) return;
        File file = new File(filepath);
        if (!file.exists()) throw new IllegalArgumentException("文件不存在或者读取文件权限不足");
        String filename = getFilename(filepath);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("inputFile", filename,
                RequestBody.create(MediaType.parse("multipart/form-data"), file));
        Network.getInstance().fileApi().uploadFile(roomId,filePart).subscribeOn(Schedulers.io())
                .subscribe((new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        LogUtil.d("上传成功");
                        RxBus.getDefault().post(RXCODE_UPLOAD_FILE, new FileInfo(recordId, true));
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        LogUtil.d("上传文件失败");
                        RxBus.getDefault().post(RXCODE_UPLOAD_FILE, new FileInfo(recordId, false));
                    }
                }));
    }

    private boolean writeFileToDisk(String savePath, ResponseBody body) {
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(savePath);
            is = body.byteStream();
            byte[] buffer = new byte[4 * 1024];
            int readSize = -1;
            while ((readSize = is.read(buffer)) != -1){
                fos.write(buffer,0,readSize);
            }
            fos.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    public String getFilename(String filePath) {
        if (TextUtils.isEmpty(filePath)) return null;
        int index = filePath.lastIndexOf("/");
        if (index != -1) {
            String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
            return filename;
        }
        return null;
    }

    public static class FileInfo {

        int recordId;
        boolean success;

        public FileInfo(int recordId, boolean success) {
            this.recordId = recordId;
            this.success = success;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
