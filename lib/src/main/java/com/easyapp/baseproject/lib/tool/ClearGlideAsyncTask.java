package com.easyapp.baseproject.lib.tool;

import android.content.Context;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

public class ClearGlideAsyncTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    public ClearGlideAsyncTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        Glide.get(context).clearDiskCache();
        return null;
    }

    @Override
    protected void onPostExecute(Void aBoolean) {
        super.onPostExecute(aBoolean);
        Logger.d("onPostExecute");
    }
}