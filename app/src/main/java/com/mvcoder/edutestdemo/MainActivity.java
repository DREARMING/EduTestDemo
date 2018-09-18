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
import com.mvcoder.edutestdemo.utils.ExceptionHandle;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.utils.MResponse;
import com.mvcoder.edutestdemo.utils.Network;

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

    @OnClick({R.id.bt_add, R.id.bt_del, R.id.bt_update})
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
        }
    }

    private int chatRoomId = 1;
    private String members = "1,2";
    private String newMembers = "1,2,3";

    private void addChatRoom(){
        Network.getInstance().chatRoomApi().createChatRoom(chatRoomId,members)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if(mResponse.code == 200){
                            ToastUtils.showShort("创建ChatRoom成功");
                        }else{
                            ToastUtils.showShort("创建ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }


    private void delChatRoom(){
        Network.getInstance().chatRoomApi().deleteChatRoom(chatRoomId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if(mResponse.code == 200){
                            ToastUtils.showShort("删除ChatRoom成功");
                        }else{
                            ToastUtils.showShort("删除ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }

    private void updChatRoom(){
        Network.getInstance().chatRoomApi().updateChatRoom(chatRoomId,newMembers)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MResponse>() {
                    @Override
                    public void onSuccess(MResponse mResponse) {
                        if(mResponse.code == 200){
                            ToastUtils.showShort("更新ChatRoom成功");
                        }else{
                            ToastUtils.showShort("更新ChatRoom失败");
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable throwable) {
                        ToastUtils.showShort(throwable.toString());
                    }
                });
    }

}
