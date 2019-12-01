package com.easyapp.http.upload;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by Allen at 2017/6/13 17:01
 */
public class WrapRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private OnUploadListener mListener;
    private ProgressInfo mInfo;
    private boolean mDoProgress;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public WrapRequestBody(RequestBody requestBody, ProgressInfo info, OnUploadListener listener) {
        mRequestBody = requestBody;
        mListener = listener;
        mInfo = info;
    }


    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        try {
            long l = mRequestBody.contentLength();
            mDoProgress = true;
            return l;
        } catch (IOException e) {
            e.printStackTrace();
            failWork();
            return -1;
        }
    }

    private void failWork() {
        mDoProgress = false;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onUploadGetContentLengthFail(mInfo);
            }
        });
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        mInfo.setContentLength(contentLength());
        WrapSink wrapSink = new WrapSink(sink, mInfo, mListener, mDoProgress);
        BufferedSink buffer = Okio.buffer(wrapSink);
        mRequestBody.writeTo(buffer);
        buffer.flush();
    }
}