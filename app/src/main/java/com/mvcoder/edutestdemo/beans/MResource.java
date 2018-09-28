package com.mvcoder.edutestdemo.beans;

import java.util.Date;

/**
 * 不用存数据库,资源搜索可以模糊查询发布者名字和资源名字
 */
public class MResource {

    private int resId;
    private int type; //资源类型，文档还是视频
    private String resUrl; //文档是下载url，视频是播放地址

    private String resName;
    private String resAbstract;
    private String publishName;

    private int readNums;
    private int starNums;
    private int donwloadNums;

    private Date publishDate;

    private boolean isStar;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAbstract() {
        return resAbstract;
    }

    public void setResAbstract(String resAbstract) {
        this.resAbstract = resAbstract;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public int getReadNums() {
        return readNums;
    }

    public void setReadNums(int readNums) {
        this.readNums = readNums;
    }

    public int getStarNums() {
        return starNums;
    }

    public void setStarNums(int starNums) {
        this.starNums = starNums;
    }

    public int getDonwloadNums() {
        return donwloadNums;
    }

    public void setDonwloadNums(int donwloadNums) {
        this.donwloadNums = donwloadNums;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }
}
