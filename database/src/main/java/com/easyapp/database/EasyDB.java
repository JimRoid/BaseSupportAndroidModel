package com.easyapp.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * 以 key value 進行資料儲存處理
 * 可儲存成列表
 */
public final class EasyDB {

    private static final String LOGIN = "LOGIN";
    private static final String TOKEN = "TOKEN";

    private static WeakReference<Application> applicationWeakReference;
    private static SharedPreferences sharedPreferences;


    public static void init(Application application) {
        applicationWeakReference = new WeakReference<>(application);
        String packageName = getContext().getPackageName();
        sharedPreferences = getContext().getSharedPreferences(packageName, Context.MODE_PRIVATE);
    }

    static Context getContext() {
        return applicationWeakReference.get();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * 建立隨機的uuid
     *
     * @return uuid
     */
    public static String RandUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 記錄登入的token
     *
     * @param token
     */
    public static void Login(String token) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(LOGIN, true).apply();
            sharedPreferences.edit().putString(TOKEN, token).apply();
        }
    }

    public static void Logout() {
        sharedPreferences.edit().putBoolean(LOGIN, false).apply();
        sharedPreferences.edit().putString(TOKEN, "").apply();
    }

    public static String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public static boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public static boolean putString(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public static boolean putBoolean(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean putInt(String key, int value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public static boolean putLong(String key, long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public static boolean putFloat(String key, float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public static String getString(String key, String value) {
        return sharedPreferences.getString(key, value);
    }


    public static String getString(String key) {
        return getString(key, "");
    }

    public static boolean getBoolean(String key, boolean value) {
        return sharedPreferences.getBoolean(key, value);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static int getInt(String key, int value) {
        return sharedPreferences.getInt(key, value);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static long getLong(String key, long value) {
        return sharedPreferences.getLong(key, value);
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static float getFloat(String key, float value) {
        return sharedPreferences.getFloat(key, value);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static void putList(String key, ArrayList<String> strings) {
        String[] array = strings.toArray(new String[strings.size()]);
        sharedPreferences.edit().putString(key, TextUtils.join("‚‗‚", array)).apply();
    }

    public static ArrayList<String> getList(String key) {
        String[] split = TextUtils.split(sharedPreferences.getString(key, ""), "‚‗‚");
        ArrayList<String> strings = new ArrayList<>();
        if (split.length > 0) {
            strings.addAll(new ArrayList<>(Arrays.asList(split)));
        }
        return strings;
    }

}