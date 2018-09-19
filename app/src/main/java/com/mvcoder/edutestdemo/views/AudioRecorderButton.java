package com.mvcoder.edutestdemo.views;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mvcoder.edutestdemo.R;
import com.mvcoder.edutestdemo.manager.AudioManager;
import com.mvcoder.edutestdemo.manager.DialogManager;
import com.mvcoder.edutestdemo.utils.LogUtil;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class AudioRecorderButton extends android.support.v7.widget.AppCompatButton implements AudioManager.AudioStateListener /*AudioRecorderManager.AudioStateListener*/ {
    //手指滑动 距离
    private static final int DISTANCE_Y_CANCEL = 50;
    //状态
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_WANT_TO_CANCEL = 3;
    //当前状态
    private int mCurState = STATE_NORMAL;
    //已经开始录音
    private boolean isRecording = false;

    private DialogManager mDialogManager;
    private AudioManager mAudioManager;
    //private AudioRecorderManager mAudioRecorderManager;

    private int audioTime = 0;

    private float mTime;
    //是否触发onlongclick
    private boolean mReady;

    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDialogManager = new DialogManager(getContext());
        //偷个懒，并没有判断 是否存在， 是否可读。

        String dir = Environment.getExternalStorageDirectory() + "/recorder_audios/";
        File audioDir = new File(dir);
        if (!audioDir.exists()) audioDir.mkdirs();
        //mAudioRecorderManager = new AudioRecorderManager(dir);
        //mAudioRecorderManager.setOnAudioStateListener(this);
        mAudioManager = new AudioManager(dir);
        mAudioManager.setOnAudioStateListener(this);
       /* //按钮长按 准备录音 包括start
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mReady = true;
                mAudioManager.prepareAudio();
                //mAudioRecorderManager.createDefaultAudio();
               // mAudioRecorderManager.startRecording();
                return false;
            }
        });*/
    }

    /**
     * 录音完成后的回调
     */
    public interface AudioFinishRecorderListener {
        //时长  和 文件
        void onFinish(float seconds, String filePath);
    }

    private AudioFinishRecorderListener mListener;

    public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
        mListener = listener;
    }

    //获取音量大小的Runnable
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecording) {
                try {
                    Thread.sleep(100);
                    mTime += 0.1;
                    mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private static final int MSG_AUDIO_PREPARED = 0X110;
    private static final int MSG_VOICE_CHANGED = 0X111;
    private static final int MSG_DIALOG_DIMISS = 0X112;
    private static final int MSG_AUDIO_FINISH = 0X113;
    private static final int MSG_START_RECORD_VIDEO = 0X114;
    private static final int MSG_CANCEL_RECORD_VIDEO = 0X115;
    private static final int MSG_RESET_RECORD_VIDEO = 0X116;

    private long recordingTime = 0;
    private volatile boolean isPrepared = false;
    private volatile boolean cancel = false;
    private boolean excuteNow = false;
    private ReentrantLock lock = new ReentrantLock();

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    if(cancel) return;
                    isPrepared = true;
                    recordingTime = System.currentTimeMillis();
                    //TODO 真正现实应该在audio end prepared以后
                    mDialogManager.showRecordingDialog();
                    //isRecording = true;

                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGED:
                    //mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));

                    break;
                case MSG_DIALOG_DIMISS:
                    mDialogManager.dimissDialog();
                    break;
                case MSG_AUDIO_FINISH:
                    if (mListener != null) {
                        mListener.onFinish(audioTime, mAudioManager.getCurrentFilePath()/*mAudioRecorderManager.getCurrentFilePath()*/);
                    }
                    audioTime = 0;
                    break;
                case MSG_START_RECORD_VIDEO:
                    mReady = true;
                    isPrepared = false;
                    singleThreadPool.execute(startRecordRunnable);
                    break;
                case MSG_CANCEL_RECORD_VIDEO:
                    //mDialogManager.dimissDialog();
                    singleThreadPool.execute(cancelRecordRunnable);
                    break;
                case MSG_RESET_RECORD_VIDEO:
                    singleThreadPool.execute(resetRecordRunnable);
                    mDialogManager.dimissDialog();
                    break;
            }
        }
    };

    @Override
    public void wellPrepared() {
        if(cancel) return;
        //注意这个消息，显示出了dialog后，没法控制关闭。。。
        mHandler.sendMessageAtFrontOfQueue(Message.obtain(mHandler,MSG_AUDIO_PREPARED));

    }

    /*@Override
    public void saveFileFinish() {
        mHandler.sendEmptyMessage(MSG_AUDIO_FINISH);
    }*/

    private Executor singleThreadPool = Executors.newSingleThreadExecutor();

    private Runnable startRecordRunnable = new Runnable() {
        @Override
        public void run() {
            LogUtil.d("start..runnble.");
            mAudioManager.prepareAudio();
        }
    };

    private Runnable cancelRecordRunnable = new Runnable() {
        @Override
        public void run() {
            mAudioManager.cancel();
            cancel = false;
            LogUtil.d("cancel..runnble.");
        }
    };

    private Runnable resetRecordRunnable = new Runnable() {
        @Override
        public void run() {
            mAudioManager.release();
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //TODO
                isRecording = true;
                changeState(STATE_RECORDING);
                if(!cancel) {
                    if(!excuteNow) {
                        //500秒后开始录音
                        mHandler.sendEmptyMessageDelayed(MSG_START_RECORD_VIDEO, 500);
                    }else {
                        excuteNow = false;
                        mHandler.sendEmptyMessage(MSG_START_RECORD_VIDEO);
                    }
                }else{
                    excuteNow = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(excuteNow && !cancel){
                    excuteNow = false;
                    mHandler.sendEmptyMessage(MSG_START_RECORD_VIDEO);
                }
                if (isRecording) {

                    //根据想x,y的坐标，判断是否想要取消
                    if (wantToCancel(x, y)) {

                        changeState(STATE_WANT_TO_CANCEL);
                    } else {

                        changeState(STATE_RECORDING);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //起来之后，无论如何都要把还没执行录音的Msg取消掉
                mHandler.removeMessages(MSG_START_RECORD_VIDEO);
                //如果longclick 没触发
                if (!mReady) {
                    reset();
                    return super.onTouchEvent(event);
                }

                long upTime = System.currentTimeMillis();
                if (!isPrepared){
                    cancel = true;
                    mHandler.sendEmptyMessage(MSG_CANCEL_RECORD_VIDEO);
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                    LogUtil.d("start but not prepared");
                } else if(mCurState == STATE_WANT_TO_CANCEL) {
                    cancel = true;
                    mHandler.sendEmptyMessage(MSG_CANCEL_RECORD_VIDEO);
                    mDialogManager.dimissDialog();
                    LogUtil.d("out of record area");
                } else if (upTime - recordingTime < 1000) {
                    cancel = true;
                    mHandler.sendEmptyMessage(MSG_CANCEL_RECORD_VIDEO);
                    mDialogManager.tooShort();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                    LogUtil.d("too short");
                } else {
                    mHandler.sendEmptyMessage(MSG_RESET_RECORD_VIDEO);
                    if (mListener != null) {
                        int seconds = (int) (((upTime - recordingTime) + 500) / 1000);
                        mListener.onFinish(seconds, mAudioManager.getCurrentFilePath());
                    }
                }
                /*

                //触发了onlongclick 没准备好，但是已经prepared 已经start
                //所以消除文件夹
                if(!isRecording||mTime<0.6f){
                    mHandler.sendEmptyMessage(MSG_CANCEL_RECORD_VIDEO);
                    mDialogManager.tooShort();
                    //mAudioManager.cancel();
                    //mAudioRecorderManager.cancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                }else if(mCurState==STATE_RECORDING){//正常录制结束
                    mHandler.sendEmptyMessage(MSG_RESET_RECORD_VIDEO);
                    mDialogManager.dimissDialog();
                    //mAudioManager.release();
                   // mAudioRecorderManager.release();
                    if(mListener != null){
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
                    }

                }else if (mCurState == STATE_RECORDING) {

                    mDialogManager.dimissDialog();
                    //release
                    //callbacktoAct
                } else if (mCurState == STATE_WANT_TO_CANCEL) {
                    mDialogManager.dimissDialog();
                    mAudioManager.cancel();
                    //mAudioRecorderManager.cancel();
                    //cancel
                }*/
                audioTime = Math.round(mTime);
                reset();

                break;

        }
        return true;
    }

    /**
     * 恢复状态 标志位
     */
    private void reset() {
        isRecording = false;
        mReady = false;
        changeState(STATE_NORMAL);
        mTime = 0;

    }

    private boolean wantToCancel(int x, int y) {
        //如果左右滑出 button
        if (x < 0 || x > getWidth()) {
            return true;
        }
        //如果上下滑出 button  加上我们自定义的距离
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
            return true;
        }
        return false;
    }

    //改变状态
    private void changeState(int state) {
        if (mCurState != state) {
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.str_recorder_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_recording);

                    if (isRecording) {
                        mDialogManager.recording();
                    }
                    break;
                case STATE_WANT_TO_CANCEL:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_want_cancel);
                    mDialogManager.wantToCancel();
                    break;
            }
        }
    }
}
