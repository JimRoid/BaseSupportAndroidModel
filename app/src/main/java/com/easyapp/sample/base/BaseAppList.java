package com.easyapp.sample.base;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.easyapp.lib.recyclerView.BaseList;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.sample.http.api.ApiTool;

import java.util.List;


/**
 * 下標紀錄列表
 */
public abstract class BaseAppList<VH extends BaseRecyclerViewAdapter.ViewHolder, T> extends BaseList<VH, T> {

    protected ApiTool apiTool;
    protected TextView tvEmptyOops;
    protected ImageView ivEmpty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiTool = new ApiTool(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onViewCreated();
            onRequestData();
        }
    }

    @Override
    public void onRefresh() {
        onRequestData();
        super.onRefresh();
    }

    @Override
    public void onRequestData() {

    }

    @Override
    protected int onGridLayoutSpanCount() {
        return 1;
    }


}
