package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

    /**
     * 是否需要用到 child manager
     *
     * @return
     */
    protected boolean isChildMode() {
        return false;
    }

    protected int getChildId() {
        return -1;
    }

    protected void AddChildFragment(Fragment fragment) {
        getParentFrag(this).beginTransaction().replace(getChildId(), fragment, "tab").addToBackStack("").commitAllowingStateLoss();
        onFragmentTransactionListener.OnAddFragment();
    }

    protected void ReplaceChildFragment(Fragment fragment) {
        getParentFrag(this).beginTransaction().add(getChildId(), fragment, "tab").commitAllowingStateLoss();
        onFragmentTransactionListener.OnReplaceFragment();
    }


    protected void AddFragment(Fragment fragment, Bundle bundle) {
        if (isChildMode() && getChildId() != -1) {
            fragment.setArguments(bundle);
            AddChildFragment(fragment);
        } else {
            onFragmentTransactionListener.AddFragment(fragment, bundle);
        }
    }

    protected void AddFragment(Fragment fragment) {
        if (isChildMode()) {
            AddChildFragment(fragment);
        } else {
            onFragmentTransactionListener.AddFragment(fragment);
        }
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
        if (isChildMode()) {
            ReplaceChildFragment(fragment);
        } else {
            onFragmentTransactionListener.ReplaceFragment(fragment);
        }
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


    protected FragmentManager getParentFrag(Fragment frag) {
        Fragment f = frag.getParentFragment();
        if (f != null) {
            return getParentFrag(f);
        } else {
            return frag.getChildFragmentManager();
        }
    }
}
