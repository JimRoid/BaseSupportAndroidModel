package com.easyapp.http.upload;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/**
 * Created by Allen at 2017/6/13 10:52
 */
public class WrapSink extends ForwardingSink {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public OnUploadListener mListener;
    public ProgressInfo mInfo;
    public boolean mDoProgress;

    public WrapSink(Sink delegate, ProgressInfo info, OnUploadListener listener, boolean doProgress) {
        super(delegate);
        mInfo = info;
        mListener = listener;
        mDoProgress = doProgress;
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        super.write(source, byteCount);
        long l = mInfo.getCurrentLength() + byteCount;
        mInfo.setCurrentLength(l);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mDoProgress) {
                    mListener.onUpLoadProgress(mInfo);
                }
            }
        });
    }
}