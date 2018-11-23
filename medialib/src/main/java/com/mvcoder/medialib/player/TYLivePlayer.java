package com.mvcoder.medialib.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvcoder.medialib.R;
import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class TYLivePlayer extends StandardGSYVideoPlayer {

    //调整进度时的类似弹窗的View
    private View mProgressView;
    private View mVolumnView;
    private ImageView mIvVolumn;

    private TextView tvErrorTip;

    private View mBrightnessView;

    private Animation mShowAnimation;
    private Animation mHideAnimation;

    public TYLivePlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public TYLivePlayer(Context context) {
        super(context);
    }

    public TYLivePlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        tvErrorTip = findViewById(R.id.video_tv_error_tip);
        tvErrorTip.setVisibility(GONE);
        initAnimation();
    }

    private void initAnimation() {
        mShowAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        mHideAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_layout_standard_live;
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
        Log.d("Play", "onInfo : what : " + what + " , extra : " + extra);
    }

    @Override
    public void onError(int what, int extra) {
        Log.d("Play", "onError : what : " + what + " , extra : " + extra);
        switch (what) {
            case -192:
            case IMediaPlayer.MEDIA_ERROR_TIMED_OUT:
                //准备、缓冲的超时时间，内部GSYVideoManager管理
                tvErrorTip.setText("加载超时，请检查网络");
                break;
            case IMediaPlayer.MEDIA_ERROR_SERVER_DIED:
                tvErrorTip.setText("服务器不可访问，请检查");
                break;
            default: {
                if(!NetworkUtils.isAvailable(getContext())){
                    tvErrorTip.setText("网络连接断开，请检查网络设置");
                }else {
                    tvErrorTip.setText("发生了点错误，请重试");
                }
            }
                break;
        }
        super.onError(what, extra);
    }

    @Override
    protected void changeUiToClear() {
        super.changeUiToClear();
        tvErrorTip.setVisibility(INVISIBLE);
    }


    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        tvErrorTip.setVisibility(INVISIBLE);
    }

    @Override
    protected void changeUiToPrepareingClear() {
        super.changeUiToPrepareingClear();
        tvErrorTip.setVisibility(INVISIBLE);
    }



    @Override
    protected void changeUiToCompleteClear() {
        super.changeUiToCompleteClear();
        tvErrorTip.setVisibility(INVISIBLE);
    }


    @Override
    protected void changeUiToError() {
        super.changeUiToError();
        tvErrorTip.setVisibility(VISIBLE);
    }

    @Override
    protected void showBrightnessDialog(float percent) {
        if (mBrightnessView == null) {
            View localView = LayoutInflater.from(getActivityContext()).inflate(getBrightnessLayoutId(), null);
            if (localView.findViewById(getBrightnessTextId()) instanceof TextView) {
                mBrightnessDialogTv = (TextView) localView.findViewById(getBrightnessTextId());
            }
            mBrightnessView = localView;
            addView(localView, -1, -1);
        }
        if (mBrightnessView.getVisibility() != VISIBLE) {
            mBrightnessView.setVisibility(VISIBLE);
            mBrightnessView.startAnimation(mShowAnimation);
        }
        if (mBrightnessDialogTv != null)
            mBrightnessDialogTv.setText((int) (percent * 100) + "%");
    }


    @Override
    protected void dismissBrightnessDialog() {
        if (mBrightnessView != null && mBrightnessView.getVisibility() == VISIBLE) {
            mBrightnessView.startAnimation(mHideAnimation);
            mBrightnessView.setVisibility(GONE);
        }
    }

    private boolean isLive = true;


    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        if (absDeltaX > mThreshold || absDeltaY > mThreshold) {
            if (absDeltaX >= mThreshold) {
                if (mProgressBar == null) return;
            }
        }
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
    }

    @Override
    protected void showVolumeDialog(float deltaY, int volumePercent) {
        if (mVolumnView == null) {
            View localView = LayoutInflater.from(getActivityContext()).inflate(getVolumeLayoutId(), null);
            if (localView.findViewById(getVolumeProgressId()) instanceof ProgressBar) {
                mDialogVolumeProgressBar = ((ProgressBar) localView.findViewById(getVolumeProgressId()));
                if (mVolumeProgressDrawable != null && mDialogVolumeProgressBar != null) {
                    mDialogVolumeProgressBar.setProgressDrawable(mVolumeProgressDrawable);
                }
            }
            if (localView.findViewById(R.id.video_iv_volumn) instanceof ImageView) {
                mIvVolumn = localView.findViewById(R.id.video_iv_volumn);
            }
            mVolumnView = localView;
            addView(mVolumnView, -1, -1);
        }
        if (mVolumnView.getVisibility() != VISIBLE) {
            mVolumnView.setVisibility(VISIBLE);
            mVolumnView.startAnimation(mShowAnimation);
        }
        if (mDialogVolumeProgressBar != null) {
            if (mIvVolumn != null) {
                if (volumePercent <= 0) {
                    mIvVolumn.setImageResource(R.mipmap.video_sound_none);
                } else if (volumePercent > 0 && volumePercent < 40) {
                    mIvVolumn.setImageResource(R.mipmap.video_sound_1);
                } else {
                    mIvVolumn.setImageResource(R.mipmap.video_sound);
                }
            }
            mDialogVolumeProgressBar.setProgress(volumePercent);
        }
    }

    @Override
    protected void dismissVolumeDialog() {
        if (mVolumnView != null && mVolumnView.getVisibility() == VISIBLE) {
            mVolumnView.startAnimation(mHideAnimation);
            mVolumnView.setVisibility(GONE);
        }
    }

    @Override
    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {
        if (isLive) return;
    }


    @Override
    protected void dismissProgressDialog() {
        if (isLive) return;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mShowAnimation.hasStarted()) {
            mShowAnimation.cancel();
            mShowAnimation = null;
        }
        if (mHideAnimation.hasStarted()) {
            mHideAnimation.cancel();
            mHideAnimation = null;
        }

    }
}
