package com.easyapp.baseproject_sample.activityTab;

import com.easyapp.baseproject_sample.screen.BlankFragment;
import com.easyapp.lib.base.activity.BaseTabActivity;
import com.easyapp.lib.widget.tabhost.EasyTab;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.screen.list.PostsList;
import com.easyapp.baseproject_sample.screen.list.PostsStaggeredList;

public class TabActivity extends BaseTabActivity {

    @Override
    protected void initialOnCreate() {

    }

    @Override
    protected void onSetupTab() {

        setTitleImageResource(R.drawable.logo);

        addTab(new EasyTab().setFragment(PostsList.class).setTabTextColorResource(R.drawable.tab_color));
        addTab(new EasyTab().setFragment(BlankFragment.class));
        addTab(new EasyTab().setFragment(PostsList.class));
        addTab(new EasyTab().setFragment(PostsStaggeredList.class));
        addTab(new EasyTab().setFragment(PostsList.class));
    }

}
