package com.easyapp.lib.recyclerView;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter
 */
public class BaseRecyclerViewAdapter<VHead extends BaseRecyclerViewAdapter.ViewHolder, VH extends BaseRecyclerViewAdapter.ViewHolder, THead, T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private final static int TYPE_HEADER = 0;
    private final static int TYPE_CONTENT = 1;
    private List<ViewHolderData<THead, T>> viewHolderDataList;
    private Context context;
    private RecyclerOnScrollListener recyclerOnScrollListener;
    private RecyclerViewTypeListener recyclerViewTypeListener;
    private OnBindViewHolder<VHead, VH, THead, T> onBindViewHolder;
    private OnCreateViewHolder<VHead, VH> onCreateViewHolder;
    private OnViewHolderLayout onViewHolderLayout;


    BaseRecyclerViewAdapter() {
        super();
        viewHolderDataList = new ArrayList<>();
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

    public void setOnBindViewHolder(OnBindViewHolder<VHead, VH, THead, T> onBindViewHolder) {
        this.onBindViewHolder = onBindViewHolder;
    }

    public OnCreateViewHolder getOnCreateViewHolder() {
        return onCreateViewHolder;
    }

    public void setOnCreateViewHolder(OnCreateViewHolder<VHead, VH> onCreateViewHolder) {
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


    public void set(int position, T t) {
        ViewHolderData<THead, T> viewHolderData = new ViewHolderData<>();
        viewHolderData.setT(t);
        viewHolderDataList.set(position, viewHolderData);
        notifyItemChanged(position);
    }

    public void addAll(List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            ViewHolderData<THead, T> viewHolderData = new ViewHolderData<>();
            viewHolderData.setT(data.get(i));
            viewHolderDataList.add(viewHolderData);
        }
        notifyDataSetChanged();
    }

    public void addHead(THead head) {
        ViewHolderData<THead, T> viewHolderData = new ViewHolderData<>();
        viewHolderData.setHead(head);
        viewHolderDataList.add(viewHolderData);
        notifyDataSetChanged();
    }

    public void add(T t) {
        ViewHolderData<THead, T> viewHolderData = new ViewHolderData<>();
        viewHolderData.setT(t);
        viewHolderDataList.add(viewHolderData);
        notifyDataSetChanged();
    }

    public void add(int pos, T t) {
        ViewHolderData<THead, T> viewHolderData = new ViewHolderData<>();
        viewHolderData.setT(t);
        viewHolderDataList.add(pos, viewHolderData);
        notifyDataSetChanged();
    }

    public void remove(int pos) {
        viewHolderDataList.remove(pos);
        notifyDataSetChanged();
    }

    public void clear() {
        viewHolderDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getDataSize();
    }

    public List<? extends ViewHolderData<THead, T>> getData() {
        return viewHolderDataList;
    }

    public THead getHead(int position) {
        return getData().get(position).getHead();
    }

    public T getItem(int position) {
        return getData().get(position).getT();
    }


    public int getDataSize() {
        return viewHolderDataList.size();
    }

    public void setItemViewType(RecyclerViewTypeListener recyclerViewTypeListener) {
        this.recyclerViewTypeListener = recyclerViewTypeListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (recyclerViewTypeListener == null) {
            return 1;
        } else {
            return recyclerViewTypeListener.getItemViewType(position);
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
            if (getItemViewType(position) == TYPE_HEADER) {
                THead tHead = getHead(position);
                if (tHead == null) {
                    tHead = (THead) getItem(position);
                }
                onBindViewHolder.onBindViewHolderHead((VHead) holder, tHead);
            } else {
                onBindViewHolder.onBindViewHolderContent((VH) holder, getItem(position));
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

    public interface OnBindViewHolder<VHead extends BaseRecyclerViewAdapter.ViewHolder, VH extends BaseRecyclerViewAdapter.ViewHolder, THead, T> {
        void onBindViewHolderHead(VHead vHead, THead tHead);

        void onBindViewHolderContent(VH vh, T t);
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
