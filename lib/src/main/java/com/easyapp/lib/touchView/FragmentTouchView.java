package com.easyapp.lib.touchView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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

    public static FragmentTouchView instance(String path) {
        FragmentTouchView fragmentTouchView = new FragmentTouchView();

        Bundle bundle = new Bundle();
        bundle.putString("PATH", path);

        fragmentTouchView.setArguments(bundle);
        return fragmentTouchView;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_touch_image, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        touchImageView = view.findViewById(R.id.ivTouch);
        progressView = view.findViewById(R.id.loading);
        cancelLoading();
        getExtraIntent();
    }

    protected void getExtraIntent() {
        Bundle bundle = getArguments();
        String path = bundle.getString("PATH", "");
        if (path == null) {
            touchImageView.setImageResource(R.drawable.icon_empty);
            return;
        }
        showLoading();
        if (path.contains("http")) {
            Glide.with(this).load(path).into(new DrawableImageViewTarget(touchImageView) {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    super.onResourceReady(resource, transition);
                    cancelLoading();
                }
            });
        } else if (path.contains("/storage")) {
            File file = new File(path);
            if (file.exists()) {
                Glide.with(this).load(path).into(new DrawableImageViewTarget(touchImageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        cancelLoading();
                    }
                });
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
