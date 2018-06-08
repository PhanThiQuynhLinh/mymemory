package com.example.vanphu.mymoney;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vanphu.mymoney.controller.AddSpendController;
import com.example.vanphu.mymoney.controller.SpendController;
import com.example.vanphu.mymoney.controller.TranfersController;

public class TranfersActivity extends AppCompatActivity {
    public static TextInputEditText sEdit_User;
    public static TextInputEditText sEdit_money_item1;
    public static TextInputEditText sEdit_money;
    public static Button sBtn_tranfers;
    private TranfersController tranfersController;
    private String mUrl = "https://vanphudhsp2015.000webhostapp.com/chuyentien.php";
    private String mUrl_item = "https://vanphudhsp2015.000webhostapp.com/inserttienthu.php";
    private String mUrl_item_2 = "https://vanphudhsp2015.000webhostapp.com/insertchitieu.php";
    private SpendController mSpendController;
    private AddSpendController mAddSpendController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfers);
        setTitle("");
        init();
        sBtn_tranfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer.parseInt(sEdit_money.getText().toString().trim());
                    tranfersController.TranfersMoney(mUrl);
                    mAddSpendController.AddSpendTranfer(mUrl_item_2,SpendActivity.mUser,Integer.parseInt(sEdit_money.getText().toString()));
                    mSpendController.UpdateTranfer(mUrl_item, "Bạn được chuyển tiền", Integer.parseInt(sEdit_money.getText().toString().trim()),sEdit_money_item1.getText().toString().trim());
                    finish();
                } catch (Exception e) {
                    Toast.makeText(TranfersActivity.this, "Vui Lòng Nhập Tiền Bằng Số", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pass) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void init() {
        sEdit_User = findViewById(R.id.edit_User);
        sEdit_User.setText(SpendActivity.mUser);
        sEdit_money_item1 = findViewById(R.id.edit_money_item1);
        sEdit_money = findViewById(R.id.edit_money);
        sBtn_tranfers = findViewById(R.id.btn_tranfers);
        tranfersController = new TranfersController(this);
        mSpendController = new SpendController(this);
        mAddSpendController=new AddSpendController(this);
    }
}
