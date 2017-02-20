package com.ikkong.download;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/11
 * Description:
 */

public class DownLoadImageService implements Runnable {
    private String url;
    private Context context;
    private ImageDownLoadCallBack callBack;

    public DownLoadImageService(Context context, String url, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void run() {
        File file = null;
        try {
            file = Glide.with(context)
                    .load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                callBack.onDownLoadSuccess(file);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

}
