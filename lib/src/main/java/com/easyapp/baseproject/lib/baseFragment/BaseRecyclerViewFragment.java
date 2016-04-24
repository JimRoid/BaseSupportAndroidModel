package com.easyapp.baseproject.lib.baseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.recycleView.BaseRecyclerViewAdapter;

/**
 * 基本的列表fragment
 */
public abstract class BaseRecyclerViewFragment extends BaseDrawerFragment {

    private View view;
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter baseRecycleViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easyapp_base_recycle_view, container, false);
        initView();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.easyapp_recycler_view);
    }

    private void initData(){
//        baseRecycleViewAdapter = new BaseRecyclerViewAdapter() {
//        };
    }


}
