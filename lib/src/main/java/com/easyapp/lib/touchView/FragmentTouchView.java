package com.easyapp.lib.touchView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.easyapp.lib.R;
import com.easyapp.lib.base.fragment.Loading;
import com.easyapp.lib.tool.Base64Tool;

import java.io.File;

/**
 * 建立touch view
 */
public class FragmentTouchView extends Fragment {
    private TouchImageView touchImageView;
    protected Loading loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    private View initView() {
        FrameLayout frameLayout = new FrameLayout(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(layoutParams);
        touchImageView = new TouchImageView(getActivity());
        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        frameLayout.addView(touchImageView);
        getExtraIntent();
        return frameLayout;
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
                }

                @Override
                public void onStop() {
                    super.onStop();
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
        if (getActivity() == null) {
            return;
        }

        if (getFragmentManager() == null) {
            return;
        }

        if (!loading.isAdded()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(loading, "loading").commitAllowingStateLoss();
        }
    }

    protected void cancelLoading() {
        if (getFragmentManager() == null) {
            return;
        }

        if (loading.isAdded()) {
            loading.dismiss();
        }
    }
}
