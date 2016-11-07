package com.easyapp.lib.tool;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.easyapp.lib.touchView.TouchViewActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * 快速開啟檔案的class
 */
public class OpenData {

    /**
     * 可自動選擇開啟檔案
     *
     * @param context
     * @param file
     */
    public static void AutoOpen(Context context, File file) {
        String file_extension = getExtension(file);
        file_extension = file_extension.toLowerCase();
        if (file_extension.equals("pdf")) {
            OpenPdf(context, file);
        } else if (file_extension.equals("jpg") || file_extension.equals("png") || file_extension.equals("bmp")) {
            OpenImage(context, file);
        } else {
            OpenUnKnowData(context, file);
        }
    }

    /**
     * 未知檔案 讓android 及使用者自己判斷
     *
     * @param context
     * @param file
     */
    public static void OpenUnKnowData(Context context, File file) {
        if (file.exists()) {
            String subname = getExtension(file);
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/" + subname);
            intent.setFlags(67108864);
            StartActivity(context, intent);
        }
    }

    public static void OpenPhone(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }


    /**
     * 開啟url
     *
     * @param activity
     * @param url
     */
    public static void OpenUrl(Activity activity, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        StartActivity(activity, browserIntent);
    }

    /**
     * 開啟pdf
     *
     * @param context
     * @param file
     */
    public static void OpenPdf(Context context, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(67108864);
            StartActivity(context, intent);
        }
    }

    /**
     * 開啟圖片
     *
     * @param context
     * @param file
     */
    public static void OpenImage(Context context, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/jpg");
            intent.setFlags(67108864);
            StartActivity(context, intent);
        }
    }


    /**
     * 開啟可放大縮小圖片
     *
     * @param context
     * @param path
     */
    public static void OpenTouchImage(Context context, ArrayList<String> path, int position) {

        Intent intent = new Intent(context, TouchViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("PATH", path);
        bundle.putInt("POSITION", position);
        intent.putExtras(bundle);
        StartActivity(context, intent);
    }


    /**
     * 開啟activity
     *
     * @param context
     * @param intent
     */
    public static void StartActivity(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * 開啟activity
     *
     * @param context
     * @param clazz
     */
    public static void StartActivity(Context context, Class<? extends Activity> clazz) {
        try {
            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * 附帶回傳值
     *
     * @param activity
     * @param clazz
     * @param requestCode
     */
    public static void StartActivityForResult(Activity activity, Class<? extends Activity> clazz, int requestCode) {
        try {
            Intent intent = new Intent(activity, clazz);
            activity.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * 附帶回傳值
     *
     * @param activity
     * @param clazz
     * @param requestCode
     */
    public static void StartActivityForResult(Activity activity, Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        try {
            Intent intent = new Intent(activity, clazz);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * 取得目標檔案的副檔名
     *
     * @param file
     * @return
     */
    public static String getExtension(File file) {
        int startIndex = file.getName().lastIndexOf(46) + 1;
        int endIndex = file.getName().length();
        return file.getName().substring(startIndex, endIndex);
    }
}