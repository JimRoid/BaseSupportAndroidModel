package com.easyapp.baseproject_sample.screen.drawer;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easyapp.baseproject_sample.R;
import com.easyapp.lib.base.fragment.BaseDrawerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class DrawerBank extends BaseDrawerFragment {


    @BindView(R.id.bt_toggle)
    Button btToggle;

    boolean isboolean = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_bank, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
        setDrawerCallback(context);

    }

    @OnClick({R.id.bt_toggle, R.id.bt_toggle_false, R.id.bt_add})
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
