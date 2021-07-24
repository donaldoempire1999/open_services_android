package com.example.openservices.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.openservices.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;

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

    public static void saveSignUpUserInfo(Activity activity, User user) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String jSon = gson.toJson(user);
        prefs.putString("userSignUp", jSon);
        prefs.apply();
    }

    public static User getSignUpUserInfo(Activity activity) {
        User data = new User();
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("userSignUp", "");
        data = gson.fromJson(json, User.class);
        return data;
    }
}
