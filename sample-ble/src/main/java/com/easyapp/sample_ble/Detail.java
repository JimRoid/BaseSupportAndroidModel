package com.easyapp.sample_ble;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easyapp.ble.SelectBleActivity;
import com.easyapp.ble.search.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detail extends Fragment {

    public static String Tag = "Detail.class";
    public static int RequestBle = 101;

    @BindView(R.id.btSelect)
    Button btSelect;
    Unbinder unbinder;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    public Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initBleDevice(SearchResult searchResult) {
        tvAddress.setText(searchResult.getAddress());
        tvName.setText(searchResult.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btSelect)
    public void onViewClicked() {
        if (getContext() != null) {
            startActivityForResult(new Intent().setClass(getContext(), SelectBleActivity.class), RequestBle);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestBle && resultCode == Activity.RESULT_OK) {
            SearchResult searchResult = data.getParcelableExtra(SelectBleActivity.EXTRA_RESULT);
            if (searchResult != null) {
                Log.d(Tag, searchResult.getAddress());
                initBleDevice(searchResult);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
