package com.mvcoder.edutestdemo.beans;

public class CourseEstimate {

    private long videoId;
    private long estimateId;
    private int type; //因为已播放的视频是资源，直播的视频是LiveVideo，主键不一致，需要用type区分
    private long userId;
    private String userName;
    private String content;
    private int score; //分数


    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public long getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(long estimateId) {
        this.estimateId = estimateId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
