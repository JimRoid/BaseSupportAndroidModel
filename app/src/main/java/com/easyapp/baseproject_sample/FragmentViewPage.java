package com.easyapp.baseproject_sample;

import com.easyapp.baseproject.lib.baseFragment.BaseViewPagerFragment;

/**
 * Created by easyapp_jim on 2016/5/16.
 */
public class FragmentViewPage extends BaseViewPagerFragment {

    @Override
    protected void initPages() {
//        for (int i = 0; i < 7; i++) {
        addPage("test", FragmentNews.class);
        addPage("test", FragmentViewer.class);
//        }
    }


    @Override
    protected void init() {
        setTabTextColor(R.color.white);
        setTabBackgrounds(R.drawable.tab_background);
        setTabImageViewIcon(new int[]{R.drawable.ic_tab1, R.drawable.ic_tab2, R.drawable.ic_tab3, R.drawable.ic_tab4, R.drawable.ic_tab5});
    }
}
