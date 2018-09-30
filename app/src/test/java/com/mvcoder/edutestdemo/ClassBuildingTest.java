package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.ClassBuilding;
import com.mvcoder.edutestdemo.beans.Floor;
import com.mvcoder.edutestdemo.beans.Room;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClassBuildingTest {

    @Test
    public void testBuilding(){
        Gson gson = GsonUtil.getInstance().fieldsGson(true,true,"baseObjId");
        //ClassBuilding building = getClassBuilding();
        List<ClassBuilding> building = getClassBuildings();
        //String result = gson.toJson(building);
       // System.out.println(result);

        List<Room> clsRoomList = new ArrayList<>();
        List<Floor> floorList = new ArrayList<>();

        ClassBuilding building1 = building.get(1);
        floorList = building1.getFloorList();
        //clsRoomList = floorList.get(0).getRoomList();

        MResponse<List<ClassBuilding>> mResponse = new MResponse<>();
        mResponse.setCode(200);
        building.get(0).setFloorList(null);
        building.get(1).setFloorList(null);
        mResponse.setData(building);

        String roomResult = gson.toJson(mResponse);
        System.out.println(roomResult);
    }


    private ClassBuilding getClassBuilding(){
        ClassBuilding building = new ClassBuilding();
        building.setBuildingId(1);
        building.setBuildingName("一教");
        building.setScroolId(1);

        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor();
        floor1.setFloorId(1);
        floor1.setFloorName("一楼");
        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Room room = new Room();
            room.setRoomId(i + 1);
            room.setRoomName(floor1.getFloorId() + "0" + room.getRoomId());
            room.setType(0);
            room.setCapacity(30);
            roomList.add(room);
        }
       // LitePal.saveAll(roomList);
        floor1.setRoomList(roomList);
        floorList.add(floor1);
       // floor1.saveOrUpdate("floorId=?", floor1.getFloorId()+"");

        Floor floor2 = new Floor();
        floor2.setFloorId(2);
        floor2.setFloorName("二楼");
        List<Room> roomList2 = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            Room room = new Room();
            room.setRoomId(i + 1);
            room.setRoomName(floor2.getFloorId() + "0" + (room.getRoomId() - 4));
            room.setType(0);
            room.setCapacity(30);
            roomList2.add(room);
        }
       // LitePal.saveAll(roomList2);
        floor2.setRoomList(roomList2);
        floorList.add(floor2);
        //floor2.saveOrUpdate("floorId=?", floor2.getFloorId()+"");

        building.setFloorList(floorList);

        return building;
    }

    private List<ClassBuilding> getClassBuildings(){
        int buildingNum = 2;
        int floorNum = 2;
        int roomNum = 3;

        int roomID = 1;
        List<ClassBuilding> buildingList = new ArrayList<>(buildingNum);
        for(int i = 0; i < buildingNum; i++){
            ClassBuilding building = new ClassBuilding();
            building.setBuildingId(i + 1);
            building.setBuildingName((i+1) + "教");
            building.setScroolId(1);
            List<Floor> floorList = new ArrayList<>();
            for(int j=0; j < floorNum; j++){
                Floor floor1 = new Floor();
                floor1.setBuildingId(building.getBuildingId());
                floor1.setFloorId(i * floorNum + j + 1);
                floor1.setFloorName((j + 1) + "楼");
                // floor1.setBuilding(building);
                List<Room> roomList = new ArrayList<>();
                for(int k = 0; k < roomNum; k++){
                    Room room = new Room();
                    room.setRoomId(roomID++);
                    room.setFloorId(floor1.getFloorId());
                    int roomNumber = (int) (room.getRoomId() % roomNum);
                    int floorNumber = (int) (floor1.getFloorId() % floorNum);
                    room.setRoomName((floorNumber % floorNum == 0?floorNum:floorNumber) + "0" + (roomNumber == 0? roomNum:roomNumber));
                    room.setType(0);
                    room.setCapacity(30);
                    room.setLastModified(System.currentTimeMillis());
                    room.setState(0);
                    roomList.add(room);
                }
               // LitePal.saveAll(roomList);
                floor1.setRoomList(roomList);
                //floor1.saveOrUpdate("floorId=?",floor1.getFloorId()+"");
                floorList.add(floor1);
            }
            building.setFloorList(floorList);
            buildingList.add(building);
            //building.saveOrUpdate("buildingId=?",building.getBuildingId()+"");
        }
        return buildingList;
    }
}
