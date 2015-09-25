package com.easyapp.baseproject.lib;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by easyapp_jim on 15/9/25.
 */
public class CommonTool {

    public static float getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float pxWidth = outMetrics.widthPixels;
        return pxWidth;
    }

    public static float getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float pxWidth = outMetrics.heightPixels;
        return pxWidth;
    }

    /**
     * 0.75 - ldpi
     * <p/>
     * 1.0 - mdpi
     * <p/>
     * 1.5 - hdpi
     * <p/>
     * 2.0 - xhdpi
     * <p/>
     * 3.0 - xxhdpi
     * <p/>
     * 4.0 - xxxhdpi
     *
     * @param activity
     * @return
     */
    public static float getDpi_float(Activity activity) {
        return activity.getResources().getDisplayMetrics().density;
    }

    /**
     * get dpi
     *
     * @param activity
     * @return
     */
    public static float getDpi(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        Log.d("dpi", metrics.densityDpi + "");
        return metrics.densityDpi;
    }


    public static void OpenUrl(Activity activity, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }

    /**
     * 是否為email 格式
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /**
     * 取版本名稱
     *
     * @param activity
     * @return
     */
    public static String getVersionName(Activity activity) {
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /**
     * 取不重複的值
     *
     * @param list
     * @return
     */
    public static ArrayList<String> getList(ArrayList<String> list) {
        Set<String> set = new HashSet<String>(list);
        ArrayList<String> stringList = new ArrayList<String>(set);
        return stringList;
    }
}
