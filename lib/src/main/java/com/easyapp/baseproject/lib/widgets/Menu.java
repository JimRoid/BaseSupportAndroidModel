package com.easyapp.baseproject.lib.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;

/**
 * 客製化menu
 */
public class Menu {

    private View menu;
    private ImageView imageView;
    private TextView textView;

    public Menu() {
        super();
    }

    public Menu Builder(Activity activity) {
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

    public Menu setMenuImage(int res) {
        imageView.setImageResource(res);
        imageView.setVisibility(View.VISIBLE);
        return this;
    }

    public Menu setMenuText(String text) {
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        return this;
    }

    public Menu setOnClickListener(View.OnClickListener onClickListener) {
        menu.setOnClickListener(onClickListener);
        return this;
    }

    public View getMenu() {
        return menu;
    }

}
