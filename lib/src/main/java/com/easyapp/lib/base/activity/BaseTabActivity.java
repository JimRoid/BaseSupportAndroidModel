package com.easyapp.lib.base.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.easyapp.lib.R;
import com.easyapp.lib.base.fragment.tab.BaseTabParentFragment;
import com.easyapp.lib.widget.EasyHorizontalScrollView;
import com.easyapp.lib.widget.tabhost.EasyTab;
import com.easyapp.lib.widget.tabhost.FragmentClickTabHost;

import java.util.ArrayList;

public abstract class BaseTabActivity extends BaseMainActivity {

    protected FragmentClickTabHost fragmentTabHost;
    private ArrayList<EasyTab> easyTabs = new ArrayList<>();

    protected ImageView iv_right;


    @Override
    protected void initial() {
        easyTabs.clear();
        setContainer(android.R.id.tabcontent);
        initialOnCreate();
    }

    protected abstract void initialOnCreate();

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_tab;
    }

    @Override
    protected void initView() {
        super.initView();
        initLoading();
        fragmentTabHost = (FragmentClickTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.setOnClickListener(getTabOnClickListener());
        fragmentTabHost.setOnTabChangedListener(getOnTabChangeListener());
        fragmentTabHost.setisCanSetCurrentTab(getIsCanSetCurrentTab());
        onSetupTab();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentTabHost = null;
    }


    protected FragmentClickTabHost.IsCanSetCurrentTab getIsCanSetCurrentTab() {
        return new FragmentClickTabHost.IsCanSetCurrentTab() {
            @Override
            public boolean isCanSetCurrentTab(int index) {
                return true;
            }
        };
    }

    protected View.OnClickListener getTabOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    protected TabHost.OnTabChangeListener getOnTabChangeListener() {
        return new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                cancelLeft();
                cancelRight();
                if (getSupportFragmentManager().findFragmentByTag(tabId) != null) {
                    if (getSupportFragmentManager().findFragmentByTag(tabId).getChildFragmentManager().getBackStackEntryCount() > 0) {
                        showBack(true);
                        return;
                    }
                }
                showBack(false);
            }
        };
    }

    abstract protected void onSetupTab();

    protected void addTab(EasyTab easyTab) {
        easyTabs.add(easyTab);
        easyTab.getBundle().putString(easyTab.getTAB(), easyTab.getTAB() + easyTabs.size());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(easyTab.getTAB() + easyTabs.size()).setIndicator(getTabIndicator(easyTab)),
                BaseTabParentFragment.class, easyTab.getBundle());


        if (easyTabs.size() > 5) {
            final EasyHorizontalScrollView horizontalScrollView = (EasyHorizontalScrollView) findViewById(R.id.horiziontal_scrollview);
            iv_right = (ImageView) findViewById(R.id.iv_right);

            if (iv_right != null && horizontalScrollView != null) {
                iv_right.setVisibility(View.VISIBLE);
                iv_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }
                });

                horizontalScrollView.setOnBottomReachedListener(new EasyHorizontalScrollView.OnBottomReachedListener() {
                    @Override
                    public void onBottomReached() {
                        iv_right.setVisibility(View.GONE);
                    }

                    @Override
                    public void onStartReached() {
                        iv_right.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }

    /**
     * 設定向右icon的image resource
     *
     * @param imageRes
     */
    protected void setImageRight(int imageRes) {
        if (iv_right != null) {
            iv_right.setImageResource(imageRes);
        }
    }

    private View getTabIndicator(EasyTab easyTab) {
        View view = LayoutInflater.from(this).inflate(easyTab.getTabLayout(), null);
        ImageView iv = (ImageView) view.findViewById(easyTab.getTabImageId());
        TextView tv = (TextView) view.findViewById(easyTab.getTabTextId());
        tv.setText(easyTab.getTabTextResource());
        tv.setTextColor(this.getResources().getColorStateList(easyTab.getTabTextColorResource()));
        iv.setImageResource(easyTab.getTabImageResource());
        view.setBackgroundResource(easyTab.getTabPressStateDrawable());
        return view;
    }

    @Override
    public void onBackPressed() {
        BaseTabParentFragment f = (BaseTabParentFragment) getSupportFragmentManager().findFragmentByTag(fragmentTabHost.getCurrentTabTag());
        if (!f.onBackPressed()) {
            super.onBackPressed();
        }
    }


}

