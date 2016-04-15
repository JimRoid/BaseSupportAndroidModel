package com.easyapp.baseproject.lib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import com.easyapp.baseproject.lib.touchView.TouchViewActivity;

import java.io.File;

/**
 * 快速開啟檔案的class
 */
public class OpenData {

    /**
     * 可自動選擇開啟檔案
     *
     * @param activity
     * @param file
     */
    public static void AutoOpen(Activity activity, File file) {
        String file_extension = getExtension(file);
        file_extension = file_extension.toLowerCase();
        if (file_extension.equals("pdf")) {
            OpenPdf(activity, file);
        } else if (file_extension.equals("jpg") || file_extension.equals("png") || file_extension.equals("bmp")) {
            OpenImage(activity, file);
        } else {
            OpenUnKnowData(activity, file);
        }
    }

    /**
     * 未知檔案 讓android 及使用者自己判斷
     *
     * @param activity
     * @param file
     */
    public static void OpenUnKnowData(Activity activity, File file) {
        if (file.exists()) {
            String subname = getExtension(file);
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/" + subname);
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }
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
     * @param activity
     * @param file
     */
    public static void OpenPdf(Activity activity, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }
    }

    /**
     * 開啟圖片
     *
     * @param activity
     * @param file
     */
    public static void OpenImage(Activity activity, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/jpg");
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }

    }

    /**
     * 開啟可放大縮小圖片
     *
     * @param activity
     * @param path
     */
    public static void OpenTouchImage(Activity activity, String path) {
        Intent intent = new Intent(activity, TouchViewActivity.class);
        intent.putExtra("PATH", path);
        StartActivity(activity, intent);
    }


    /**
     * 開啟activity
     *
     * @param activity
     * @param intent
     */
    public static void StartActivity(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
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