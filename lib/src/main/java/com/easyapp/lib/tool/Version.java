package com.easyapp.lib.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.orhanobut.logger.Logger;

public class Version implements Comparable<Version> {

    private String version;

    public final String get() {
        return this.version;
    }

    public Version(String version) {
        if (version == null)
            throw new IllegalArgumentException("Version can not be null");
        if (!version.matches("[0-9]+(\\.[0-9]+)*"))
            throw new IllegalArgumentException("Invalid version format");
        this.version = version;
    }

    /**
     * @param that
     * @return 1 版本 marketVersion.compareTo（舊版本）
     */
    @Override
    public int compareTo(Version that) {
        if (that == null)
            return 1;
        String[] thisParts = this.get().split("\\.");
        String[] thatParts = that.get().split("\\.");
        int length = Math.max(thisParts.length, thatParts.length);
        for (int i = 0; i < length; i++) {
            int thisPart = i < thisParts.length ?
                    Integer.parseInt(thisParts[i]) : 0;
            int thatPart = i < thatParts.length ?
                    Integer.parseInt(thatParts[i]) : 0;
            if (thisPart < thatPart)
                return -1;
            if (thisPart > thatPart)
                return 1;
        }
        return 0;
    }

    /**
     * 檢查版本
     *
     * @param context
     * @param marektVersion
     * @param appVersion
     */
    public static void checkVersionShowDialog(final Context context, Version marektVersion, Version appVersion) {
        if (marektVersion.compareTo(appVersion) == 1) {
            new AlertDialog.Builder(context).setCancelable(false).setTitle("更新").setMessage("目前商店已有最新版本，請前往更新").setNegativeButton("前往", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    String sParam = "com.easyapp.fisher";
                    String sParam = context.getPackageName();
                    Logger.d(sParam);
                    Intent intent;
                    try {
                        // Open app with Google Play app
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + sParam));
                        context.startActivity(intent);
                    } catch (android.content.ActivityNotFoundException anfe) {
                        // Open Google Play website
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + sParam));
                        context.startActivity(intent);
                    }
                }
            }).create().show();
        } else {
            return;
        }
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        return this.compareTo((Version) that) == 0;
    }

}