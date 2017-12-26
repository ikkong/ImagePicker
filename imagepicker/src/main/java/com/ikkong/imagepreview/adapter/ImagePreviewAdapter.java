package com.ikkong.imagepreview.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.ikkong.imagepreview.listener.ShareListener;
import com.lzy.imagepicker.Utils;
import com.lzy.imagepicker.loader.GlideImageLoader;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 保留图片预览，去除其他代码
 */
public class ImagePreviewAdapter extends PagerAdapter {

    private int screenWidth;
    private int screenHeight;
    private String[] imgUrls;
    private Activity mActivity;
    public PhotoViewClickListener listener;

    public ImagePreviewAdapter(Activity activity, String[] imgUrls) {
        this.mActivity = activity;
        this.imgUrls = imgUrls;

        DisplayMetrics dm = Utils.getScreenPix(activity);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public void setData(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    public void setPhotoViewClickListener(PhotoViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        PhotoView photoView = new PhotoView(mActivity);
        GlideImageLoader.getInstance().displayImage(mActivity, imgUrls[position], photoView, screenWidth, screenHeight,1);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (listener != null) listener.OnPhotoTapListener(view, x, y);
            }
        });
        photoView.setOnLongClickListener(new ShareListener(imgUrls[position],mActivity));
        container.addView(photoView);
        return photoView;
    }

    @Override
    public int getCount() {
        return imgUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public interface PhotoViewClickListener {
        void OnPhotoTapListener(View view, float v, float v1);
    }
}
