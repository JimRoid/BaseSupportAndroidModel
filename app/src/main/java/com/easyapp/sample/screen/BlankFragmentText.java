package com.easyapp.sample.screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.sample.R;
import com.easyapp.sample.base.BaseAppFragment;
import com.orhanobut.logger.Logger;

//
public class BlankFragmentText extends BaseAppFragment {

    public static BlankFragmentText getInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("value", i);

        BlankFragmentText blankFragmentText = new BlankFragmentText();
        blankFragmentText.setArguments(bundle);
        return blankFragmentText;
    }

    private int getStringValue() {
        Bundle bundle = getArguments();
        return bundle.getInt("value", 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(getStringValue() + "");
        textView.setTextSize(20f);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(BlankFragmentText.getInstance(getStringValue() + 1));
//                replaceFragment(BlankFragmentText.getInstance(getStringValue() + 1));
//                addFragment(DiscussList.getInstance(getStringValue() + 1));
//                AddFragment();
            }
        });
        Logger.e("show:" + getStringValue());
        return textView;
    }
}
