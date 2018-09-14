package com.mvcoder.edutestdemo.bean;

import java.io.ByteArrayOutputStream;

/**
 * Created by mvcoder on 2017/10/26.
 */

public class IOBuffer extends ByteArrayOutputStream {

    public int readInt(){
        return readInt(false);
    }

    /**
     * 读取一个整数
     * @param isClear if true,清楚内部Buf中的前4个字节，false 则不清除
     * @return
     */
    public int readInt(boolean isClear){
        if(count < 4) {
            throw new IllegalArgumentException("readInt() methos error! IOBuffer 不足4个字节，无法读取整形");
        }
        byte[] intBytes = new byte[4];
        System.arraycopy(buf,0,intBytes,0,intBytes.length);
        if(isClear) {
            System.arraycopy(buf, 4, buf, 0, buf.length - 4);
            count -= 4;
        }
        return byte2Int(intBytes);
    }

    /**
     *
     * @param buffer
     */
    public void readBytes(byte[] buffer){
        if(buffer == null || buffer.length > buf.length) {
            throw new IllegalArgumentException("没有足够的字节填充buffer");
        }
        System.arraycopy(buf,0,buffer,0,buffer.length);
        System.arraycopy(buf,buffer.length,buf,0,buf.length - buffer.length);
        count -= buffer.length;
    }



    public void readBytes(byte[] buffer,int skipBytes){
        if(buffer == null || (buffer.length + skipBytes) > buf.length) {
            throw new IllegalArgumentException("没有足够的字节填充buffer");
        }
        System.arraycopy(buf,skipBytes,buffer,0,buffer.length);
        System.arraycopy(buf,buffer.length + skipBytes,buf,0,buf.length - buffer.length - skipBytes);
        count -= (buffer.length + skipBytes);
    }

    private int byte2Int(byte[] data){
        if(data == null || data.length != 4) throw new IllegalArgumentException("byteArray must not be null and data's length must be 4");
        int len = ((data[0] & 0xFF) << 24) + ((data[1] & 0xFF) << 16) + ((data[2] & 0xFF) << 8) + (data[3] & 0xFF);
        return len;
    }
}
