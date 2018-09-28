package com.mvcoder.edutestdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.mvcoder.edutestdemo.bean.BaseSubscriber;
import com.mvcoder.edutestdemo.beans.ClassBuilding;
import com.mvcoder.edutestdemo.beans.Floor;
import com.mvcoder.edutestdemo.beans.Room;
import com.mvcoder.edutestdemo.utils.ExceptionHandle;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.utils.MResponse;
import com.mvcoder.edutestdemo.utils.Network;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private final static int RC_EXTERNAL_STORAGE_AND_RECORD = 100;

    @BindView(R.id.bt_chart)
    Button btChart;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_del)
    Button btDel;
    @BindView(R.id.bt_update)
    Button btUpdate;
    @BindView(R.id.bt_live)
    Button btLive;
    @BindView(R.id.bt_db)
    Button btDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPermission();
    }

    @AfterPermissionGranted(RC_EXTERNAL_STORAGE_AND_RECORD)
    private void requestPermission() {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "", RC_EXTERNAL_STORAGE_AND_RECORD, perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @OnClick(R.id.bt_chart)
    public void onViewClicked() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == RC_EXTERNAL_STORAGE_AND_RECORD) {
            LogUtil.d("权限被允许");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_EXTERNAL_STORAGE_AND_RECORD) {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this, "xxxx").build().show();
            }
        }
    }

    @OnClick({R.id.bt_add, R.id.bt_del, R.id.bt_update, R.id.bt_live,R.id.bt_db})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                addChatRoom();
                break;
            case R.id.bt_del:
                delChatRoom();
                break;
            case R.id.bt_update:
                updChatRoom();
                break;
            case R.id.bt_live:
                joinToLiveActivity();
                break;
            case R.id.bt_db:
                testDB2();
                break;
        }
    }

    private void joinToLiveActivity() {
        Intent intent = new Intent(this, LiveActivity.class);
        startActivity(intent);
    }

    private int chatRoomId = 1;
    private String members = "1,2";
    private String newMembers = "1,2,3";

    private void addChatRoom() {
        Network.getInstance().chatRoomApi().createChatRoom(chatRoomId, members)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if (mResponse.code == 200) {
                            ToastUtils.showShort("创建ChatRoom成功");
                        } else {
                            ToastUtils.showShort("创建ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }


    private void delChatRoom() {
        Network.getInstance().chatRoomApi().deleteChatRoom(chatRoomId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if (mResponse.code == 200) {
                            ToastUtils.showShort("删除ChatRoom成功");
                        } else {
                            ToastUtils.showShort("删除ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }

    private void updChatRoom() {
        Network.getInstance().chatRoomApi().updateChatRoom(chatRoomId, newMembers)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if (mResponse.code == 200) {
                            ToastUtils.showShort("更新ChatRoom成功");
                        } else {
                            ToastUtils.showShort("更新ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }

    private void testDB2() {
        ClassBuilding building = LitePal.find(ClassBuilding.class, 1, true);
        List<Floor>floorList = building.getFloorList();

        Floor floor = LitePal.findFirst(Floor.class, true);
        List<Room> roomList = floor.getRoomList();
        System.out.println(building.getBuildingName());


        building.setBuildingId(2);
        boolean flag = building.saveOrUpdate("buildingId=?","1");
        System.out.println(flag);
    }


    private void testDB() {
        ClassBuilding building = new ClassBuilding();
        building.setBuildingId(1);
        building.setBuildingName("一教");
        building.setScroolId(1);

        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor();
        floor1.setFloorId(1);
        floor1.setFloorName("一楼");
       // floor1.setBuilding(building);
        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Room room = new Room();
            room.setRoomId(i + 1);
            room.setRoomName(floor1.getFloorId() + "0" + room.getRoomId());
            room.setType(0);
            //room.setFloor(floor1);
            roomList.add(room);
        }
        LitePal.saveAll(roomList);
        floor1.setRoomList(roomList);
        floorList.add(floor1);
        floor1.saveOrUpdate("floorId=?", floor1.getFloorId()+"");

        Floor floor2 = new Floor();
        floor2.setFloorId(2);
        floor2.setFloorName("二楼");
        //floor2.setBuilding(building);
        List<Room> roomList2 = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            Room room = new Room();
            room.setRoomId(i + 1 - 4);
            room.setRoomName(floor2.getFloorId() + "0" + room.getRoomId());
            room.setType(0);
            //room.setFloor(floor2);
            roomList2.add(room);
        }
        LitePal.saveAll(roomList2);
        floor2.setRoomList(roomList2);
        floorList.add(floor2);
        floor2.saveOrUpdate("floorId=?", floor2.getFloorId()+"");

        building.setFloorList(floorList);

       boolean flag = building.saveOrUpdate("buildingId=?", building.getBuildingId() + "");
       LogUtil.d("flag : " + flag);
    }

}
