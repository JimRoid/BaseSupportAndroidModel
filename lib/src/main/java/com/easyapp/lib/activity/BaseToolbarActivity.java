package com.easyapp.lib.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.lib.R;
import com.easyapp.lib.backpressHelper.BackHandlerHelper;
import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 提供一般 基本 toolbar main activity layout
 */
public abstract class BaseToolbarActivity extends BaseFragmentActivity implements iToolbarCallback {

    protected AppBarLayout appBar;
    protected Toolbar toolbar;
    protected ImageView ivTitle;
    protected TextView tvTitle;
    protected LinearLayout flRight, flLeft;
    protected View container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setContainer(R.id.container);
        initView();
        initial();
    }

    protected int getLayoutId() {
        return R.layout.layout_base_toolbar;
    }

    protected abstract void initial();

    protected void initView() {
        appBar = findViewById(R.id.appBar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        container = findViewById(containerId);
        ivTitle = toolbar.findViewById(R.id.ivTitle);
        tvTitle = toolbar.findViewById(R.id.tvTitle);
        flRight = toolbar.findViewById(R.id.flRight);
        flLeft = toolbar.findViewById(R.id.flLeft);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setTitleImageResource(int resId) {
        tvTitle.setVisibility(View.GONE);
        ivTitle.setVisibility(View.VISIBLE);
        ivTitle.setImageResource(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
        ivTitle.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(int titleId) {
        tvTitle.setText(titleId);
        tvTitle.setVisibility(View.VISIBLE);
        ivTitle.setVisibility(View.GONE);
    }

    @Override
    public void showBack(boolean value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(value);
        }
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return appBar;
    }

    @Override
    public void OnAddFragment() {
        showBack(true);
    }

    @Override
    public void OnReplaceFragment() {
        showBack(false);
    }

    @Override
    public void clearRightMenu() {
        flRight.removeAllViews();
    }

    @Override
    public void clearLeftMenu() {
        flLeft.removeAllViews();
    }

    @Override
    public void clearMenu() {
        clearLeftMenu();
        clearRightMenu();
    }

    @Override
    public ViewGroup getLeftMenu() {
        return flLeft;
    }

    @Override
    public ViewGroup getRightMenu() {
        return flRight;
    }

    @Override
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            cancelLoading();
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    showBack(false);
                }
                super.onBackPressed();
            } else {
                super.onBackPressed();
            }
        }
    }
}
