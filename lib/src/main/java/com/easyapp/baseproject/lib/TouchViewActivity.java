package com.easyapp.baseproject.lib;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.easyapp.baseproject.lib.View.TouchImageView;
import com.easyapp.baseproject.lib.asyncTool.DownloadImageTask;
import com.easyapp.baseproject.lib.util.Common;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by easyapp_jim on 2015/10/22.
 */
public class TouchViewActivity extends AppCompatActivity {
    private TouchImageView touchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        touchImageView = new TouchImageView(this);
        setContentView(touchImageView);

        getExtraIntent();
    }

    protected void getExtraIntent() {
        Intent intent = getIntent();
        String path = intent.getStringExtra(Common.PATH);

        if (path.contains("http")) {
            new DownloadImageTask(touchImageView)
                    .execute(path);
        } else if (path.contains("file")) {
            File file = new File(path);
            if (file.exists()) {
                Logger.d(path);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                touchImageView.setImageBitmap(bitmap);
            }
        }
    }


}
