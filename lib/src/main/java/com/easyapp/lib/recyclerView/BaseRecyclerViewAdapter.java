package com.easyapp.lib.recyclerView;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Recycler View Adapter
 */
public class BaseRecyclerViewAdapter<VHead extends BaseRecyclerViewAdapter.ViewHolder, VH extends BaseRecyclerViewAdapter.ViewHolder, T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private final static int TYPE_HEADER = 0;
    private final static int TYPE_CONTENT = 1;
    private List<T> data;
    private Context context;
    private RecyclerOnScrollListener recyclerOnScrollListener;
    private OnBindViewHolder onBindViewHolder;
    private OnCreateViewHolder onCreateViewHolder;
    private OnViewHolderLayout onViewHolderLayout;


    public BaseRecyclerViewAdapter() {
        super();
        data = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public OnBindViewHolder getOnBindViewHolder() {
        return onBindViewHolder;
    }

    public void setOnBindViewHolder(OnBindViewHolder onBindViewHolder) {
        this.onBindViewHolder = onBindViewHolder;
    }

    public OnCreateViewHolder getOnCreateViewHolder() {
        return onCreateViewHolder;
    }

    public void setOnCreateViewHolder(OnCreateViewHolder onCreateViewHolder) {
        this.onCreateViewHolder = onCreateViewHolder;
    }

    public OnViewHolderLayout getOnViewHolderLayout() {
        return onViewHolderLayout;
    }

    public void setOnViewHolderLayout(OnViewHolderLayout onViewHolderLayout) {
        this.onViewHolderLayout = onViewHolderLayout;
    }

    public void setRecyclerOnScrollListener(RecyclerOnScrollListener recyclerOnScrollListener) {
        this.recyclerOnScrollListener = recyclerOnScrollListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(recyclerOnScrollListener);
    }


    public void set(int position, T obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public void addAll(Collection<? extends T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(T data) {
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

    public List<? extends T> getData() {
        return data;
    }

    public T getItem(int position) {
        return getData().get(position);
    }

    public int getDataSize() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView;
        if (viewType == BaseRecyclerViewAdapter.TYPE_HEADER) {
            contactView = LayoutInflater.from(context).inflate(onViewHolderLayout.onViewHolderLayoutHead(), parent, false);
            return onCreateViewHolder.onCreateViewHolderHead(contactView);
        } else {
            contactView = LayoutInflater.from(context).inflate(onViewHolderLayout.onViewHolderLayoutContent(), parent, false);
            return onCreateViewHolder.onCreateViewHolderContent(contactView);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (onBindViewHolder != null) {
            if (holder.getItemViewType() == TYPE_HEADER) {
                onBindViewHolder.onBindViewHolderHead(holder, getItem(position));
            } else {
                onBindViewHolder.onBindViewHolderContent(holder, getItem(position));
            }
        }
    }



    public interface OnViewHolderLayout {
        int onViewHolderLayoutHead();

        int onViewHolderLayoutContent();
    }

    public interface OnCreateViewHolder<VHead extends BaseRecyclerViewAdapter.ViewHolder, VH extends BaseRecyclerViewAdapter.ViewHolder> {
        VHead onCreateViewHolderHead(View view);

        VH onCreateViewHolderContent(View view);
    }

    public interface OnBindViewHolder<VHead extends BaseRecyclerViewAdapter.ViewHolder, VH extends BaseRecyclerViewAdapter.ViewHolder, T> {
        void onBindViewHolderHead(VHead vHead, T t);

        void onBindViewHolderContent(VH vh, T t);
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
