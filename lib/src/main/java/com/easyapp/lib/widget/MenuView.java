package com.easyapp.lib.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapp.lib.R;


/**
 * 客製化menu
 */
public class MenuView {

    private View menu;
    protected ImageView imageView;
    protected TextView textView;
    protected Context context;

    public MenuView() {
        super();
    }

    public MenuView Builder(Activity activity) {
        context = activity;
        menu = activity.getLayoutInflater().inflate(R.layout.menu_item, null, false);
        textView = (TextView) menu.findViewById(R.id.tv_title);
        imageView = (ImageView) menu.findViewById(R.id.iv_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return this;
    }

    public MenuView setMenuImage(int res) {
        imageView.setImageResource(res);
        imageView.setVisibility(View.VISIBLE);
        return this;
    }

    public MenuView setMenuText(String text) {
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        return this;
    }

    public MenuView setMenuTextColor(int color) {
        textView.setTextColor(context.getResources().getColor(color));
        return this;
    }

    public MenuView setOnClickListener(View.OnClickListener onClickListener) {
        menu.setOnClickListener(onClickListener);
        return this;
    }

    public View getMenu() {
        return menu;
    }

}
