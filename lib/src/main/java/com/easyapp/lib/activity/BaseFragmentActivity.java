package com.easyapp.lib.activity;


import android.os.Bundle;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.easyapp.lib.R;
import com.easyapp.lib.callback.iFragmentTransactionListener;

/**
 * * 簡單可支援fragment 切換的 activity
 */
public abstract class BaseFragmentActivity extends BaseLoadingActivity implements iFragmentTransactionListener {

    protected final static String FADE = "FADE";
    protected final static String SLIDE = "SLIDE";
    protected final static String SLIDE_UP = "SLIDE_UP";


    protected int containerId = 0;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    protected void setContainer(int container_id) {
        this.containerId = container_id;
    }


    @Override
    public void AddFragment(Fragment fragment) {
        AddFragment(fragment, SLIDE);
    }


    @Override
    public void AddFragment(Fragment fragment, String anim) {
        if (containerId == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (anim.equals(SLIDE)) {
            fragmentTransaction.setCustomAnimations(
                    R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit,
                    R.animator.fragment_slide_right_enter,
                    R.animator.fragment_slide_right_exit);
        } else if (anim.equals(SLIDE_UP)) {
            fragmentTransaction.setCustomAnimations(
                    R.animator.slide_fragment_in,
                    R.animator.slide_fragment_out);
        } else if (anim.equals(FADE)) {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        } else if (anim.equals("")) {

        }
        fragmentTransaction.replace(containerId, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        OnAddFragment();
    }

    @Override
    public void AddFragmentZoom(Fragment fragment) {
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(containerId, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        OnAddFragment();
    }

    @Override
    public void AddFragmentUp(Fragment fragment) {
        if (containerId == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(containerId);
        if (!fragment.getClass().equals(originalFragment.getClass())) {
            fragmentManager.beginTransaction().setCustomAnimations(
                    R.animator.slide_fragment_in,
                    R.animator.slide_fragment_out,
                    R.animator.slide_fragment_in,
                    R.animator.slide_fragment_out)
                    .replace(containerId, fragment, "main")
                    .addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }


    @Override
    public void ReplaceFragment(Fragment fragment) {
        ReplaceFragment(fragment, SLIDE);
    }


    @Override
    public void ReplaceFragment(Fragment fragment, String anim) {
        if (containerId == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        PopAllBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (anim.equals(SLIDE)) {
            fragmentTransaction.setCustomAnimations(
                    R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit,
                    R.animator.fragment_slide_right_exit,
                    R.animator.fragment_slide_right_enter);
        } else if (anim.equals(SLIDE_UP)) {
            fragmentTransaction.setCustomAnimations(
                    R.animator.slide_fragment_in,
                    R.animator.slide_fragment_out,
                    R.animator.slide_fragment_in,
                    R.animator.slide_fragment_out);
        } else if (anim.equals(FADE)) {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        } else if (anim.equals("")) {

        }
        fragmentTransaction.replace(containerId, fragment, "main").disallowAddToBackStack().commitAllowingStateLoss();
        OnReplaceFragment();
    }


    @Override
    public void PopBackStack() {
        fragmentManager.popBackStack();
    }

    @Override
    public void PopBackStack(int i) {
        for (int j = 0; j < i; j++) {
            int backStackId = fragmentManager.getBackStackEntryAt(j).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void PopAllBackStack() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void OnAddFragment() {
        //..加入 fragment後要執行的動作
    }

    @Override
    public void OnReplaceFragment() {
        //..切換 fragment後要執行的動作
    }


}