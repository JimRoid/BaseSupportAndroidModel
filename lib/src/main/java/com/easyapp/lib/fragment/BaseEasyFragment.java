package com.easyapp.lib.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.easyapp.lib.backpressHelper.FragmentBackHandler;
import com.easyapp.lib.callback.iFragmentTransactionListener;

/**
 * 簡單可支援fragment 切換的base
 */
public abstract class BaseEasyFragment extends BaseFragment implements FragmentBackHandler {

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


    protected void AddFragment(Fragment fragment) {
        onFragmentTransactionListener.AddFragment(fragment);
    }

    protected void AddFragment(Fragment fragment, String anim) {
        onFragmentTransactionListener.AddFragment(fragment, anim);
    }

    protected void AddFragment(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragment(fragment, container);
    }

    protected void AddFragmentFade(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragmentZoom(fragment, container);
    }

    protected void AddFragmentUp(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        AddFragmentUp(fragment);
    }

    protected void AddFragmentUp(Fragment fragment) {
        onFragmentTransactionListener.AddFragmentUp(fragment);
    }

    protected void AddFragmentUp(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragmentUp(fragment, container);
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


    protected FragmentManager getParentFrag(Fragment frag) {
        Fragment f = frag.getParentFragment();
        if (f != null) {
            return getParentFrag(f);
        } else {
            return frag.getChildFragmentManager();
        }
    }


}
