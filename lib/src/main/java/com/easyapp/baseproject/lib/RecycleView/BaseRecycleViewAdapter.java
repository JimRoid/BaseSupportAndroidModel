package com.easyapp.baseproject.lib.RecycleView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.util.JDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by easyapp_jim on 15/9/9.
 */
public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    public static final int STATE_NETWORK_ERROR = 5;

    public static final int TYPE_FOOTER = 0x101;
    public static final int TYPE_HEADER = 0x102;

    protected int state = STATE_LESS_ONE_PAGE;
    protected int _loadmoreText;
    protected int _loadFinishText;

    private LayoutInflater mInflater;
    private View mHeaderView;

    protected List data;
    protected JDevice jDevice;

    public BaseRecycleViewAdapter(Context context) {
        _loadmoreText = R.string.loading;
        _loadFinishText = R.string.loading_no_more;
        data = new ArrayList();
        jDevice = new JDevice(context);
    }

    @Override
    public int getItemCount() {
        int size = getDataSize();
        if (hasFooter()) size += 1;

        if (hasHeader()) size += 1;

        return size;
    }

    public int getDataSize() {
        return data.size();
    }

    private boolean hasHeader() {
        return mHeaderView != null;
    }

    protected boolean loadMoreHasBg() {
        return true;
    }

    private boolean hasFooter() {
        switch (getState()) {
            case STATE_EMPTY_ITEM:
            case STATE_LOAD_MORE:
            case STATE_NO_MORE:
                return true;
            default:
                break;
        }
        return false;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeader())
            return TYPE_HEADER;
        if (position == getItemCount() - 1 && hasFooter())
            return TYPE_FOOTER;

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        if (viewType == TYPE_FOOTER) {
            View v = getLayoutInflater(parent.getContext())
                    .inflate(R.layout.recycle_foot, null);
            vh = new FooterViewHolder(viewType, v);
        } else if (viewType == TYPE_HEADER) {
            if (mHeaderView == null)
                throw new RuntimeException("Header view is null");
            vh = new HeaderViewHolder(viewType, mHeaderView);
        } else {
            final View itemView = onCreateItemView(parent, viewType);
            vh = onCreateItemViewHolder(itemView, viewType);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if ((getItemViewType(position) == TYPE_HEADER && position == 0)
                || holder instanceof HeaderViewHolder) {
            onBindHeadViewHolder(holder, position);
        } else if ((getItemViewType(position) == TYPE_FOOTER && position == getItemCount() - 1)
                || holder instanceof FooterViewHolder) {
            onBindFootViewHolder(holder, position);
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    protected void onBindHeadViewHolder(ViewHolder holder, int position) {
        //需要被繼承重構
    }

    protected void onBindItemViewHolder(ViewHolder holder, int position) {
        //需要被繼承重構
    }

    protected void onBindFootViewHolder(ViewHolder holder, int position) {
        FooterViewHolder vh = (FooterViewHolder) holder;
        if (!loadMoreHasBg()) vh.LoadMore.setBackground(null);

        switch (getState()) {
            case STATE_LOAD_MORE:
                vh.LoadMore.setVisibility(View.VISIBLE);
                vh.progressBar.setVisibility(View.VISIBLE);
                vh.textView.setVisibility(View.VISIBLE);
                vh.textView.setText(_loadmoreText);
                break;
            case STATE_NO_MORE:
                vh.LoadMore.setVisibility(View.VISIBLE);
                vh.progressBar.setVisibility(View.GONE);
                vh.textView.setVisibility(View.VISIBLE);
                vh.textView.setText(_loadFinishText);
                break;
            case STATE_EMPTY_ITEM:
                vh.LoadMore.setVisibility(View.GONE);
                vh.progressBar.setVisibility(View.GONE);
                vh.textView.setVisibility(View.GONE);
                break;
            case STATE_NETWORK_ERROR:
                vh.LoadMore.setVisibility(View.VISIBLE);
                vh.progressBar.setVisibility(View.GONE);
                vh.textView.setVisibility(View.VISIBLE);
                if (jDevice.hasInternet()) {
                    vh.textView.setText(R.string.loading_error);
                } else {
                    vh.textView.setText(R.string.loading_no_network);
                }
                break;
            default:
                vh.LoadMore.setVisibility(View.GONE);
                vh.progressBar.setVisibility(View.GONE);
                vh.textView.setVisibility(View.GONE);
                break;
        }
    }

    protected abstract View onCreateItemView(ViewGroup parent, int viewType);

    protected abstract ViewHolder onCreateItemViewHolder(View view, int viewType);

    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null)
            mInflater = (LayoutInflater.from(context));

        return mInflater;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
