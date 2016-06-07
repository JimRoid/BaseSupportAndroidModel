package com.easyapp.baseproject.lib.baseFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.baseproject.lib.customview.loadDialog.SpotsDialog;

/**
 * 基本fragment
 */
public abstract class BaseFragment extends Fragment {
    private Toast toast;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new SpotsDialog(getActivity());
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
        toast = Toast.makeText(getContext(), content, isLong ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }
}
