package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.easyapp.baseproject.lib.callback.iFragmentTransactionListener;
import com.easyapp.baseproject.lib.R;

/**
 * * 簡單可支援fragment 切換的 activity
 */
public abstract class BaseSupportActivity extends BaseActivity implements iFragmentTransactionListener {

    protected final static String FADE = "FADE";
    protected final static String SLIDE = "SLIDE";
    protected final static String SLIDE_POP = "SLIDE_POP";
    protected final static String PUSH = "PUSH";


    private Toast toast;
    protected int container = 0;
    protected FragmentManager fragmentManager = getSupportFragmentManager();


    protected void setContainer(int container) {
        this.container = container;
    }

    @Override
    public void AddFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        AddFragment(fragment);
    }

    @Override
    public void AddFragment(Fragment fragment) {
        AddFragment(fragment, false);
    }

    @Override
    public void AddFragment(Fragment fragment, int container) {
        AddFragment(fragment, container, SLIDE, false);
    }

    @Override
    public void AddFragment(Fragment fragment, boolean instead) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        AddFragment(fragment, container, SLIDE, instead);
    }

    @Override
    public void AddFragment(Fragment fragment, String anim) {
        AddFragment(fragment, container, anim, false);
    }

    @Override
    public void AddFragment(Fragment fragment, int container, String anim, boolean instead) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(container);
        if (!fragment.getClass().equals(originalFragment.getClass()) || instead) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (anim.equals(SLIDE)) {
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
            } else if (anim.equals(PUSH)) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up);
            } else if (anim.equals(FADE)) {
                fragmentTransaction.setCustomAnimations(R.anim.zoom_in, 0, 0, R.anim.zoom_out);
            } else if (anim.equals("")) {

            }
            fragmentTransaction.replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }

    @Override
    public void AddFragment_Zoom(Fragment fragment, int container) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.zoom_in, 0, 0, R.anim.zoom_out).add(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        OnAddFragment();
    }

    @Override
    public void AddFragment_Up(Fragment fragment) {
        AddFragment_Up(fragment, container);
    }

    @Override
    public void AddFragment_Up(Fragment fragment, int container) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(container);
        if (!fragment.getClass().equals(originalFragment.getClass())) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up).add(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }

    @Override
    public void ReplaceFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        ReplaceFragment(fragment);
    }

    @Override
    public void ReplaceFragment(Fragment fragment) {
        ReplaceFragment(fragment, container, SLIDE);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, String anim) {
        ReplaceFragment(fragment, container, anim);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, String anim, Bundle bundle) {
        fragment.setArguments(bundle);
        ReplaceFragment(fragment, container, anim);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, int container) {
        ReplaceFragment(fragment, container, SLIDE);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, int container, String anim) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        PopAllBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (anim.equals(SLIDE)) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
        } else if (anim.equals(SLIDE_POP)) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_right);
        } else if (anim.equals(PUSH)) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up);
        } else if (anim.equals(FADE)) {
            fragmentTransaction.setCustomAnimations(R.anim.zoom_in, 0, 0, R.anim.zoom_out);
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

    /**
     * 可關閉的 Toast
     *
     * @param content
     */
    protected void showToast(CharSequence content) {
        showToast(content, false);
    }

    /**
     * 可關閉的 Toast
     *
     * @param content
     */
    protected void showToast(CharSequence content, boolean isLong) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, content, isLong ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }
}
