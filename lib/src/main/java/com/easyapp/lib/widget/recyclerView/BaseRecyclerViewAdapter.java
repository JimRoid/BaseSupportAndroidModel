package com.easyapp.lib.widget.recyclerView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter
 */
public abstract class BaseRecyclerViewAdapter<VH extends BaseRecyclerViewAdapter.ItemHolder, T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ItemHolder> {

    public final static int VIEWTYPEHEADER = 0;
    public final static int VIEWTYPECONTENT = 1;
    protected ArrayList<T> data;
    protected Context context;
    private EndlessRecyclerOnScrollListener recyclerOnScrollListener;

    public BaseRecyclerViewAdapter(Context context, EndlessRecyclerOnScrollListener recyclerOnScrollListener) {
        data = new ArrayList<T>();
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


    public void setItem(int position, T obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public void addData(ArrayList<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public List getData() {
        if (data == null) data = new ArrayList();

        return data;
    }

    public void addItem(int pos, T obj) {
        getData();
        data.add(pos, obj);
        notifyDataSetChanged();
    }

    public void addItem(T obj) {
        getData();
        data.add(obj);
        notifyDataSetChanged();
    }

    public void removeItem(T obj) {
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

    public T getItem(int position) {
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
