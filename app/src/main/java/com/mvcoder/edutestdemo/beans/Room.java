package com.mvcoder.edutestdemo.beans;

import org.litepal.crud.LitePalSupport;

public class Room extends LitePalSupport{

    /*@Column(nullable = false, unique = true)*/
    private int roomId;

    private Floor floor;

    /*@Column(nullable = false)*/
    private String roomName;
    //教室类型
    private int type;

    private int capacity; //教室或者寝室的人数容量，暂时可以返回空

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
