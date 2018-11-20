package com.mvcoder.edutestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.dou361.ijkplayer.widget.PlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveActivity extends AppCompatActivity {

    @BindView(R.id.fl_video_container)
    FrameLayout videoContainer;
    @BindView(R.id.bt_play)
    Button btPlay;

    private String URL = "http://192.168.1.105:8080/hqg.mp4";
    @BindView(R.id.et_url)
    EditText etUrl;

    private PlayerView mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);
        //initView();
    }

    private void initView() {
        String url = etUrl.getText().toString();
        if(!TextUtils.isEmpty(url)){
            URL = url;
        }
        addPlayerView();
    }

    @OnClick(R.id.bt_play)
    public void onViewClicked() {
        initView();
        if (mPlayer != null) {
            mPlayer.startPlay();
        }
    }

    private void addPlayerView() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_videocomponent, videoContainer);
        mPlayer = new PlayerView(this, videoContainer);
        mPlayer.setPlaySource(URL)
                .forbidTouch(false)
                .setForbidHideControlPanl(false);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer != null) {
            mPlayer.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null) {
            mPlayer.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.onDestroy();
        }
    }
}
