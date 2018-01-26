package com.easyapp.lib.widget.viewPager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jim on 2018/1/26.
 */

public class EasyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public EasyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
