package com.easyapp.app_http;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class CheckVersion extends AsyncTask<String, String, String> {

    private WeakReference<Activity> activityWeakReference;

    private Result result;

    public static void Check(Activity activity, String packageName) {
        new CheckVersion(activity).execute(packageName);
    }

    private CheckVersion(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activityWeakReference.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activityWeakReference.get().isFinishing()) {
//                    mDialog = new ProgressDialog(activityWeakReference.get());
//                    mDialog.setMessage(activityWeakReference.get().getString(com.easyapp.lib.R.string.downloading));
//                    mDialog.setCancelable(false);
//                    mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                    mDialog.show();
                }
            }
        });
    }

    @Override
    protected String doInBackground(String... strings) {
        String packageName = strings[0];
        try {
            Result result = getNewVersion(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.versionName;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        Logger.d(new Gson().toJson(result));
    }

    private Result getNewVersion(String packageName) throws IOException {
        result = new Result();
        result.versionName = Jsoup.connect("https://play.google.com/store/apps/details?id=" + packageName + "&hl=it")
                .timeout(30000)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get()
                .select(".hAyfc .htlgb")
                .get(7)
                .ownText();

        Logger.d("result.versionName:" + result.versionName);

        return result;
    }

    class Result {
        String versionName = null;
        String updateInfos = null;
    }
}
