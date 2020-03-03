package com.easyapp.lib.recyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.easyapp.lib.R;
import com.easyapp.lib.drawer.BaseDrawerFragment;

import java.util.List;

/**
 * 基本的列表fragment
 */
public abstract class BaseList<VH extends BaseRecyclerViewAdapter.ViewHolder, T> extends BaseDrawerFragment
        implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseRecyclerViewAdapter.OnBindViewHolder<VH, T>,
        BaseRecyclerViewAdapter.OnCreateViewHolder<VH>,
        BaseRecyclerViewAdapter.OnViewHolderLayout {

    protected View view;
    protected View emptyView;
    protected TextView tvEmpty;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected BaseRecyclerViewAdapter<VH, T> baseRecycleViewAdapter;
    protected GridLayoutManager gridLayoutManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initView();
        initEmptyView();
        initScrollListener();
        onRefresh();
        return view;
    }

    protected int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    protected void initView() {
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

    protected void initEmptyView() {
        emptyView = view.findViewById(R.id.flEmpty);
        tvEmpty = view.findViewById(R.id.tvEmpty);
    }

    //註冊滑動事件
    protected void initScrollListener() {
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
        baseRecycleViewAdapter = new BaseRecyclerViewAdapter<>();
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

    @Override
    public void onRefresh() {
        getAdapter().clear();
        hideEmpty();
        onLoad();
    }

    protected void showEmpty() {
        emptyView.setVisibility(View.VISIBLE);
    }

    protected void hideEmpty() {
        emptyView.setVisibility(View.GONE);
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

    protected BaseRecyclerViewAdapter<VH, T> getAdapter() {
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
