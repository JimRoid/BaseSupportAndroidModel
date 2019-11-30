package com.easyapp.app_http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.easyapp.image.MultiImageSelectorActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CODE = 1010;
    private ArrayList<String> imageSelect;
    private ImageView imageView;
    private Button btUpload;
    private Button btSelect;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSelect = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.ivPicture);
        btSelect = findViewById(R.id.btSelect);
        btUpload = findViewById(R.id.btUpload);
        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureSelect();
            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                imageSelect = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (imageSelect.size() > 0) {
                    Logger.d(imageSelect.get(0));
                    Glide.with(this).load(imageSelect.get(0)).into(imageView);
                }
            }
        }
    }


    private void pictureSelect() {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 8);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        if (imageSelect != null && imageSelect.size() > 0) {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, imageSelect);
        }
        startActivityForResult(intent, REQUEST_IMAGE_CODE);
    }
}
