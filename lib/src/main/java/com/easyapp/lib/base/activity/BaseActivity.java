package com.easyapp.lib.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.lib.base.fragment.Loading;
import com.easyapp.lib.callback.iLoading;

/**
 * @author jim
 */
public abstract class BaseActivity extends AppCompatActivity implements iLoading {
    protected Toast toast;
    protected View progressLoading;
    protected Loading loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();
    }

    protected void setProgressLoading(View progressView) {
        progressLoading = progressView;
        progressLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void showLoading() {
        if (progressLoading == null) {
            if (loading == null) {
                return;
            }

            if (!loading.isAdded()) {
                loading.show(getSupportFragmentManager(), null);
            }
            return;
        }
        progressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelLoading() {
        if (progressLoading == null) {
            if (loading == null) {
                return;
            }
            if (loading.isAdded()) {
                loading.dismiss();
            }
            return;
        }
        progressLoading.setVisibility(View.GONE);
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
