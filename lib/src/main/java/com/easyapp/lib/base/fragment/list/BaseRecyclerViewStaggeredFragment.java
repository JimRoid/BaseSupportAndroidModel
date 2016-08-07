package com.easyapp.lib.base.fragment.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.easyapp.lib.R;

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
        initFab();
        emptyView = view.findViewById(R.id.easyapp_empty_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.light_blue),
                android.graphics.PorterDuff.Mode.SRC_IN);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.easyapp_swiperefresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.easyapp_recycler_view);
        recyclerView.setLayoutManager(getLayoutManager());
    }


    protected abstract RecyclerView.LayoutManager getLayoutManager();

    @Override
    final protected int setGridLayoutSpanCount() {
        return 0;
    }


}
