package com.example.lakalaka.servicebestpractice;

/**
 * Created by lakalaka on 2018/1/18/0018.
 */

public interface DownloadListener {
    void onProgress(int progress);//用于通知下载进度

    void onSuccess();//通知下载成功事件

    void onFailed();//通知下载失败事件

    void onPaused();//通知下载暂停事假

    void onCanceled();//通知下载取消事件
}
