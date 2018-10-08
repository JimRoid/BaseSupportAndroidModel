package com.easyapp.sample.screen;

import com.easyapp.lib.fragment.BaseTabFragment;

public class DiscussPager extends BaseTabFragment {
    @Override
    public void initialAdapter() {
        getPagerAdapter().addFragment(DiscussList.instance(), "所有討論");
        getPagerAdapter().addFragment(DiscussList.instance(), "我的發問");
    }


}
