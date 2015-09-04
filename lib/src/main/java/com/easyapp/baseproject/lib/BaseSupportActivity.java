package com.easyapp.baseproject.lib;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * * 簡單可支援fragment 切換的base
 */
public abstract class BaseSupportActivity extends AppCompatActivity implements OnFragmentTransactionListener {
    private Toast toast;
    protected int container = 0;
    protected FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onPause() {
        hideKeyboard();
        super.onPause();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected void setContainer(int container) {
        this.container = container;
    }

    @Override
    public void AddFragment(Fragment fragment) {
        AddFragment(fragment, false);
    }

    @Override
    public void AddFragment(Fragment fragment, boolean instead) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Fragment originalFragment = fragmentManager.findFragmentById(container);
        if (!fragment.getClass().equals(originalFragment.getClass()) || instead) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left).replace(container, fragment, "main").addToBackStack("main_interface").commitAllowingStateLoss();
        }
        OnAddFragment();
    }

    @Override
    public void AddFragment_Up(Fragment fragment) {
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
    public void ReplaceFragment(Fragment fragment) {
        if (container == 0) {
            Toast.makeText(this, "Please Set container ID", Toast.LENGTH_SHORT).show();
            return;
        }
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
        //..加入 fragment後要執行的動作
    }

    @Override
    public void OnReplaceFragment() {
        //..切換 fragment後要執行的動作
    }

    protected void SetBarTitle(CharSequence content) {
        getSupportActionBar().setTitle(content);
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