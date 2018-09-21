package com.dou361.ijkplayer.utils;

import com.dou361.ijkplayer.bean.Position;
import com.dou361.ijkplayer.bean.VideoItem;

import java.util.List;

public class VideoConfiguration {

    //音视频控件位置信息
    private Position position;
    private String videoId;

    private List<VideoItem> playList;
    /**
     * 音视频控件是否播放视频画面
     */
    private boolean videoEnable = true;

    /**
     * 音视频控件是否播放音频声音
     */
    private boolean audioEnable = true;

    /**
     * webview覆盖音视频模式
     */
    private boolean webviewMode = false;

    /**
     * 禁止触摸，该项会影响 音量、亮度、视频进度滑动，该项为true，将会忽略brightnessControlEnable、volumnControlEnable的值
     */
    private boolean forbitTouch = true;
    /**
     * 在forbitTouch == true,brightnessControlEnable为true时，可以控制视频亮度
     */
    private boolean brightnessControlEnable = !forbitTouch;
    /**
     * 在forbitTouch == true,volumnControlEnable为true时，可以控制视频声音大小
     */
    private boolean volumnControlEnable = !forbitTouch;

    /**
     * 是否支持多分辨率大小，该项会影响读取视频URL时，是从Resoution里面取还是playList的videoUrl；该项为false
     */
    private boolean multiResolutionEnable = false;

    /**
     * 点播模式下，最大观看时长，该值>=0时（单位为秒），将会进行限时播放
     */
    private int maxPlayTime = -1;

    /**
     * 缓冲的网速提示
     */
    private boolean showSpeed = false;

    /**
     * 禁止自动收起控制面板 -- Top bar and bottom bar will close after no operation in 5 seconds
     */
    private boolean forbidHideControlPanl = false;

    /**
     * 播放控件全屏显示,全屏显示依赖于控件所在布局文件的配置是否在横屏模式下占满屏幕
     */
    private boolean isOnlyFullScreen = false;

    /**
     * 是否显示视频控件顶部栏
     */
    private boolean topbarVisible = false;

    /**
     * 返回按钮的可见性,返回按钮可以退出全屏播放视频，在竖屏观看下，会释放当前视频控件，并退出当前Activity
     */
    private boolean backBtVisible = topbarVisible;

    /**
     * 是否显示视频控件的底部栏
     */
    private boolean bottombarVisible = false;

    /**
     * 是否显示视频全屏按钮
     */
    private boolean fullScreenBtVisible;

    /**
     * 是否在4G、3G、2G播放视频时暂停，并进行提示
     */
    private boolean mobileNetworkEnable = false;


    /**
     * 是否显示旋转按钮，该项没什么用，可以忽略
     */

    private boolean rotateBtVisible;

    /**
     * 选集列表，暂未完成
     */
    private boolean playListBtVisible;

    /**
     * 菜单按钮
     */
    private boolean menuBtVisible = false;

    /**
     * 截帧按钮，暂未完成
     */
    private boolean screenShotBtVisible;

    /**
     * 视频分享按钮，暂未完成
     */
    private boolean shareBtVisible;

    /**
     * 是否支持拖拽操作，true时长按触发拖拽事件，该选项仅当forbitTouch==true时生效
     */
    private boolean canDrag = false;

    /**
     * 是否接受拖拽事件
     */
    private boolean canReceiveDrag = false;


    public Position getPosition() {
        return position;
    }


    public String getVideoId() {
        return videoId;
    }


    public List<VideoItem> getPlayList() {
        return playList;
    }


    public boolean isVideoEnable() {
        return videoEnable;
    }


    public boolean isAudioEnable() {
        return audioEnable;
    }


    public boolean isWebviewMode() {
        return webviewMode;
    }


    public boolean isForbitTouch() {
        return forbitTouch;
    }


    public boolean isBrightnessControlEnable() {
        return brightnessControlEnable;
    }


    public boolean isVolumnControlEnable() {
        return volumnControlEnable;
    }


    public boolean isMultiResolutionEnable() {
        return multiResolutionEnable;
    }


    public boolean isTopbarVisible() {
        return topbarVisible;
    }


    public boolean isBottombarVisible() {
        return bottombarVisible;
    }


    public boolean isFullScreenBtVisible() {
        return fullScreenBtVisible;
    }


    public boolean isRotateBtVisible() {
        return rotateBtVisible;
    }


    public boolean isPlayListBtVisible() {
        return playListBtVisible;
    }


    public boolean isScreenShotBtVisible() {
        return screenShotBtVisible;
    }


    public boolean isShareBtVisible() {
        return shareBtVisible;
    }

    public boolean isMobileNetworkEnable() {
        return mobileNetworkEnable;
    }

    public void setMobileNetworkEnable(boolean mobileNetworkEnable) {
        this.mobileNetworkEnable = mobileNetworkEnable;
    }

    public boolean isMenuBtVisible() {
        return menuBtVisible;
    }

    public boolean isBackBtVisible() {
        return backBtVisible;
    }

    public int getMaxPlayTime() {
        return maxPlayTime;
    }

    public void setMaxPlayTime(int maxPlayTime) {
        this.maxPlayTime = maxPlayTime;
    }

    public boolean isShowSpeed() {
        return showSpeed;
    }

    public void setShowSpeed(boolean showSpeed) {
        this.showSpeed = showSpeed;
    }

    public boolean isCanReceiveDrag() {
        return canReceiveDrag;
    }

    public void setCanReceiveDrag(boolean canReceiveDrag) {
        this.canReceiveDrag = canReceiveDrag;
    }

    public boolean isForbidHideControlPanl() {
        return forbidHideControlPanl;
    }

    public void setForbidHideControlPanl(boolean forbidHideControlPanl) {
        this.forbidHideControlPanl = forbidHideControlPanl;
    }

    public boolean isOnlyFullScreen() {
        return isOnlyFullScreen;
    }

    public boolean isCanDrag() {
        return canDrag;
    }

    public void setCanDrag(boolean canDrag) {
        this.canDrag = canDrag;
    }


    public void setOnlyFullScreen(boolean onlyFullScreen) {
        isOnlyFullScreen = onlyFullScreen;
    }

    public void setBackBtVisible(boolean backBtVisible) {
        this.backBtVisible = backBtVisible;
    }

    public void setMenuBtVisible(boolean menuBtVisible) {
        this.menuBtVisible = menuBtVisible;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setPlayList(List<VideoItem> playList) {
        this.playList = playList;
    }

    public void setVideoEnable(boolean videoEnable) {
        this.videoEnable = videoEnable;
    }

    public void setAudioEnable(boolean audioEnable) {
        this.audioEnable = audioEnable;
    }

    public void setWebviewMode(boolean webviewMode) {
        this.webviewMode = webviewMode;
    }

    public void setForbitTouch(boolean forbitTouch) {
        this.forbitTouch = forbitTouch;
    }

    public void setBrightnessControlEnable(boolean brightnessControlEnable) {
        this.brightnessControlEnable = brightnessControlEnable;
    }

    public void setVolumnControlEnable(boolean volumnControlEnable) {
        this.volumnControlEnable = volumnControlEnable;
    }

    public void setMultiResolutionEnable(boolean multiResolutionEnable) {
        this.multiResolutionEnable = multiResolutionEnable;
    }

    public void setTopbarVisible(boolean topbarVisible) {
        this.topbarVisible = topbarVisible;
    }

    public void setBottombarVisible(boolean bottombarVisible) {
        this.bottombarVisible = bottombarVisible;
    }

    public void setFullScreenBtVisible(boolean fullScreenBtVisible) {
        this.fullScreenBtVisible = fullScreenBtVisible;
    }

    public void setRotateBtVisible(boolean rotateBtVisible) {
        this.rotateBtVisible = rotateBtVisible;
    }

    public void setPlayListBtVisible(boolean playListBtVisible) {
        this.playListBtVisible = playListBtVisible;
    }

    public void setScreenShotBtVisible(boolean screenShotBtVisible) {
        this.screenShotBtVisible = screenShotBtVisible;
    }

    public void setShareBtVisible(boolean shareBtVisible) {
        this.shareBtVisible = shareBtVisible;
    }

}
