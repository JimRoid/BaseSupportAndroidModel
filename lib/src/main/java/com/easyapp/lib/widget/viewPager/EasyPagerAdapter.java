package com.easyapp.lib.widget.viewPager;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * adapter
 */

public class EasyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private ArrayList<Integer> integers;

    //custom
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public EasyPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    public void initCustomTab(ViewPager viewPager, TabLayout tabLayout) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;

        viewPager.setAdapter(this);
        tabLayout.setupWithViewPager(viewPager);

        if (tabLayout.getTabCount() > 0) {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(getTabView(i));
                }
            }
        }
    }

    /**
     * custom tabLayout.tab
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        return null;
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
        titles.add("");
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    public void addFragment(Fragment fragment, int resourceId) {
        fragments.add(fragment);
        integers.add(resourceId);
    }

    public void addFragment(Fragment fragment, String title, int resourceId) {
        fragments.add(fragment);
        titles.add(title);
        integers.add(resourceId);
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(ArrayList<Integer> integers) {
        this.integers = integers;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
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
