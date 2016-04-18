package com.easyapp.baseproject_sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.baseFragment.BaseToolbarFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends BaseToolbarFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sample, container, false);

        showLoading();
        return view;
    }

}
