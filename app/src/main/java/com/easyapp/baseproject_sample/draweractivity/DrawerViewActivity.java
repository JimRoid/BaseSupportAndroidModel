package com.easyapp.baseproject_sample.draweractivity;

import com.easyapp.baseproject.lib.base.activity.BaseDrawerMainActivity;
import com.easyapp.baseproject_sample.screen.FragmentViewPage;

public class DrawerViewActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
        setTitle("BaseProject Sample");
        ReplaceFragment(new FragmentViewPage());
    }



    @Override
    public void OnAddFragment() {
        super.OnAddFragment();
        showBack(true);
    }
}
