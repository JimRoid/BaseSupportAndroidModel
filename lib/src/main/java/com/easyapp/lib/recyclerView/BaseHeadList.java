package com.easyapp.lib.recyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.lib.R;
import com.easyapp.lib.drawer.BaseDrawerFragment;

import java.util.List;

/**
 * 基本的列表fragment
 */
public abstract class BaseHeadList<
        VHead extends BaseRecyclerViewAdapter.ViewHolder,
        VH extends BaseRecyclerViewAdapter.ViewHolder,
        T> extends BaseDrawerFragment
        implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseRecyclerViewAdapter.OnBindViewHolder<VHead, VH, T>,
        BaseRecyclerViewAdapter.OnCreateViewHolder<VHead, VH>,
        BaseRecyclerViewAdapter.OnViewHolderLayout {

    protected View view;
    protected View emptyView;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected BaseRecyclerViewAdapter<VHead, VH, T> baseRecycleViewAdapter;
    protected GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setToolbarCallback(activity);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initView();
        initData();
        init();
        onRefresh();
        return view;
    }


    protected int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    /**
     * 初始化view;
     */
    protected void initView() {
        initEmptyView();
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), onGridLayoutSpanCount());
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * 設定成橫向
     */
    protected void setHorizontal() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    /**
     * emptyView
     */
    protected void initEmptyView() {
        emptyView = view.findViewById(R.id.flEmpty);
    }

    protected void initData() {
        RecyclerOnScrollListener endlessRecyclerOnScrollListener = new RecyclerOnScrollListener() {

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                onScrollTop();
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                onScrollUp();
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                onScrollDown();
            }

            @Override
            public void onScrolledLoadMore() {
                onScrollLoadMore();
            }

            @Override
            public void onScrolledToBottom() {
                onScrollBottom();
            }

        };
        baseRecycleViewAdapter = new BaseRecyclerViewAdapter<VHead, VH, T>();
        baseRecycleViewAdapter.setContext(getContext());
        baseRecycleViewAdapter.setRecyclerOnScrollListener(endlessRecyclerOnScrollListener);
        baseRecycleViewAdapter.setOnViewHolderLayout(this);
        baseRecycleViewAdapter.setOnBindViewHolder(this);
        baseRecycleViewAdapter.setOnCreateViewHolder(this);
        recyclerView.setAdapter(baseRecycleViewAdapter);
    }

    public void onScrollBottom() {

    }

    public void onScrollTop() {

    }

    public void onScrollUp() {

    }

    public void onScrollDown() {

    }

    public void onScrollLoadMore() {
    }


    /**
     * 設定頁面的初始化
     */
    protected abstract void init();

    @Override
    public void onRefresh() {
        getAdapter().clear();
        onLoad();
    }

    protected void emptyViewShow() {
        if (getSize() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    protected abstract void onLoad();

    @Override
    protected void cancelLoading() {
        super.cancelLoading();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 設定data 並清除原本的檔案
     *
     * @param arrayList
     */
    protected void setData(List<T> arrayList) {
        baseRecycleViewAdapter.clear();
        addAll(arrayList);
    }

    /**
     * 增加data
     *
     * @param arrayList
     */
    protected void addAll(List<T> arrayList) {
        swipeRefreshLayout.setRefreshing(false);
        baseRecycleViewAdapter.addAll(arrayList);
        baseRecycleViewAdapter.notifyDataSetChanged();
        emptyViewShow();
    }

    protected void add(T o) {
        swipeRefreshLayout.setRefreshing(false);
        baseRecycleViewAdapter.add(o);
        baseRecycleViewAdapter.notifyDataSetChanged();
    }


    /**
     * 設定單筆的訊息改變
     *
     * @param position
     * @param o
     */
    protected void set(int position, T o) {
        baseRecycleViewAdapter.set(position, o);
    }

    /**
     * 移除單筆的資訊
     *
     * @param position
     */
    protected void remove(int position) {
        baseRecycleViewAdapter.remove(position);
    }

    protected BaseRecyclerViewAdapter<VHead, VH, T> getAdapter() {
        return baseRecycleViewAdapter;
    }

    protected T getItem(int position) {
        return baseRecycleViewAdapter.getItem(position);
    }

    protected int getSize() {
        return baseRecycleViewAdapter.getDataSize();
    }

    protected abstract int onGridLayoutSpanCount();

}
