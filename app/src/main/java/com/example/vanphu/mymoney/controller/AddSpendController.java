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
import com.example.vanphu.mymoney.AddSpendActivity;
import com.example.vanphu.mymoney.SpendActivity;

import java.util.HashMap;
import java.util.Map;



public class AddSpendController {
    private Context mContext;

    public AddSpendController(Context mContext) {
        this.mContext = mContext;
    }

    public void AddSpend(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Thêm Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Lỗi Thêm", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Lỗi Xảy ra! " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenchitieu", AddSpendActivity.sEdit_TitleSpend.getText().toString().trim());
                params.put("giachitieu", AddSpendActivity.sEdit_MoneySpend.getText().toString().trim());
                params.put("idhinh", String.valueOf(AddSpendActivity.sIdImage));
                params.put("email", SpendActivity.mUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void addmoneycollected(String URL, final String nameSpend, final int MoneySpend){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Thành Công")) {
                            Toast.makeText(mContext, "Thêm Thành Công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Lỗi Thêm", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Lỗi Xảy ra! " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenchitieu",nameSpend);
                params.put("giachitieu", String.valueOf(MoneySpend));
                params.put("email", SpendActivity.mUser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
