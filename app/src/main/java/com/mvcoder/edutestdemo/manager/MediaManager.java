package com.mvcoder.edutestdemo.manager;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MediaManager {

    private static MediaPlayer mMediaPlayer;

    private static boolean isPause;

    private static AudioTrack audioTrack;

    public static void playPCM(String filePath, MediaPlayer.OnCompletionListener onCompletionListener){
        int bufferSize = AudioRecord.getMinBufferSize(44100,AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        if(audioTrack == null){
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
        }
        audioTrack.play();
        byte[] buffer = new byte[bufferSize];
        FileInputStream fis = null;
        try {
             fis = new FileInputStream(filePath);
             int readNum = -1;
             while ((readNum = fis.read(buffer)) != -1){
                audioTrack.write(buffer,0,readNum);
             }
             if(onCompletionListener != null){
                 onCompletionListener.onCompletion(null);
             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            audioTrack.stop();
            audioTrack.release();
            audioTrack = null;
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //播放录音
    public static void playSound(String filePath, MediaPlayer.OnCompletionListener onCompletionListener){
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            //播放错误 防止崩溃
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        }else{
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.setVolume(1,1);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 如果 播放时间过长,如30秒
     * 用户突然来电话了,则需要暂停
     */
    public static void pause() {
        if (mMediaPlayer != null&&mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 播放
     */
    public static void resume(){
        if (mMediaPlayer != null && isPause) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * activity 被销毁  释放
     */
    public static void release(){
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}
