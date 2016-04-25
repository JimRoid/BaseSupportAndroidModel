package com.easyapp.baseproject.lib.recycleView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter
 */
public abstract class BaseRecyclerViewAdapter<VH extends BaseRecyclerViewAdapter.ItemHolder> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemHolder> {

    private LayoutInflater mInflater;
    protected View mHeaderView;
    protected List data;
    protected Context context;
    protected EndlessRecyclerOnScrollListener recyclerOnScrollListener;

    public BaseRecyclerViewAdapter(Context context, EndlessRecyclerOnScrollListener recyclerOnScrollListener) {
        this.context = context;
        data = new ArrayList();
        this.recyclerOnScrollListener = recyclerOnScrollListener;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerOnScrollListener != null) {
            recyclerView.addOnScrollListener(recyclerOnScrollListener);
        }
    }

    public void setItem(int position, Object obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public void addData(List data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List getData() {
        if (data == null) data = new ArrayList();

        return data;
    }

    public void addItem(int pos, Object obj) {
        getData();
        data.add(pos, obj);
        notifyDataSetChanged();
    }

    public void addItem(Object obj) {
        getData();
        data.add(obj);
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        if (data != null) {
            data.remove(obj);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int pos) {
        if (data != null) {
            data.remove(pos);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        int size = getDataSize();
        return size;
    }

    public Object getItem(int position) {
        return getData().get(position);
    }

    public int getDataSize() {
        return data.size();
    }

    protected boolean loadMoreHasBg() {
        return true;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHolder vh;
        final View itemView = onCreateItemView(parent, viewType);
        vh = onCreateItemViewHolder(itemView, viewType);

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
//        onBaseBindViewHolder(holder, position);
    }

    protected abstract void onBindHeadViewHolder(ItemHolder holder, int position);

    protected abstract void onBindItemViewHolder(ItemHolder holder, int position);


    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return new View(parent.getContext());
    }


    protected ItemHolder onCreateItemViewHolder(View view, int viewType) {
        return new ItemHolder(viewType, view) {
        };
    }


    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null)
            mInflater = (LayoutInflater.from(context));

        return mInflater;
    }


    public static abstract class ItemHolder extends RecyclerView.ViewHolder {
        public int viewType;
        private int position;

        public ItemHolder(int viewType, View itemView) {
            super(itemView);
            this.viewType = viewType;
        }
    }

}
