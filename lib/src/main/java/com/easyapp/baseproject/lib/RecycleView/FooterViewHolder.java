package com.easyapp.baseproject.lib.RecycleView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;

/**
 * Created by easyapp_jim on 15/9/10.
 */
public class FooterViewHolder extends ViewHolder {
    public ProgressBar progressBar;
    public TextView textView;
    public View LoadMore;

    public FooterViewHolder(int viewType, View itemView) {
        super(viewType, itemView);
        LoadMore = itemView;
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        textView = (TextView) itemView.findViewById(R.id.hint);
    }
}
