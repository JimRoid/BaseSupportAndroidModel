package com.easyapp.app_http;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.easyapp.http.listener.EasyApiCallback;
import com.easyapp.http.upload.OnUploadListener;
import com.easyapp.http.upload.ProgressInfo;
import com.easyapp.image.MultiImageSelectorActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CODE = 1010;
    private ArrayList<String> imageSelect;
    private ImageView imageView;
    private TextView tvProgress;
    private Button btUpload;
    private Button btSelect;
    private ProgressBar progressBar;
    private ApiTool apiTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSelect = new ArrayList<>();

        apiTool = new ApiTool(this);
        apiTool.setUploadListener(new OnUploadListener() {
            @Override
            public void onUpLoadProgress(ProgressInfo info) {
                onProgressUpdate(info.getPercent());
            }

            @Override
            public void onUploadGetContentLengthFail(ProgressInfo info) {
                onError();
            }
        });

        tvProgress = findViewById(R.id.tvProgress);
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
                onUpload();
            }
        });
    }

    private void onUpload() {
        if (imageSelect.size() > 0) {
            Logger.d(imageSelect.get(0));
            tvProgress.setText("0%");
            progressBar.setProgress(0);

            apiTool.upload(imageSelect, new EasyApiCallback<JsonObject>() {
                @Override
                public void onCallback(JsonObject jsonObject) {
                    Logger.d("complete");
                    onFinish();
                }
            });
        }
    }

    //    @Override
    public void onProgressUpdate(int percentage) {
        // set current progress
        tvProgress.setText(percentage + "%");
        progressBar.setProgress(percentage);
    }

    //    @Override
    public void onError() {
        // do something on error
        tvProgress.setText("upload error");
    }

    //    @Override
    public void onFinish() {
        // do something on upload finished,
        // for example, start next uploading at a queue
        tvProgress.setText(100 + "%");
        progressBar.setProgress(100);
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
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 8);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        if (imageSelect != null && imageSelect.size() > 0) {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, imageSelect);
        }
        startActivityForResult(intent, REQUEST_IMAGE_CODE);
    }
}
