package com.easyapp.baseproject_sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.widgets.SlidingTabLayout;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItem;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItemAdapter;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItems;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPageCircle extends Fragment {


    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_page_circle, container, false);
        initView();
        return view;
    }

    private void initView() {
        FragmentPagerItems fragmentPagerItems = new FragmentPagerItems(getContext());
        fragmentPagerItems.add(FragmentPagerItem.of("", FragmentNews.class));
        fragmentPagerItems.add(FragmentPagerItem.of("", FragmentNews.class));
        fragmentPagerItems.add(FragmentPagerItem.of("", FragmentNews.class));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), fragmentPagerItems);

        viewPager.setAdapter(fragmentPagerItemAdapter);
        SlidingTabLayout tabLayout = (SlidingTabLayout)view.findViewById(R.id.slidingTabLayout);
        tabLayout.setViewPager(viewPager);
    }

}
