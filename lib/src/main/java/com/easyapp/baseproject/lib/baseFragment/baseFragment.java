package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * 基本fragment
 */
public abstract class BaseFragment extends Fragment {
    private Toast toast;


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
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }
}
