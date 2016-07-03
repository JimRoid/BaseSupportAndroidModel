package com.easyapp.baseproject.lib.base.fragment.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.easyapp.baseproject.lib.R;

/**
 * 基本的列表fragment
 */
public abstract class BaseRecyclerViewStaggeredFragment extends BaseRecyclerViewFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected int getLayoutId() {
        return R.layout.easyapp_base_recycler_staggered_view;
    }

    /**
     * 初始化view;
     */
    @Override
    protected void initView() {
        emptyView = view.findViewById(R.id.easyapp_empty_view);
        easyapp_pb = (ProgressBar) view.findViewById(R.id.easyapp_pb);
        easyapp_pb.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.light_blue),
                android.graphics.PorterDuff.Mode.SRC_IN);


        easyapp_swiperefresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.easyapp_swiperefresh_layout);
        easyapp_swiperefresh_layout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.easyapp_recycler_view);
        recyclerView.setLayoutManager(getLayoutManager());
    }


    protected abstract RecyclerView.LayoutManager getLayoutManager();

    @Override
    final protected int setGridLayoutSpanCount() {
        return 0;
    }


}
