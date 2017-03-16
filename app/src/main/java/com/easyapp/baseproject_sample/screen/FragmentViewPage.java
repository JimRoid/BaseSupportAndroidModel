package com.easyapp.baseproject_sample.screen;

import com.easyapp.baseproject_sample.screen.list.PostsList;
import com.easyapp.lib.base.fragment.tab.BaseViewPagerFragment;

/**
 * Created by easyapp_jim on 2016/5/16.
 */
public class FragmentViewPage extends BaseViewPagerFragment {

    @Override
    protected void initPages() {
//        for (int i = 0; i < 7; i++) {
        addPage("Apple", PostsList.class);
        addPage("Android", PostsList.class);
        addPage("Mac Air", PostsList.class);
//        addPage("iPhone", PostsList.class);
//        addPage("Goods", PostsList.class);
//        addPage("Youtube", PostsList.class);
//        }
    }


    @Override
    protected void init() {
        setFillViewport(true);
//        setCustomTab(R.layout.item_circle, View.NO_ID);

//        setTabTextColor(R.color.white);
//        setTabBackgrounds(R.drawable.tab_background);
//        setTabImageViewIcon(new int[]{R.drawable.ic_tab1, R.drawable.ic_tab2, R.drawable.ic_tab3, R.drawable.ic_tab4, R.drawable.ic_tab5});
//        setFillViewport(true);
    }
}
