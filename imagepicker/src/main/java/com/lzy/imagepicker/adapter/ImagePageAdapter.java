package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ikkong.download.DownLoadHelper;
import com.ikkong.download.ImageDownLoadCallBack;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.Utils;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧 Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ImagePageAdapter extends PagerAdapter {

    private int screenWidth;
    private int screenHeight;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images = new ArrayList<>();
    private Activity mActivity;
    public PhotoViewClickListener listener;
    public View.OnLongClickListener longClickListener;

    public ImagePageAdapter(Activity activity, ArrayList<ImageItem> images) {
        this.mActivity = activity;
        this.images = images;

        DisplayMetrics dm = Utils.getScreenPix(activity);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        imagePicker = ImagePicker.getInstance();
    }

    public void setData(ArrayList<ImageItem> images) {
        this.images = images;
    }

    public void setPhotoViewClickListener(PhotoViewClickListener listener) {
        this.listener = listener;
    }

    public void setLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(mActivity);
        final ImageItem imageItem = images.get(position);
        imagePicker.getImageLoader().displayImage(mActivity, imageItem.path, photoView, screenWidth, screenHeight,1);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (listener != null) listener.OnPhotoTapListener(view, x, y);
            }
        });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //弹框 分享按钮
                new AlertDialog.Builder(mActivity)
                        .setItems(new String[]{"分享到微信","分享到QQ"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int which) {
                                //1.首先下载图片到本地
                                //2.调用分享
                                if(!imageItem.path.startsWith("http")){
                                    switch (which){
                                        case 0:
                                            shareWeChat(imageItem.path);
                                            break;
                                        case 1:
                                            shareQQ(imageItem.path);
                                            break;
                                    }
                                }else {
                                    Toast.makeText(mActivity,"正在保存图片到本地，请稍候。。。",Toast.LENGTH_SHORT).show();
                                    DownLoadHelper.downLoad(mActivity, imageItem.path, new ImageDownLoadCallBack() {
                                        @Override
                                        public void onDownLoadSuccess(final File file) {
                                            final String savePath = Utils.getDownloadImgPath();
                                            final boolean success = Utils.copyFile(file,savePath);
                                            mActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if(success) {
                                                        switch (which){
                                                            case 0:
                                                                shareWeChat(savePath);
                                                                break;
                                                            case 1:
                                                                shareQQ(savePath);
                                                                break;
                                                        }
                                                    }else {
                                                        Toast.makeText(mActivity,"复制图片失败，无法分享",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDownLoadFailed() {
                                            mActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(mActivity,"保存图片到本地失败，无法分享",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }).setCancelable(true).create().show();
                return false;
            }
        });
        container.addView(photoView);
        return photoView;
    }

    @Override
    public int getCount() {
        return images.size();
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

    /**
     * 分享图片到 微信
     * @param path  本地路径的图片
     */
    private void shareWeChat(String path){
        Uri uriToImage = Uri.fromFile(new File(path));
        Intent shareIntent = new Intent();
        //发送图片给好友。
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        shareIntent.setComponent(comp);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/jpeg");
        mActivity.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    public void shareQQ(String path){
        Uri uriToImage = Uri.fromFile(new File(path));
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        sendIntent.setType("image/jpeg");
        try {
            sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
            Intent chooserIntent = Intent.createChooser(sendIntent, "选择分享途径");
            if (chooserIntent == null) {
                return;
            }
            mActivity.startActivity(chooserIntent);
        } catch (Exception e) {
            mActivity.startActivity(sendIntent);
        }
    }
}
