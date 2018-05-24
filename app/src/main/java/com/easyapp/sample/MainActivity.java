package com.easyapp.sample;


import com.easyapp.lib.activity.BaseMainActivity;
import com.easyapp.lib.drawer.BaseDrawerMainActivity;
import com.easyapp.sample.screen.DiscussCreate;
import com.easyapp.sample.screen.DiscussList;
import com.easyapp.sample.screen.DiscussPager;

/**
 *
 */
public class MainActivity extends BaseMainActivity {

    @Override
    protected void initial() {
//        ReplaceFragment(new DiscussList());
        ReplaceFragment(new DiscussPager());
//        ReplaceFragment(new DiscussCreate());
    }
}
