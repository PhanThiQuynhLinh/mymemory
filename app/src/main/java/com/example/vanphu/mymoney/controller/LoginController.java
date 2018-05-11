package com.example.vanphu.mymoney.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SpendActivity;
import com.example.vanphu.mymoney.tab.Tab_Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private StringRequest mRequest;
    private SharedPreferences mSharedPreferences;

    public LoginController(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        Tab_Login.sEdit_User.setText(mSharedPreferences.getString("username", ""));
        Tab_Login.sEdit_Password.setText(mSharedPreferences.getString("password", ""));
        Tab_Login.sCb_Remember.setChecked(mSharedPreferences.getBoolean("checked", false));
    }

    public void login(final String URL) {
        Tab_Login.sBtn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tab_Login.sEdit_Password.getText().toString().equals("") || Tab_Login.sEdit_User.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Vui Lòng Chọn Điền Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show();
                } else {
                    //                Declare Dialog
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_watting);
                    dialog.show();
                    mRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
//                            if login success
                                if (jsonObject.names().get(0).equals("success")) {
//                                declare dialog

                                    Toast.makeText(mContext, "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                                    if (Tab_Login.sCb_Remember.isChecked()) {
                                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                                        editor.putString("username", Tab_Login.sEdit_User.getText().toString().trim());
                                        editor.putString("password", Tab_Login.sEdit_Password.getText().toString().trim());
                                        editor.putBoolean("checked", true);
                                        editor.commit();
                                    } else {
                                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                                        editor.remove("username");
                                        editor.remove("password");
                                        editor.remove("checked");
                                        editor.commit();
                                    }
                                    Intent intent = new Intent(mContext, SpendActivity.class);
                                    intent.putExtra("User", Tab_Login.sEdit_User.getText().toString().trim());
                                    mContext.startActivity(intent);
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(mContext, "Đăng Nhập Thất Bại", Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                dialog.dismiss();
                                Toast.makeText(mContext, "Đăng Nhập Thất Bại", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(mContext, "Error" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<>();
//                        post item data
                            hashMap.put("email", Tab_Login.sEdit_User.getText().toString());
                            hashMap.put("password", Tab_Login.sEdit_Password.getText().toString());
                            return hashMap;
                        }
                    };
                    mRequestQueue = Volley.newRequestQueue(mContext);
                    mRequestQueue.add(mRequest);
                }

            }
        });

    }

}
