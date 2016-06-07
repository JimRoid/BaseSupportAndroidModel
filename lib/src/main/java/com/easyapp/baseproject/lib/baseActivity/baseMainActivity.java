package com.easyapp.baseproject.lib.baseActivity;

import android.animation.Animator;
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

        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        fl_right = (LinearLayout) toolbar.findViewById(R.id.fl_right);
        fl_left = (LinearLayout) toolbar.findViewById(R.id.fl_left);
    }

    @Override
    public void hideToolbar() {
        if (toolbar == null || container == null) {
            return;
        }
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2)).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (container.getLayoutParams().getClass().equals(LinearLayout.LayoutParams.class)) {

                } else if (container.getLayoutParams().getClass().equals(FrameLayout.LayoutParams.class)) {
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
                    params.setMargins(0, 0, 0, 0);
                    container.setLayoutParams(params);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (container.getLayoutParams().getClass().equals(LinearLayout.LayoutParams.class)) {
                    toolbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void showToolbar() {
        if (toolbar == null || container == null) {
            return;
        }
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).setListener(new Animator.AnimatorListener() {
            TypedValue tv = new TypedValue();
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());

            @Override
            public void onAnimationStart(Animator animation) {
                if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
                    if (container.getLayoutParams().getClass().equals(LinearLayout.LayoutParams.class)) {
                        toolbar.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    if (container.getLayoutParams().getClass().equals(LinearLayout.LayoutParams.class)) {

                    } else if (container.getLayoutParams().getClass().equals(FrameLayout.LayoutParams.class)) {
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
                        params.setMargins(0, actionBarHeight, 0, 0);
                        container.setLayoutParams(params);
                    }

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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
