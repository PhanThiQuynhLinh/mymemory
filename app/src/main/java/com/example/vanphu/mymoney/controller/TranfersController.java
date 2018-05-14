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
import com.example.vanphu.mymoney.TranfersActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VanPhu on 5/14/2018.
 */

public class TranfersController {
    private Context mContext;

    public TranfersController(Context mContext) {
        this.mContext = mContext;
    }
    public void TranfersMoney(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(mContext);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Thành Công")){
                            Toast.makeText(mContext,"Chuyển Thàng Công",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(mContext,"Chuyển Thất Bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"Lỗi"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("TransferAmount", TranfersActivity.sEdit_money.getText().toString().trim());
                params.put("emailfirst",TranfersActivity.sEdit_User.getText().toString().trim());
                params.put("emaillast",TranfersActivity.sEdit_money_item1.getText().toString().trim());
                return params;
            }
        } ;
        requestQueue.add(stringRequest);
    }

}
