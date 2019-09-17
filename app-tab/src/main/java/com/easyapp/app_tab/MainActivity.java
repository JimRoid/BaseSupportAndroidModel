package com.easyapp.app_tab;

import com.easyapp.lib.activity.BaseTabActivity;

public class MainActivity extends BaseTabActivity {

    @Override
    protected void initTab() {
        addTab("1", new BlankFragment1());
        addTab("2", new BlankFragment2());
        addTab("3", new BlankFragment3());
        addTab("4", new BlankFragment1());
//        addTab("5", new BlankFragment1());
    }

}
