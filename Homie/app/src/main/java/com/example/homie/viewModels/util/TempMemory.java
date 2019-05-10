package com.example.homie.viewModels.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TempMemory {

    private final static String APP_PREFERENCES = "tempdata";
    private final static String USER_NAME_KEY = "userName";

    private final static String DEFAULT_NAME = "Product Owner";

    public static void saveUserName(Context context, String userName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME_KEY, userName);
        editor.apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString(USER_NAME_KEY, DEFAULT_NAME);
        return userName;
    }
}
