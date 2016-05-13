package com.easyapp.baseproject.lib.baseActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.easyapp.baseproject.lib.customview.loadDialog.SpotsDialog;
import com.easyapp.baseproject.lib.tool.CleanCache;

/**
 * 提供一些基本方法的activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CleanCache.CleanGlideCache(this);
        alertDialog = new SpotsDialog(this);
    }

    /**
     * 顯示讀取的進度動畫dialog
     */
    protected void showLoading() {
        showLoading(false);
    }

    /**
     * 顯示讀取的進度動畫dialog
     */
    protected void showLoading(boolean notautodismiss) {
        if (!alertDialog.isShowing()) {
            alertDialog.show();
            if (!notautodismiss) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (alertDialog != null && alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                }, 10000);
            }
        }
    }

    /**
     * 關閉dialog
     */
    protected void cancelLoading() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /**
     * 隱藏鍵盤
     */
    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
