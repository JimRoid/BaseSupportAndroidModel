package com.easyapp.baseproject_sample;

import android.content.Intent;
import android.view.View;

import com.easyapp.baseproject.lib.baseActivity.BaseMainActivity;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initial() {
        setTitle("BaseProject Sample");
        ReplaceFragment(new SampleFragment());
    }

    public void openDrawerActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainDrawerActivity.class);
        startActivity(intent);
    }
}
