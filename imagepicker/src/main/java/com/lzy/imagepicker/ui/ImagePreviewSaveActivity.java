package com.lzy.imagepicker.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ikkong.download.DownLoadHelper;
import com.ikkong.download.ImageDownLoadCallBack;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.Utils;

import java.io.File;


/**
 * ================================================
 * 作    者：ikkong
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：保留图片预览，去除其他代码
 * ================================================
 */
public class ImagePreviewSaveActivity extends ImagePreviewBaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView mBtn = (ImageView) findViewById(R.id.btn_save);
        mBtn.setOnClickListener(this);
        mBtn.setVisibility(View.VISIBLE);

        mTitleCount.setText(getString(R.string.ip_preview_image_count, mCurrentPosition + 1, mImageItems.size()));
        //滑动ViewPager的时候，根据外界的数据改变当前的选中状态和当前的图片的位置描述文本
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                mTitleCount.setText(getString(R.string.ip_preview_image_count, mCurrentPosition + 1, mImageItems.size()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_save) {
            saveImg();
        } else if (id == R.id.btn_back) {
            onBackPressed();
        }
    }

    /** 保存图片 */
    private void saveImg() {
        String url = mImageItems.get(mCurrentPosition).path;
        if(!url.startsWith("http")){
            Toast.makeText(ImagePreviewSaveActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            return ;
        }
        DownLoadHelper.downLoad(ImagePreviewSaveActivity.this, url, new ImageDownLoadCallBack() {
            @Override
            public void onDownLoadSuccess(File file) {
                final String savePath = Utils.getDownloadImgPath();
                final boolean success = Utils.copyFile(file,savePath);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ImagePreviewSaveActivity.this,success?
                                ("保存成功:"+savePath):"保存失败",Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onDownLoadFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ImagePreviewSaveActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    /** 单击时，隐藏头和尾 */
    @Override
    public void onImageSingleTap() {
        if (topBar.getVisibility() == View.VISIBLE) {
            topBar.setAnimation(AnimationUtils.loadAnimation(this, com.lzy.imagepicker.R.anim.ip_top_out));
            topBar.setVisibility(View.GONE);
            tintManager.setStatusBarTintResource(com.lzy.imagepicker.R.color.ip_transparent);//通知栏所需颜色
            //给最外层布局加上这个属性表示，Activity全屏显示，且状态栏被隐藏覆盖掉。
            if (Build.VERSION.SDK_INT >= 16) content.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        } else {
            topBar.setAnimation(AnimationUtils.loadAnimation(this, com.lzy.imagepicker.R.anim.ip_top_in));
            topBar.setVisibility(View.VISIBLE);
            tintManager.setStatusBarTintResource(com.lzy.imagepicker.R.color.ip_status_bar);//通知栏所需颜色
            //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
            if (Build.VERSION.SDK_INT >= 16) content.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

}
