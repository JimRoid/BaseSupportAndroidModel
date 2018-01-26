package com.easyapp.lib.callback;

import android.app.Fragment;
import android.os.Bundle;


/**
 * Created by easyapp_jim on 15/9/3.
 */
public interface iFragmentTransactionListener {
    void OnAddFragment();

    void OnReplaceFragment();

    void AddFragment(Fragment fragment);

    void AddFragment(Fragment fragment, int container);

    void AddFragment(Fragment fragment, String anim);

    void AddFragment(Fragment fragment, int container, String anim);

    void AddFragmentZoom(Fragment fragment, int container);

    void AddFragmentUp(Fragment fragment);

    void AddFragmentUp(Fragment fragment, int container);

    void ReplaceFragment(Fragment fragment);

    void ReplaceFragment(Fragment fragment, int container);

    void ReplaceFragment(Fragment fragment, String anim);

    void ReplaceFragment(Fragment fragment, int container, String anim);

    void PopBackStack();

    void PopBackStack(int i);

    void PopAllBackStack();
}
