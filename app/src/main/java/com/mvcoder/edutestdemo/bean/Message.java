package com.mvcoder.edutestdemo.bean;

public class Message {

    private int senderId;
    private int chatRoomId;
    private long sendTime;

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }
}
