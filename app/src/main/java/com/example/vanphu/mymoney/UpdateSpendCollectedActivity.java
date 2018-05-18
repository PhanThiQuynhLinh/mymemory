package com.example.vanphu.mymoney;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vanphu.mymoney.controller.UpdateSpendController;

public class UpdateSpendCollectedActivity extends AppCompatActivity {
    public static TextInputEditText sEdit_TitleSpend;
    public static TextInputEditText sEdit_MoneySpend;
    Button btn_Update;
    Button btn_Delete;
    public static int sId;
    public static String mTitle;
    public static String mMoney;
    private String mUrl="https://vanphudhsp2015.000webhostapp.com/updatetienthu.php";
    private String mUrl_item_1="https://vanphudhsp2015.000webhostapp.com/deletetienthu.php";
    private UpdateSpendController mUpdateSpendController;
    private TextView mTxt_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_spend_collected);
        init();
        getIntentData();
        updateSpend();
        deleteSpend();
    }
    public void updateSpend(){

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateSpendController.UpdateSpendCollected(mUrl);
                Intent intent=new Intent(UpdateSpendCollectedActivity.this,SpendActivity.class);
                intent.putExtra("User",SpendActivity.mUser);
                startActivity(intent);
            }
        });
    }
    public void deleteSpend(){
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateSpendController.deleteSpendCollected(mUrl_item_1);
                Intent intent=new Intent(UpdateSpendCollectedActivity.this,SpendActivity.class);
                intent.putExtra("User",SpendActivity.mUser);
                startActivity(intent);
            }
        });
    }
    public void getIntentData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("Title");
        mMoney = intent.getStringExtra("Money");
        sId = Integer.parseInt(intent.getStringExtra("id"));

        sEdit_TitleSpend.setText(mTitle);
        sEdit_MoneySpend.setText(mMoney);
        mTxt_time.setText(intent.getStringExtra("Date"));
    }

    public void init() {
        mUpdateSpendController=new UpdateSpendController(this);
        sEdit_TitleSpend = findViewById(R.id.edit_TitleSpend);
        sEdit_MoneySpend = findViewById(R.id.edit_MoneySpend);
        btn_Update=findViewById(R.id.btn_Update);
        btn_Delete=findViewById(R.id.btn_Delete);
        mTxt_time=findViewById(R.id.txt_time);
    }
    public void btnSkipClick(View view) {
        finish();
    }
}
