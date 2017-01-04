package com.lzy.imagepickerdemo.wxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ikkong.wximagepicker.ImagePickerAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepickerdemo.R;

import java.util.ArrayList;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2017/1/4
 * Description: 多组选择
 */

public class MuliSelActivity extends AppCompatActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数
    private RecyclerView recyclerView;

    private ImagePickerAdapter adapter1;
    private ArrayList<ImageItem> selImageList1; //当前选择的所有图片
    private int maxImgCount1 = 4;               //允许选择图片最大数
    private RecyclerView recyclerView1;
    
    private int currentRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulisel);

        initWidget();
    }

    private void initWidget() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount,0);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new WrapHeightGridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        selImageList1 = new ArrayList<>();
        adapter1 = new ImagePickerAdapter(this, selImageList1, maxImgCount1,1);
        adapter1.setOnItemClickListener(this);
        recyclerView1.setLayoutManager(new WrapHeightGridLayoutManager(this, 4));
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(adapter1);
    }

    @Override
    public void onItemClick(View view, int position,int adapterTag) {
        currentRec = adapterTag;
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                if(currentRec == 0){
                    ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                }else if(currentRec == 1){
                    ImagePicker.getInstance().setSelectLimit(maxImgCount1 - selImageList1.size());
                }
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                if(currentRec == 0){
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                }else if(currentRec == 1){
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter1.getImages());
                }
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
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
                if(currentRec == 1){
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }else if(currentRec == 0){
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
                
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if(currentRec == 0) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }else if(currentRec == 1) {
                    selImageList1.clear();
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }
            }
        }
    }
}
