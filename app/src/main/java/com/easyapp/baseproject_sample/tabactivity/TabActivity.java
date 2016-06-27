package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseActivity.BaseMainActivity;

public class TabActivity extends BaseMainActivity {
    @Override
    protected void initial() {
        ReplaceFragment(new BaseViewPagerTab());
    }
    //    @Override
//    protected void onSetupTab() {
//
//        setTitleImageResource(R.drawable.logo);
//
//        addTab(new EasyTab().setFragment(SampleTabFragment.class));
//        addTab(new EasyTab().setFragment(FragmentHospitals.class));
//        addTab(new EasyTab().setFragment(SampleTabFragment.class));
//        addTab(new EasyTab().setFragment(FragmentHospitals.class));
//        addTab(new EasyTab().setFragment(SampleTabFragment.class));
//        addTab(new EasyTab().setFragment(FragmentHospitals.class));
//        addTab(new EasyTab().setFragment(SampleTabFragment.class));
//    }

}
