package com.easyapp.sample_ble;

import android.content.Intent;

import com.easyapp.lib.activity.BaseMainActivity;

public class MainActivity extends BaseMainActivity {


    @Override
    protected void initial() {
        ReplaceFragment(new Detail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
