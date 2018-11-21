package com.mvcoder.edutestdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mvcoder.medialib.player.TYPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {

 /*   @BindView(R.id.videoComponent)
    VideoComponent videoComponent;*/
    @BindView(R.id.constrantLayout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.player)
    TYPlayer player;

    private OrientationUtils orientationUtils;

    private boolean isPlay = false;
    private boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        initView();
    }

    private final static String liveUrl = "http://cdn.can.cibntv.net/12/201702161000/rexuechangan01/rexuechangan01.m3u8";
    private final static String mp4Url = "http://pic.qiantucdn.com/58pic/32/27/73/2258PIC58PICg2HyKaXcHyH4s.mp4";
    private final static String rtmpUrl = "rtmp://v1.one-tv.com:1935/live/mpegts.stream";

    private void initView() {
        /*VideoConfiguration configuration = new VideoConfiguration();
        configuration.setVideoId("1");
        configuration.setPosition(new Position());
        VideoItem item = new VideoItem();
        item.setVideoTitle("Hello Title");
        item.setVideoUrl(rtmpUrl);
        configuration.setVideoEnable(true);
        List<VideoItem> list = new ArrayList<>();
        list.add(item);
        configuration.setPlayList(list);
        videoComponent.setActivity(this);
        videoComponent.apply(configuration);
        videoComponent.startPlay();*/


        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, player);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        GSYVideoOptionBuilder optionBuilder = new GSYVideoOptionBuilder();
        optionBuilder.setUrl(mp4Url)
                .setVideoTitle("千图网")
                //.setLooping(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setCacheWithPlay(true)
                .setNeedShowWifiTip(true)
                .setVideoAllCallBack(new GSYSampleCallBack(){

                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);
                        }
                    }
                })
                .build(player);


        player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                player.startWindowFullscreen(PlayerActivity.this, true, true);
            }
        });

    }



    @Override
    protected void onResume() {
        player.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            player.getCurrentPlayer().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    protected void onPause() {
        player.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            player.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }
}
