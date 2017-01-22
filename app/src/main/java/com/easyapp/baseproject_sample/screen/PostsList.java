package com.easyapp.baseproject_sample.screen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.http.api.ApiTool;
import com.easyapp.baseproject_sample.http.entity.ItemProduct;
import com.easyapp.lib.base.fragment.list.BaseList;
import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

/**
 * 測試用列表
 */
public class PostsList extends BaseList<PostsList.AdapterItemHolder, ItemProduct.DataBean.ContentBean> {

    private ApiTool apiTool;


    @Override
    protected int setGridLayoutSpanCount() {
        return 2;
    }

    @Override
    protected void init() {
        apiTool = new ApiTool(getContext());
        setTitleImageResource(R.drawable.logo);
        setFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("fab on click");
            }
        });
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        apiTool.getProductList("", "", "", new Callback<ItemProduct>() {
            @Override
            public void initCallback() {
                showLoading();
            }

            @Override
            public void callback(ItemProduct photos) {
                addAll(photos.getData().getContent());
            }

            @Override
            public void onComplete() {
                cancelLoading();
            }
        });
    }

    @Override
    protected int getRecycleViewHolderLayout() {
        return R.layout.item_news_layout;
    }

    @Override
    protected int getRecycleViewHolderHeaderLayout() {
        return R.layout.item_news_layout;
    }

    @Override
    protected AdapterItemHolder getItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }


    @Override
    protected void getBindViewHolder(AdapterItemHolder holder, ItemProduct.DataBean.ContentBean photo) {
        Glide.with(getContext()).load(photo.getS_pic()).placeholder(R.drawable.icon_empty).into(holder.iv_picture);
        holder.textView.setText(photo.getName());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(new SampleFragment());
            }
        });
    }

    @Override
    protected void getBindHeaderViewHolder(AdapterItemHolder holder, ItemProduct.DataBean.ContentBean obj) {
        getBindViewHolder(holder, obj);
    }

    public class AdapterItemHolder extends BaseRecyclerViewAdapter.ItemHolder {
        public TextView textView;
        public ImageView iv_picture;
        public View content;

        public AdapterItemHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
            iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
        }
    }
}
