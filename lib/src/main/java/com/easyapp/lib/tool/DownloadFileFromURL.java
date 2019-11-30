package com.easyapp.lib.tool;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import com.easyapp.lib.R;
import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

/**
 * Background Async Task to download file
 */
public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    private final static String SDCard = Environment.getExternalStorageDirectory().getPath() + "/";
    private final static String DIR = SDCard + "Download/";

    private WeakReference<Activity> activityWeakReference;
    private ProgressDialog mDialog;

    public static void Download(Activity activity, String url) {
        new DownloadFileFromURL(activity).execute(url);
    }

    private DownloadFileFromURL(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activityWeakReference.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activityWeakReference.get().isFinishing()) {
                    mDialog = new ProgressDialog(activityWeakReference.get());
                    mDialog.setMessage(activityWeakReference.get().getString(R.string.downloading));
                    mDialog.setCancelable(false);
                    mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mDialog.show();
                }
            }
        });
    }


    /**
     * Downloading file in background thread
     */
    @Override
    protected String doInBackground(String... urls) {
        int count;
        String url = urls[0];
        String filename = url.substring(url.lastIndexOf('/') + 1);

        try {
            URL urs = new URL(url);
            URLConnection connection = urs.openConnection();
            connection.setRequestProperty("Accept-Encoding", "identity"); // <--- Add this line can get length
            connection.connect();

            // getting file length
            int lengthOfFile = connection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(urs.openStream(), 8192);

            // Output stream to write file
            File file = new File(DIR + filename);

            if (file.exists()) {
                file.delete();
            }

            OutputStream output = new FileOutputStream(DIR + filename);


            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lengthOfFile));

                // writing data to file
                output.write(data, 0, count);
            }


            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updating progress bar
     */
    protected void onProgressUpdate(String... progress) {
        try {
            if (mDialog == null) {
                mDialog = new ProgressDialog(activityWeakReference.get());
            }
            mDialog.setProgress(Integer.parseInt(progress[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     **/
    @Override
    protected void onPostExecute(String file_url) {
        try {
            mDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
