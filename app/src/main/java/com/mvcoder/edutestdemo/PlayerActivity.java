package com.mvcoder.edutestdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.mvcoder.edutestdemo.utils.LogUtil;
import com.mvcoder.medialib.player.TYLivePlayer;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayerActivity extends AppCompatActivity {

 /*   @BindView(R.id.videoComponent)
    VideoComponent videoComponent;*/
    @BindView(R.id.constrantLayout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.player)
    TYLivePlayer player;

    private OrientationUtils orientationUtils;

    private boolean isPlay = false;
    private boolean isPause = false;
    private boolean isFirst = false;

    private Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    boolean isHardAccelerated = constraintLayout.isHardwareAccelerated();
                    LogUtil.d("is Hard enable " + isHardAccelerated);
                    break;
            }
        }
    };

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

    private final static String rtmpUrl2 = "rtmp://192.168.3.26/live/stream";


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

        //用exo来做播放器
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);

        //CacheFactory.setCacheManager(ExoPlayerCacheManager.class);//exo缓存模式，支持m3u8，只支持exo
        //CacheFactory.setCacheManager(ProxyCacheManager.class);//代理缓存模式，支持所有模式，不支持m3u8等


        //initOption(rtmpUrl2);
        GSYVideoOptionBuilder optionBuilder = new GSYVideoOptionBuilder();
        optionBuilder.setUrl(rtmpUrl2)
                .setVideoTitle("千图网")
                .setBottomProgressBarDrawable(null)
                //.setLooping(true)
                .setCacheWithPlay(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                //.setCacheWithPlay(true)
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

                    @Override
                    public void onPlayError(String url, Object... objects) {
                        super.onPlayError(url, objects);
                        LogUtil.d("on Play Error");
                        //player.release();
                        //player.startPlayLogic();
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

        GSYVideoManager.instance();

    }


    private void initOption(String url){
        if(TextUtils.isEmpty(url) || url.length() < 14) return;
        List<VideoOptionModel> list = new ArrayList<>();
        VideoOptionModel videoOptionModel =  null;
        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();

        //硬解码支持,硬件解码关闭后，一定要开启软解码
        boolean isMediaCodec = GSYVideoType.isMediaCodec();
        if(isMediaCodec) {
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC, "mediacodec", 1);
        }else{
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC, "mediacodec", 0);
        }
        list.add(videoOptionModel);
        if(isMediaCodec){
            //mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "videotoolbox", 0);
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC,"videotoolbox", 0);
        }else{
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC,"videotoolbox", 1);
        }
        list.add(videoOptionModel);

        //rtsp支持
        String prefix = url.substring(0,url.indexOf(":"));
        if("rtsp".equalsIgnoreCase(prefix)) {
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "tcp");
            list.add(videoOptionModel);
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_flags", "prefer_tcp");
            list.add(videoOptionModel);
        }

        boolean isRTMPOrRTSP = false;
        if(prefix.equalsIgnoreCase("rtmp") || prefix.equalsIgnoreCase("rtsp")){
            isRTMPOrRTSP = true;
        }

        if(isRTMPOrRTSP) {
            // ----------------直播设置 start------------------
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", 100);
            list.add(videoOptionModel);

            //probesize设置不够大，很容易造成黑屏有声
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 102400);
            list.add(videoOptionModel);
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", 1);
            list.add(videoOptionModel);
            //  关闭播放器缓冲，这个必须关闭，否则会出现播放一段时间后，一直卡主，控制台打印 FFP_MSG_BUFFERING_START
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
            list.add(videoOptionModel);
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
            list.add(videoOptionModel);
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "infbuf", 1);  // 无限读
            list.add(videoOptionModel);

            //不确定有没有用
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
            list.add(videoOptionModel);

            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
            list.add(videoOptionModel);

            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "buffer_size", 1316);
            list.add(videoOptionModel);

            //这个在hls里面不能设置，会造成无法播放
            videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reconnect", 3);
            list.add(videoOptionModel);

            //-------------------直播设置 end-----------------------------------
        }

        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "allowed_media_types", "video"); //根据媒体类型来配置
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 20000);
        list.add(videoOptionModel);

        GSYVideoManager.instance().setOptionModelList(list);
    }

    private boolean isHls(String url){
        String prefix = url.substring(0,url.indexOf(":"));
        if(url.length() < 21) return false;
        String subfix = url.substring(url.length() - 5);
        if(TextUtils.isEmpty(subfix)) return false;
        if((prefix.equalsIgnoreCase("http") || prefix.equalsIgnoreCase("https"))&& subfix.equalsIgnoreCase(".m3u8")){
            return true;
        }
        return false;
    }


    @Override
    protected void onResume() {
        player.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
        mHandler.sendEmptyMessageDelayed(100, 100);
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
