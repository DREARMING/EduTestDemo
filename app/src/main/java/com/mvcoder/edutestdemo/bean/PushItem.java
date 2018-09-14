package com.mvcoder.edutestdemo.bean;

/**
 * Created by mvcoder on 2018/3/21.
 */

public class PushItem {

    final byte[] content;

    public PushItem(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
