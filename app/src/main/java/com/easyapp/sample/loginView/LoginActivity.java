package com.easyapp.sample.loginView;

import com.easyapp.lib.activity.BaseMainActivity;

/**
 * Created by easyapp_jim on 2016/7/27.
 */
public class LoginActivity extends BaseMainActivity {
    @Override
    protected void initial() {
        ReplaceFragment(new Login(), "");
    }
}
