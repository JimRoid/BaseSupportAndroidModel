package com.easyapp.lib.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyapp.lib.R;


/**
 * 客製化menu
 */
public class MenuView {

    private View menu;
    private ImageView imageView;
    private TextView textView;
    protected Context context;

    public MenuView(Context context) {
        super();
        setContext(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MenuView into(ViewGroup root) {
        if (getContext() != null) {
            menu = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, root, false);
            textView = menu.findViewById(R.id.tvTitle);
            imageView = menu.findViewById(R.id.ivMenu);
        }
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
        textView.setTextColor(getContext().getResources().getColor(color));
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
