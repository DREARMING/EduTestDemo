package com.mvcoder.edutestdemo.beans;

import org.litepal.crud.LitePalSupport;

import java.util.List;


public class Floor extends LitePalSupport {

    /*@Column(nullable = false, unique = true)*/
    private int floorId;

    /*@Column(nullable = false)*/
    private String floorName;

    private ClassBuilding building;

    private String testColumn33;

    //楼层的教室列表
    private List<Room> roomList;

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public ClassBuilding getBuilding() {
        return building;
    }

    public void setBuilding(ClassBuilding building) {
        this.building = building;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getTestColumn33() {
        return testColumn33;
    }

    public void setTestColumn33(String testColumn33) {
        this.testColumn33 = testColumn33;
    }
}
