package com.easyapp.baseproject.lib.tool;

import android.content.Context;

import com.bumptech.glide.Glide;

/**
 * Created by easyapp_jim on 2016/5/4.
 */
public class CleanCache {
    public static void CleanGlideCache(Context context) {
        Glide.get(context).clearMemory();
        new CleanGlideAsyncTask(context).execute();
    }
}