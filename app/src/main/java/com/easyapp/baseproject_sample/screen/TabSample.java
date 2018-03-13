package com.easyapp.baseproject_sample.screen;

import com.easyapp.lib.base.fragment.BaseTabFragment;

/**
 * Created by jim on 2018/2/22.
 */

public class TabSample extends BaseTabFragment {

    public static TabSample instance(){
        return new TabSample();
    }

    @Override
    public void initialAdapter() {
        getPagerAdapter().addFragment(new BlankFragment(),"測試1");
        getPagerAdapter().addFragment(new Detail(),"測試2");
        getPagerAdapter().addFragment(new Detail(),"測試2");
    }
}
