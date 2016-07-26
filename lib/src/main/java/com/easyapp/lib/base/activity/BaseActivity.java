package com.easyapp.lib.base.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.lib.dialog.LoadingDialog;

/**
 * initial
 * <p>
 * load dialog
 * hide keyboard
 */
public abstract class BaseActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new LoadingDialog(this);
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
    protected void showLoading(boolean auto_dismiss) {
        if (!alertDialog.isShowing()) {
            alertDialog.show();
            if (!auto_dismiss) {
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
