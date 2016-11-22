package com.easyapp.lib.base.fragment.list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.easyapp.lib.R;
import com.easyapp.lib.base.fragment.BaseDrawerFragment;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.lib.widget.recyclerView.EndlessRecyclerOnScrollListener;

import java.util.List;

/**
 * 基本的列表fragment
 */
public abstract class BaseRecyclerViewFragment<VH extends BaseRecyclerViewAdapter.ItemHolder, T> extends BaseDrawerFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected View view;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected BaseRecyclerViewAdapter baseRecycleViewAdapter;
    protected View emptyView;
    protected View error_network_state_view;
    protected View empty_state_view;
    protected ProgressBar progressBar;
    protected FloatingActionButton floatingActionButton;

    protected boolean isNoMore = false;

    protected boolean fabVisible = true;
    protected boolean isScrollTop = true;

    protected int limit = 40;
    protected int page = 0;
    protected int oldPage = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        hideToolbar();
        initView();
        initData();
        init();
        return view;
    }


    protected int getLayoutId() {
        return R.layout.easyapp_base_recycle_view;
    }

    /**
     * 初始化view;
     */
    protected void initView() {
        initEmptyView();
        initFab();
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.light_blue),
                android.graphics.PorterDuff.Mode.SRC_IN);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.easyapp_swiperefresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.easyapp_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), setGridLayoutSpanCount());
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    protected void initFab() {
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    /**
     * 初始化空值或是沒有網路的view
     */
    protected void initEmptyView() {
        emptyView = view.findViewById(R.id.easyapp_empty_view);
        error_network_state_view = emptyView.findViewById(R.id.error_state_view);
        empty_state_view = emptyView.findViewById(R.id.empty_state_view);
    }

    final protected void setRecyclerViewAnimDisable() {
        recyclerView.setLayoutAnimation(null);
    }

    protected void initData() {
        EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                isScrollTop = true;
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                if (!fabVisible) {
                    showFab();
                }
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                if (!fabVisible) {
                    hideFab();
                }
                isScrollTop = false;
            }

            @Override
            public void onScrolledLoadMore() {
                if (isNoMore) {
                    return;
                }
                if (progressBar.getVisibility() == View.GONE) {
                    showProgress();
                    onLoadMore();
                }
            }

            @Override
            public void onScrolledToBottom() {

            }

        };
        baseRecycleViewAdapter = new BaseRecyclerViewAdapter(getActivity(), endlessRecyclerOnScrollListener) {


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View contactView;
                RecyclerView.ViewHolder viewHolder;
                if (viewType == BaseRecyclerViewAdapter.VIEWTYPEHEADER) {
                    contactView = LayoutInflater.from(context).inflate(getRecycleViewHolderHeaderLayout(), parent, false);
                    viewHolder = getHeaderItemHolder(contactView);
                } else {
                    contactView = LayoutInflater.from(context).inflate(getRecycleViewHolderLayout(), parent, false);
                    viewHolder = getItemHolder(contactView);
                }

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder.getItemViewType() == VIEWTYPEHEADER) {
                    getBindHeaderViewHolder(holder, (T) getItem(position));
                } else {
                    getBindViewHolder(holder, (T) getItem(position));
                }
            }
        };
        recyclerView.setAdapter(baseRecycleViewAdapter);
    }

    /**
     * 設定頁面的初始化
     */
    protected abstract void init();

    @Override
    public void onRefresh() {
//        cancelProgress();
        page = 0;
        setIsNoMore(false);
        getAdapter().clear();
        onLoad();
    }

    final protected void onLoad() {
//        showProgress();
        onLoadMore();
    }

    protected abstract void onLoadMore();

    protected void setIsNoMore(boolean isNoMore) {
        this.isNoMore = isNoMore;
    }

    /**
     * 設定data 並清除原本的檔案
     *
     * @param arrayList
     */
    protected void setData(List arrayList) {
        baseRecycleViewAdapter.clear();
        addData(arrayList);
    }

    /**
     * 增加data
     *
     * @param arrayList
     */
    protected void addData(List arrayList) {
        cancelProgress();
        if (arrayList.size() < limit) {
            setIsNoMore(true);
        }
        page++;
        swipeRefreshLayout.setRefreshing(false);
        baseRecycleViewAdapter.addData(arrayList);
        setEmptyView();
        baseRecycleViewAdapter.notifyDataSetChanged();
    }

    protected void addData(Object o) {
        cancelProgress();
        swipeRefreshLayout.setRefreshing(false);
        baseRecycleViewAdapter.addData(o);
        setEmptyView();
        baseRecycleViewAdapter.notifyDataSetChanged();
    }

    protected void setEmptyView() {
        setEmptyView(false);
    }

    /**
     * 設定無資料時的顯示狀態
     */
    protected void setEmptyView(boolean error_network) {
        if (error_network) {
            if (baseRecycleViewAdapter.getData().size() == 0) {
                emptyView.setVisibility(View.VISIBLE);
                empty_state_view.setVisibility(View.GONE);
                error_network_state_view.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        } else {
            if (baseRecycleViewAdapter.getData().size() == 0) {
                emptyView.setVisibility(View.VISIBLE);
                empty_state_view.setVisibility(View.VISIBLE);
                error_network_state_view.setVisibility(View.GONE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 設定單筆的訊息改變
     *
     * @param position
     * @param o
     */

    protected <T> void setItem(int position, T o) {
        baseRecycleViewAdapter.setItem(position, o);
    }

    /**
     * 移除單筆的資訊
     *
     * @param position
     */
    protected void removeItem(int position) {
        baseRecycleViewAdapter.removeItem(position);
    }

    /**
     * 設定float action 是否要顯示
     *
     * @param fabVisible
     */
    protected void setFabVisibleHide(boolean fabVisible) {
        this.fabVisible = fabVisible;
        if (fabVisible) {
            hideFab();
            floatingActionButton.setVisibility(View.GONE);
        } else {
            floatingActionButton.setVisibility(View.VISIBLE);
            showFab();
        }
    }

    protected void showFab() {
        floatingActionButton.show();
    }

    protected void hideFab() {
        floatingActionButton.hide();
    }

    /**
     * 設定列表是否自動隱藏bar
     *
     * @param autoHideToolBar
     */
    protected void setAutoHideToolBar(boolean autoHideToolBar) {
        if (autoHideToolBar) {
            hideToolbar();
        } else {
            showToolbar();
        }
    }

    /**
     * 是否拉到最頂端
     *
     * @return
     */
    protected boolean isScrollTop() {
        return isScrollTop;
    }

    /**
     * 設定float action button image resource
     *
     * @param res
     */
    protected void setFabSrc(int res) {
        floatingActionButton.setImageResource(res);
    }

    /**
     * 設定float action button 背景顏色
     */
    protected void setFabBackground(int color) {
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
    }

    /**
     * 設定float action 點擊時的動作
     *
     * @param fabOnClickListener
     */
    protected void setFabOnClickListener(View.OnClickListener fabOnClickListener) {
        floatingActionButton.setOnClickListener(fabOnClickListener);
    }

    protected BaseRecyclerViewAdapter getAdapter() {
        return baseRecycleViewAdapter;
    }

    protected <T> T getItem(int position) {
        return (T) getAdapter().getItem(position);
    }

    protected int getSize() {
        return getAdapter().getDataSize();
    }

    protected abstract int setGridLayoutSpanCount();

    /**
     * 取回其他的 view holder
     *
     * @return
     */
    protected abstract int getRecycleViewHolderLayout();

    /**
     * 取回第一個header view holder
     *
     * @return
     */
    protected abstract int getRecycleViewHolderHeaderLayout();


    /**
     * 設定單一格列表畫面
     *
     * @param contactView
     * @return
     */
    protected abstract BaseRecyclerViewAdapter.ItemHolder getItemHolder(View contactView);

    /**
     * 設定header 列表畫面
     *
     * @param contactView
     * @return
     */
    protected abstract BaseRecyclerViewAdapter.ItemHolder getHeaderItemHolder(View contactView);

    protected abstract void getBindViewHolder(RecyclerView.ViewHolder holder, T obj);

    protected abstract void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, T obj);


    /**
     * 關閉讀取進度條
     */
    private void cancelProgress() {
        progressBar.setVisibility(View.GONE);
    }


    /**
     * 顯示讀取進度條
     */
    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

}
