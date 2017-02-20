package com.ikkong.download;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/11
 * Description:
 */

public class DownLoadHelper {
    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;


    /**
     * 执行单线程列队执行
     */
    public static void runOnQueue(Runnable runnable) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(runnable);
    }

    /**
     * 启动图片下载线程
     * @param context
     * @param url
     * @param callBack
     */
    public static void downLoad(Context context,String url,ImageDownLoadCallBack callBack){
        runOnQueue(new DownLoadImageService(context,url,callBack));
    }

}
