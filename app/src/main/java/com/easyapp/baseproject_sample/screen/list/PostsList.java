package com.easyapp.baseproject_sample.screen.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.http.api.ApiTool;
import com.easyapp.baseproject_sample.http.entity.ItemSchool;
import com.easyapp.baseproject_sample.screen.BlankFragment;
import com.easyapp.lib.base.fragment.list.BaseHeadList;
import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.easyapp.lib.widget.recyclerView.BaseRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

/**
 * 測試用列表
 */
public class PostsList extends BaseHeadList<PostsList.AdapterItemHolder,
        PostsList.AdapterItemHolder,
        ItemSchool.DataBean,
        ItemSchool.DataBean> {

    private ApiTool apiTool;


    @Override
    protected int setGridLayoutSpanCount() {
        return 1;
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
        ItemSchool.DataBean contentBean = new ItemSchool.DataBean();
        addHead(contentBean);
        onRefresh();

    }

    @Override
    protected void onLoadMore() {
        apiTool.getSchool(new EasyApiCallback<ItemSchool>() {
            @Override
            public void initial() {
                showLoading();
            }

            @Override
            public void onComplete() {
                cancelLoading();
            }

            @Override
            public void onCallback(ItemSchool itemSchool) {
                addAll(itemSchool.getData());
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
    protected AdapterItemHolder getHeaderItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected void getBindViewHolder(AdapterItemHolder holder, ItemSchool.DataBean photo) {
        Glide.with(getContext()).load(photo.getPicture()).into(holder.iv_picture);
        holder.textView.setText(photo.getName());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(new BlankFragment());
            }
        });
    }

    @Override
    protected void getBindHeaderViewHolder(AdapterItemHolder holder, ItemSchool.DataBean dataBean) {
        holder.textView.setText(dataBean.getEmail());
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
