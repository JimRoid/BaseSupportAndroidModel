package com.easyapp.baseproject_sample.tabactivity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject_sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends BaseTabFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setTitle("Sample");
        return inflater.inflate(R.layout.fragment_child, container, false);
    }

}
