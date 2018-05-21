package com.easyapp.lib.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;
import com.easyapp.lib.R;

/**
 * tab fragment
 */
public abstract class BaseTabFragment extends BaseToolbarFragment {

    private View view;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected EasyPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easyapp_base_tab, container, false);
        pagerAdapter = new EasyPagerAdapter(getChildFragmentManager());
        initialAdapter();
        initialView();
        return view;
    }

    private void initialView() {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpager);

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            CharSequence title = pagerAdapter.getPageTitle(i);
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    abstract public void initialAdapter();


    public EasyPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }


}
