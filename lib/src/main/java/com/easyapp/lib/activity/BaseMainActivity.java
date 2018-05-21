package com.easyapp.lib.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.lib.R;
import com.easyapp.lib.callback.iFloatingActionButton;
import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 提供一般 基本 toolbar main activity layout
 */
public abstract class BaseMainActivity extends BaseSupportActivity implements iToolbarCallback, iFloatingActionButton {
    protected Toolbar toolbar;
    protected ImageView ivTitle;
    protected TextView tvTitle;
    protected LinearLayout flRight, flLeft;
    protected View container;
    protected FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setContainer(R.id.container);
        initLoading();
        initView();
        initial();
    }

    protected void initLoading() {
        View flLoading = findViewById(R.id.flLoading);
        setProgressLoading(flLoading);
    }

    protected int getLayoutId() {
        return R.layout.layout_base_toolbar;
    }

    protected abstract void initial();

    protected void initView() {
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
        floatingActionButton = findViewById(R.id.floatingActionButton);
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
    public void cancelRight() {
        flRight.removeAllViews();
        flRight.setVisibility(View.GONE);
    }

    @Override
    public void cancelLeft() {
        flLeft.removeAllViews();
        flLeft.setVisibility(View.GONE);
    }

    @Override
    public void setLeft(View view) {
        flLeft.removeAllViews();
        flLeft.setVisibility(View.VISIBLE);
        flLeft.addView(view);
    }

    @Override
    public void setRight(View view) {
        flRight.removeAllViews();
        flRight.setVisibility(View.VISIBLE);
        flRight.addView(view);
    }

    @Override
    public void setLeft(View[] views) {
        flLeft.removeAllViews();
        flLeft.setVisibility(View.VISIBLE);
        for (int i = views.length - 1; i >= 0; i--) {
            flLeft.addView(views[i]);
        }
    }

    @Override
    public void setRight(View[] views) {
        flRight.removeAllViews();
        flRight.setVisibility(View.VISIBLE);
        for (int i = views.length - 1; i >= 0; i--) {
            flRight.addView(views[i]);
        }
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
    public FloatingActionButton getFab() {
        return floatingActionButton;
    }

    @Override
    public void showFab() {
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFab() {
        floatingActionButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        cancelLoading();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            if (getFragmentManager().getBackStackEntryCount() == 1) {
                showBack(false);
            }
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
