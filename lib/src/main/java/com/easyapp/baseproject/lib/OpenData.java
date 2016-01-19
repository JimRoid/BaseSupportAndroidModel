package com.easyapp.baseproject.lib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class OpenData {
    public OpenData() {
    }

    public static void OpenUnKnowData(Activity activity, File file) {
        if(file.exists()) {
            String subname = getExtension(file);
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/" + subname);
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }

    }

    public static void OpenUrl(Activity activity, String url) {
        if(!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        StartActivity(activity, browserIntent);
    }

    public static void OpenPdf(Activity activity, File file) {
        if(file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }

    }

    public static void OpenImage(Activity activity, File file) {
        if(file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(path, "application/jpg");
            intent.setFlags(67108864);
            StartActivity(activity, intent);
        }

    }

    public static void OpenTouchImage(Activity activity, String path) {
        Intent intent = new Intent(activity, TouchViewActivity.class);
        intent.putExtra("PATH", path);
        StartActivity(activity, intent);
    }

    public static void StartActivity(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    public static String getExtension(File file) {
        int startIndex = file.getName().lastIndexOf(46) + 1;
        int endIndex = file.getName().length();
        return file.getName().substring(startIndex, endIndex);
    }
}