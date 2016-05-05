package com.easyapp.baseproject.lib.recycleView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter
 */
public abstract class BaseRecyclerViewAdapter<VH extends BaseRecyclerViewAdapter.ItemHolder> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemHolder> {

    public final static int VIEWTYPEHEADER = 0;
    public final static int VIEWTYPECONTENT = 1;
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

    public <T> void setItem(int position, T obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public <T> void addData(List data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public <T> List getData() {
        if (data == null) data = new ArrayList();

        return data;
    }

    public <T> void addItem(int pos, T obj) {
        getData();
        data.add(pos, obj);
        notifyDataSetChanged();
    }

    public <T> void addItem(T obj) {
        getData();
        data.add(obj);
        notifyDataSetChanged();
    }

    public <T> void removeItem(T obj) {
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

    public <T> T getItem(int position) {
        return (T) getData().get(position);
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
