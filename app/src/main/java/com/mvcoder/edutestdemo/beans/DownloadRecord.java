package com.mvcoder.edutestdemo.beans;

import java.util.Date;

public class DownloadRecord {

    //谁的下载记录表
    private int userId;

    private int resId;
    private int type; //资源类型，文档还是视频

    private String resName;
    private String resAbstract;
    private String publishName;

    private int readNums;
    private int starNums;
    private int donwloadNums;

    private Date publishDate;

    private boolean isStar;

    private String resPath;
}
