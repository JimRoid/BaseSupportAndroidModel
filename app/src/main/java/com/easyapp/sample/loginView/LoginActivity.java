package com.easyapp.sample.loginView;

import com.easyapp.lib.activity.BaseToolbarActivity;

/**
 * Created by easyapp_jim on 2016/7/27.
 */
public class LoginActivity extends BaseToolbarActivity {
    @Override
    protected void initial() {
        ReplaceFragment(new Login(), "");
    }
}
