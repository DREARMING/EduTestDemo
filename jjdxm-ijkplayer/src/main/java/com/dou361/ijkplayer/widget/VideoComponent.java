package com.dou361.ijkplayer.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dou361.ijkplayer.R;
import com.dou361.ijkplayer.bean.VideoItem;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.utils.VideoConfiguration;

import java.util.List;
import java.util.concurrent.Executor;

public class VideoComponent extends FrameLayout implements View.OnDragListener {

    private Context context;
    private PlayerView playerView;
    private Activity mActivity;
    private int initHeight;
    private int initWidth;
    private int marginLeft;
    private int marginTop;
    private Executor threadPool;
    private int orientation;
    private ImageView snapshot;
    private final String label = "VideoComponentDrag";
    private final String KEY = "VideoItem";

    public VideoComponent(@NonNull Context context) {
        super(context);
    }

    public VideoComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        initView(context);
    }

    public VideoComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoComponent(@NonNull Activity activity, @NonNull Context context, Executor threadPool) {
        super(context);
        this.mActivity = activity;
        this.context = context;
        this.threadPool = threadPool;
        orientation = getResources().getConfiguration().orientation;
        // initView(context);
    }

    public void setThreadPool(Executor threadPool) {
        this.threadPool = threadPool;
    }


    public void onConfigurationChanged(Configuration newConfig) {
        if (playerView != null) {
            if (orientation != newConfig.orientation) {
                orientation = newConfig.orientation;
                doConfigurationChanged(newConfig);
            }
        }
    }

    private boolean isPortrait = true;

    private LayoutParams originalParams;


    private void doConfigurationChanged(Configuration newConfig) {
        isPortrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT;
        if (isPortrait) {
            LayoutParams params = (LayoutParams) getLayoutParams();

            if (originalParams == null) {
                originalParams = new LayoutParams(0, 0);
                copyLayoutParms(originalParams, params);
            }

            copyLayoutParms(params, originalParams);

            setLayoutParams(params);
        } else {
            LayoutParams params = (LayoutParams) getLayoutParams();
            if (originalParams == null) {
                originalParams = new LayoutParams(0, 0);
                copyLayoutParms(originalParams, params);
            }

            int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            params.height = Math.min(screenHeight, screenWidth);
            params.width = Math.max(screenHeight, screenWidth);
            params.leftMargin = 0;
            params.topMargin = 0;
            params.rightMargin = 0;
            params.bottomMargin = 0;
            setLayoutParams(params);
        }

        //playerView.onConfigurationChanged(newConfig);
    }

    private void copyLayoutParms(LayoutParams originalParams, LayoutParams params) {
        originalParams.height = params.height;
        originalParams.width = params.width;
        originalParams.bottomMargin = params.bottomMargin;
        originalParams.topMargin = params.topMargin;
        originalParams.leftMargin = params.leftMargin;
        originalParams.rightMargin = params.rightMargin;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void apply(VideoConfiguration config) {
        if (config.getPosition() == null || TextUtils.isEmpty(config.getVideoId())
                || config.getPlayList() == null || config.getPlayList().size() <= 0) {
            throw new IllegalArgumentException("VideoId、position and playList must not be null by setting VideoComponent!!");
        }
        View videoRootView = LayoutInflater.from(context).inflate(R.layout.view_videocomponent, this);

        snapshot = (ImageView) videoRootView.findViewById(R.id.iv_snapshot);

        playerView = new PlayerView(mActivity, videoRootView, threadPool);

        playerView.audioEnable(config.isAudioEnable());
        playerView.videoEnable(config.isVideoEnable());

        //底部栏是否可见
        playerView.hideBottonBar(!config.isBottombarVisible());

        //顶部栏是否可见
        playerView.hideHideTopBar(!config.isTopbarVisible());

        if (config.isCanDrag()) {
            setOnLongClickListener(new MyLongClickListener());
        }

        if (config.isCanReceiveDrag()) {
            setOnDragListener(this);
        }

        //滑动控制亮度和声音
        if (config.isForbitTouch()) {
            playerView.forbidTouch(true);
        } else {
            playerView.forbidTouch(false);
            playerView.setBrightnessControlEnable(config.isBrightnessControlEnable());
            playerView.setVolumnControlEnable(config.isVolumnControlEnable());
        }

        //分辨率选择
        if (!config.isMultiResolutionEnable()) {
            playerView.hideSteam(true);
            playerView.setMultiResolutionEnable(false);
        } else {
            playerView.hideSteam(false);
            playerView.setMultiResolutionEnable(true);
        }

        //注意先设置 multiResolution 后再设置 playSource
        List<VideoItem> playList = config.getPlayList();
        playerView.setPlaySource(playList);

        //全屏按钮是否显示
        if (config.isFullScreenBtVisible()) {
            playerView.hideFullscreen(false);
            playerView.setForbidDoulbeUp(false);
        } else {
            playerView.hideFullscreen(true);
            playerView.setForbidDoulbeUp(true);
        }

        playerView.setNetWorkTypeTie(config.isMobileNetworkEnable());
        playerView.hideRotation(!config.isRotateBtVisible());

        playerView.hideMenu(!config.isMenuBtVisible());
        playerView.hideCenterPlayer(true);

        playerView.hideBack(!config.isBackBtVisible());
        playerView.showThumbnail(new OnShowThumbnailListener() {
            @Override
            public void onShowThumbnail(ImageView ivThumbnail) {

            }
        });

        playerView.setForbidHideControlPanl(config.isForbidHideControlPanl());

        if (config.getMaxPlayTime() < 0) {
            playerView.setChargeTie(false, 0);
        } else {
            playerView.setChargeTie(true, config.getMaxPlayTime());
        }

        if (config.isOnlyFullScreen())
            playerView.setOnlyFullScreen(true);


        // playerView.setChargeTie(true,200);
        // 不提供
        // playerView.setAutoReConnect();

        //暂未实现
        //playerView.setShowSpeed();
    }


    public void startPlay() {
        playerView.startPlay();
    }


    public void switchVideo(VideoItem item) {
        playerView.setPlaySource(item);
        playerView.startPlay();
    }

    public void closeVideo() {
        playerView.stopPlay();
    }

    public void pauseVideo() {
        playerView.pausePlay();
    }


    // ---------------- 内部方法 ---------------------------

    //http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4
    //rtmp://live.hkstv.hk.lxdns.com/live/hks

    private void initView(Context context) {
       /* List<VideoijkBean> videoList = new ArrayList<>();
        VideoijkBean video1 = new VideoijkBean();
        video1.setUrl("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        video1.setSelect(false);
        video1.setStream("live");

         VideoijkBean video2 = new VideoijkBean();
        video2.setUrl("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
        video2.setSelect(true);
        video2.setStream("http");

        videoList.add(video2);
        videoList.add(video1);*/

        //View videoRootView = LayoutInflater.from(context).inflate(R.layout.view_videocomponent, this);
        //playerView = new PlayerView(mActivity, videoRootView, threadPool);

        /*playerView = new PlayerView(mActivity, videoRootView, threadPool)
                .setNetWorkTypeTie(false)
                .setTitle("香港卫视")
                .hideHideTopBar(true)
                .hideFullscreen(true)
                .setForbidDoulbeUp(true)
                .hideSteam(true)
                .hideRotation(true)
                .hideCenterPlayer(true)
                .setPlaySource("rtmp://live.hkstv.hk.lxdns.com/live/hks")
                .startPlay();*/
    }


    /**
     * 控件从页面被移除后，停止播放并回收资源
     */
    @Override
    protected void onDetachedFromWindow() {
        if (playerView != null) {
            playerView.stopPlay();
            playerView.releasePlayerView();
        }
        threadPool = null;
        super.onDetachedFromWindow();
    }


    class MyShadowBuilder extends DragShadowBuilder {

        private Drawable mShadow;

        public MyShadowBuilder(View view) {
            super(view);
            mShadow = new ColorDrawable(Color.BLUE);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            int width, height;
            width = getView().getWidth();
            height = getView().getHeight();
            mShadow.setBounds(0, 0, width, height);
            outShadowSize.set(width, height);
            outShadowTouchPoint.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            mShadow.draw(canvas);
            // super.onDrawShadow(canvas);
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().getLabel().equals(label)) {
                    snapshot.setImageDrawable(new ColorDrawable(Color.argb(50, 0, 255, 0)));
                    return true;
                }
                //isDrag = false;
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(50, 255, 0, 255)));
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(50, 0, 255, 0)));
                break;
            case DragEvent.ACTION_DROP:
                ClipData data = event.getClipData();
                final ClipData.Item item = data.getItemAt(0);
                //String str = item.getText().toString();
                Intent intent = item.getIntent();
                VideoItem videoItem = (VideoItem) intent.getSerializableExtra(KEY);

                switchVideo(videoItem);
               /* Gson gson = GsonUtil.getInstance().defaultGson();
                SwitchVideoItem obj = gson.fromJson(str, SwitchVideoItem.class);*/
                //Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                //isDrag = false;
                snapshot.setImageDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
                break;
        }
        return true;
    }

    private class MyLongClickListener implements OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            VideoItem item = playerView.getCurVideo();
            Intent intent = new Intent();
            intent.putExtra("VideoItem", item);
            // ClipData data = ClipData.newPlainText(label, command);
            ClipData data = ClipData.newIntent(label, intent);
            v.startDrag(data, new MyShadowBuilder(v), null, 0);
            return true;
        }
    }
}
