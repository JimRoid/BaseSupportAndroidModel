package com.easyapp.http.upload;

/**
 * Created by Allen at 2017/6/13 17:27
 */
public class ProgressInfo {
    //路径url,不包含参数键值对,打印查看
    public String mUrl;
    //调用时间
    public String mTime;
    //当前读、写长度
    public long mCurrentLength;
    //总文件长度
    public long mContentLength;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public long getContentLength() {
        return mContentLength;
    }

    public void setContentLength(long contentLength) {
        mContentLength = contentLength;
    }

    public long getCurrentLength() {
        return mCurrentLength;
    }

    public void setCurrentLength(long currentLength) {
        mCurrentLength = currentLength;
    }

    public String getPercentString() {
        int i = 0;
        if (mContentLength == 0 || mContentLength == -1) {
            return "0%";
        }
        i = (int) (mCurrentLength / (float) mContentLength * 100);
        return i + "%";
    }

    public int getPercent() {
        int i = 0;
        if (mContentLength == 0 || mContentLength == -1) {
            return 0;
        }
        i = (int) (mCurrentLength / (float) mContentLength * 100);
        return i;
    }

    public float getPercentFloat() {
        return mContentLength == 0 || mContentLength == -1 ? 0 : mCurrentLength / (float) mContentLength;
    }
}