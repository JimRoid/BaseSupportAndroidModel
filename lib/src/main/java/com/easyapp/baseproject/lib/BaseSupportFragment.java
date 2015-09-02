package com.easyapp.baseproject.lib;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by easyapp_jim on 15/9/2.
 */
public abstract class BaseSupportFragment extends Fragment {
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

    protected void AddFragment(Fragment fragment){
        onFragmentTransactionListener.AddFragment(fragment);
    }

    protected void AddFragment_Up(Fragment fragment){
        onFragmentTransactionListener.AddFragment_Up(fragment);
    }

    protected void ReplaceFragment(Fragment fragment){
        onFragmentTransactionListener.ReplaceFragment(fragment);
    }

    protected void PopBackStack(){
        onFragmentTransactionListener.PopBackStack();
    }

    protected void PopBackStack(int i) {
        onFragmentTransactionListener.PopBackStack(i);
    }

    protected void PopAllBackStack() {
        onFragmentTransactionListener.PopAllBackStack();
    }

}
