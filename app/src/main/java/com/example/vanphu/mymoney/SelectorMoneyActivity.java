package com.example.vanphu.mymoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.example.vanphu.mymoney.controller.MoneyController;

public class SelectorMoneyActivity extends AppCompatActivity {
    private GridView mGrid_Avatar;

    MoneyController moneyController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_selector_money);
//        call init
        init();
//        call money
        moneyController = new MoneyController(this);
        moneyController.addImage(mGrid_Avatar);
    }

    //    Declare
    private void init() {
        mGrid_Avatar = findViewById(R.id.grid_Avatar);

    }

    public void btnSkipClick(View view) {
        finish();
    }
}
