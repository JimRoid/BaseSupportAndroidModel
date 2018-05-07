package com.easyapp.baseproject_sample.screen.drawer;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easyapp.baseproject_sample.R;
import com.easyapp.lib.base.fragment.BaseDrawerFragment;


/**
 *
 */
public class DrawerBank extends BaseDrawerFragment implements View.OnClickListener {


    boolean isboolean = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_bank, container, false);
        Button btToggle = view.findViewById(R.id.bt_toggle);
        Button btToggleFalse = view.findViewById(R.id.bt_toggle_false);
        Button add = view.findViewById(R.id.bt_add);
        add.setOnClickListener(this);
        btToggle.setOnClickListener(this);
        btToggleFalse.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
        setDrawerCallback(context);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_toggle:
                getDrawerToggle().setDrawerIndicatorEnabled(true);
                break;
            case R.id.bt_toggle_false:
                getDrawerToggle().setDrawerIndicatorEnabled(false);
                break;
            case R.id.bt_add:
                AddFragment(new DrawerBank());
                break;
        }
    }
}
