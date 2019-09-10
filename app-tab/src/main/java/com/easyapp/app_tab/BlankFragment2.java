package com.easyapp.app_tab;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.easyapp.lib.fragment.BaseToolbarFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends BaseToolbarFragment {


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
        textView.setText("hello_blank_fragment 2");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment(new BlankFragment1());
            }
        });
        return textView;
    }

}
