package com.easyapp.baseproject.lib;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * 簡單可支援fragment 切換的base
 */
public class BaseEasyFragment extends Fragment {
    private Toast toast;
    protected OnFragmentTransactionListener onFragmentTransactionListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragmentTransactionListener = (OnFragmentTransactionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onPause() {
        hideKeyboard();
        super.onPause();
    }


    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    protected void AddFragment(Fragment fragment) {
        onFragmentTransactionListener.AddFragment(fragment);
    }

    protected void AddFragment_Up(Fragment fragment) {
        onFragmentTransactionListener.AddFragment_Up(fragment);
    }

    protected void ReplaceFragment(Fragment fragment) {
        onFragmentTransactionListener.ReplaceFragment(fragment);
    }

    protected void PopBackStack() {
        onFragmentTransactionListener.PopBackStack();
    }

    protected void PopBackStack(int i) {
        onFragmentTransactionListener.PopBackStack(i);
    }

    protected void PopAllBackStack() {
        onFragmentTransactionListener.PopAllBackStack();
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
}
