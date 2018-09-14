package com.mvcoder.edutestdemo.bean;

/**
 * Created by mvcoder on 2017/11/23.
 */

public class SynchronizedItem {

    private int order;
    private int flag;
    private byte[] content;
    private int relativeId = -1;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }
}
