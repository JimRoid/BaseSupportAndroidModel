package com.easyapp.lib.tool;

import android.content.Context;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

public class CleanGlideAsyncTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    public CleanGlideAsyncTask(Context context) {
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