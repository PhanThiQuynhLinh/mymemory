package com.example.vanphu.mymoney.controller;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.adapter.SpendAdapter;
import com.example.vanphu.mymoney.model.SpendModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by VanPhu on 5/12/2018.
 */

public class StatisticsController {
    private Context mContext;
    private ArrayList<SpendModel> mArrayList;
    private SpendAdapter mAdapter;
    private ArrayList<String> mArrayImage;
    public StatisticsController(Context Context) {
        mContext = Context;
        this.mArrayList = new ArrayList<>();
        mAdapter = new SpendAdapter(mContext, mArrayList);
        String[] arrayAvatar = mContext.getResources().getStringArray(R.array.list_image_spend);
        mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
    }
    public void addImage(ListView lv_Spend) {
        mArrayList.add(new SpendModel(1, R.drawable.img_spend_item24, "Chi TIeu 1", 1000, "avc", "sadsd"));
        lv_Spend.setAdapter(mAdapter);
    }
    public void readJsonSpend(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mArrayList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                final int mIdImage = mContext.getResources().getIdentifier(mArrayImage.get(object.getInt("idhinh")), "drawable", mContext.getPackageName());
                                mArrayList.add(new SpendModel(object.getInt("id"), mIdImage, object.getString("tenchitieu"), object.getInt("giachitieu"),
                                        object.getString("email"), object.getString("ngay")
                                ));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Error"+error, Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void readMonths(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mArrayList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                final int mIdImage = mContext.getResources().getIdentifier(mArrayImage.get(object.getInt("idhinh")), "drawable", mContext.getPackageName());
                                mArrayList.add(new SpendModel(object.getInt("id"), mIdImage, object.getString("tenchitieu"), object.getInt("giachitieu"),
                                        object.getString("email"), object.getString("ngay")
                                ));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Error"+error, Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
