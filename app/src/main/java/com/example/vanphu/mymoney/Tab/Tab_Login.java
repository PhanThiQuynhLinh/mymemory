package com.example.vanphu.mymoney.Tab;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.Controller.LoginController;
import com.example.vanphu.mymoney.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tab_Login extends Fragment {
    public static TextInputEditText edit_User;
    public static  TextInputEditText edit_Password;
    public static  CheckBox cb_Remember;
    TextView txt_Registered;
    Button btn_Login;
    LoginController loginController;
    String URL="https://vanphudhsp2015.000webhostapp.com/user_control.php";
    public RequestQueue requestQueue;
    public StringRequest request;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_login, container, false);
//        call function init
        init(rootView);
//        call class login
        loginController=new LoginController(inflater.getContext());
        loginController.login(URL,cb_Remember,edit_User,edit_Password,btn_Login);
        return rootView;
    }

    //    Declare
    public void init(View rootView) {
        edit_User = rootView.findViewById(R.id.edit_User);
        edit_Password = rootView.findViewById(R.id.edit_Password);
        cb_Remember = rootView.findViewById(R.id.cb_Remember);
        txt_Registered = rootView.findViewById(R.id.txt_Registered);
        btn_Login = rootView.findViewById(R.id.btn_Login);
    }

}
