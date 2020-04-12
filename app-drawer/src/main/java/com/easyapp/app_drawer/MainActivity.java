package com.easyapp.app_drawer;

import android.os.Bundle;

import com.easyapp.app_drawer.screen.BlankFragment1;
import com.easyapp.lib.activity.BaseDrawerActivity;

public class MainActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initial() {
        replaceFragment(BlankFragment1.getInstance());
    }


}
