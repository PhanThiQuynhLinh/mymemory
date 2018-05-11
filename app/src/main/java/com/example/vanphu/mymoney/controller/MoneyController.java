package com.example.vanphu.mymoney.controller;

import android.content.Context;
import android.widget.GridView;

import com.example.vanphu.mymoney.adapter.MoneyAdapter;
import com.example.vanphu.mymoney.model.MoneyModel;
import com.example.vanphu.mymoney.R;

import java.util.ArrayList;
import java.util.Arrays;



public class MoneyController {
    private ArrayList<MoneyModel> mArrayList;
    private MoneyAdapter mAdapter;
    private ArrayList<String> mArrayImage;
    private ArrayList<String> mArrayName;
    private Context mContext;

    public MoneyController(Context Context) {
        mContext = Context;
        this.mArrayList = new ArrayList<>();
        mAdapter = new MoneyAdapter(mContext, mArrayList);
        String[] arrayAvatar = mContext.getResources().getStringArray(R.array.list_image_flag);
        mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
        String[] arrayName = mContext.getResources().getStringArray(R.array.list_name_flag);
        mArrayName = new ArrayList<>(Arrays.asList(arrayName));
    }

    public void addImage(GridView gridView) {
        for (int i = 0; i < 5; i++) {
            int idImage = mContext.getResources().getIdentifier(mArrayImage.get(i), "drawable", mContext.getPackageName());
            String nameImage = mArrayName.get(i);
            mArrayList.add(new MoneyModel(idImage, nameImage));
        }
        gridView.setAdapter(mAdapter);
    }
}
