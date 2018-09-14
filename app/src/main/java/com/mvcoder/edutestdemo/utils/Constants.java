package com.mvcoder.edutestdemo.utils;

/**
 * Created by 爱的LUICKY on 2017/4/7.
 */

public class Constants {

    public static final String PACKAGE_NAME = "com.creator.tymanagersystem";

    public static final String NETWORK_UNAVILABLE = "网络连接失败，请重试";
    public static final String NETWORK_TIME_OUT = "请求超时，请检查与服务器的连接";
    public static final String NETWORK_SERVER_ERROR = "服务器内部异常";

    public static final int throttleFirstTime = 1000;

    public static final String cacheFileName = "mcache";

    public static final String DEFAULT_DEVICE_NAME = "PCBox";

    public static final long OVERTIME_BY_COMMAND = 1000 * /*60 * 2*/ 15;

    public static final int RUNNING = 1;
    public static final int CLOSE = 0;
    public static final int DISCONNECTION = 0xFF;
    public static final int STARTINIG = 2;
    public static final int CLOSEING = 3;

    public static final int CONTROL_TYPE_CLOSE = 0;
    public static final int CONTROL_TYPE_OPEN_SHORT_TOUCH = 1;
    public static final int CONTROL_TYPE_RESTART = 2;
    public static final int CONTROL_TYPE_OPEN_LONG_TOUCH = 3;

    public static final int OPEN_IN_LONG_TOUCH_MODE = 1;
    public static final int RXCODE_PC_STATE_BACK = 15;



    public static boolean updateRoomContent = false;


    public static final int LEVEL_SHARE = 2;

    //当正在选择会议成员的时候，为true。
    public static volatile boolean isSelectedMeetingMember = false;
    public static class RxCode{

        //string
        public static final int UPDATE_DEVICE_LIST = 1;
        public static final int SERVICE_RECONNECT = 2;

        public static final int LOGIN_STATE = 7;


        //Synchronized

        //设备
        public static final int SYNCHRONIZE_ADD_DEVICE = 3;
        public static final int SYNCHRONIZE_RENAME_DEVICE = 4;
        public static final int SYNCHRONIZE_DEL_DEVICE = 5;

        //PC
        public static final int SYNCHRONIZE_UPD_CHANNEL_SETTINGS = 8;

        //GROUP
        public static final int SYNCHRONIZE_ADD_GROUP = 9;
        public static final int SYNCHRONIZE_DEL_GROUP = 10;
        public static final int SYNCHRONIZE_RENAME_GROUP = 11;
        public static final int SYNCHRONIZE_ADD_PC_TO_GROUP = 12;
        public static final int SYNCHRONIZE_RM_PC_FROM_GROUP = 13;


        public static final int PUSH_NUM = 2000;
        //指令
        public static final int SYNCHRONIZE_EXE_COMMAND = 15;
        public static final int PUSH_PC_STATE_BACK = SYNCHRONIZE_EXE_COMMAND;


        public static final int SYN_INNER_NUM = 3000;

        //deviceType 设备同步指令返回，进行内部同步，即 PCControlPresenter 改变了内存后，通知其他页面进行刷新
        public static final int SYNCHRONIZE_INNER_ADD_DEVICE = SYNCHRONIZE_ADD_DEVICE + SYN_INNER_NUM ;
        public static final int SYNCHRONIZE_INNER_RENAME_DEVICE = SYNCHRONIZE_RENAME_DEVICE + SYN_INNER_NUM;
        public static final int SYNCHRONIZE_INNER_UPD_CHANNEL_SETTINGS = SYNCHRONIZE_UPD_CHANNEL_SETTINGS + SYN_INNER_NUM;

        public static final int SYNCHRONIZE_INNER_DEL_DEVICE = SYNCHRONIZE_DEL_DEVICE + SYN_INNER_NUM;

        //groupType 分组同步指令返回，进行内部同步，即 PCControlPresenter 改变了内存后，通知其他页面进行刷新
        public static final int SYNCHRONIZE_INNER_ADD_GROUP = SYNCHRONIZE_ADD_GROUP + SYN_INNER_NUM ;
        public static final int SYNCHRONIZE_INNER_RENAME_GROUP = SYNCHRONIZE_RENAME_GROUP + SYN_INNER_NUM;
        public static final int SYNCHRONIZE_INNER_DEL_GROUP = SYNCHRONIZE_DEL_GROUP + SYN_INNER_NUM;


        public static final int SYNCHRONIZE_INNER_ADD_PC_TO_GROUP = SYNCHRONIZE_ADD_PC_TO_GROUP + SYN_INNER_NUM;
        public static final int SYNCHRONIZE_INNER_RM_PC_FROM_GROUP = SYNCHRONIZE_RM_PC_FROM_GROUP + SYN_INNER_NUM;


        //OutVideoFragment页面内部同步
        public static final int APP_INNER_DEL_DEVICE = SYNCHRONIZE_DEL_DEVICE + 1000; //PASS
        public static final int APP_INNER_ADD_DEVICE = SYNCHRONIZE_ADD_DEVICE + 1000;  //PASS

    }


    public static class StateCode{
        public static final int OK = 200;

    }


}
