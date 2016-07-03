package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.base.activity.BaseTabActivity;
import com.easyapp.baseproject.lib.widget.tabhost.EasyTab;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.screen.PostsList;
import com.easyapp.baseproject_sample.screen.PostsStaggeredList;

public class TabActivity extends BaseTabActivity {

    @Override
    protected void onSetupTab() {

        setTitleImageResource(R.drawable.logo);

        addTab(new EasyTab().setFragment(PostsList.class).setTabTextResource(R.drawable.tab_color));
        addTab(new EasyTab().setFragment(PostsStaggeredList.class));
        addTab(new EasyTab().setFragment(PostsList.class));
        addTab(new EasyTab().setFragment(PostsStaggeredList.class));
        addTab(new EasyTab().setFragment(PostsList.class));
    }

}
