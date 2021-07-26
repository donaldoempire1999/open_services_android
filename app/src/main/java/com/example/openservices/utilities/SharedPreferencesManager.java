package com.example.openservices.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.openservices.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {

    public static void saveUserInfo(Activity activity, String id, String token, String category) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE).edit();
        editor.putString("userId", id);
        editor.putString("token", token);
        editor.putString("category", category);
        editor.apply();
    }

    public static ArrayList<String> getUserInfo(Activity activity) {
        ArrayList<String> data = new ArrayList<String>();
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_USER_INFO, MODE_PRIVATE);
        data.add(prefs.getString("userId", null));
        data.add(prefs.getString("token", null));
        data.add(prefs.getString("category", null));
        return data;
    }

    public static void saveSignUpUserInfo(Activity activity, User user) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SIGN_UP_USER_INFO, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String jSon = gson.toJson(user);
        prefs.putString("userSignUp", jSon);
        prefs.apply();
    }

    public static User getSignUpUserInfo(Activity activity) {
        User data = new User();
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SIGN_UP_USER_INFO, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("userSignUp", "");
        data = gson.fromJson(json, User.class);
        return data;
    }

    public static void saveSearchType(Activity activity, String type) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SEARCH_TYPES, MODE_PRIVATE).edit();
        prefs.putString("searchType", type);
        prefs.apply();
    }

    public static String getSearchType(Activity activity) {
        String data = null;
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SEARCH_TYPES, MODE_PRIVATE);
        data = prefs.getString("searchType", ConstantValue.SEARCH_TYPE_POSTS);
        return data;
    }

    public static void saveSearchMatching(Activity activity, String searchMatching) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SEARCH_TYPES, MODE_PRIVATE).edit();
        prefs.putString("searchMatching", searchMatching);
        prefs.apply();
    }

    public static String getSearchMatching(Activity activity) {
        String data = null;
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.ARG_PARAM_SEARCH_MATCHING, MODE_PRIVATE);
        data = prefs.getString("searchMatching", ConstantValue.SEARCH_COLLECTION_KEY_WORD_FACETED);
        return data;
    }

    public static void saveSearchCollection(Activity activity, String searchCollection) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SEARCH_TYPES, MODE_PRIVATE).edit();
        prefs.putString("searchCollection", searchCollection);
        prefs.apply();
    }

    public static String getSearchCollection(Activity activity) {
        String data = null;
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.ARG_PARAM_SEARCH_MATCHING, MODE_PRIVATE);
        data = prefs.getString("searchCollection", ConstantValue.SEARCH_COLLECTION_PUBLICATIONS);
        return data;
    }

    public static void saveIsProvider(Activity activity, boolean isProvider) {
        SharedPreferences.Editor prefs = activity.getSharedPreferences(ConstantValue.PREFERENCES_SEARCH_TYPES, MODE_PRIVATE).edit();
        prefs.putBoolean("isProvider", isProvider);
        prefs.apply();
    }

    public static boolean getIsProvider(Activity activity) {
        boolean data = true;
        SharedPreferences prefs = activity.getSharedPreferences(ConstantValue.ARG_PARAM_SEARCH_MATCHING, MODE_PRIVATE);
        data = prefs.getBoolean("isProvider", true);
        return data;
    }
}
