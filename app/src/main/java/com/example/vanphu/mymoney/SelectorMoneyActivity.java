package com.example.vanphu.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.vanphu.mymoney.Controller.MoneyController;

public class SelectorMoneyActivity extends AppCompatActivity {
    GridView grid_Avatar;
    Button btn_Previous;
    MoneyController moneyController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_selector_money);
//        call init
        init();
//        call money
        moneyController=new MoneyController(this);
        moneyController.addImage(grid_Avatar);
    }
//    Declare
    private void init(){
        grid_Avatar=findViewById(R.id.grid_Avatar);
        btn_Previous=findViewById(R.id.btn_Previous);
    }

    public void btnSkipClick(View view) {
        finish();
    }
}
