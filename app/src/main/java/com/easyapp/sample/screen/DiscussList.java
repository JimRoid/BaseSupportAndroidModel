package com.easyapp.sample.screen;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.easyapp.lib.recyclerView.BaseList;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.lib.widget.SquareFrameLayout;
import com.easyapp.lib.widget.viewPager.EasyImageViewPagerAdapter;
import com.easyapp.sample.R;
import com.easyapp.sample.http.api.ApiTool;
import com.easyapp.sample.http.entity.EntityDiscuss;
import com.orhanobut.logger.Logger;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscussList extends BaseList<DiscussList.ViewHolder, EntityDiscuss.DataBean> {

    private ApiTool apiTool;

    public static DiscussList instance() {
        return new DiscussList();
    }

    @Override
    protected void init() {
        setTitle("討論區");
        apiTool = new ApiTool(getContext());
        showFab();
        getFab().setImageResource(R.drawable.icon_add_white);
        getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("hello");
                AddFragment(new DiscussCreate());
            }
        });
    }


    @Override
    protected void onLoad() {
        apiTool.discussList(new EasyApiCallback<EntityDiscuss>() {
            @Override
            public void initial() {
                showLoading();
            }

            @Override
            public void onComplete() {
                cancelLoading();
                addAll(Collections.<EntityDiscuss.DataBean>emptyList());
            }

            @Override
            public void onCallback(EntityDiscuss entityDiscuss) {
                showToast(entityDiscuss.getMessage(), true);
                addAll(entityDiscuss.getData());
            }
        });
    }

    @Override
    protected int onGridLayoutSpanCount() {
        return 1;
    }

    @Override
    public int onViewHolderLayoutContent() {
        return R.layout.item_discuss;
    }

    @Override
    public ViewHolder onCreateViewHolderContent(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolderContent(ViewHolder holder, EntityDiscuss.DataBean dataBean) {
        holder.tvContent.setText(dataBean.getContent());
        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvCreateDate.setText(dataBean.getCreateDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("TEST CARD VIEW ONCLICK");
            }
        });

        if (dataBean.getPicture().size() > 0) {
            holder.squareFrameLayout.setVisibility(View.VISIBLE);
            EasyImageViewPagerAdapter.initial(getActivity(),
                    dataBean.getPicture(),
                    holder.viewPager,
                    holder.tabLayout);
        } else {
            holder.squareFrameLayout.setVisibility(View.GONE);
        }
    }


    static class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvCreateDate)
        TextView tvCreateDate;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.tabLayout)
        TabLayout tabLayout;
        @BindView(R.id.squareFrameLayout)
        SquareFrameLayout squareFrameLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
