package com.example.vanphu.mymoney.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.Tab.Tab_Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Context mContext;

    private RequestQueue requestQueue;
    private StringRequest request;
    private SharedPreferences sharedPreferences;

    public LoginController(Context mContext) {
        this.mContext = mContext;
        sharedPreferences =mContext.getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        Tab_Login.edit_User.setText(sharedPreferences.getString("username",""));
        Tab_Login.edit_Password.setText(sharedPreferences.getString("password",""));
        Tab_Login.cb_Remember.setChecked(sharedPreferences.getBoolean("checked",false));
    }

    public void login(final String URL, final CheckBox cb_Remember, final TextInputEditText edit_User, final TextInputEditText edit_Password, Button btn_Login) {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Declare Dialog
                final Dialog dialog=new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_watting);
                dialog.show();
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            if login success
                            if (jsonObject.names().get(0).equals("success")) {
//                                declare dialog

                                Toast.makeText(mContext, "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                                if (cb_Remember.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", edit_User.getText().toString().trim());
                                    editor.putString("password", edit_Password.getText().toString().trim());
                                    editor.putBoolean("checked", true);
                                    editor.commit();
                                } else {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("username");
                                    editor.remove("password");
                                    editor.remove("checked");
                                    editor.commit();
                                }
                            } else {
                                dialog.dismiss();
                                Toast.makeText(mContext,"Đăng Nhập Thất Bại",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            dialog.dismiss();
                            Toast.makeText(mContext,"Đăng Nhập Thất Bại",Toast.LENGTH_LONG).show();
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
                        hashMap.put("email", edit_User.getText().toString());
                        hashMap.put("password", edit_Password.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue = Volley.newRequestQueue(mContext);
                requestQueue.add(request);
            }
        });

    }
}
