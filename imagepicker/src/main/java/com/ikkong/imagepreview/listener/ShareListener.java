package com.ikkong.imagepreview.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.ikkong.download.DownLoadHelper;
import com.ikkong.download.ImageDownLoadCallBack;
import com.lzy.imagepicker.Utils;

import java.io.File;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2017/12/25
 * Description:
 */

public class ShareListener implements View.OnLongClickListener {
    private String imgUrl;
    private Activity context;

    public ShareListener(String imgUrl, Activity context) {
        this.imgUrl = imgUrl;
        this.context = context;
    }

    @Override
    public boolean onLongClick(View v) {
        //弹框 分享按钮
        final String imgUrl = this.imgUrl;
        new AlertDialog.Builder(context)
                .setItems(new String[]{"分享到微信","分享到QQ"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        //1.首先下载图片到本地
                        //2.调用分享
                        if(!imgUrl.startsWith("http")){
                            switch (which){
                                case 0:
                                    shareWeChat(imgUrl);
                                    break;
                                case 1:
                                    shareQQ(imgUrl);
                                    break;
                            }
                        }else {
                            Toast.makeText(context,"正在保存图片到本地，请稍候。。。",Toast.LENGTH_SHORT).show();
                            DownLoadHelper.downLoad(context, imgUrl, new ImageDownLoadCallBack() {
                                @Override
                                public void onDownLoadSuccess(final File file) {
                                    final String savePath = Utils.getDownloadImgPath();
                                    final boolean success = Utils.copyFile(file,savePath);
                                    context.runOnUiThread(new Runnable() {
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
                                                Toast.makeText(context,"复制图片失败，无法分享",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onDownLoadFailed() {
                                    context.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context,"保存图片到本地失败，无法分享",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }
                }).setCancelable(true).create().show();
        return false;
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
        context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
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
            context.startActivity(chooserIntent);
        } catch (Exception e) {
            context.startActivity(sendIntent);
        }
    }
}
