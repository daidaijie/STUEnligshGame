package com.stugame.main.stuenligshgame.util;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 负责连接网络，只有一个实例
 * Created by daidaijie on 2016/4/25.
 */
public class Client {
    //初始化一个OkHttpClient实例
    public static final OkHttpClient client = new OkHttpClient();

    static {
//      设置最大访问时间为3s
        client.setConnectTimeout(3, TimeUnit.SECONDS);
    }
}
