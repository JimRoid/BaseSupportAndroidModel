package com.easyapp.baseproject.lib;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;

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
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(layoutParams);
        touchImageView = new TouchImageView(this);
        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        frameLayout.addView(touchImageView);
        setContentView(frameLayout);
        getExtraIntent();
    }

    protected void getExtraIntent() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("PATH");

        if (path.contains("http")) {
            Glide.with(this).load(path).dontAnimate().into(touchImageView);
        } else if (path.contains("/storage")) {
            File file = new File(path);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                touchImageView.setImageBitmap(bitmap);
            }
        } else {
            byte[] decodedString = Base64Tool.decodeBase64(path);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            touchImageView.setImageBitmap(decodedByte);
        }
    }


}
