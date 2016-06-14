package com.easyapp.baseproject.lib.baseFragment.list;

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

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.baseFragment.BaseDrawerFragment;
import com.easyapp.baseproject.lib.recycleView.BaseRecyclerViewAdapter;
import com.easyapp.baseproject.lib.recycleView.EndlessRecyclerOnScrollListener;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 基本的列表fragment
 */
public abstract class BaseRecyclerViewFragment extends BaseDrawerFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected View view;
    protected SwipeRefreshLayout easyapp_swiperefresh_layout;
    protected RecyclerView recyclerView;
    protected View emptyView;
    protected ProgressBar easyapp_pb;
    protected FloatingActionButton fab;
    protected boolean isNoMore = false;

    protected BaseRecyclerViewAdapter baseRecycleViewAdapter;

    protected boolean fabVisible = true;
    protected boolean isScrollTop = true;
    protected boolean isAutoHideToolBar = true;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easyapp_base_recycle_view, container, false);
        initView();
        initData();
        init();
        return view;
    }

    /**
     * 初始化view;
     */
    protected void initView() {
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        emptyView = view.findViewById(R.id.easyapp_empty_view);
        easyapp_pb = (ProgressBar) view.findViewById(R.id.easyapp_pb);
        easyapp_pb.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.light_blue),
                android.graphics.PorterDuff.Mode.SRC_IN);


        easyapp_swiperefresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.easyapp_swiperefresh_layout);
        easyapp_swiperefresh_layout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.easyapp_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), setGridLayoutSpanCount());
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    final protected void setRecyclerViewAnimDisable() {
        recyclerView.setLayoutAnimation(null);
    }

    protected void initData() {
        EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
            boolean hideToolBar = false;

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                isScrollTop = true;
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                showFab();
                hideToolBar = false;
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                hideFab();
                hideToolBar = true;
                isScrollTop = false;
            }

            @Override
            public void onScrolledToBottom() {
                if (isNoMore) {
                    return;
                }
                if (easyapp_pb.getVisibility() == View.GONE) {
                    showProgress();
                    onLoadMore();
                }
            }


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isAutoHideToolBar) {
                    if (hideToolBar) {
                        hideToolbar();
                    } else {
                        showToolbar();
                    }
                }
            }
        };
        baseRecycleViewAdapter = new BaseRecyclerViewAdapter(getActivity(), endlessRecyclerOnScrollListener) {
            @Override
            protected void setOnCreateAdapter() {
                setOnRecycleAdapter();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View contactView;
                RecyclerView.ViewHolder viewHolder;
                if (viewType == BaseRecyclerViewAdapter.VIEWTYPEHEADER && getHeaderView() == null) {
                    contactView = LayoutInflater.from(context).inflate(getRecycleViewHolderHeaderLayout(), parent, false);
                    viewHolder = getHeaderItemHolder(contactView);
                } else if (viewType == BaseRecyclerViewAdapter.VIEWTYPEHEADER && getHeaderView() != null) {
                    Logger.d("get header view != null");
                    viewHolder = getHeaderItemHolder(getHeaderView());
                } else {
                    contactView = LayoutInflater.from(context).inflate(getRecycleViewHolderLayout(), parent, false);
                    viewHolder = getItemHolder(contactView);
                }

                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder.getItemViewType() == VIEWTYPEHEADER) {
                    getBindHeaderViewHolder(holder, getItem(position));
                } else {
                    getBindViewHolder(holder, getItem(position));
                }
            }
        };
        recyclerView.setAdapter(baseRecycleViewAdapter);
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
     * 設定header view
     *
     * @param headerView
     */
    protected void setHeaderView(View headerView) {
        baseRecycleViewAdapter.setHeaderView(headerView);
    }

    /**
     * @return
     */
    @Nullable
    protected View getHeaderView() {
        return baseRecycleViewAdapter.getHeaderView();
    }

    /**
     * 設定float action button image resource
     *
     * @param res
     */
    protected void setFabSrc(int res) {
        fab.setImageResource(res);
    }

    /**
     * 設定float action button 背景顏色
     */
    protected void setFabBackground(int color) {
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
    }

    /**
     * 設定float action 點擊時的動作
     *
     * @param fabOnClickListener
     */
    protected void setFabOnClickListener(View.OnClickListener fabOnClickListener) {
        fab.setOnClickListener(fabOnClickListener);
    }

    /**
     * 設定頁面的初始化
     */
    protected abstract void init();

    /**
     * 設定float action 是否要顯示
     *
     * @param fabVisible
     */
    protected void setFabVisible(boolean fabVisible) {
        this.fabVisible = fabVisible;
        if (fabVisible) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }

    /**
     * 設定列表是否自動隱藏bar
     *
     * @param isAutoHideToolBar
     */
    protected void setAutoHideToolBar(boolean isAutoHideToolBar) {
        this.isAutoHideToolBar = isAutoHideToolBar;
        if (!isAutoHideToolBar) {
            showToolbar();
        }
    }

    protected void showFab() {
        if (fabVisible) {
            fab.show();
        }
    }

    protected void hideFab() {
        if (fabVisible) {
            fab.hide();
        }
    }

    protected void setIsNoMore(boolean isNoMore) {
        this.isNoMore = isNoMore;
    }

    /**
     * 設定data 並清除原本的檔案
     *
     * @param arrayList
     */
    protected void setData(List arrayList) {
        easyapp_swiperefresh_layout.setRefreshing(false);
        baseRecycleViewAdapter.clear();
        baseRecycleViewAdapter.addData(arrayList);
        setEmptyView();
        baseRecycleViewAdapter.notifyDataSetChanged();
    }

    /**
     * 設定單筆的訊息改變
     *
     * @param position
     * @param o
     */
    protected void setItem(int position, Object o) {
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
     * 增加data
     *
     * @param arrayList
     */
    protected void addData(List arrayList) {
        cancelProgress();
        easyapp_swiperefresh_layout.setRefreshing(false);
        baseRecycleViewAdapter.addData(arrayList);
        setEmptyView();
        baseRecycleViewAdapter.notifyDataSetChanged();
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

    protected abstract void setOnRecycleAdapter();

    protected abstract int setGridLayoutSpanCount();

    @Override
    public abstract void onRefresh();

    protected abstract void onLoadMore();

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

    protected abstract void getBindViewHolder(RecyclerView.ViewHolder holder, Object obj);

    protected abstract void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, Object obj);

    protected void setEmptyView() {
        if (baseRecycleViewAdapter.getData().size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 關閉讀取進度條
     */
    private void cancelProgress() {
        easyapp_pb.setVisibility(View.GONE);
    }


    /**
     * 顯示讀取進度條
     */
    private void showProgress() {
        easyapp_pb.setVisibility(View.VISIBLE);
    }

}
