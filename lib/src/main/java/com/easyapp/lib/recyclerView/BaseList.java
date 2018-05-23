package com.easyapp.lib.recyclerView;


import android.view.View;


/**
 * 基本列表 List
 */

public abstract class BaseList<VH extends BaseRecyclerViewAdapter.ViewHolder, T> extends BaseHeadList<VH, VH, T, T> {
    @Override
    public int onViewHolderLayoutHead() {
        return onViewHolderLayoutContent();
    }

    @Override
    public VH onCreateViewHolderHead(View view) {
        return onCreateViewHolderContent(view);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolderHead(VH viewHolder, T o) {
        onBindViewHolderContent(viewHolder, o);
    }
}
