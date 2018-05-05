package com.example.vanphu.mymoney.Controller;


import android.content.Context;
import android.widget.GridView;

import com.example.vanphu.mymoney.Adapter.ImageAdapter;
import com.example.vanphu.mymoney.Model.imageModel;
import com.example.vanphu.mymoney.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageController {
    private ArrayList<imageModel> mArrayList;
    private ImageAdapter mAdapter;
    private ArrayList<String> mArrayImage;
    private ArrayList<String> mArrayName;
    private Context mContext;

    public ImageController(Context Context) {
        mContext = Context;
        this.mArrayList = new ArrayList<>();
        mAdapter = new ImageAdapter(mContext, mArrayList);
        String[] arrayAvatar = mContext.getResources().getStringArray(R.array.list_image_avatar);
        mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
        String[] arrayName = mContext.getResources().getStringArray(R.array.list_name_avatar);
        mArrayName = new ArrayList<>(Arrays.asList(arrayName));
    }

    public void addImage(GridView gridView) {
        for (int i = 0; i < 5; i++) {
            int idImage = mContext.getResources().getIdentifier(mArrayImage.get(i), "drawable", mContext.getPackageName());
            String nameImage=mArrayName.get(i);
            mArrayList.add(new imageModel(idImage, nameImage));
        }
        gridView.setAdapter(mAdapter);
    }
}
