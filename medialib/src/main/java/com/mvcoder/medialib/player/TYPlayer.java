package com.mvcoder.medialib.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvcoder.medialib.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class TYPlayer extends StandardGSYVideoPlayer {

    //调整进度时的类似弹窗的View
    private View mProgressView;
    private View mVolumnView;
    private ImageView mIvVolumn;

    private View mBrightnessView;

    private Animation mShowAnimation;
    private Animation mHideAnimation;

    public TYPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public TYPlayer(Context context) {
        super(context);
    }

    public TYPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initAnimation();
    }

    private void initAnimation() {
        mShowAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        mHideAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
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
            if(localView.findViewById(R.id.video_iv_volumn) instanceof ImageView){
                mIvVolumn = localView.findViewById(R.id.video_iv_volumn);
            }
            mVolumnView = localView;
            addView(mVolumnView, -1 , -1);
        }
        if (mVolumnView.getVisibility() != VISIBLE) {
            mVolumnView.setVisibility(VISIBLE);
            mVolumnView.startAnimation(mShowAnimation);
        }
        if (mDialogVolumeProgressBar != null) {
            if(mIvVolumn != null) {
                if (volumePercent <= 0) {
                    mIvVolumn.setImageResource(R.mipmap.video_sound_none);
                }else if(volumePercent > 0 && volumePercent < 40){
                    mIvVolumn.setImageResource(R.mipmap.video_sound_1);
                }else{
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
        if (mProgressView == null) {
            View localView = LayoutInflater.from(getActivityContext()).inflate(getProgressDialogLayoutId(), null);
            if (localView.findViewById(getProgressDialogProgressId()) instanceof ProgressBar) {
                mDialogProgressBar = ((ProgressBar) localView.findViewById(getProgressDialogProgressId()));
                if (mDialogProgressBarDrawable != null) {
                    mDialogProgressBar.setProgressDrawable(mDialogProgressBarDrawable);
                }
            }
            if (localView.findViewById(getProgressDialogCurrentDurationTextId()) instanceof TextView) {
                mDialogSeekTime = ((TextView) localView.findViewById(getProgressDialogCurrentDurationTextId()));
            }
            if (localView.findViewById(getProgressDialogAllDurationTextId()) instanceof TextView) {
                mDialogTotalTime = ((TextView) localView.findViewById(getProgressDialogAllDurationTextId()));
            }
            if (localView.findViewById(getProgressDialogImageId()) instanceof ImageView) {
                mDialogIcon = ((ImageView) localView.findViewById(getProgressDialogImageId()));
            }

            addView(localView, -1, -1);
            mProgressView = localView;
        }
        if(mProgressView.getVisibility() != VISIBLE){
            mProgressView.setVisibility(VISIBLE);
            mProgressView.startAnimation(mShowAnimation);
        }
        if (mDialogSeekTime != null) {
            mDialogSeekTime.setText(seekTime);
        }
        if (mDialogTotalTime != null) {
            mDialogTotalTime.setText(" / " + totalTime);
        }
        if (totalTimeDuration > 0)
            if (mDialogProgressBar != null) {
                mDialogProgressBar.setProgress(seekTimePosition * 100 / totalTimeDuration);
            }
        if (deltaX > 0) {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(R.drawable.video_forward_icon);
            }
        } else {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(R.drawable.video_backward_icon);
            }
        }
    }


    @Override
    protected void dismissProgressDialog() {
        //super.dismissProgressDialog();
        if(mProgressView != null && mProgressView.getVisibility() == VISIBLE){
            mProgressView.setVisibility(GONE);
            mProgressView.startAnimation(mHideAnimation);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mShowAnimation.hasStarted()) {
            mShowAnimation.cancel();
            mShowAnimation = null;
        }
        if(mHideAnimation.hasStarted()){
            mHideAnimation.cancel();
            mHideAnimation = null;
        }

    }
}
