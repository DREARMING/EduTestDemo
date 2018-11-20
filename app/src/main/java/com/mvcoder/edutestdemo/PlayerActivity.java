package com.mvcoder.edutestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dou361.ijkplayer.bean.Position;
import com.dou361.ijkplayer.bean.VideoItem;
import com.dou361.ijkplayer.utils.VideoConfiguration;
import com.dou361.ijkplayer.widget.VideoComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.videoComponent)
    VideoComponent videoComponent;

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
        VideoConfiguration configuration = new VideoConfiguration();
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
        videoComponent.startPlay();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoComponent.closeVideo();
    }
}
