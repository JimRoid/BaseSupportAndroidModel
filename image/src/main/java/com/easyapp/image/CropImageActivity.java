package com.easyapp.image;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easyapp.image.crop.CropDemoPreset;

public abstract class CropImageActivity extends AppCompatActivity {

    private CropMainFragment mCurrentFragment;

    public CropImageActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        setMainFragmentByPreset(getCropDemoPreset(), getPath());

        Button bt_select = findViewById(R.id.bt_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentFragment.getCroppedImageAsync();
            }
        });
    }

    protected abstract CropDemoPreset getCropDemoPreset();


    protected abstract String getPath();

    private void setMainFragmentByPreset(CropDemoPreset demoPreset, String path) {
        mCurrentFragment = CropMainFragment.newInstance(demoPreset, path);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mCurrentFragment)
                .commit();
    }


}
