package com.easyapp.baseproject.lib.baseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.widgets.SlidingTabLayout;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItem;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItemAdapter;
import com.easyapp.baseproject.lib.widgets.adapter.v4.FragmentPagerItems;

/**
 * 提供tab 的基本畫面
 */
public abstract class BaseViewPagerFragment extends BaseToolbarFragment {
    protected View view;
    protected ViewPager viewPager;
    protected FrameLayout fl_sliding_bg;
    protected View iv_right;
    protected SlidingTabLayout slidingTabLayout;
    protected FragmentPagerItemAdapter pagerItemAdapter;
    protected FragmentPagerItems pagerItems;

    private int TabBackgrounds = 0;
    private int TabTextColor = 0;
    private int[] TabIcons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easyapp_base_fragment_view_pager, container, false);
        initVar();
        initPages();
        initView();
        return view;
    }

    private void initVar() {
        pagerItems = new FragmentPagerItems(getContext());
        pagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), pagerItems);
    }

    protected void initView() {
        iv_right = view.findViewById(R.id.iv_right);
        fl_sliding_bg = (FrameLayout) view.findViewById(R.id.fl_sliding_bg);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerItemAdapter);
        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab_icon_and_text, R.id.tv_tab);
        setCustomViewTab();
        init();
        slidingTabLayout.setViewPager(viewPager);
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() + 1 < pagerItemAdapter.getCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
    }

    protected void setFlSlidingBg(int color) {
        fl_sliding_bg.setBackgroundColor(color);
    }

    protected void hideSlidingTab() {
        fl_sliding_bg.setVisibility(View.GONE);
    }

    protected void showSlidingTab() {
        fl_sliding_bg.setVisibility(View.VISIBLE);
    }

    private void setCustomViewTab() {
        slidingTabLayout.setCustomViewTab(new SlidingTabLayout.TabProvider() {
            @Override
            public View createTabView(View container, int position) {
                customViewTab(container, position);
                return container;
            }
        });
    }

    protected void customViewTab(View container, int position) {
        if (TabBackgrounds != 0) {
            View ll_content = container.findViewById(R.id.ll_content);
            ll_content.setBackgroundResource(TabBackgrounds);
        }

        if (TabIcons != null) {
            ImageView iv = (ImageView) container.findViewById(R.id.iv_tab_icon);
            if (position < TabIcons.length) {
                iv.setImageResource(TabIcons[position]);
            } else {
                iv.setVisibility(View.GONE);
            }
        } else {
            ImageView iv = (ImageView) container.findViewById(R.id.iv_tab_icon);
            iv.setVisibility(View.GONE);
        }

        if (TabTextColor != 0) {
            TextView tv = (TextView) container.findViewById(R.id.tv_tab);
            tv.setTextColor(getResources().getColor(TabTextColor));
        }
    }

    protected abstract void init();

    protected abstract void initPages();

    /**
     * 設定tab的背景顏色
     *
     * @param color
     * @return
     */
    protected void setTabBackgrounds(int color) {
        this.TabBackgrounds = color;
    }

    /**
     * 設定tab icon
     *
     * @param icons
     */
    protected void setTabImageViewIcon(int[] icons) {
        this.TabIcons = icons;
    }

    /**
     * 設定tab文字顏色
     *
     * @param color
     */
    protected void setTabTextColor(int color) {
        this.TabTextColor = color;
    }

    protected void addPage(CharSequence title, Class<? extends Fragment> clazz) {
        pagerItems.add(FragmentPagerItem.of(title, clazz));
    }

    protected void addPage(CharSequence title, Class<? extends Fragment> clazz, Bundle bundle) {
        pagerItems.add(FragmentPagerItem.of(title, clazz, bundle));
    }


}
