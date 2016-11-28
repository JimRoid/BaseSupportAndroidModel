package com.easyapp.baseproject_sample.toolbaractivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.http.api.ApiTool;
import com.easyapp.baseproject_sample.http.entity.ItemProduct;
import com.easyapp.baseproject_sample.screen.PostsList;
import com.easyapp.baseproject_sample.screen.SampleFragment;
import com.easyapp.lib.base.fragment.list.BaseRecyclerViewFragment;
import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class ListSample extends BaseRecyclerViewFragment<PostsList.AdapterItemHolder, ItemProduct.DataBean.ContentBean> {

    private ApiTool apiTool;


    @Override
    protected int setGridLayoutSpanCount() {
        return 2;
    }

    @Override
    protected void init() {
        apiTool = new ApiTool();
        setRecyclerViewAnimDisable();
        setTitleImageResource(R.drawable.logo);
        setAutoHideToolBar(false);
//        setFabVisible(false);
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        apiTool.getProductList("", "", "", new Callback() {
            @Override
            public void callback(Object object) {
                ItemProduct photos = (ItemProduct) object;
                addAll(photos.getData().getContent());
            }
        });
    }

    @Override
    public void onRefresh() {
        onLoadMore();
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
    protected BaseRecyclerViewAdapter.ItemHolder getItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected BaseRecyclerViewAdapter.ItemHolder getHeaderItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected void getBindViewHolder(PostsList.AdapterItemHolder holder, ItemProduct.DataBean.ContentBean photo) {
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
    protected void getBindHeaderViewHolder(PostsList.AdapterItemHolder holder, ItemProduct.DataBean.ContentBean obj) {
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
