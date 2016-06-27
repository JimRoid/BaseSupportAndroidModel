package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseFragment.tab.BaseViewPagerFragment;

/**
 * Created by easyapp_jim on 2016/6/26.
 */
public class BaseViewPagerTab extends BaseViewPagerFragment {
    @Override
    protected void init() {

    }

    @Override
    protected void initPages() {
        addPage("測試", FragmentHospitals.class);
        addPage("測試", FragmentHospitals.class);
        addPage("測試", FragmentHospitals.class);
    }
}
