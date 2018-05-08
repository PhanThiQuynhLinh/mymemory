package com.example.vanphu.mymoney.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.vanphu.mymoney.PayonActivity;
import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.Tab.Tab_Registered;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisteredController {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private StringRequest mRequest;

    public RegisteredController(Context mContext) {
        this.mContext = mContext;
    }

    public void registered(final String URL) {
        Tab_Registered.sBtn_Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tab_Registered.sMIdImage == -1) {
                    Toast.makeText(mContext, "Vui Lòng Chọn Avatar Đăng Ký", Toast.LENGTH_LONG).show();
                } else {
                    if (Tab_Registered.sEdit_Name.getText().toString().equals("") || Tab_Registered.sEdit_Password.getText().toString().equals("") || Tab_Registered.sEdit_User.getText().toString().equals("")) {
                        Toast.makeText(mContext, "Vui Lòng Chọn Điền Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show();
                    } else {
                        final Dialog dialog = new Dialog(mContext);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_watting);
                        dialog.show();
                        mRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.names().get(0).equals("success")) {
                                        Intent intent=new Intent(mContext, PayonActivity.class);
                                        intent.putExtra("User",Tab_Registered.sEdit_User.getText().toString());
                                        mContext.startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        Toast.makeText(mContext, "Tài khoản đã được đăng ký hoặc không hợp lệ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    dialog.dismiss();
                                    Toast.makeText(mContext, "Error" + e, Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialog.dismiss();
                                Toast.makeText(mContext, "Error" + error, Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("email", Tab_Registered.sEdit_User.getText().toString());
                                hashMap.put("password", Tab_Registered.sEdit_Password.getText().toString());
                                hashMap.put("name", Tab_Registered.sEdit_Name.getText().toString().trim());
                                hashMap.put("idhinh", String.valueOf(Tab_Registered.sMIdImage));
                                return hashMap;
                            }
                        };
                        mRequestQueue = Volley.newRequestQueue(mContext);
                        mRequestQueue.add(mRequest);
                    }

                }

            }
        });
    }


}
