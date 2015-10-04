package com.easyapp.baseproject.lib.View;

import android.content.Context;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by easyapp_jim on 15/5/1.
 */
public class AutoLinkTextView extends TextView {
    public AutoLinkTextView(Context context) {
        super(context);
    }

    public AutoLinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLinkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        Linkify.addLinks(this, Linkify.WEB_URLS);
    }
}
