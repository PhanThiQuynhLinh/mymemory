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
import com.example.vanphu.mymoney.SpendActivity;

import java.util.HashMap;
import java.util.Map;


public class MoneyInController {
    private Context mContext;

    public MoneyInController(Context mContext) {
        this.mContext = mContext;
    }

    public void moneyIn(final String url) {
        PayonActivity.sBtn_Success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PayonActivity.sMIdImage == -1) {
                    Toast.makeText(mContext, "Vui Lòng Chọn Mã Tiền Để Đăng Ký", Toast.LENGTH_LONG).show();
                } else {
                    if (PayonActivity.sEdit_Money.getText().toString().equals("")) {
                        Toast.makeText(mContext, "Vui Lòng Chọn Điền Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show();
                    } else {
                        final Dialog dialog = new Dialog(mContext);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_watting);
                        dialog.show();
                        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                                      if success then done
                                        if (response.trim().equals("Thành Công")) {
                                            Toast.makeText(mContext, "Thêm Thành Công", Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(mContext, SpendActivity.class);
                                            intent.putExtra("User",PayonActivity.sUser);
                                            mContext.startActivity(intent);
                                        } else {
                                            Toast.makeText(mContext, "Lỗi", Toast.LENGTH_LONG).show();
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
//                                post api
                                params.put("email", PayonActivity.sUser);
                                params.put("matien", PayonActivity.sKeyMoney);
                                params.put("tienvao", PayonActivity.sEdit_Money.getText().toString().trim());
                                params.put("idhinh", String.valueOf(PayonActivity.sMIdImage));
                                return params;
                            }
                        };
                        requestQueue.add(stringRequest);

                    }
                }
            }
        });
    }
}
