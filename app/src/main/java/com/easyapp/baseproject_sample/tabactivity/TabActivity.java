package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseActivity.BaseTabActivity;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;
import com.easyapp.baseproject_sample.R;

public class TabActivity extends BaseTabActivity {
    @Override
    protected void onSetupTab() {

        setTitleImageResource(R.drawable.logo);

        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
    }

}
