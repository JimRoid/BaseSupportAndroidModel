package com.easyapp.lib.activity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.easyapp.lib.event.MessageAddFragment;
import com.easyapp.lib.event.MessageReplaceFragment;
import com.easyapp.lib.tab.FragNavController;
import com.easyapp.lib.tab.FragmentHistory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * * 簡單可支援fragment 切換的 activity
 */
public abstract class BaseFragmentActivity extends BaseLoadingActivity implements FragNavController.TransactionListener, FragNavController.RootFragmentListener {

    protected FragmentHistory fragmentHistory;
    protected FragNavController fragNavController;
    protected ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVar(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //fragment 切換id
    protected abstract int getContainerId();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageAddFragment(MessageAddFragment event) {
        addFragment(event.getFragment());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReplaceFragment(MessageReplaceFragment event) {
        replaceFragment(event.getFragment());
    }

    protected void initVar(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        fragmentHistory = new FragmentHistory();
        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), getContainerId())
                .transactionListener(this)
                .rootFragmentListener(this, getNumberOfTabs())
                .build();
    }

    protected abstract int getNumberOfTabs();

    protected void addFragment(Fragment fragment) {
        if (fragNavController != null) {
            fragNavController.pushFragment(fragment);
        }
        onAddFragment();
    }

    protected void replaceFragment(Fragment fragment) {
        if (fragNavController != null) {
            fragNavController.clearStack();
            fragNavController.replaceFragment(fragment);
        }
        onReplaceFragment();
    }

    public abstract void onAddFragment();

    public abstract void onReplaceFragment();

    @Override
    public void onBackPressed() {
        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

}
