package com.mvcoder.edutestdemo.beans;

import java.util.List;

public class Floor {

    private int floorId;
    private ClassBuilding building;
    private String floorName;

    //楼层的教室列表
    private List<Room> roomList;
    private String extra;
}
