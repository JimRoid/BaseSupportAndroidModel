package com.easyapp.baseproject_sample.tabactivity;

import com.easyapp.baseproject.lib.baseFragment.BaseToolbarFragment;
import com.easyapp.baseproject_sample.R;

/**
 * Created by easyapp_jim on 2016/6/7.
 */
public abstract class BaseTabFragment extends BaseToolbarFragment {
    @Override
    protected boolean isChildMode() {
        return true;
    }

    @Override
    protected int getChildId() {
        return R.id.tab_content;
    }
}
