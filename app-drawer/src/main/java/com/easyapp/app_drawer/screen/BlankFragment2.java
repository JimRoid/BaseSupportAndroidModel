package com.easyapp.app_drawer.screen;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.app_drawer.R;
import com.easyapp.lib.drawer.BaseDrawerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends BaseDrawerFragment {


    public static BlankFragment2 getInstance() {
        return new BlankFragment2();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

}
