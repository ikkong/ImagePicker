package com.lzy.imagepickerdemo.wxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ikkong.wximagepicker.ImagePickerAdapter;
import com.ikkong.wximagepicker.ImagePickerConstants;
import com.lzy.imagepicker.ui.ImagePreviewSaveActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepickerdemo.R;
import com.lzy.imagepickerdemo.data.Images;

import java.util.ArrayList;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2017/2/20
 * Description:
 */

public class ReadOnlyActivity extends AppCompatActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数
    private RecyclerView recyclerView;
    private boolean readOnly = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxdemo);

        initWidget();
    }

    private void initWidget() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        ImageItem temp;
        for (int i = 0; i < 5; i++) {
            temp = new ImageItem();
            temp.path = Images.getImages().get(i);
            temp.addTime = -1;
            selImageList.add(temp);
        }
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount,1,readOnly);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new WrapHeightGridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position,int adapterTag) {
        switch (position) {
            case ImagePickerConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                if(readOnly){
                    Intent intentPreview = new Intent(this, ImagePreviewSaveActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    startActivity(intentPreview);
                }else {
                    Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        }
    }
}
