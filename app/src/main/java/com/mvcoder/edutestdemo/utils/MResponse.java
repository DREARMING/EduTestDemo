package com.mvcoder.edutestdemo.utils;

import java.io.Serializable;

/**
 * Created by 爱的LUICKY on 2017/4/11.
 */

public class MResponse<T> implements Serializable {

    private final static int OK = 200;

    public int code;
    public String msg;
    public T data;

    public boolean success(){
        if(code == OK) return true;
        return false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
