package com.example.vanphu.mymoney.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SpendActivity;
import com.example.vanphu.mymoney.controller.StatisticsController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanPhu on 5/13/2018.
 */

public class Tab_Week extends Fragment {
    private Button mBtnWeek_item_1;
    private Button mBtnWeek_item_2;
    private ListView mLv_statis;
    private StatisticsController mStatisticsController;
    private String mUrl="https://vanphudhsp2015.000webhostapp.com/getcactuan.php?";
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_week, container, false);
        init(rootView);
        mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&week1=" + mBtnWeek_item_1.getText().toString().trim()+"&week2="+mBtnWeek_item_2.getText().toString().trim());
        mStatisticsController.addImage(mLv_statis);
        mBtnWeek_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(mBtnWeek_item_1);

            }
        });
        mBtnWeek_item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(mBtnWeek_item_2);
            }
        });
        return rootView;
    }
    private void ShowMenu(final Button btnMonth_item1) {
        PopupMenu popupMenu = new PopupMenu(getLayoutInflater().getContext(), btnMonth_item1);
        for (int i = 1; i <= 52; i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, String.valueOf(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                btnMonth_item1.setText(item.getTitle());
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&week1=" + mBtnWeek_item_1.getText().toString().trim()+"&week2="+mBtnWeek_item_2.getText().toString().trim());
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });

        popupMenu.show();
    }
    public void init(View rootView){
        mBtnWeek_item_1=rootView.findViewById(R.id.btnWeek_item_1);
        mBtnWeek_item_2=rootView.findViewById(R.id.btnWeek_item_2);
        mLv_statis=rootView.findViewById(R.id.lv_statis);
        mStatisticsController=new StatisticsController(getContext());
    }
}
