package com.mvcoder.edutestdemo.beans;

public class LiveVideo {

    private long videoId;

    private String videoTitle;

    private String videoUrl;

    private String thumbUrl;

    private int courseId;

    private int classRoomId;

    private long teacherId;

    private String teacherName;

    private String videoAbstract;

    private int viewerNums;

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getVideoAbstract() {
        return videoAbstract;
    }

    public void setVideoAbstract(String videoAbstract) {
        this.videoAbstract = videoAbstract;
    }

    public int getViewerNums() {
        return viewerNums;
    }

    public void setViewerNums(int viewerNums) {
        this.viewerNums = viewerNums;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
