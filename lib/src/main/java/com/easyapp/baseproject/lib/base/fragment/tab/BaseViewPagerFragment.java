package com.easyapp.baseproject.lib.base.fragment.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.base.fragment.BaseToolbarFragment;
import com.easyapp.baseproject.lib.widget.viewPager.SlidingTabLayout;
import com.easyapp.baseproject.lib.widget.viewPager.adapter.v4.FragmentPagerItem;
import com.easyapp.baseproject.lib.widget.viewPager.adapter.v4.FragmentPagerItemAdapter;
import com.easyapp.baseproject.lib.widget.viewPager.adapter.v4.FragmentPagerItems;

/**
 * 提供tab 的基本畫面
 */
public abstract class BaseViewPagerFragment extends BaseToolbarFragment {
    protected View view;
    protected ViewPager viewPager;

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

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerItemAdapter);
        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setFillViewport(false);
        slidingTabLayout.setDistributeEvenly(true);


        init();
        slidingTabLayout.setViewPager(viewPager);

    }

    final protected void setFillViewport(boolean fillViewport) {
        slidingTabLayout.setFillViewport(fillViewport);
    }

    protected void setCustomTab(int layoutId, int textLayoutId) {
        slidingTabLayout.setCustomTabView(layoutId, textLayoutId);
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
