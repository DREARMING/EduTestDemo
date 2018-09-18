package com.mvcoder.edutestdemo.bean;

import com.google.gson.annotations.Expose;

public class FileMsg extends Message{

    private String fileType;	//语音是audio
    private String filename;	//不是uuid，是真正的文件名字，语音文件可以为NULL
    private boolean isShow;
    private int fileSize;
    private String downloadUrl;
    private String extraInfo;

    @Expose
    private String playPath;

    @Expose
    private int state;      // 0代表未操作，1代表正在操作，2代表成功，3代表失败


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPlayPath() {
        return playPath;
    }

    public void setPlayPath(String playPath) {
        this.playPath = playPath;
    }
}
