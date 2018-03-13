package com.easyapp.baseproject_sample.screen;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.screen.list.PostsList;
import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;

import com.easyapp.lib.touchView.FragmentTouchView;


/**
 * test dot view pager
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
        ViewPager viewPager = view.findViewById(R.id.viewpager);


        EasyPagerAdapter fragmentPagerItemAdapter = new EasyPagerAdapter(getFragmentManager());

        fragmentPagerItemAdapter.addFragment(new PostsList());
        fragmentPagerItemAdapter.addFragment(FragmentTouchView.instance("http://i.imgur.com/1cULBoj.jpg"));
        fragmentPagerItemAdapter.addFragment(new PostsList());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
