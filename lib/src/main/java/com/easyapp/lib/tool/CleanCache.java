package com.easyapp.lib.tool;

import android.content.Context;

import com.bumptech.glide.Glide;

/**
 * 清除glide暫存記憶體
 */
public class CleanCache {
    public static void CleanGlideCache(Context context) {
        Glide.get(context).clearMemory();
        new CleanGlideAsyncTask(context).execute();
    }
}