package com.stugame.main.stuenligshgame.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by daidaijie on 2016/4/25.
 */
public class StateUtil {
    /**
     * 判断是否连接到网络
     * @param context
     * @return answer
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if (wifi | internet) {
            return true;
        } else {
            return false;
        }
    }
}
