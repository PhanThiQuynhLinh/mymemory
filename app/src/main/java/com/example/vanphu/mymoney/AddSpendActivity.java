package com.example.vanphu.mymoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vanphu.mymoney.controller.AddSpendController;

import java.util.ArrayList;
import java.util.Arrays;

public class AddSpendActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static ImageView sImg_Spend;
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_TitleSpend;
    @SuppressLint("StaticFieldLeak")
    public static TextInputEditText sEdit_MoneySpend;
    @SuppressLint("StaticFieldLeak")
    public static Button sBtn_Add;
    public static int sIdImage = -1;
    private AddSpendController mAddSpendController;
    private String mUrl = "https://vanphudhsp2015.000webhostapp.com/insertchitieu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.addspend_activity);
        init();
        sBtn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sEdit_MoneySpend.getText().toString().equals("") || sEdit_TitleSpend.getText().toString().trim().equals("")) {
                    Toast.makeText(AddSpendActivity.this, getResources().getString(R.string.pleasefull), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Integer.parseInt(sEdit_MoneySpend.getText().toString().trim());
                        if (sIdImage == -1) {
                            Toast.makeText(AddSpendActivity.this, "Vui Lòng Chọn Hình Ảnh Chi Tiêu", Toast.LENGTH_LONG).show();
                        } else {
                            mAddSpendController = new AddSpendController(AddSpendActivity.this);
                            mAddSpendController.AddSpend(mUrl);
                            finish();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AddSpendActivity.this, "Vui Lòng Nhập Tiền Bằng Số", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }

    public void init() {
        sImg_Spend = findViewById(R.id.img_Spend);
        sEdit_TitleSpend = findViewById(R.id.edit_TitleSpend);
        sEdit_MoneySpend = findViewById(R.id.edit_MoneySpend);
        sBtn_Add = findViewById(R.id.btn_Add);
    }

    public void btnSkipClick(View view) {
        finish();
    }

    public void btnSelectorImage(View view) {
        startActivityForResult(new Intent(AddSpendActivity.this, SelectImageSpendActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            sIdImage = Integer.parseInt(data.getStringExtra("idImage"));
            sEdit_TitleSpend.setText(data.getStringExtra("nameImage"));
            String[] arrayAvatar = getResources().getStringArray(R.array.list_image_spend);
            ArrayList<String> mArrayImage = new ArrayList<>(Arrays.asList(arrayAvatar));
            int idImage = getResources().getIdentifier(mArrayImage.get(sIdImage), "drawable", getPackageName());
            sImg_Spend.setImageResource(idImage);
        }
    }
}
