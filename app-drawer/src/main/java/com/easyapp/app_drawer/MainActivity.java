package com.easyapp.app_drawer;

import android.os.Bundle;
import com.easyapp.app_drawer.screen.BlankFragment1;
import com.easyapp.lib.drawer.BaseDrawerMainActivity;

public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initial() {
        ReplaceFragment(BlankFragment1.getInstance());
    }


}
