package com.easyapp.baseproject.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.easyapp.baseproject.lib.View.TouchImageView;
import com.easyapp.baseproject.lib.asyncTool.DownloadImageTask;
import com.easyapp.baseproject.lib.tool.Base64Tool;
import com.easyapp.baseproject.lib.util.Common;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by easyapp_jim on 2015/10/22.
 */
public class TouchViewFragment extends BaseEasyFragment {
    private TouchImageView touchImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        String path = bundle.getString(Common.PATH);
        Logger.d(path);

        if (path.contains("http")) {
            new DownloadImageTask(touchImageView)
                    .execute(path);
        } else if (path.contains("/storage")) {
            File file = new File(path);
            if (file.exists()) {
                Logger.d(path);
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
