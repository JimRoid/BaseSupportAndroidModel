package com.easyapp.lib.callback;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by easyapp_jim on 15/9/3.
 */
public interface iFragmentTransactionListener {
    void OnAddFragment();

    void OnReplaceFragment();

    void AddFragment(Fragment fragment, Bundle bundle);

    void AddFragment(Fragment fragment);

    void AddFragment(Fragment fragment, int container);

    void AddFragment(Fragment fragment, String anim);

    void AddFragment(Fragment fragment, int container, String anim);

    void AddFragment_Zoom(Fragment fragment, int container);

    void AddFragment_Up(Fragment fragment);

    void AddFragment_Up(Fragment fragment, int container);

    void ReplaceFragment(Fragment fragment, Bundle bundle);

    void ReplaceFragment(Fragment fragment);

    void ReplaceFragment(Fragment fragment, int container);

    void ReplaceFragment(Fragment fragment, String anim);

    void ReplaceFragment(Fragment fragment, String anim, Bundle bundle);

    void ReplaceFragment(Fragment fragment, int container, String anim);

    void PopBackStack();

    void PopBackStack(int i);

    void PopAllBackStack();
}
