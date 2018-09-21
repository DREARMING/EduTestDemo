package com.dou361.ijkplayer.bean;

import java.io.Serializable;

public class Resolution implements Serializable {

    //stream id
    private int id;
    /**
     * 流名称
     */
    private String stream;
    //视频地址
    private String url;
    private boolean isSelect;
    private String videoTitle;

    /**
     * 留着备用
     */
    private String remark;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
