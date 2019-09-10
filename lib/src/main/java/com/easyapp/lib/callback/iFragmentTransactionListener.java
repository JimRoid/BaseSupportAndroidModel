package com.easyapp.lib.callback;


import androidx.fragment.app.Fragment;

/**
 * Created by easyapp_jim on 15/9/3.
 */
public interface iFragmentTransactionListener {
    void OnAddFragment();

    void OnReplaceFragment();

    void AddFragment(Fragment fragment);

    void AddFragment(Fragment fragment, String anim);

    void AddFragmentUp(Fragment fragment);

    void AddFragmentZoom(Fragment fragment);

    void ReplaceFragment(Fragment fragment);

    void ReplaceFragment(Fragment fragment, String anim);

    void PopBackStack();

    void PopBackStack(int i);

    void PopAllBackStack();
}
