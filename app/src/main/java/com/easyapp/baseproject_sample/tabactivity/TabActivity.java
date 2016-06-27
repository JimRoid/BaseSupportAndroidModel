package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseActivity.BaseTabActivity;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;
import com.easyapp.baseproject_sample.R;

public class TabActivity extends BaseTabActivity {
    //    @Override
//    protected void initial() {
//        ReplaceFragment(new BaseViewPagerTab());
//    }
    @Override
    protected void onSetupTab() {

        setTitleImageResource(R.drawable.logo);

        addTab(new EasyTab().setFragment(SampleTabFragment.class).setTabTextResource(R.drawable.tab_color));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
        addTab(new EasyTab().setFragment(FragmentHospitals.class));
        addTab(new EasyTab().setFragment(SampleTabFragment.class));
    }

}
