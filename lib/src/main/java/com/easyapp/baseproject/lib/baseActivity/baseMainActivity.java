package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.callback.iToolbarCallback;

/**
 * 提供一般 基本 toolbar main activity layout
 */
public abstract class BaseMainActivity extends BaseSupportActivity implements iToolbarCallback {
    protected Toolbar toolbar;
    protected TextView tv_title;
    protected LinearLayout fl_right, fl_left;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setContainer(R.id.container);
        initView();
        initial();
    }

    protected int getLayoutId(){
        return R.layout.easyapp_base_main;
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

        container = (FrameLayout) findViewById(R.id.container);

        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        fl_right = (LinearLayout) toolbar.findViewById(R.id.fl_right);
        fl_left = (LinearLayout) toolbar.findViewById(R.id.fl_left);
    }

    @Override
    public void hideToolbar() {
        if(toolbar == null){
            return;
        }
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//        getSupportActionBar().hide();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) container.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        container.setLayoutParams(layoutParams);
    }

    @Override
    public void showToolbar() {
        if(toolbar == null){
            return;
        }
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        getSupportActionBar().show();
        TypedValue tv = new TypedValue();
        int actionBarHeight;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) container.getLayoutParams();
            layoutParams.setMargins(0, actionBarHeight, 0, 0);
            container.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        tv_title.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tv_title.setText(titleId);
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
