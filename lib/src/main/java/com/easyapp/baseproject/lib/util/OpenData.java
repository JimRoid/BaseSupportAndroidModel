package com.easyapp.baseproject.lib.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import com.easyapp.baseproject.lib.TouchViewActivity;

import java.io.File;

/**
 * Created by easyapp_jim on 2015/10/19.
 */
public class OpenData {

    public static void OpenUrl(Activity activity, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        StartActivity(activity, browserIntent);
    }


    public static void OpenPdf(Activity activity, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            StartActivity(activity, intent);
        }
    }

    public static void OpenImage(Activity activity, File file) {
        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/jpg");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            StartActivity(activity, intent);
        }
    }

    public static void OpenTouchImage(Activity activity, String path) {
        Intent intent = new Intent(activity, TouchViewActivity.class);
        intent.putExtra(Common.PATH, path);
        StartActivity(activity, intent);
    }

    public static void StartActivity(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
