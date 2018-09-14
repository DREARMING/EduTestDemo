package com.mvcoder.edutestdemo.services;

import com.mvcoder.edutestdemo.bean.PushItem;
import com.mvcoder.edutestdemo.bean.SynchronizedItem;
import com.mvcoder.edutestdemo.rxbus.RxBus;
import com.mvcoder.edutestdemo.utils.Constants;

import java.util.Arrays;

/**
 * Created by mvcoder on 2017/11/12.
 */

public class TcpHandler implements ITCPHandler {

    private final  byte[] heartBeatBytes = new byte[] {0x00, 0x01};
    private final byte[] sychronizeBytes = new byte[] {0x00, 0x01};
    private final byte[] PushBytes = new byte[] {0x00, 0x03};
    private final byte[] HEAD = new byte[]{(byte) 0xFA, (byte) 0XFB};
    private final byte[] TAIL = new byte[]{(byte) 0xFC, (byte) 0XFD};

    private final int HEART_TYPE = 1;
    private final int SYCHRONIZED_TYPE = 2;
    private final int PUSH_TYPE = 3;

    private int flag = 0;

    public TcpHandler(int flag){
        this.flag = flag;
    }

    @Override
    public void handle(byte[] command) {
        if(!checkCommand(command)) return;
        byte[] commandType = new byte[2];
        System.arraycopy(command, 2,commandType, 0, commandType.length);

        int type = ((commandType[0] & 0xff) << 8) + (commandType[1] & 0xff);

        byte[] keycode = new byte[2];
        System.arraycopy(command,4,keycode,0,keycode.length);

        int flag = (keycode[0] & 0xff)  + ((keycode[1] & 0xff) << 8);

        //LogUtil.d("TCP RECEIVE FLAG :" + flag);

        byte[] commandOrder = new byte[2];
        System.arraycopy(command,6,commandOrder,0,commandOrder.length);

        int order = (commandOrder[0] & 0xff)  + ((commandOrder[1] & 0xff) << 8);

        byte[] content = new byte[command.length - 10];
        System.arraycopy(command,8,content,0,content.length);

        if(type == SYCHRONIZED_TYPE){
            dealSychronize(flag, order, content);
        }else if(type == PUSH_TYPE){
            //dealPush(order, content);
            dealPush(order,content);
        }
    }

    private void dealPush(int order, byte[] content){
        PushItem item = new PushItem(content);
        RxBus.getDefault().post(Constants.RxCode.PUSH_NUM + order, item);
    }

    private void dealSychronize(int flag, final int order, byte[] content){
        final SynchronizedItem item = new SynchronizedItem();
        item.setFlag(flag);
        item.setOrder(order);
        if(content != null && content.length >= 2){
            int id = content[0] & 0xff + (content[1] & 0xff << 8);
            item.setRelativeId(id);
        }
        item.setContent(content);
        if(shouldSyn(flag,order)){
            RxBus.getDefault().post(order,item);
        }
    }

    private boolean shouldSyn(int flag, int order){
        if(flag != this.flag) return true;
        if(order == Constants.RXCODE_PC_STATE_BACK) return true;
        return false;
    }

    /*private void dealPush(int order, byte[] content){
        try {
            PushItem item = new PushItem();
            item.setOrder(order);
            item.setCommand(new String(content,"utf-8"));
            RxBus.getDefault().post(Constants.RxCode.PUSH_MEETING,item);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*/

    private boolean checkCommand(byte[] commands){
        if(commands == null || commands.length < 10) return false;
        byte[] head = new byte[2];
        System.arraycopy(commands,0,head,0,head.length);
        byte[] tail = new byte[2];
        System.arraycopy(commands,commands.length - tail.length,tail,0,tail.length);
        if(!Arrays.equals(head,HEAD) || !Arrays.equals(tail,TAIL)){
            return false;
        }
        return true;
    }
}
