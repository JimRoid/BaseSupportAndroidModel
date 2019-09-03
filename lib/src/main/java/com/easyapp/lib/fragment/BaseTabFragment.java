package com.easyapp.lib.fragment;


import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.easyapp.lib.tool.DisplayUtil;
import com.easyapp.lib.tool.Utils;
import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;
import com.easyapp.lib.R;
import com.google.android.material.tabs.TabLayout;

/**
 * tab fragment
 */
public abstract class BaseTabFragment extends BaseToolbarFragment {

    protected View view;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected EasyPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hideAppbarElevation();
        view = inflater.inflate(R.layout.layout_base_tab, container, false);
        pagerAdapter = new EasyPagerAdapter(getChildFragmentManager());
        initialAdapter();
        initialView();
        return view;
    }

    protected void hideAppbarElevation() {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0));
        getAppBarLayout().setStateListAnimator(stateListAnimator);
    }

    @Override
    public void onDestroyView() {
        if (getActivity() != null) {
            getAppBarLayout().setElevation(DisplayUtil.dpToPx(getActivity(), 8));
        }
        super.onDestroyView();
    }

    protected void initialView() {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpager);

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            CharSequence title = pagerAdapter.getPageTitle(i);
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    abstract protected void initialAdapter();


    protected EasyPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }


}
