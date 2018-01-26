package com.easyapp.baseproject_sample.screen.list;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.http.api.ApiTool;
import com.easyapp.baseproject_sample.http.entity.ItemSchool;
import com.easyapp.baseproject_sample.screen.SampleFragment;
import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.easyapp.lib.base.fragment.list.BaseWaterFull;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;

/**
 * 測試用列表
 */
public class PostsStaggeredList extends BaseWaterFull<PostsStaggeredList.AdapterItemHolder, ItemSchool.DataBean> {

    private ApiTool apiTool;


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL);
    }


    @Override
    protected void init() {
        apiTool = new ApiTool(getContext());
//        setRecyclerViewAnimDisable();
        setTitleImageResource(R.drawable.logo);
//        setFabVisible(false);
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        apiTool.getSchool(new EasyApiCallback<ItemSchool>() {
            @Override
            public void onCallback(ItemSchool itemSchool) {
                super.onCallback(itemSchool);
                addAll(itemSchool.getData());
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
    protected AdapterItemHolder getItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected void getBindViewHolder(AdapterItemHolder holder, ItemSchool.DataBean bean) {
        Glide.with(getContext()).load(bean.getPicture()).into(holder.iv_picture);
        holder.textView.setText(bean.getName());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(new SampleFragment());
            }
        });
    }


    public class AdapterItemHolder extends BaseRecyclerViewAdapter.ItemHolder {
        public TextView textView;
        public ImageView iv_picture;
        public View content;

        public AdapterItemHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            textView = itemView.findViewById(R.id.tv_title);
            iv_picture = itemView.findViewById(R.id.iv_picture);
        }
    }
}
