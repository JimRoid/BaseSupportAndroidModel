package com.easyapp.baseproject_sample.tabactivity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyapp.apitest.entity.Photo;
import com.easyapp.baseproject.lib.callback.Callback;
import com.easyapp.baseproject.lib.recycleView.BaseRecyclerViewAdapter;
import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.SampleFragment;
import com.easyapp.apitest.api.ApiTool;


import java.util.List;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class FragmentHospitals extends BaseTabRecyclerFragment {

    private ApiTool apiTool;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }


    @Override
    protected void init() {
        apiTool = new ApiTool();
        setRecyclerViewAnimDisable();
        setTitleImageResource(R.drawable.logo);
//        setAutoHideToolBar(false);
//        setFabSrc(1);
        setFabVisible(false);
//        setFabBackground();
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        apiTool.getPhotos(new Callback() {
            @Override
            public void initCallback() {
                showLoading();
            }

            @Override
            public void onComplete() {
                cancelLoading();
            }

            @Override
            public void callback(Object object) {
                List<Photo> photos = (List<Photo>) object;
                if (getSize() > 0) {
                    addData(photos);
                } else {
                    setData(photos);
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
        Photo photo = (Photo) obj;
        Glide.with(getContext()).load(photo.getUrl()).placeholder(R.drawable.icon_empty).into(adapterItemHolder.iv_picture);
        adapterItemHolder.textView.setText(photo.getTitle());
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
