package com.easyapp.sample.screen;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.easyapp.http.listener.EasyApiCallback;
import com.easyapp.lib.recyclerView.BaseList;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.lib.tool.OpenData;
import com.easyapp.lib.widget.viewPager.EasyImageViewPagerAdapter;
import com.easyapp.sample.R;
import com.easyapp.sample.base.BaseAppList;
import com.easyapp.sample.http.api.ApiTool;
import com.easyapp.sample.http.entity.EntityDiscuss;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscussList extends BaseAppList<DiscussList.ViewHolder, EntityDiscuss.DataBean> {

    private ApiTool apiTool;

    public static DiscussList getInstance() {
        return new DiscussList();
    }

    @Override
    public void onViewCreated() {
        setTitle("討論區");
        apiTool = new ApiTool(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discuss_list;
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
        return R.layout.card_discuss;
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
                addFragment(DiscussList.getInstance());
//                addFragment(BlankFragmentText.getInstance(holder.getAdapterPosition()));
            }
        });

        if (dataBean.getPicture().size() > 0) {
            holder.frameLayout.setVisibility(View.VISIBLE);
            EasyImageViewPagerAdapter easyImageViewPagerAdapter = new EasyImageViewPagerAdapter(getActivity());
            ArrayList<String> pic = new ArrayList<>();
            pic.add("https://www.northguan-nsa.gov.tw/att/pic/11003190.JPG");
            pic.add("https://www.northguan-nsa.gov.tw/att/pic/11003190.JPG");
            pic.add("https://cdn.clm02.com/ezvivi.com/266528/1499736713_90.jpg");
            pic.add("https://i.imgur.com/6D4iRNd.jpg");
//            easyImageViewPagerAdapter.setData(dataBean.getPicture());
            easyImageViewPagerAdapter.setData(pic);
            easyImageViewPagerAdapter.initView(holder.viewPager, holder.tabLayout);
            easyImageViewPagerAdapter.setItemClickListener(new EasyImageViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    OpenData.OpenTouchImage(getContext(), pic, 0, true);
                }
            });

        } else {
            holder.frameLayout.setVisibility(View.GONE);
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
        @BindView(R.id.frameLayout)
        FrameLayout frameLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
