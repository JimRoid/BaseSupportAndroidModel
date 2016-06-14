package com.easyapp.baseproject_sample.draweractivity;

import com.easyapp.baseproject.lib.baseActivity.BaseDrawerMainActivity;
import com.easyapp.baseproject_sample.FragmentImage;
import com.easyapp.baseproject_sample.FragmentViewPage;

public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
        setTitle("BaseProject Sample");
//        ReplaceFragment(new SampleFragment());
        ReplaceFragment(new FragmentViewPage());
//        ReplaceFragment(new FragmentViewPageCircle());

        addLeftDrawer(new FragmentImage());
    }




    @Override
    public void OnAddFragment() {
        super.OnAddFragment();
        showBack(true);
    }
}
