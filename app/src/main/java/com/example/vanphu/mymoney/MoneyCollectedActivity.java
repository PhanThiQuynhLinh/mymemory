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
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanphu.mymoney.controller.AddSpendController;
import com.example.vanphu.mymoney.controller.SpendController;

public class MoneyCollectedActivity extends AppCompatActivity {
    private ListView mLvMoneyCollected;
    private String mURL = "http://192.168.149.2/chitieu/getdatatienthu.php?email=";
    private String mURL_item_1 = "http://192.168.149.2/chitieu/inserttienthu.php";
    private String mUrl_item_2="http://192.168.149.2/chitieu/tinhngaytienthu.php?email=";
    private String mUrl_item_3="http://192.168.149.2/chitieu/maxtienthu.php?email=";
    public static TextView txt_MoneyMax;
    public static TextView sTxt_Day;
    public static TextView sTxt_Date;
    public static TextView sTxt_Month;
    public static TextView sTxt_Year;
    public static TextView sTxt_SumSpendMoney;
    private SpendController mSpendController;
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
        sTxt_Day=findViewById(R.id.txt_Day);
        sTxt_Date=findViewById(R.id.txt_Date);
        sTxt_Month=findViewById(R.id.txt_Month);
        sTxt_Year=findViewById(R.id.txt_Year);
        txt_MoneyMax=findViewById(R.id.txt_MoneyMax);
        sTxt_SumSpendMoney=findViewById(R.id.txt_SumSpendMoney);
        mSpendController=new SpendController(MoneyCollectedActivity.this);
        mSpendController.ReadJsonDateCollected(mUrl_item_2+SpendActivity.mUser);
        mSpendController.readJsonMoneyCollected(mUrl_item_3+SpendActivity.mUser);
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
