package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseActivity.BaseTabActivity;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;

public class TabActivity extends BaseTabActivity {
    @Override
    protected void onSetupTab() {
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
    }

    @Override
    protected void initial() {

    }


}
