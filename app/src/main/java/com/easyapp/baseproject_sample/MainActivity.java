package com.easyapp.baseproject_sample;

import com.easyapp.baseproject.lib.base.activity.BaseMainActivity;
import com.easyapp.baseproject_sample.screen.SampleFragment;

/**
 * Created by easyapp_jim on 2016/7/1.
 */
public class MainActivity extends BaseMainActivity {
    @Override
    protected void initial() {
        ReplaceFragment(new SampleFragment(), "");
    }
}
