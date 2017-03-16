package com.easyapp.baseproject_sample.activityDrawer;

import com.easyapp.baseproject_sample.screen.drawer.DrawerBank;
import com.easyapp.lib.base.activity.BaseDrawerMainActivity;

public class DrawerViewActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
        setTitle("BaseProject Sample");
        ReplaceFragment(new DrawerBank());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            showBack(false);
        }
    }

    @Override
    public void OnAddFragment() {
        super.OnAddFragment();
        showBack(true);
    }


}
