package com.easyapp.lib.widget.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class SceneAnimation {

    private Context context;
    private static Handler handler;
    private ImageView mImageView;
    private List<Integer> images;
    private int mDuration;
    private int mLastFrameNo;
    private int pFrameNo;
    private AnimRunnable animRunnable;


    public SceneAnimation(final Context mContext, ImageView pImageView, TypedArray array, int pDuration) {
        context = mContext;
        mImageView = pImageView;
        images = new ArrayList<>();
        mDuration = pDuration;
        mLastFrameNo = array.length() - 1;
        animRunnable = new AnimRunnable();

        handler = new Handler() {
            @Override
            public void handleMessage(final Message msg) {
                super.handleMessage(msg);
                //start Asyntask here. progress show/hide should be done in asynctaswk itself.
                mImageView.setImageDrawable(context.getResources().getDrawable(images.get(pFrameNo)));
                if (pFrameNo == mLastFrameNo) {
                    onAnimationFinish();
                } else {
                    playConstant(pFrameNo + 1);
                    onAnimationCount(pFrameNo + 1);
                }
            }
        };

        for (int i = 0; i < array.length(); i++) {
            images.add(array.getResourceId(i, -1));
        }
        array.recycle();

        mImageView.setImageDrawable(context.getResources().getDrawable(images.get(0)));
        playConstant(1);
    }

    public void Repeat(){
        playConstant(0);
    }

    public void cancel() {
        mImageView.removeCallbacks(animRunnable);
        handler = null;
        animRunnable = null;
    }


    private void playConstant(final int pFrameNo) {
        this.pFrameNo = pFrameNo;
        mImageView.postDelayed(animRunnable, mDuration);
    }


    protected class AnimRunnable implements Runnable {
        @Override
        public void run() {
            if (handler != null) {
                Message m = handler.obtainMessage();
                m.what = 0;
                handler.sendMessage(m);
            }
        }
    }

    public abstract void onAnimationFinish();

    public abstract void onAnimationCount(int value);
}