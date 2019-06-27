package com.easyapp.lib.fragment;


import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.lib.tool.DisplayUtil;
import com.easyapp.lib.tool.Utils;
import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;
import com.easyapp.lib.R;

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

    private void hideAppbarElevation() {
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

    private void initialView() {
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

    abstract public void initialAdapter();


    public EasyPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }


}
