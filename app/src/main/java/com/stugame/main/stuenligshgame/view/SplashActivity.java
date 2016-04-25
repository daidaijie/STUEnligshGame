package com.stugame.main.stuenligshgame.view;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.stugame.main.stuenligshgame.R;

/**
 * 用于展示信息及加载界面
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SHOW_TIME_MIN = 3000;// 最小显示时间
    private long mStartTime;// 开始时间

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x1233) {
                long loadingTime = System.currentTimeMillis() - mStartTime;
                if (loadingTime < SHOW_TIME_MIN) {
                    myHandler.postDelayed(toMainActivity, SHOW_TIME_MIN - loadingTime);
                } else {
                    myHandler.post(toMainActivity);
                }
            }
        }
    };

    Runnable toMainActivity = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        mStartTime = System.currentTimeMillis();
        myHandler.sendEmptyMessage(0x1233);
    }
}
