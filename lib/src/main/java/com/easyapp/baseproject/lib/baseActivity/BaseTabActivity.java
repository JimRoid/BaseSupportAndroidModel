package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.baseFragment.BaseTabParentFragment;
import com.easyapp.baseproject.lib.widgets.EasyHorizontalScrollView;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;
import com.easyapp.baseproject.lib.widgets.tabhost.FragmentClickTabHost;

import java.util.ArrayList;

public abstract class BaseTabActivity extends BaseMainActivity {

    protected FragmentClickTabHost fragmentTabHost;
    private ArrayList<EasyTab> easyTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setContainer(android.R.id.tabcontent);
        easyTabs.clear();
        initView();
        initial();
    }

    @Override
    protected void initial() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.easyapp_activity_base_tab;
    }

    @Override
    protected void initView() {
        super.initView();
        fragmentTabHost = (FragmentClickTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.setOnClickListener(getTabOnClickListener());
        fragmentTabHost.setOnTabChangedListener(getOnTabChangeListener());
        fragmentTabHost.setisCanSetCurrentTab(getIsCanSetCurrentTab());
        onSetupTab();
    }

    protected FragmentClickTabHost.IsCanSetCurrentTab getIsCanSetCurrentTab(){
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
            final ImageView iv_right = (ImageView) findViewById(R.id.iv_right);

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

    private View getTabIndicator(EasyTab easyTab) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_icon_and_text, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab);
        tv.setText(easyTab.getTabTextResource());
        tv.setTextColor(getResources().getColor(easyTab.getTabTextColorResource()));
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

