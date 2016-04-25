package com.stugame.main.stuenligshgame.model;

/**
 * Created by daidaijie on 2016/4/25.
 * 保存用户登录数据
 */
public class UserData {
    private static String username;
    private static String password;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserData.password = password;
    }
}
