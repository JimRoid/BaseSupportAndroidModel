package com.easyapp.baseproject.lib;

import android.support.v4.app.Fragment;

/**
 * Created by easyapp_jim on 15/9/3.
 */
public interface OnFragmentTransactionListener {
    void OnAddFragment();

    void OnReplaceFragment();

    void AddFragment(Fragment fragment);

    void AddFragment_Up(Fragment fragment);

    void ReplaceFragment(Fragment fragment);

    void PopBackStack();

    void PopBackStack(int i);

    void PopAllBackStack();
}
