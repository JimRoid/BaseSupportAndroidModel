package com.easyapp.baseproject.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Preference_DB {
    private String package_name = "";
    private final String LOGIN = "LOGIN";
    private final String TOKEN = "TOKEN";
    private SharedPreferences sharedPreferences;

    public Preference_DB(Context context) {
        package_name = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(package_name, context.MODE_PRIVATE);
    }

    public void Login(String token) {
        sharedPreferences.edit().putBoolean(LOGIN, true).apply();
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }

    public void Logout() {
        sharedPreferences.edit().remove(LOGIN).apply();
        sharedPreferences.edit().remove(TOKEN).apply();
    }


    public String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public boolean putString(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public boolean putBoolean(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public boolean putInt(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public boolean putLong(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public boolean putFloat(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getIngValue(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public long getLongValue(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public float getFloatValue(String key) {
        return sharedPreferences.getFloat(key, (float) 0.0);
    }

    public void putList(String key, ArrayList<String> strings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array = strings.toArray(new String[strings.size()]);
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        editor.putString(key, TextUtils.join("‚‗‚", array));
        editor.apply();
    }

    public ArrayList<String> getList(String key) {
        String[] split = TextUtils.split(sharedPreferences.getString(key, ""), "‚‗‚");
        ArrayList<String> gottenlist = new ArrayList<String>(Arrays.asList(split));
        return gottenlist;
    }
}