package com.easyapp.sample_ble;

import com.easyapp.lib.recyclerView.BaseList;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;

public abstract class BaseAppList<VH extends BaseRecyclerViewAdapter.ViewHolder, T> extends BaseList<VH, T> {
    @Override
    protected int onGridLayoutSpanCount() {
        return 1;
    }


}
