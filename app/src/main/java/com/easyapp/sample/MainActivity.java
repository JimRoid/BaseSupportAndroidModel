package com.easyapp.sample;


import com.easyapp.lib.base.activity.BaseMainActivity;
import com.easyapp.sample.screen.DiscussList;

/**
 *
 */
public class MainActivity extends BaseMainActivity {

    @Override
    protected void initial() {
        ReplaceFragment(new DiscussList());
    }
}
