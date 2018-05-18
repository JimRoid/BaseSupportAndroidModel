package com.easyapp.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * 以 key value 進行資料儲存處理
 * 可儲存成列表
 */
public class EasyDB {

    private static final String LOGIN = "LOGIN";
    private static final String TOKEN = "TOKEN";


    private static SharedPreferences initial(Context context) {
        try {
            String packageName = context.getPackageName();
            return context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear(Context context) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }
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
    public static void Login(Context context, String token) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(LOGIN, true).apply();
            sharedPreferences.edit().putString(TOKEN, token).apply();
        }
    }

    public static void Logout(Context context) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(LOGIN, false).apply();
            sharedPreferences.edit().putString(TOKEN, "").apply();
        }
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(TOKEN, "");
    }

    public static boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(LOGIN, false);
    }


    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public static String getStringValue(Context context, String key, String value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(key, value);
    }


    public static String getStringValue(Context context, String key) {
        return getStringValue(context, key, "");
    }

    public static boolean getBooleanValue(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(key, value);
    }

    public static boolean getBooleanValue(Context context, String key) {
        return getBooleanValue(context, key, false);
    }

    public static int getIntValue(Context context, String key, int value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt(key, value);
    }

    public static int getIntValue(Context context, String key) {
        return getIntValue(context, key, 0);
    }

    public static long getLongValue(Context context, String key, long value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(key, value);
    }

    public static long getLongValue(Context context, String key) {
        return getLongValue(context, key, 0);
    }

    public static float getFloatValue(Context context, String key, float value) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return 0f;
        }
        return sharedPreferences.getFloat(key, value);
    }

    public static float getFloatValue(Context context, String key) {
        return getFloatValue(context, key, 0f);
    }

    public static boolean putList(Context context, String key, ArrayList<String> strings) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array = strings.toArray(new String[strings.size()]);
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        editor.putString(key, TextUtils.join("‚‗‚", array));
        return editor.commit();
    }

    public static ArrayList<String> getList(Context context, String key) {
        SharedPreferences sharedPreferences = initial(context);
        if (sharedPreferences == null) {
            return new ArrayList<>();
        }

        String[] split = TextUtils.split(sharedPreferences.getString(key, ""), "‚‗‚");
        ArrayList<String> strings = new ArrayList<>();
        if (split.length > 0) {
            strings.addAll(new ArrayList<>(Arrays.asList(split)));
        }
        return strings;
    }

}