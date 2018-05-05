package com.example.vanphu.mymoney.Tab;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.vanphu.mymoney.Controller.RegisteredController;
import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SelectorImageActivity;

import java.util.ArrayList;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

public class Tab_Registered extends Fragment {
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_User;
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_Password;
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_Name;
    @SuppressLint("StaticFieldLeak")
    public static ImageView sImg_Avatar;
    RegisteredController registeredController;
    @SuppressLint("StaticFieldLeak")
    public static Button sBtn_Registered;
    String URL = "https://vanphudhsp2015.000webhostapp.com/login.php";
    public static int sMIdImage = -1;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStat) {
        View rootView = inflater.inflate(R.layout.tab_registered, container, false);
        init(rootView);
        registeredController = new RegisteredController(inflater.getContext());
        registeredController.registered(URL);
        sImg_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(inflater.getContext(), SelectorImageActivity.class), 100);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            sMIdImage = Integer.parseInt(data.getStringExtra("idImage"));
            String[] arrayAvatar = getResources().getStringArray(R.array.list_image_avatar);
            ArrayList<String> mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
            int idImage = getResources().getIdentifier(mArrayImage.get(sMIdImage), "drawable", getContext().getPackageName());
            sImg_Avatar.setImageResource(idImage);
        }
    }

    public void init(View rootView) {
        sEdit_User = rootView.findViewById(R.id.edit_User);
        sEdit_Password = rootView.findViewById(R.id.edit_Password);
        sEdit_Name = rootView.findViewById(R.id.edit_Name);
        sImg_Avatar = rootView.findViewById(R.id.img_Avatar);
        sBtn_Registered = rootView.findViewById(R.id.btn_Registered);
    }
}
