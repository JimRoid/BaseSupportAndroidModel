package com.easyapp.lib.touchView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.easyapp.lib.R;
import com.easyapp.lib.glide.GlideImageLoader;
import com.easyapp.lib.tool.Base64Tool;
import com.easyapp.lib.tool.DownloadFileFromURL;

import com.orhanobut.logger.Logger;

/**
 * 建立touch view
 */
public class FragmentTouchView extends Fragment {
    private TouchImageView touchImageView;

    private ImageView ivDownload;
    private FrameLayout flMenu;
    private ProgressBar progressBar;

    public static FragmentTouchView getInstance(String path) {
        FragmentTouchView fragmentTouchView = new FragmentTouchView();

        Bundle bundle = new Bundle();
        bundle.putString("PATH", path);
        bundle.putBoolean("isShowDownload", false);

        fragmentTouchView.setArguments(bundle);
        return fragmentTouchView;
    }

    public static FragmentTouchView getInstance(String type, String path, boolean isShowDownload) {
        FragmentTouchView fragmentTouchView = new FragmentTouchView();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("path", path);
        bundle.putBoolean("isShowDownload", isShowDownload);

        fragmentTouchView.setArguments(bundle);
        return fragmentTouchView;
    }

    public static FragmentTouchView getInstance(String type, int path, boolean isShowDownload) {
        FragmentTouchView fragmentTouchView = new FragmentTouchView();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putInt("resource", path);
        bundle.putBoolean("isShowDownload", isShowDownload);

        fragmentTouchView.setArguments(bundle);
        return fragmentTouchView;
    }

    private boolean isShowDownload() {
        return getArguments().getBoolean("isShowDownload", false);
    }

    private int getResource() {
        return getArguments().getInt("resource", 0);
    }

    private String getPath() {
        return getArguments().getString("path", "");
    }

    private String getType() {
        return getArguments().getString("type", "");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_touch_image, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ivDownload = view.findViewById(R.id.ivDownload);
        flMenu = view.findViewById(R.id.flMenu);
        touchImageView = view.findViewById(R.id.ivTouch);
        progressBar = view.findViewById(R.id.progressBar);
        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("ivDownload");
            }
        });

        getExtraIntent();
    }

    private void getExtraIntent() {

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        boolean isShowDownload = bundle.getBoolean("isShowDownload");
        if (isShowDownload) {
            flMenu.setVisibility(View.VISIBLE);
        } else {
            flMenu.setVisibility(View.GONE);
        }


        if (getPath().equals("") && getResource() == 0) {
            touchImageView.setImageResource(R.mipmap.ic_empty);
            return;
        }

        if (getType().equals("http")) {
            new GlideImageLoader(touchImageView, progressBar).load(getPath(), new RequestOptions());
            ivDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownloadFileFromURL.Download(getActivity(), getPath());
                }
            });
        } else if (getType().equals("sd")) {
            byte[] decodedString = Base64Tool.decodeBase64(getPath());
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            touchImageView.setImageBitmap(decodedByte);
        } else if (getType().equals("resource")) {
            touchImageView.setImageResource(getResource());
        }
    }
}
