package com.easyapp.http.upload;

/**
 * Created by Allen at 2017/6/13 17:04
 */
public interface OnUploadListener {
    /**
     * 上传进度信息更新回调
     *
     * @param info 上传进度信息
     */
    void onUpLoadProgress(ProgressInfo info);

    /**
     * 获取上传字节总长度失败
     * 如果该方法回调,那么上传进度信息更新回调则不执行
     *
     *
     * @param info 上传进度信息
     */
    void onUploadGetContentLengthFail(ProgressInfo info);
}