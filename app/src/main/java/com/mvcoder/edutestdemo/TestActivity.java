package com.mvcoder.edutestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.mvcoder.edutestdemo.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.btJoin)
    Button btJoin;
    @BindView(R.id.btBack)
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btJoin, R.id.btBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btJoin:
                joinActivity();
                break;
            case R.id.btBack:
                ActivityUtils.finishAllActivities();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("ondestroy!");
    }

    private void joinActivity(){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}
