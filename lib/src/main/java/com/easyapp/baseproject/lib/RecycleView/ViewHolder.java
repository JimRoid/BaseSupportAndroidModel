package com.easyapp.baseproject.lib.RecycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by easyapp_jim on 15/9/10.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public int viewType;

    public ViewHolder(int viewType, View itemView) {
        super(itemView);
        this.viewType = viewType;
    }

}
