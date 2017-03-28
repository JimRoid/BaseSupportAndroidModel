package com.easyapp.lib.touchView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.easyapp.lib.R;
import com.easyapp.lib.tool.Base64Tool;
import com.easyapp.lib.widget.anim.CircularProgressView;

import java.io.File;

/**
 * 建立touch view
 */
public class FragmentTouchView extends Fragment {
    private TouchImageView touchImageView;
    private CircularProgressView progressView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_touch_image, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        touchImageView = (TouchImageView) view.findViewById(R.id.iv_touch);
        progressView = (CircularProgressView) view.findViewById(R.id.loading);
        progressView.setVisibility(View.GONE);
        getExtraIntent();
    }

    protected void getExtraIntent() {
        Bundle bundle = getArguments();
        String path = bundle.getString("PATH");
        if (path == null) {
            touchImageView.setImageResource(R.drawable.icon_empty);
            return;
        }

        if (path.contains("http")) {
            Glide.with(this).load(path).error(R.drawable.icon_empty).into(new GlideDrawableImageViewTarget(touchImageView) {
                @Override
                public void onStart() {
                    super.onStart();
                    showLoading();
                }

                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    touchImageView.setImageDrawable(resource);
                    cancelLoading();
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    cancelLoading();
                }
            });
        } else if (path.contains("/storage")) {
            File file = new File(path);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                touchImageView.setImageBitmap(bitmap);
            }
        } else {
            byte[] decodedString = Base64Tool.decodeBase64(path);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            touchImageView.setImageBitmap(decodedByte);
        }
    }

    protected void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    protected void cancelLoading() {
        progressView.setVisibility(View.GONE);
    }
}
