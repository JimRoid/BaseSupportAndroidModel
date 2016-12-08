package com.easyapp.lib.base.fragment.list;

import android.view.View;

import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;

/**
 * 沒有頭的recycler List
 */

public abstract class BaseRecyclerList<VH extends BaseRecyclerViewAdapter.ItemHolder, T> extends BaseRecyclerViewFragment<VH, VH, T> {
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
        getBindViewHolder(holder,obj);
    }
}
