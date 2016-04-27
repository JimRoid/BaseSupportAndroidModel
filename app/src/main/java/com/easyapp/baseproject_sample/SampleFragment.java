package com.easyapp.baseproject_sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.OpenData;
import com.easyapp.baseproject.lib.baseFragment.BaseToolbarFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends BaseToolbarFragment implements View.OnClickListener {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sample, container, false);
        initView();
        return view;
    }

    private void initView() {
        View bt_showtoolbar = view.findViewById(R.id.bt_showtoolbar);
        bt_showtoolbar.setOnClickListener(this);

        View bt_hidetoolbar = view.findViewById(R.id.bt_hidetoolbar);
        bt_hidetoolbar.setOnClickListener(this);

        View bt_loading = view.findViewById(R.id.bt_loading);
        bt_loading.setOnClickListener(this);

        View bt_touchview = view.findViewById(R.id.bt_touchview);
        bt_touchview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_showtoolbar:
                showToolbar();
                break;
            case R.id.bt_hidetoolbar:
                hideToolbar();
                break;
            case R.id.bt_loading:
                showLoading();
                break;
            case R.id.bt_touchview:
                String[] d = {"https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg"};
                OpenData.OpenTouchImage(getActivity(), d);
                break;
        }
    }
}
