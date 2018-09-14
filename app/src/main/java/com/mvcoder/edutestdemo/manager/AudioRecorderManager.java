package com.mvcoder.edutestdemo.manager;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.text.TextUtils;

import com.blankj.utilcode.util.ConvertUtils;
import com.mvcoder.edutestdemo.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class AudioRecorderManager {

    private String mDir;
    private String mCurrentFilePath;
    private String mWavFilePath;

    private final int SAMPLE_RATE = 44100;
    private int bufferSizeInBytes = 0;

    private AudioRecord mAudioRecord;

    private static AudioManager mInstance;

    private Status mStatus = Status.STATUS_NO_READY;


    private boolean isPrepared;

    public AudioRecorderManager(String dir) {
        mDir = dir;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
    }

    enum Status {
        STATUS_NO_READY,
        STATUS_READY,
        STATUS_START,
        STATUS_STOP
    }

    /**
     * 回调准备完毕
     */
    public interface AudioStateListener {
        void wellPrepared();
        void saveFileFinish();
    }

    public AudioStateListener mListener;

    public void setOnAudioStateListener(AudioStateListener listener) {
        mListener = listener;
    }

    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }


    public void createAudio(int audioSource, int sampleRateInHz, int channelConfigs, int audioFormat) {
        bufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioRecord = new AudioRecord(audioSource, sampleRateInHz, channelConfigs, audioFormat, bufferSizeInBytes);
        String fileName = generateFileName();
        File file = new File(mDir, fileName);
        mCurrentFilePath = file.getAbsolutePath();
        mStatus = Status.STATUS_READY;
    }

    public void createDefaultAudio() {
        bufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes);
        String fileName = generateFileName();
        File file = new File(mDir, fileName);
        mCurrentFilePath = file.getAbsolutePath();
        mStatus = Status.STATUS_READY;
    }

    public void startRecording() {
        if (mStatus == Status.STATUS_NO_READY || TextUtils.isEmpty(mCurrentFilePath)) {
            throw new IllegalStateException("录音尚未初始化，请检查是否禁止了权限");
        }
        if (mStatus == Status.STATUS_START) {
            throw new IllegalStateException("正在录音..");
        }
        mAudioRecord.startRecording();
        mStatus = Status.STATUS_START;
        if (mListener != null) {
            mListener.wellPrepared();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeDataToFile();
            }
        }).start();
    }

    private void writeDataToFile() {
        byte[] audioData = new byte[bufferSizeInBytes];
        FileOutputStream fos = null;
        FileOutputStream warOS = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            fos = new FileOutputStream(mCurrentFilePath);
            int readNum = 0;
            while (mStatus == Status.STATUS_START) {
                readNum = mAudioRecord.read(audioData, 0, bufferSizeInBytes);
                if (AudioRecord.ERROR_INVALID_OPERATION != readNum && fos != null) {
                    try {
                        bos.write(audioData);
                        fos.write(audioData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
           // File mWavFile = new File(mDir, generateWavFileName());
            //mWavFilePath = mWavFile.getAbsolutePath();
            //MediaUtils.pcm2wav(SAMPLE_RATE, 16, 1,
               //     bufferSizeInBytes, mCurrentFilePath, mWavFile.getAbsolutePath());
            LogUtil.d("PCM:\n" + ConvertUtils.bytes2HexString(bos.toByteArray()));
            if(mListener != null){
                mListener.saveFileFinish();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (warOS != null) warOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    //    生成UUID唯一标示符
//    算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID
//    .amr音频文件
    private String generateFileName() {
        return UUID.randomUUID().toString() + ".pcm";
    }

    private String generateWavFileName() {
        return UUID.randomUUID().toString() + ".wav";
    }

    /*public int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            //获得最大的振幅getMaxAmplitude() 1-32767
            try {
                return maxLevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {

            }
        }
        return 1;
    }*/

    public void stopRecord() {
        if (mStatus == Status.STATUS_NO_READY || mStatus == Status.STATUS_READY) {
            throw new IllegalStateException("录音尚未开始");
        } else {
            mAudioRecord.stop();
            mStatus = Status.STATUS_STOP;
            release();
        }
    }

    public void release() {
        if (mAudioRecord != null) {
            mAudioRecord.release();
            mAudioRecord = null;
            mStatus = Status.STATUS_NO_READY;
        }
    }

    public void cancel() {
        release();
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }

    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }

    public String getmWavFilePath(){
        return mWavFilePath;
    }



}
