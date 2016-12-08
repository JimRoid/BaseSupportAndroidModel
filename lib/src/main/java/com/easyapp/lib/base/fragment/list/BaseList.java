package com.easyapp.lib.base.fragment.list;

import android.view.View;

import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;

/**
 * 基本列表 List
 */

public abstract class BaseList<VH extends BaseRecyclerViewAdapter.ItemHolder, T> extends BaseHeadList<VH, VH, T, T> {
    @Override
    protected VH getHeaderItemHolder(View contactView) {
        return getItemHolder(contactView);
    }

    @Override
    protected int getRecycleViewHolderHeaderLayout() {
        return getRecycleViewHolderLayout();
    }

    @Override
    protected void getBindHeaderViewHolder(VH holder, T obj) {
        getBindViewHolder(holder, obj);
    }
}
