package com.easyapp.sample.screen;

import com.easyapp.lib.fragment.BaseTabPagerFragment;

public class DiscussPager extends BaseTabPagerFragment {
    @Override
    public void initialAdapter() {
        getPagerAdapter().addFragment(DiscussList.getInstance(), "所有討論");
        getPagerAdapter().addFragment(DiscussList.getInstance(), "我的發問");
    }


}
