package com.easyapp.sample;


import com.easyapp.lib.drawer.BaseDrawerMainActivity;
import com.easyapp.sample.screen.DiscussList;

/**
 *
 */
public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
        ReplaceFragment(new DiscussList());
    }
}
