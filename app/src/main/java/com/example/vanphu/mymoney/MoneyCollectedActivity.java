package com.example.vanphu.mymoney;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vanphu.mymoney.controller.AddSpendController;
import com.example.vanphu.mymoney.controller.SpendController;

public class MoneyCollectedActivity extends AppCompatActivity {
    private SpendController mSpendController;
    private ListView mLvMoneyCollected;
    private String mURL = "http://192.168.149.2/chitieu/getdatatienthu.php?email=";
    private String mURL_item_1 = "http://192.168.149.2/chitieu/inserttienthu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneycollected);
        setTitle("");
        init();
    }

    public void init() {
        mSpendController = new SpendController(this);
        mLvMoneyCollected = findViewById(R.id.lvMoneyCollected);
        mSpendController.addImage(mLvMoneyCollected);
        mSpendController.readJsonSpend(mURL + SpendActivity.mUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_money_collected, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_money_collected) {
            dialog();
            init();
        }else if(id==R.id.action_finished){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.row_money_collected);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextInputEditText edit_TitleSpend = dialog.findViewById(R.id.edit_TitleSpend);
        final TextInputEditText edit_MoneySpend = dialog.findViewById(R.id.edit_MoneySpend);
        Button btn_Add = dialog.findViewById(R.id.btn_Add);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer.parseInt(edit_MoneySpend.getText().toString().trim());
                    mSpendController.addmoneycollected(mURL_item_1, edit_TitleSpend.getText().toString().trim(), Integer.parseInt(edit_MoneySpend.getText().toString().trim()));
                    dialog.dismiss();
                    init();
                } catch (Exception e) {
                    Toast.makeText(MoneyCollectedActivity.this, "Vui Lòng Nhập Số", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
        init();
    }
}
