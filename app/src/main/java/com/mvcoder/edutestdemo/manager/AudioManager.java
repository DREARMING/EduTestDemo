package com.mvcoder.edutestdemo.manager;

import android.media.MediaRecorder;

import com.mvcoder.edutestdemo.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudioManager {

    private MediaRecorder mMediaRecorder;
    private String mDir;
    private String mCurrentFilePath;

    private static AudioManager mInstance;

    private volatile boolean isPrepared;
    public AudioManager(String dir){
        mDir = dir;
    }


    /**
     * 回调准备完毕
     */
    public interface AudioStateListener {
        void wellPrepared();
    }

    public AudioStateListener mListener;

    public void setOnAudioStateListener(AudioStateListener listener){
        mListener = listener;
    }

    public static AudioManager getInstance(String dir){
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }


    /**
     * 准备
     */
    public void prepareAudio() {
        try {
            long time = System.currentTimeMillis();
            isPrepared = false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = generateFileName();

            File file = new File(dir, fileName);

            mCurrentFilePath = file.getAbsolutePath();

            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSamplingRate(44100);
            //mMediaRecorder.setAudioEncodingBitRate();
            //设置输出文件
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            //设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
            //设置音频的格式为amr
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            //准备结束
            isPrepared = true;
            if (mListener != null) {
                mListener.wellPrepared();
            }
            LogUtil.d("waste time : " + (System.currentTimeMillis() - time));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    生成UUID唯一标示符
//    算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID
//    .amr音频文件
    private String generateFileName() {
        return UUID.randomUUID().toString()+".aac";
    }

    public int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            //获得最大的振幅getMaxAmplitude() 1-32767
            try {
                return maxLevel * mMediaRecorder.getMaxAmplitude()/32768+1;
            } catch (Exception e) {

            }
        }
        return 1;
    }

    public void release() {
        if(!isPrepared) return;
        try {
            mMediaRecorder.stop();
        }catch (Exception e){
            e.printStackTrace();
            if (mCurrentFilePath != null) {
                File file = new File(mCurrentFilePath);
                file.delete();
                mCurrentFilePath = null;
            }
        }finally {
            mMediaRecorder.release();
            mMediaRecorder = null;
            isPrepared = false;
        }
    }

    public void cancel(){
        if(!isPrepared) return;
        try {
            mMediaRecorder.stop();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mMediaRecorder.release();
            mMediaRecorder = null;
            isPrepared = false;
            if (mCurrentFilePath != null) {
                File file = new File(mCurrentFilePath);
                file.delete();
                mCurrentFilePath = null;
            }
        }
    }
    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }
}
