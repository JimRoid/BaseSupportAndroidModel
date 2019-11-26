package com.easyapp.app_drawer.screen;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easyapp.app_drawer.R;
import com.easyapp.lib.drawer.BaseDrawerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends BaseDrawerFragment {


    public static BlankFragment1 getInstance() {
        return new BlankFragment1();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        Button btAdd = view.findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(BlankFragment2.getInstance());
            }
        });
        return view;
    }

    @Override
    protected void AddFragment(Fragment fragment) {
        super.AddFragment(fragment);
    }
}
