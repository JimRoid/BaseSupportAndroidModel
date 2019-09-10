package com.easyapp.lib.activity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.easyapp.lib.R;
import com.easyapp.lib.tab.FragNavController;
import com.easyapp.lib.tab.FragmentHistory;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public abstract class BaseTabActivity extends BaseToolbarActivity implements FragNavController.TransactionListener, FragNavController.RootFragmentListener {

    protected FragmentHistory fragmentHistory;
    protected FragNavController fragNavController;

    protected TabLayout tabLayout;

    protected ArrayList<String> TABS;
    protected ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVar(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_tab_host;
    }

    @Override
    protected void initial() {
        TABS = new ArrayList<>();
        fragments = new ArrayList<>();
        tabLayout = findViewById(R.id.tabLayout);
        initTab();
        initTabListener();
    }

    protected abstract void initTab();

    protected void initTabListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentHistory.push(tab.getPosition());
                switchTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                fragNavController.clearStack();
                switchTab(tab.getPosition());
            }
        });
    }

    protected void switchTab(int position) {
        fragNavController.switchTab(position);
    }

    protected void initVar(Bundle savedInstanceState) {
        fragmentHistory = new FragmentHistory();
        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), containerId)
                .transactionListener(this)
                .rootFragmentListener(this, TABS.size())
                .build();
    }

    protected void addTab(String title, Fragment fragment) {
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText(title);
        tabLayout.addTab(tab);
        TABS.add(title);
        fragments.add(fragment);
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        if (getSupportActionBar() != null && fragNavController != null) {
            updateToolbar();
        }
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        if (getSupportActionBar() != null && fragNavController != null) {
            updateToolbar();
        }
    }

    protected void updateToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(!fragNavController.isRootFragment());
        getSupportActionBar().setDisplayShowHomeEnabled(!fragNavController.isRootFragment());
    }

    @Override
    public Fragment getRootFragment(int index) {
        if (index < fragments.size()) {
            return fragments.get(index);
        } else {
            return new Fragment();
        }
    }


    public void initToolbar(Toolbar toolbar, boolean isBackEnabled) {
        setSupportActionBar(toolbar);
        if (isBackEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void initToolbar(Toolbar toolbar, String title, boolean isBackEnabled) {
        setSupportActionBar(toolbar);

        if (isBackEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected void updateTabSelection(int currentTab) {
        for (int i = 0; i < TABS.size(); i++) {
            TabLayout.Tab selectedTab = tabLayout.getTabAt(i);

            if (selectedTab.getCustomView() != null) {
                if (currentTab != i) {
                    selectedTab.getCustomView().setSelected(false);
                } else {
                    selectedTab.getCustomView().setSelected(true);
                }
            } else {

                if (currentTab != i) {

                } else {
                    selectedTab.select();
                }
            }
        }
    }

    @Override
    public void AddFragment(Fragment fragment, String anim) {
        if (fragNavController != null) {
            fragNavController.pushFragment(fragment);
        }
    }

    @Override
    public void onBackPressed() {

        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
        } else {

            if (fragmentHistory.isEmpty()) {
                super.onBackPressed();
            } else {
                if (fragmentHistory.getStackSize() > 1) {

                    int position = fragmentHistory.popPrevious();

                    switchTab(position);

                    updateTabSelection(position);

                } else {

                    switchTab(0);

                    updateTabSelection(0);

                    fragmentHistory.emptyStack();
                }
            }

        }
    }
}
