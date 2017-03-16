package com.easyapp.baseproject_sample.activityToolbar;

import com.easyapp.lib.base.activity.BaseMainActivity;

/**
 * Created by easyapp_jim on 2016/6/29.
 */
public class ToolbarActivity extends BaseMainActivity {
    @Override
    protected void initial() {
        ReplaceFragment(new ListSample(), "");
    }
}
