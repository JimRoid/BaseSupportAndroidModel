package com.easyapp.baseproject.lib.touchView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject.lib.Base64Tool;
import com.easyapp.baseproject.lib.R;

import java.io.File;

/**
 * 建立touch view
 */
public class FragmentTouchView extends Fragment {
    private TouchImageView touchImageView;

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
        touchImageView.setImageResource(R.drawable.ic_camera);
        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        frameLayout.addView(touchImageView);
        getExtraIntent();
        return frameLayout;
    }

    protected void getExtraIntent() {
        Bundle bundle = getArguments();
        String path = bundle.getString("PATH");
        if (path == null) {
            touchImageView.setImageResource(R.drawable.ic_camera);
            return;
        }

        if (path.contains("http")) {
            Glide.with(this).load(path).error(R.drawable.ic_camera).into(touchImageView);
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
}
