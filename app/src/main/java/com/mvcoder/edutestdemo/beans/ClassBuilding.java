package com.mvcoder.edutestdemo.beans;

import org.litepal.crud.LitePalSupport;

import java.util.List;

/**
 * 教学楼
 */
public class ClassBuilding extends LitePalSupport{

    //学校id、分校
    private int scroolId;

    private int buildingId;

    private String buildingName;

    private List<Floor> floorList;

    public int getScroolId() {
        return scroolId;
    }

    public void setScroolId(int scroolId) {
        this.scroolId = scroolId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
