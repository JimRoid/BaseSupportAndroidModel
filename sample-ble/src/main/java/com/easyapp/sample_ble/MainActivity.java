package com.easyapp.sample_ble;

import com.easyapp.lib.activity.BaseMainActivity;

public class MainActivity extends BaseMainActivity {


    @Override
    protected void initial() {
//        ReplaceFragment(new DeviceList());
        ReplaceFragment(new Detail());
    }
}
