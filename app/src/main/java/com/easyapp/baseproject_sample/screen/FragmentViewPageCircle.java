package com.easyapp.baseproject_sample.screen;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.screen.list.PostsList;
import com.easyapp.easypager.EasyFragmentPagerAdapter;
import com.easyapp.easypager.PagerBullet;
import com.easyapp.lib.touchView.FragmentTouchView;



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
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        EasyFragmentPagerAdapter fragmentPagerItemAdapter = new EasyFragmentPagerAdapter(getFragmentManager());

        fragmentPagerItemAdapter.addFragment(new PostsList());
        fragmentPagerItemAdapter.addFragment(FragmentTouchView.instance("http://i.imgur.com/1cULBoj.jpg"));
        fragmentPagerItemAdapter.addFragment(new PostsList());
        viewPager.setAdapter(fragmentPagerItemAdapter);

        PagerBullet pagerBullet = new PagerBullet(this.getActivity(), viewPager, view);


        pagerBullet.invalidateBullets();
    }

}
