package com.easyapp.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.easyapp.lib.R;
import com.easyapp.lib.base.activity.BaseTabActivity;
import com.easyapp.lib.callback.iFragmentTransactionListener;

/**
 * 簡單可支援fragment 切換的base
 */
public abstract class BaseEasyFragment extends BaseFragment {

    protected iFragmentTransactionListener onFragmentTransactionListener;

    private boolean isTabMode = false;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof BaseTabActivity) {
            isTabMode = true;
        } else {
            isTabMode = false;
        }
    }

    protected void setTabMode(boolean isTabMode){
        this.isTabMode = isTabMode;
    }


    protected int getChildId() {
        return R.id.tab_content;
    }

    protected void AddChildFragment(Fragment fragment) {
        getParentFrag(this).beginTransaction().replace(getChildId(), fragment, "tab").addToBackStack("").commitAllowingStateLoss();
        onFragmentTransactionListener.OnAddFragment();
    }

    protected void AddChildFragmentUp(Fragment fragment) {
        getParentFrag(this).beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(getChildId(), fragment, "tab")
                .addToBackStack("").commitAllowingStateLoss();
        onFragmentTransactionListener.OnAddFragment();
    }

    protected void ReplaceChildFragment(Fragment fragment) {
        getParentFrag(this).beginTransaction().add(getChildId(), fragment, "tab").commitAllowingStateLoss();
        onFragmentTransactionListener.OnReplaceFragment();
    }


    protected void AddFragment(Fragment fragment, Bundle bundle) {
        if (isTabMode && getChildId() != -1) {
            fragment.setArguments(bundle);
            AddChildFragment(fragment);
        } else {
            onFragmentTransactionListener.AddFragment(fragment, bundle);
        }
    }

    protected void AddFragment(Fragment fragment) {
        if (isTabMode) {
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

    protected void AddFragmentFade(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragmentZoom(fragment, container);
    }

    protected void AddFragmentUp(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        AddFragmentUp(fragment);
    }

    protected void AddFragmentUp(Fragment fragment) {
        if (isTabMode) {
            AddChildFragmentUp(fragment);
        } else {
            onFragmentTransactionListener.AddFragmentUp(fragment);
        }
    }

    protected void AddFragmentUp(Fragment fragment, int container) {
        onFragmentTransactionListener.AddFragmentUp(fragment, container);
    }

    protected void ReplaceFragment(Fragment fragment, Bundle bundle) {
        onFragmentTransactionListener.ReplaceFragment(fragment, bundle);
    }

    protected void ReplaceFragment(Fragment fragment) {
        if (isTabMode) {
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
