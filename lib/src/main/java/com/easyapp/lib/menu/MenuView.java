package com.easyapp.lib.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyapp.lib.R;


/**
 * 客製化menu
 */
public class MenuView {

    protected View menu;
    protected ImageView imageView;
    protected TextView textView;
    protected Context context;

    public MenuView(Context context) {
        super();
        setContext(context);
        menu = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, null, false);
        textView = menu.findViewById(R.id.tvTitle);
        imageView = menu.findViewById(R.id.ivMenu);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
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

    public void setMenu(View menu) {
        this.menu = menu;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
