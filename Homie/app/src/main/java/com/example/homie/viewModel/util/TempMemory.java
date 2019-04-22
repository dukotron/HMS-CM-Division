package com.example.homie.viewModel.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TempMemory {
    private final static String APP_PREFERENCES = "tempdata";
    private final static String USERID_KEY = "userId";
    public static void saveUserId(Context context, String userId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        editor.putString(USERID_KEY, userId);
        editor.commit();
    }
}
