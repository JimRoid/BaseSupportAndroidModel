package com.easyapp.lib.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.lib.R;
import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 提供一般 基本 toolbar main activity layout
 */
public abstract class BaseMainActivity extends BaseSupportActivity implements iToolbarCallback {
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        ivTitle = (ImageView) toolbar.findViewById(R.id.iv_title);
        tvTitle = (TextView) toolbar.findViewById(R.id.tv_title);
        flRight = (LinearLayout) toolbar.findViewById(R.id.fl_right);
        flLeft = (LinearLayout) toolbar.findViewById(R.id.fl_left);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(value);
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
}
