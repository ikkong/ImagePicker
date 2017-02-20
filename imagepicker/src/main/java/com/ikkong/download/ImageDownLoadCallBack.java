package com.ikkong.download;

import java.io.File;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/11
 * Description:
 */

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(File file);

    void onDownLoadFailed();
}
