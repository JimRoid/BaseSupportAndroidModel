package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.easyapp.baseproject.lib.callback.iFragmentTransactionListener;

/**
 * 簡單可支援fragment 切換的base
 */
public abstract class BaseEasyFragment extends BaseFragment {

    protected iFragmentTransactionListener onFragmentTransactionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFragmentTransactionListener = (iFragmentTransactionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    protected void AddFragment(Fragment fragment, Bundle bundle) {
        onFragmentTransactionListener.AddFragment(fragment, bundle);
    }

    protected void AddFragment(Fragment fragment) {
        onFragmentTransactionListener.AddFragment(fragment);
    }

    protected void AddFragment(Fragment fragment, String anim) {
        onFragmentTransactionListener.AddFragment(fragment, anim);
    }

    protected void AddFragment(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragment(fragment, container);
    }

    protected void AddFragment_Fade(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragment_Zoom(fragment, container);
    }

    protected void AddFragment_Up(Fragment fragment) {
        onFragmentTransactionListener.AddFragment_Up(fragment);
    }

    protected void AddFragment_Up(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragment_Up(fragment, container);
    }

    protected void ReplaceFragment(Fragment fragment, Bundle bundle) {
        onFragmentTransactionListener.ReplaceFragment(fragment, bundle);
    }

    protected void ReplaceFragment(Fragment fragment) {
        onFragmentTransactionListener.ReplaceFragment(fragment);
    }

    protected void ReplaceFragment(Fragment fragment, int container) {
        onFragmentTransactionListener.ReplaceFragment(fragment, container);
    }

    protected void ReplaceFragment(Fragment fragment, String anim) {
        onFragmentTransactionListener.ReplaceFragment(fragment, anim);
    }

    protected void ReplaceFragment(Fragment fragment, String anim, Bundle bundle) {
        onFragmentTransactionListener.ReplaceFragment(fragment, anim, bundle);
    }

    protected void ReplaceFragment(Fragment fragment, int container, String anim) {
        onFragmentTransactionListener.ReplaceFragment(fragment, container, anim);
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


}
