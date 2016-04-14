package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;

import com.easyapp.baseproject.lib.callback.iToolbarCallback;

/**
 * Created by easyapp_jim on 2016/4/13.
 */
public class baseToolbarFragment extends baseEasyFragment {

    protected iToolbarCallback toolbar_callback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            toolbar_callback = (iToolbarCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
