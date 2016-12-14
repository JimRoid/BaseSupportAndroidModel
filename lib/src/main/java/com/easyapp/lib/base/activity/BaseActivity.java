package com.easyapp.lib.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.lib.base.fragment.Loading;

/**
 * initial
 * <p>
 * load dialog
 * hide keyboard
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toast toast;
    protected Loading loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();
    }


    protected void showLoading() {
        if (getSupportFragmentManager() == null) {
            return;
        }

        if (!loading.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(loading, "loading").commitAllowingStateLoss();
        }
    }

    protected void cancelLoading() {
        if (getSupportFragmentManager() == null) {
            return;
        }

        if (loading.isAdded()) {
            loading.dismiss();
        }
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

    /**
     * 隱藏鍵盤
     */
    protected void hideKeyboard() {
        if (getCurrentFocus() == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
