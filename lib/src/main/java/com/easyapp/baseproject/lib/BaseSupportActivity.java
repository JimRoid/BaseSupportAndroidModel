package com.easyapp.baseproject.lib;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by easyapp_jim on 15/9/3.
 */
public abstract class BaseSupportActivity extends AppCompatActivity implements OnFragmentTransactionListener {
    protected int container = 0;
    protected FragmentManager fragmentManager = getSupportFragmentManager();

    protected void setContainer(int container) {
        this.container = container;
    }


    @Override
    public void AddFragment(Fragment fragment) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(container);
        if (!fragment.getClass().equals(originalFragment.getClass())) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left).replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }

    @Override
    public void AddFragment_Up(Fragment fragment) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up).add(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
    }

    @Override
    public void ReplaceFragment(Fragment fragment) {
        PopAllBackStack();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left).replace(container, fragment, "main").disallowAddToBackStack().commitAllowingStateLoss();
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

    }

    @Override
    public void OnReplaceFragment() {

    }
}
