package com.easyapp.lib.base.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
    protected ImageView iv_title;
    protected TextView tv_title;
    protected LinearLayout fl_right, fl_left;
    private View container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setContainer(R.id.container);
        initView();
        initial();
    }

    protected int getLayoutId() {
        return R.layout.easyapp_activity_base_toolbar;
    }

    protected abstract void initial();

    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        container = findViewById(container_id);

        iv_title = (ImageView) toolbar.findViewById(R.id.iv_title);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        fl_right = (LinearLayout) toolbar.findViewById(R.id.fl_right);
        fl_left = (LinearLayout) toolbar.findViewById(R.id.fl_left);

    }



    @Override
    public void hideToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    }

    @Override
    public void showToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);  // clear all scroll flags
    }

    @Override
    public void setTitleImageResource(int resId) {
        tv_title.setVisibility(View.GONE);
        iv_title.setVisibility(View.VISIBLE);
        iv_title.setImageResource(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        tv_title.setText(title);
        tv_title.setVisibility(View.VISIBLE);
        iv_title.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(int titleId) {
        tv_title.setText(titleId);
        tv_title.setVisibility(View.VISIBLE);
        iv_title.setVisibility(View.GONE);
    }

    @Override
    public void showBack(boolean value) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }

    @Override
    public void cancelRight() {
        fl_right.removeAllViews();
        fl_right.setVisibility(View.GONE);
    }

    @Override
    public void cancelLeft() {
        fl_left.removeAllViews();
        fl_left.setVisibility(View.GONE);
    }

    @Override
    public void setLeft(View view) {
        fl_left.removeAllViews();
        fl_left.setVisibility(View.VISIBLE);
        fl_left.addView(view);
    }

    @Override
    public void setRight(View view) {
        fl_right.removeAllViews();
        fl_right.setVisibility(View.VISIBLE);
        fl_right.addView(view);
    }

    @Override
    public void setLeft(View[] views) {
        fl_left.removeAllViews();
        fl_left.setVisibility(View.VISIBLE);
        for (int i = views.length - 1; i >= 0; i--) {
            fl_left.addView(views[i]);
        }
    }

    @Override
    public void setRight(View[] views) {
        fl_right.removeAllViews();
        fl_right.setVisibility(View.VISIBLE);
        for (int i = views.length - 1; i >= 0; i--) {
            fl_right.addView(views[i]);
        }
    }


}
