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

}
