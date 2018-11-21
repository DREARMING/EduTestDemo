package com.mvcoder.edutestdemo.bean;

public class Recorder {

    float time;
    String filePath;

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Recorder(float time, String filePath) {
        this.time = time;
        this.filePath = filePath;
    }
}
