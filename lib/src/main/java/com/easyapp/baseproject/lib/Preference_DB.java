package com.easyapp.baseproject.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by easyapp_jim on 15/8/21.
 */
public class Preference_DB {
    private String package_name = "";
    private final String FIRST = "FIRST";
    private final String USER_UUID = "USER_UUID";
    private final String LOGIN = "LOGIN";
    private final String TOKEN = "TOKEN";
    private SharedPreferences sharedPreferences;

    public Preference_DB(Context context) {
        package_name = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(package_name, context.MODE_PRIVATE);
        if (isFirst()) {
            GenUUID();
        }
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    private void GenUUID() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        sharedPreferences.edit().putString(USER_UUID, randomUUIDString).apply();
        sharedPreferences.edit().putBoolean(FIRST, false).apply();
    }

    public String getUUID() {
        return sharedPreferences.getString(USER_UUID, "");
    }

    public void Login(String token) {
        sharedPreferences.edit().putBoolean(LOGIN, true).apply();
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }


    public String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public boolean isFirst() {
        return sharedPreferences.getBoolean(FIRST, true);
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

    public String getStringValue(String key, String value) {
        return sharedPreferences.getString(key, value);
    }


    public String getStringValue(String key) {
        return getStringValue(key, "");
    }

    public boolean getBooleanValue(String key, boolean value) {
        return sharedPreferences.getBoolean(key, value);
    }

    public boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }

    public int getIntValue(String key, int value) {
        return sharedPreferences.getInt(key, value);
    }

    public int getIntValue(String key) {
        return getIntValue(key, 0);
    }

    public long getLongValue(String key, long value) {
        return sharedPreferences.getLong(key, value);
    }

    public long getLongValue(String key) {
        return getLongValue(key, 0);
    }

    public float getFloatValue(String key, float value) {
        return sharedPreferences.getFloat(key, value);
    }

    public float getFloatValue(String key) {
        return getFloatValue(key,0f);
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
