package com.example.vanphu.mymoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.vanphu.mymoney.controller.MoneyInController;

import java.util.ArrayList;
import java.util.Arrays;

public class PayonActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static Button sBtn_Success;
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_Money;
    private ImageView mImg_Money;
    public static int sMIdImage = -1;
    public static String sKeyMoney = "$";
    public static String sUser="";
    private MoneyInController mMoneyInController;
    private String mURL = "http://192.168.149.2/chitieu/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.payon_activity);
//        call init
        init();
        mImg_Money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PayonActivity.this, SelectorMoneyActivity.class), 100);
            }
        });
        mMoneyInController = new MoneyInController(this);
        mMoneyInController.moneyIn(mURL);
        getIntentdata();
    }
    public void getIntentdata(){
        Intent intent=getIntent();
        sUser=intent.getStringExtra("User");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            sMIdImage = Integer.parseInt(data.getStringExtra("idImage"));
            sKeyMoney = data.getStringExtra("keyMoney");
            String[] arrayAvatar = getResources().getStringArray(R.array.list_image_flag);
            ArrayList<String> mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
            int idImage = getResources().getIdentifier(mArrayImage.get(sMIdImage), "drawable", getPackageName());
            mImg_Money.setImageResource(idImage);
        }
    }

    //    Declare
    public void init() {
        sBtn_Success = findViewById(R.id.btn_Success);
        sEdit_Money = findViewById(R.id.edit_Money);
        mImg_Money = findViewById(R.id.img_Money);

    }
}
