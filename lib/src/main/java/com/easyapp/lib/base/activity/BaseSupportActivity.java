package com.easyapp.lib.base.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.easyapp.lib.R;
import com.easyapp.lib.callback.iFragmentTransactionListener;

/**
 * * 簡單可支援fragment 切換的 activity
 */
public abstract class BaseSupportActivity extends BaseActivity implements iFragmentTransactionListener {

    protected final static String FADE = "FADE";
    protected final static String SLIDE = "SLIDE";
    protected final static String SLIDE_POP = "SLIDE_POP";
    protected final static String PUSH = "PUSH";


    protected int containerId = 0;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    protected void setContainer(int container_id) {
        this.containerId = container_id;
    }


    @Override
    public void AddFragment(Fragment fragment) {
        AddFragment(fragment, containerId, SLIDE);
    }

    @Override
    public void AddFragment(Fragment fragment, int container) {
        AddFragment(fragment, container, SLIDE);
    }


    @Override
    public void AddFragment(Fragment fragment, String anim) {
        AddFragment(fragment, containerId, anim);
    }

    @Override
    public void AddFragment(Fragment fragment, int container, String anim) {
        if (container == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (anim.equals(SLIDE)) {
            fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_left);
        } else if (anim.equals(PUSH)) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_out_up, R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_in_up);
        } else if (anim.equals(FADE)) {
            fragmentTransaction.setCustomAnimations(R.animator.zoom_in, 0, 0, R.animator.zoom_out);
        } else if (anim.equals("")) {

        }
        fragmentTransaction.replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        OnAddFragment();
    }

    @Override
    public void AddFragmentZoom(Fragment fragment, int container) {
        fragmentManager.beginTransaction().setCustomAnimations(R.animator.zoom_in, 0, 0, R.animator.zoom_out).replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        OnAddFragment();
    }

    @Override
    public void AddFragmentUp(Fragment fragment) {
        AddFragmentUp(fragment, containerId);
    }

    @Override
    public void AddFragmentUp(Fragment fragment, int container) {
        if (container == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(container);
        if (!fragment.getClass().equals(originalFragment.getClass())) {
            fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_out_up, R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_in_up).replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }


    @Override
    public void ReplaceFragment(Fragment fragment) {
        ReplaceFragment(fragment, containerId, SLIDE);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, String anim) {
        ReplaceFragment(fragment, containerId, anim);
    }


    @Override
    public void ReplaceFragment(Fragment fragment, int container) {
        ReplaceFragment(fragment, container, SLIDE);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, int container, String anim) {
        if (container == 0) {
            Toast.makeText(this, "Please Set containerId ID", Toast.LENGTH_SHORT).show();
            return;
        }
        PopAllBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (anim.equals(SLIDE)) {
            fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_left);
        } else if (anim.equals(SLIDE_POP)) {
            fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_right);
        } else if (anim.equals(PUSH)) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_out_up, R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_in_up);
        } else if (anim.equals(FADE)) {
            fragmentTransaction.setCustomAnimations(R.animator.zoom_in, 0, 0, R.animator.zoom_out);
        } else if (anim.equals("")) {

        }
        fragmentTransaction.replace(container, fragment, "main").disallowAddToBackStack().commitAllowingStateLoss();
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
