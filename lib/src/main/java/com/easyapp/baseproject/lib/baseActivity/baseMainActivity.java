package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.callback.iToolbarCallback;

/**
 * 提供一般 基本 toolbar main activity layout
 */
public abstract class baseMainActivity extends baseSupportActivity implements iToolbarCallback {
    protected Toolbar toolbar;
    protected TextView tv_title;
    protected LinearLayout fl_right, fl_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easyapp_base_main);
        setContainer(R.id.container);
        initView();
        initial();
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

        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        fl_right = (LinearLayout) toolbar.findViewById(R.id.fl_right);
        fl_left = (LinearLayout) toolbar.findViewById(R.id.fl_left);
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
    public void setLeft(View[] view) {
        fl_left.removeAllViews();
        fl_left.setVisibility(View.VISIBLE);
        for (int i = view.length - 1; i >= 0; i--) {
            fl_left.addView(view[i]);
        }
    }

    @Override
    public void setRight(View[] view) {
        fl_right.removeAllViews();
        fl_right.setVisibility(View.VISIBLE);
        for (int i = view.length - 1; i >= 0; i--) {
            fl_right.addView(view[i]);
        }
    }




}
