package com.example.vanphu.mymoney.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanphu.mymoney.Model.imageModel;
import com.example.vanphu.mymoney.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<imageModel> mImgList;
    private Context mContext;

    public ImageAdapter(Context Context, List<imageModel> imgList) {
        this.mContext = Context;
        this.mImgList = imgList;
        mInflater = LayoutInflater.from(mContext);
        ArrayList<imageModel> mArrayList = new ArrayList<>();
        mArrayList.addAll(imgList);
    }

    public class ViewHolder {
        ImageView img_Avatar;
        TextView txt_NameAvatar;
    }

    @Override
    public int getCount() {
        return mImgList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.row_image_avatar, null);

            //locate the views in row.xml
            holder.img_Avatar = view.findViewById(R.id.img_Avatar);
            holder.txt_NameAvatar = view.findViewById(R.id.txt_NameAvatar);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.img_Avatar.setImageResource(mImgList.get(position).getmImage());
        holder.txt_NameAvatar.setText(mImgList.get(position).getmName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ((Activity) mContext).getIntent();
                intent.putExtra("idImage", String.valueOf(position));
                ((Activity) mContext).setResult(RESULT_OK,
                        intent);
                ((Activity) mContext).finish();
            }
        });
        return view;
    }
}
