package com.easyapp.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * 基本fragment
 */
public abstract class BaseFragment extends Fragment {
    private Toast toast;
    protected Loading loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();
    }

    protected void showLoading() {
        if (getActivity() == null) {
            return;
        }

        if (getFragmentManager() == null) {
            return;
        }

        if (!loading.isAdded()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(loading, "loading").commitAllowingStateLoss();
        }
    }

    protected void cancelLoading() {
        if (getFragmentManager() == null) {
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
        toast = Toast.makeText(getContext(), content, isLong ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }

    protected void hideKeyboard() {
        if (getView() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }
}
