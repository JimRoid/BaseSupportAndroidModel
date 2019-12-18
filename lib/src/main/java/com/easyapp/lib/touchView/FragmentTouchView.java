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

    public static FragmentTouchView getInstance(String path, boolean isShowDownload) {
        FragmentTouchView fragmentTouchView = new FragmentTouchView();
        Bundle bundle = new Bundle();
        bundle.putString("PATH", path);
        bundle.putBoolean("isShowDownload", isShowDownload);

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

        final String path = bundle.getString("PATH", "");
        if (path == null) {
            touchImageView.setImageResource(R.mipmap.ic_empty);
            return;
        }

        if (path.contains("http") || path.contains("/storage")) {
            new GlideImageLoader(touchImageView, progressBar).load(path, new RequestOptions());
            if (path.contains("http")) {
                ivDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DownloadFileFromURL.Download(getActivity(), path);
                    }
                });
            }
        } else {
            byte[] decodedString = Base64Tool.decodeBase64(path);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            touchImageView.setImageBitmap(decodedByte);
        }
    }
}
