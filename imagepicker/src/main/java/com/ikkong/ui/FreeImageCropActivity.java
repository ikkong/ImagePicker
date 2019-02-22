package com.ikkong.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.Utils;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageBaseActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

/**
 * ================================================
 * 作    者：ikkong
 * 版    本：1.0
 * 创建日期：2019/2/22
 * 描    述：接入 https://github.com/Yalantis/uCrop 实现图片自由裁剪
 * 修订历史：
 * ================================================
 */
public class FreeImageCropActivity extends ImageBaseActivity {

    private ArrayList<ImageItem> mImageItems;
    private ImagePicker imagePicker;
    private File saveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePicker = ImagePicker.getInstance();
        mImageItems = imagePicker.getSelectedImages();
        String imagePath = mImageItems.get(0).path;
        
        saveFile = Utils.createFile(imagePicker.getCropCacheFolder(this), "IMG_", ".jpg");
        
        //调用 第三方 裁剪库 准备
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(100);
        options.setHideBottomControls(true);//隐藏裁剪界面控制按钮
        options.setFreeStyleCropEnabled(true);//自由裁剪
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setToolbarColor(ContextCompat.getColor(this, R.color.ip_status_bar));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.ip_status_bar));
        options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.ip_green));
        UCrop.of(Uri.fromFile(new File(imagePath)), Uri.fromFile(saveFile))
                .withOptions(options)
                .start(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                handleCropResult(data);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            handleCropError(data);
        }
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null) {
            //裁剪后替换掉返回数据的内容，但是不要改变全局中的选中数据
            mImageItems.remove(0);
            ImageItem imageItem = new ImageItem();
            
            imageItem.path = saveFile.getAbsolutePath();
            mImageItems.add(imageItem);

            Intent intent = new Intent();
            intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, mImageItems);
            setResult(ImagePicker.RESULT_CODE_ITEMS, intent);   //单选不需要裁剪，返回数据
            finish();
        } else {
            Toast.makeText(FreeImageCropActivity.this, "Cannot retrieve cropped image", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Log.e("FreeImageCropActivity", "handleCropError: ", cropError);
            Toast.makeText(FreeImageCropActivity.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(FreeImageCropActivity.this, "Unexpected error", Toast.LENGTH_SHORT).show();
        }
    }

}
