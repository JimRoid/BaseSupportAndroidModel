package com.easyapp.lib.widget.recyclerView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter
 */
public abstract class BaseRecyclerViewAdapter<THead, T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemHolder> {

    public final static int VIEWTYPEHEADER = 0;
    public final static int VIEWTYPECONTENT = 1;
    protected List data;
    protected Context context;
    private EndlessRecyclerOnScrollListener recyclerOnScrollListener;

    public BaseRecyclerViewAdapter(Context context, EndlessRecyclerOnScrollListener recyclerOnScrollListener) {
        data = new ArrayList<>();
        this.context = context;
        this.recyclerOnScrollListener = recyclerOnScrollListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerOnScrollListener != null) {
            recyclerView.addOnScrollListener(recyclerOnScrollListener);
        }
    }


    public void set(int position, T obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public void addAll(List data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(T data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void addHead(THead data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void add(int pos, T obj) {
        data.add(pos, obj);
        notifyDataSetChanged();
    }

    public void remove(int pos) {
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
        return getDataSize();
    }

    public List getData() {
        return data;
    }

    public T getItem(int position) {
        return (T) getData().get(position);
    }

    public THead getHeadItem(int position) {
        return (THead) getData().get(position);
    }

    public int getDataSize() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEWTYPEHEADER;
        }
        return VIEWTYPECONTENT;
    }

    public static abstract class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }

}
