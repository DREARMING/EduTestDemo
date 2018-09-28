package com.mvcoder.edutestdemo.beans;

import java.util.Date;
import java.util.List;

public class PreLessionRecord {

    //备课记录id, 即ChatRoomId
    private int recordId;
    //课表记录id
    private int lessionId;
    private String lessionName;
    private int publisherId;
    private String publisherName;
    private String avatar;
    private Date creatTime;
    private int state; //备课的三种状态：未开始、进行中、已结束

    private Date preparedTime;
    private String liveUrl; //流地址

    //备课参与人
    private List<User> memberList;


    //备课过程中，产生的文件存放的路径
    private String fileDir;



}
