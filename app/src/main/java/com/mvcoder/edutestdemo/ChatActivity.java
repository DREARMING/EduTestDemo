package com.mvcoder.edutestdemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.mvcoder.edutestdemo.bean.FileMsg;
import com.mvcoder.edutestdemo.bean.Message;
import com.mvcoder.edutestdemo.bean.Recorder;
import com.mvcoder.edutestdemo.bean.TextMessage;
import com.mvcoder.edutestdemo.manager.FileManager;
import com.mvcoder.edutestdemo.manager.MediaManager;
import com.mvcoder.edutestdemo.rxbus.RxBus;
import com.mvcoder.edutestdemo.rxbus.Subscribe;
import com.mvcoder.edutestdemo.rxbus.ThreadMode;
import com.mvcoder.edutestdemo.utils.Constants;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.edutestdemo.views.AudioRecorderButton;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.id_recorder_button)
    AudioRecorderButton mAudioRecorderButton;

    @BindView(R.id.rv_talk_records)
    RecyclerView rvTalkRecord;

    List<Recorder> mDatas = new ArrayList<>();

    List<Message> mMsgs = new ArrayList<>();
    @BindView(R.id.iv_keybroad)
    ImageView ivKeybroad;
    @BindView(R.id.bt_send)
    Button btSend;
    @BindView(R.id.ll_type_layout)
    LinearLayout llTypeLayout;

    @BindView(R.id.et_content)
    EditText etContent;

    private CommonAdapter<Recorder> adapter;

    private MultiItemTypeAdapter<Message> chatAdapter;

    private FileManager fileManager = new FileManager();

    private int mSenderId = 2;

    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chatroom/1/";

    private File mDir;

    boolean audioType = true;

    private Socket mSocket;

    private boolean isConnected = false;
    private boolean hasSendID = false;

    private static final String CHAT_SERVER_URL = "http://192.168.1.67:3001/";

    private static final String TXT_MSG = "new_txt_msg";
    private static final String FILE_MSG = "new_file_msg";
    private static final String SEND_USERID_MSG = "send_userid_msg";
    private static final String RECEIVE_USERID_MSG = "receive_userid_msg";

    private final int MSG_CONNECT = 1;
    private final int MSG_DISCONNECT = 2;
    private final int MSG_CONNECT_ERROR = 3;
    private final int MSG_TXT_RECEIVE = 4;
    private final int MSG_FILE_RECEIVE = 5;
    private final int MSG_USERID_RECEIVE = 6;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CONNECT:
                    on_connect_msg();
                    break;
                case MSG_DISCONNECT:
                    on_disconnect_msg();
                    break;
                case MSG_CONNECT_ERROR:
                    on_connect_eror_msg();
                    break;
                case MSG_TXT_RECEIVE:
                    TextMessage txtMsg = (TextMessage) msg.obj;
                    receiveTxtMsg(txtMsg);
                    break;
                case MSG_FILE_RECEIVE:
                    FileMsg fileMsg = (FileMsg) msg.obj;
                    receiveFileMsg(fileMsg);
                    break;
                case MSG_USERID_RECEIVE:
                    hasSendID = true;
                    break;
            }
        }
    };

    private void receiveTxtMsg(TextMessage textMessage) {
        LogUtil.d("receive txt msg : " + textMessage.getContent());
        addMsg(textMessage);
    }

    private void receiveFileMsg(FileMsg fileMsg) {
        LogUtil.d("receive file msg : " + fileMsg.getDownloadUrl());
        addMsg(fileMsg);
    }

    private void on_connect_msg() {
        if(mSocket.connected()) {
            mSocket.emit(SEND_USERID_MSG, mSenderId);
            Toast.makeText(this,
                    R.string.connect, Toast.LENGTH_LONG).show();
            isConnected = true;
        }
    }

    private void on_disconnect_msg() {
        isConnected = false;
        hasSendID = false;
        Toast.makeText(this, R.string.disconnect, Toast.LENGTH_LONG).show();
    }

    private void on_connect_eror_msg() {
        Toast.makeText(this,
                R.string.error_connect, Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        RxBus.getDefault().register(this);
        initData();
        initView();
    }

    private void initData() {
        mDir = new File(dir);
        if (mDir != null) mDir.mkdirs();
        try {
            mSocket = IO.socket(CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(mSocket == null) throw new IllegalStateException("socket init error!");
        registerSocketInfo();
    }

    private void registerSocketInfo() {
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on(RECEIVE_USERID_MSG, onReceiveUserID);
        mSocket.on(TXT_MSG, onNewTxtMessage);
        mSocket.on(FILE_MSG, onNewFileMessage);
        mSocket.connect();
    }

    private void unRegisterSocketInfo(){
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off(RECEIVE_USERID_MSG, onReceiveUserID);
        mSocket.off(TXT_MSG, onNewTxtMessage);
        mSocket.off(FILE_MSG, onNewFileMessage);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mHandler.sendEmptyMessage(MSG_CONNECT);
        }
    };

    private Emitter.Listener onReceiveUserID = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mHandler.sendEmptyMessage(MSG_USERID_RECEIVE);
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mHandler.sendEmptyMessage(MSG_DISCONNECT);
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mHandler.sendEmptyMessage(MSG_CONNECT_ERROR);
        }
    };

    private Emitter.Listener onNewTxtMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            String data = (String) args[0];
            TextMessage textMessage = GsonUtil.getInstance().defaultGson().fromJson(data, TextMessage.class);
            if(textMessage == null){
                LogUtil.d("receiveMsg error!!! gson can't convert it to TextMessage");
                return;
            }
            android.os.Message message = android.os.Message.obtain();
            message.what = MSG_TXT_RECEIVE;
            message.obj = textMessage;
            mHandler.sendMessage(message);
        }
    };

    private Emitter.Listener onNewFileMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            String data = (String) args[0];
            FileMsg fileMsg = GsonUtil.getInstance().defaultGson().fromJson(data, FileMsg.class);
            if(fileMsg == null){
                LogUtil.d("receiveMsg error!!! gson can't convert it to FileMsg");
                return;
            }
            android.os.Message message = android.os.Message.obtain();
            message.what = MSG_TXT_RECEIVE;
            message.obj = fileMsg;
            mHandler.sendMessage(message);
        }
    };


    private void initView() {

        rvTalkRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        chatAdapter = new MultiItemTypeAdapter<Message>(this, mMsgs);
        chatAdapter.addItemViewDelegate(new TextMsgItemDelegate());
        chatAdapter.addItemViewDelegate(new FileMsgDelegate());
        rvTalkRecord.setAdapter(chatAdapter);

        /*adapter = new CommonAdapter<Recorder>(this, R.layout.item_record, mDatas) {
            @Override
            protected void convert(ViewHolder holder, Recorder recorder, int position) {
                holder.setText(R.id.id_recorder_time, Math.round(recorder.getTime()) + "\"");
            }
        };

        rvTalkRecord.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
               *//* if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
                animation.start();*//*

                //播放音频  完成后改回原来的background
                *//*MediaManager.playPCM(mDatas.get(position).getFilePath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //mAnimView.setBackgroundResource(R.drawable.adj);
                    }
                });
*//*
                MediaManager.playSound(mDatas.get(position).getFilePath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //mAnimView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });*/

        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                /*//每完成一次录音
                Recorder recorder = new Recorder(seconds, filePath);
                mDatas.add(recorder);
                //更新adapter
                adapter.notifyDataSetChanged();

                rvTalkRecord.scrollToPosition(mDatas.size() - 1);*/

                FileMsg msg = new FileMsg();
                msg.setSenderId(mSenderId);
                msg.setChatRoomId(1);
                msg.setSendTime(System.currentTimeMillis());
                msg.setState(0);
                msg.setPlayPath(filePath);
                msg.setExtraInfo(Math.round(seconds) + "");
                msg.setFilename(fileManager.getFilename(filePath));
                msg.setFileType("audio");
                msg.setShow(true);

                if(!mSocket.connected() && hasSendID) {
                    mSocket.emit(FILE_MSG, GsonUtil.getInstance().defaultGson().toJson(msg));
                }

                addMsg(msg);
            }
        });
    }

    private void addMsg(Message msg){
        chatAdapter.getDatas().add(msg);
        chatAdapter.notifyDataSetChanged();
        rvTalkRecord.scrollToPosition(chatAdapter.getDatas().size() - 1);
    }

    /**
     * 根据生命周期 管理播放录音
     */
    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterSocketInfo();
        MediaManager.release();
        RxBus.getDefault().unRegister(this);
    }

    @OnClick({R.id.iv_keybroad, R.id.bt_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_keybroad:
                if(audioType){
                    llTypeLayout.setVisibility(View.VISIBLE);
                    mAudioRecorderButton.setVisibility(View.GONE);
                }else{
                    llTypeLayout.setVisibility(View.GONE);
                    mAudioRecorderButton.setVisibility(View.VISIBLE);
                }
                audioType = !audioType;
                break;
            case R.id.bt_send:
                sendText();
                break;
        }
    }

    private void sendText() {
        String text = etContent.getText().toString();
        if(TextUtils.isEmpty(text)) return;
        TextMessage msg = new TextMessage();
        msg.setSenderId(mSenderId);
        msg.setChatRoomId(1);
        msg.setContent(text);
        msg.setSendTime(System.currentTimeMillis());

        if(mSocket.connected() && hasSendID)
            mSocket.emit(TXT_MSG, GsonUtil.getInstance().defaultGson().toJson(msg));

        etContent.setText("");
        addMsg(msg);
    }


    class TextMsgItemDelegate implements ItemViewDelegate<Message> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_text_msg;
        }

        @Override
        public boolean isForViewType(Message item, int position) {
            return item instanceof TextMessage;
        }

        @Override
        public void convert(ViewHolder holder, Message message, int position) {
            TextMessage textMessage = (TextMessage) message;
            holder.setText(R.id.tv_content, textMessage.getContent());
            int avatarId = R.mipmap.icon;
            if (message.getSenderId() != mSenderId) {
                avatarId = R.mipmap.ic_icon2;
            }
            holder.setImageResource(R.id.id_icon, avatarId);
        }
    }

    class FileMsgDelegate implements ItemViewDelegate<Message> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_file_msg;
        }

        @Override
        public boolean isForViewType(Message item, int position) {
            return item instanceof FileMsg;
        }

        @Override
        public void convert(ViewHolder holder, Message message, int position) {
            FileMsg fileMsg = (FileMsg) message;
            int avatarId = R.mipmap.icon;
            if (message.getSenderId() != mSenderId) {
                avatarId = R.mipmap.ic_icon2;
            }
            holder.setImageResource(R.id.id_icon, avatarId);
            if ("audio".equals(fileMsg.getFileType())) {
                if (fileMsg.getState() == 0) {
                    fileMsg.setState(1);
                    if (fileMsg.getSenderId() == mSenderId) {
                        LogUtil.d("上传文件");
                        fileManager.uploadFile(fileMsg.getPlayPath(), position);
                        holder.setText(R.id.id_recorder_time, "上传语音文件中..");
                    } else {
                        LogUtil.d("下载文件");
                        File file = new File(mDir, fileManager.getFilename(fileMsg.getDownloadUrl()));
                        fileMsg.setPlayPath(file.getAbsolutePath());
                        fileManager.downloadFile(fileMsg.getDownloadUrl(), fileMsg.getPlayPath(), position);
                        holder.setText(R.id.id_recorder_time, "下载语音文件中..");
                    }
                } else if (fileMsg.getState() == 2) {
                    String seconds = fileMsg.getExtraInfo();
                    holder.setText(R.id.id_recorder_time, seconds + "\"");
                } else if (fileMsg.getState() == 1) {
                    if (fileMsg.getSenderId() == mSenderId) {
                        holder.setText(R.id.id_recorder_time, "上传语音文件中..");
                    } else {
                        holder.setText(R.id.id_recorder_time, "下载语音文件中..");
                    }
                } else if (fileMsg.getState() == 3) {
                    if (fileMsg.getSenderId() == mSenderId) {
                        holder.setText(R.id.id_recorder_time, "上传语失败");
                    } else {
                        holder.setText(R.id.id_recorder_time, "下载失败");
                    }
                }
            }
            holder.getView(R.id.id_recorder_length).setOnClickListener(null);
            holder.getView(R.id.id_recorder_length).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fileMsg.getState() == 2) {
                        MediaManager.playSound(fileMsg.getPlayPath(), new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                ToastUtils.showShort("播放完成");
                            }
                        });
                    } else {
                        ToastUtils.showShort("文件暂时无法播放, state ： " + fileMsg.getState());
                    }
                }
            });
        }
    }

    @Subscribe(code = Constants.RXCODE_UPLOAD_FILE, threadMode = ThreadMode.MAIN)
    public void uploadFileBack(FileManager.FileInfo fileInfo) {
        LogUtil.d("uploadFileBack");
        int position = fileInfo.getRecordId();
        Message msg = chatAdapter.getDatas().get(position);
        if(msg != null && msg instanceof FileMsg){
            FileMsg fileMsg = (FileMsg) msg;
            if(fileInfo.isSuccess()) {
                fileMsg.setState(2);
            }else{
                fileMsg.setState(3);
            }
            chatAdapter.notifyItemChanged(position);
        }
    }

    @Subscribe(code = Constants.RXCODE_DOWNLOAD_FILE, threadMode = ThreadMode.MAIN)
    public void downloadFileBack(FileManager.FileInfo fileInfo) {
        LogUtil.d("downloadFileBack");
        int position = fileInfo.getRecordId();
        Message msg = chatAdapter.getDatas().get(position);
        if(msg != null && msg instanceof FileMsg){
            FileMsg fileMsg = (FileMsg) msg;
            if(fileInfo.isSuccess()) {
                fileMsg.setState(2);
            }else{
                fileMsg.setState(3);
            }
            chatAdapter.notifyItemChanged(position);
        }

    }
}
