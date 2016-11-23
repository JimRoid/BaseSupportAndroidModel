package com.easyapp.baseproject_sample.screen;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.http.api.ApiTool;
import com.easyapp.baseproject_sample.http.entity.ItemProduct;
import com.easyapp.lib.base.fragment.list.BaseRecyclerViewStaggeredFragment;
import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;

/**
 * 測試用列表
 */
public class PostsStaggeredList extends BaseRecyclerViewStaggeredFragment {

    private ApiTool apiTool;


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL);
    }


    @Override
    protected void init() {
        apiTool = new ApiTool();
//        setRecyclerViewAnimDisable();
        setTitleImageResource(R.drawable.logo);
        setAutoHideToolBar(true);
//        setFabVisible(false);
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        apiTool.getProductList("","","",new Callback() {
            @Override
            public void callback(Object object) {
                ItemProduct photos = (ItemProduct) object;
                if (getSize() > 0) {
                    addAll(photos.getData().getContent());
                } else {
                    addAll(photos.getData().getContent());
                }
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
    protected void getBindViewHolder(RecyclerView.ViewHolder holder, Object obj) {
        AdapterItemHolder adapterItemHolder = (AdapterItemHolder) holder;
        ItemProduct.DataBean.ContentBean photo = ( ItemProduct.DataBean.ContentBean) obj;
        Glide.with(getContext()).load(photo.getS_pic()).placeholder(R.drawable.icon_empty).into(adapterItemHolder.iv_picture);
        adapterItemHolder.textView.setText(photo.getName());
        adapterItemHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(new SampleFragment());
            }
        });
    }

    @Override
    protected void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, Object obj) {
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
