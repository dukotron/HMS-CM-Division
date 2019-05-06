package com.example.homie.viewModels.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TempMemory {

    private final static String APP_PREFERENCES = "tempdata";
    private final static String USERID_KEY = "userId";

    private final static String DEFAULT_VALUE = "0";

    public static void saveUserId(Context context, String userId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERID_KEY, userId);
        editor.apply();
    }

    public static String getUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString(USERID_KEY,DEFAULT_VALUE);
        return userId;
    }
}
