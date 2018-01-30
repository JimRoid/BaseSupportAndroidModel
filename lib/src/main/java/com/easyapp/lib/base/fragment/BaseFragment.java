package com.easyapp.lib.base.fragment;

import android.app.Fragment;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.easyapp.lib.callback.iLoading;

/**
 * 基本fragment
 */
public abstract class BaseFragment extends Fragment {
    private Toast toast;
    private iLoading loading;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loading = (iLoading) context;
    }

    protected void showLoading() {
        if (loading == null) {
            return;
        }
        loading.showLoading();
    }

    protected void cancelLoading() {
        if (loading == null) {
            return;
        }
        loading.cancelLoading();
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
        toast = Toast.makeText(getActivity(), content, isLong ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }

    protected void hideKeyboard() {
        if (getView() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }
}
