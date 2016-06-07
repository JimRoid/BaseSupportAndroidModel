package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.baseFragment.BaseTabParentFragment;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;
import com.easyapp.baseproject.lib.widgets.tabhost.FragmentClickTabHost;

import java.util.ArrayList;

public abstract class BaseTabActivity extends BaseMainActivity {

    protected FragmentTabHost fragmentTabHost;
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
    protected int getLayoutId() {
        return R.layout.easyapp_activity_base_tab;
    }

    @Override
    protected void initView() {
        super.initView();
        fragmentTabHost = (FragmentClickTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        onSetupTab();
    }

    abstract protected void onSetupTab();

    protected void addTab(EasyTab easyTab) {
        easyTabs.add(easyTab);
        easyTab.getBundle().putString(easyTab.getTAB(), easyTab.getTAB() + easyTabs.size());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(easyTab.getTAB() + easyTabs.size()).setIndicator(getTabIndicator(easyTab)),
                BaseTabParentFragment.class, easyTab.getBundle());
    }

    private View getTabIndicator(EasyTab easyTab) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_icon_and_text, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab);
        tv.setText(easyTab.getTabTextResource());
        iv.setImageResource(easyTab.getTabImageResource());
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

