package com.easyapp.baseproject_sample;

import com.easyapp.lib.base.activity.BaseMainActivity;
import com.easyapp.lib.tool.Version;

/**
 * Created by easyapp_jim on 2016/7/1.
 */
public class MainActivity extends BaseMainActivity {
    @Override
    protected void initial() {
//        ReplaceFragment(new SampleFragment(), "");
        Version version = new Version("1.1");
        Version app = new Version("1.0");
        Version.checkVersionShowDialog(this, version, app);
    }
}
