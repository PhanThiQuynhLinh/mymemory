package com.example.vanphu.mymoney.controller;

import android.content.Context;
import android.widget.GridView;

import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.adapter.ImageAdapter;
import com.example.vanphu.mymoney.model.imageModel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by VanPhu on 5/14/2018.
 */

public class ImageSpendController {
    private ArrayList<imageModel> mArrayList;
    private ImageAdapter mAdapter;
    private ArrayList<String> mArrayImage;
    private ArrayList<String> mArrayName;
    private Context mContext;

    public ImageSpendController(Context Context) {
        mContext = Context;
        this.mArrayList = new ArrayList<>();
        mAdapter = new ImageAdapter(mContext, mArrayList);
        String[] arrayAvatar = mContext.getResources().getStringArray(R.array.list_image_spend);
        mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
        String[] arrayName = mContext.getResources().getStringArray(R.array.list_name_spend);
        mArrayName = new ArrayList<>(Arrays.asList(arrayName));
    }

    public void addImage(GridView gridView) {
        for (int i = 0; i < 23; i++) {
            int idImage = mContext.getResources().getIdentifier(mArrayImage.get(i), "drawable", mContext.getPackageName());
            String nameImage=mArrayName.get(i);
            mArrayList.add(new imageModel(idImage, nameImage));
        }
        gridView.setAdapter(mAdapter);
    }
}
