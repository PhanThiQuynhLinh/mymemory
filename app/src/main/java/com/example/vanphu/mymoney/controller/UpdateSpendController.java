package com.example.vanphu.mymoney.controller;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.UpdateSpendActivity;
import com.example.vanphu.mymoney.UpdateSpendCollectedActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VanPhu on 5/11/2018.
 */

public class UpdateSpendController {
    private Context mContext;

    public UpdateSpendController(Context mContext) {
        this.mContext = mContext;
    }

    public void UpdateSpend(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Cập Nhật Thàng Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Cập Nhật THất Bại", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Lỗi" + error, Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(UpdateSpendActivity.sId));
                params.put("tenchitieu", UpdateSpendActivity.sEdit_TitleSpend.getText().toString().trim());
                params.put("giachitieu", UpdateSpendActivity.sEdit_MoneySpend.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void deleteSpend(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Xóa Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Lỗi", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Lõi" + error, Toast.LENGTH_LONG).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(UpdateSpendActivity.sId));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void UpdateSpendCollected(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Cập Nhật Thàng Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Cập Nhật THất Bại", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Lỗi" + error, Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(UpdateSpendCollectedActivity.sId));
                params.put("tenchitieu", UpdateSpendActivity.sEdit_TitleSpend.getText().toString().trim());
                params.put("giachitieu", UpdateSpendActivity.sEdit_MoneySpend.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void deleteSpendCollected(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Xóa Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Lỗi", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Lõi" + error, Toast.LENGTH_LONG).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(UpdateSpendCollectedActivity.sId));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
