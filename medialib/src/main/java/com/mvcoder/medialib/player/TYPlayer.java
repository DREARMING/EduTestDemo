package com.mvcoder.medialib.player;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvcoder.medialib.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class TYPlayer extends StandardGSYVideoPlayer {


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
    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {
        if (mProgressDialog == null) {
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
            mProgressDialog = new Dialog(getActivityContext(), R.style.video_dialog_style);
            mProgressDialog.setContentView(localView);
            //mProgressDialog.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
            //mProgressDialog.getWindow().addFlags(32);
            //mProgressDialog.getWindow().addFlags(16);
            mProgressDialog.getWindow().setLayout(getWidth(), getHeight());
            Log.d("edu", "widht : " + getWidth() + " , height : " + getHeight());
            if (mDialogProgressNormalColor != -11 && mDialogTotalTime != null) {
                mDialogTotalTime.setTextColor(mDialogProgressNormalColor);
            }
            if (mDialogProgressHighLightColor != -11 && mDialogSeekTime != null) {
                mDialogSeekTime.setTextColor(mDialogProgressHighLightColor);
            }
            WindowManager.LayoutParams localLayoutParams = mProgressDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.TOP;
            localLayoutParams.width = getWidth();
            localLayoutParams.height = getHeight();
            int location[] = new int[2];
            getLocationOnScreen(location);
            location[1] = 0;
            Log.d("edu", "locationX : " + location[0] + " locationY : " + location[1]);
            Log.d("edu", "view left : " + getLeft() + " , top : " + getTop());
            localLayoutParams.x = location[0];
            localLayoutParams.y = location[1];
            mProgressDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
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
}
