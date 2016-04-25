package com.stugame.main.stuenligshgame.util;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by daidaijie on 2016/4/25.
 */
public class Client {
    public static final OkHttpClient client = new OkHttpClient();

    static {
        client.setConnectTimeout(3, TimeUnit.SECONDS);
    }
}
