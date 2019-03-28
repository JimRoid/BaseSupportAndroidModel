package com.easyapp.lib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.lib.fragment.Loading;
import com.easyapp.lib.callback.iLoading;

/**
 * @author jim
 */
public abstract class BaseLoadingActivity extends AppCompatActivity implements iLoading {
    protected Toast toast;
    protected Loading loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();
    }


    @Override
    public void showLoading() {
        if (loading.isAdded() && loading.isVisible() && loading.isRemoving()) {
            return;
        }
        if (!loading.isAdded()
                && !loading.isVisible()
                && !loading.isRemoving()) {
            loading.show(getSupportFragmentManager(), "load");
        }
    }

    @Override
    public void cancelLoading() {
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
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
