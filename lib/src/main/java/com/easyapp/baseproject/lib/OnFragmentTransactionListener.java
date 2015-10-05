package com.easyapp.baseproject.lib;

import android.support.v4.app.Fragment;

/**
 * Created by easyapp_jim on 15/9/3.
 */
public interface OnFragmentTransactionListener {
    void OnAddFragment();

    void OnReplaceFragment();

    void AddFragment(Fragment fragment);

    void AddFragment(Fragment fragment, int container);

    void AddFragment(Fragment fragment, boolean instead);

    void AddFragment(Fragment fragment, int container, boolean instead);

    void AddFragment_Fade(Fragment fragment, int container);

    void AddFragment_Up(Fragment fragment);

    void AddFragment_Up(Fragment fragment, int container);

    void ReplaceFragment(Fragment fragment);

    void ReplaceFragment(Fragment fragment, int container);

    void PopBackStack();

    void PopBackStack(int i);

    void PopAllBackStack();
}
