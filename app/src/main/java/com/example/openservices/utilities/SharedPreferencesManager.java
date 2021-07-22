package com.example.openservices.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {

    public static void saveUserInfo(Activity activity, String id, String token, String category){
        SharedPreferences.Editor editor = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE).edit();
        editor.putString("userId", id);
        editor.putString("token", token);
        editor.putString("category", category);
        editor.apply();
    }

    public static ArrayList<String> getUserInfo(Activity activity){
        ArrayList<String> data = new ArrayList<String>();
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE);
        data.add(prefs.getString("userId", null));
        data.add(prefs.getString("token", null));
        data.add(prefs.getString("category", null));
        return data;
    }
}
