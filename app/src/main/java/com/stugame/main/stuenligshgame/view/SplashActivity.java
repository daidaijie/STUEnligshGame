package com.stugame.main.stuenligshgame.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.stugame.main.stuenligshgame.model.UserData;

import com.stugame.main.stuenligshgame.R;
import com.stugame.main.stuenligshgame.util.Client;
import com.stugame.main.stuenligshgame.util.StateUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于展示信息及加载界面
 */
public class SplashActivity extends AppCompatActivity {

    private static final long SHOW_TIME_MIN = 1500;// 最小显示时间
    private long mStartTime;// 开始时间

    /**
     * 判断登录状态
     */
    private enum LoginState {
        LOGIN_SUCCESS,
        EMPTY_DATA,
        Login_ERROR,
        NO_INTERNET,
        SERVER_ERROR,
    }


    /**
     * 登录状态及其信息
     */
    public LoginState loginState;
    private Map<LoginState, String> stateInfo;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x1233) {
                long loadingTime = System.currentTimeMillis() - mStartTime;
                long delay = SHOW_TIME_MIN - loadingTime;
                if (delay < 0) delay = 0;

                //登录成功就进入主界面,否则进入登录界面
                //此处要用post来执行更新UI的代码
                if (loginState == LoginState.LOGIN_SUCCESS) {
                    myHandler.postDelayed(toMainActivity, delay);
                } else {
                    Toast.makeText(SplashActivity.this, stateInfo.get(loginState), Toast.LENGTH_SHORT).show();
                    myHandler.postDelayed(toLoginActivity, delay);
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

    Runnable toLoginActivity = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //获取系统当前时间
        mStartTime = System.currentTimeMillis();

        //初始化状态对应的信息
        initStateInfo();

        //从SharedPreferences中获取保存的用户名和密码
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        UserData.setUsername(preferences.getString("username", ""));
        UserData.setPassword(preferences.getString("password", ""));

        //Debug 因为目前SharedPreferences还没保存
        UserData.setUsername("daidaijie");
        UserData.setPassword("password");

        if (UserData.getUsername().isEmpty() || UserData.getPassword().isEmpty()) {
            //用户名或者密码为空
            loginState = LoginState.EMPTY_DATA;
            myHandler.sendEmptyMessage(0x1233);
        } else if (!StateUtil.isNetworkAvailable(this)) {
            //无法连接到网络
            loginState = LoginState.NO_INTERNET;
            myHandler.sendEmptyMessage(0x1233);
        } else {
            //新建一个线程来处理登录时间
            new Thread(new Runnable() {
                @Override
                public void run() {
                    login();
                }
            }).start();
        }
    }

    /**
     * 这里连接服务器判断密码是否正确
     */
    private void login() {
//       新建post请求的数据
        RequestBody body = new FormEncodingBuilder()
                .add("username", UserData.getUsername())
                .add("password", UserData.getPassword())
                .build();

//      请求的URL
        String login_url = getString(R.string.login_url);

//      创建请求
        Request request = new Request.Builder()
                .url(login_url)
                .post(body)
                .build();

//      用异步来进行网络请求，因为API23不允许直接在UI线程中进行网络操作
        Client.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
//              无法和服务器进行连接
                loginState = LoginState.SERVER_ERROR;
                myHandler.sendEmptyMessage(0x1233);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String state = response.body().string();
                if (state.equals("success")) {
//                  登录成功
                    loginState = LoginState.LOGIN_SUCCESS;
                } else if (state.equals("fail")) {
//                  登录失败，账号密码错误
                    loginState = LoginState.Login_ERROR;
                } else {
//                  服务器出现错误
                    loginState = LoginState.SERVER_ERROR;
                }
                myHandler.sendEmptyMessage(0x1233);
            }
        });
    }

    private void initStateInfo() {
        stateInfo = new HashMap<>();
        stateInfo.put(LoginState.LOGIN_SUCCESS, getString(R.string.login_success));
        stateInfo.put(LoginState.EMPTY_DATA, getString(R.string.empty_data));
        stateInfo.put(LoginState.Login_ERROR, getString(R.string.login_error));
        stateInfo.put(LoginState.NO_INTERNET, getString(R.string.no_internet));
        stateInfo.put(LoginState.SERVER_ERROR, getString(R.string.server_error));
    }
}
