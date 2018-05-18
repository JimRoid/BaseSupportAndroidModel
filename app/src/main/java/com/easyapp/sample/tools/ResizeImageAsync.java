package com.easyapp.sample.tools;

import android.content.Context;
import android.os.AsyncTask;

import com.easyapp.file.FileTool;
import com.easyapp.image.resize.ImageResizer;
import com.easyapp.lib.callback.EasyCallback;

import java.io.File;
import java.util.ArrayList;

/**
 * 進行圖片resize
 */
public class ResizeImageAsync extends AsyncTask<String, String, ArrayList<String>> {
    private EasyCallback<ArrayList<String>> callback;
    private ArrayList<String> return_files;


    public ResizeImageAsync(Context context, EasyCallback<ArrayList<String>> callback) {
        super();
        return_files = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        for (String path : params) {
            File new_image = FileTool.CreateFile(FileTool.CreateDir("/.temp"), System.currentTimeMillis() + ".jpg");
            return_files.add(new_image.getAbsolutePath());
            try {
                ImageResizer.saveToFile(ImageResizer.resize(new File(path), 1024, 1024, null), new_image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return return_files;
    }

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        callback.callback(s);
    }
}