package com.example.vanphu.mymoney.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SpendActivity;
import com.example.vanphu.mymoney.controller.StatisticsController;

/**
 * Created by VanPhu on 5/18/2018.
 */

public class Tab_Date extends Fragment {
    private Button btnDay;
    private Button btnDay_item1_2;
    private Button btnMonth;
    private Button btnMonth_item1_2;
    private ListView mLv_statis;
    private StatisticsController mStatisticsController;
    private String mUrl = "https://vanphudhsp2015.000webhostapp.com/getcacngay.php?";
    private String date;
    private String date_item_1;
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_date, container, false);
        init(rootView);
        date="2018-"+btnMonth.getText().toString().trim()+"-"+btnDay.getText().toString().trim();
        date_item_1="2018-"+btnMonth_item1_2.getText().toString().trim()+"-"+btnDay_item1_2.getText().toString().trim();
        mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&date1=" +date+"&date2="+date_item_1);
        mStatisticsController.addImage(mLv_statis);
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(btnMonth);
            }
        });
        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu1(btnDay);
            }
        });
        btnDay_item1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu1(btnDay_item1_2);
            }
        });
        btnMonth_item1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(btnMonth_item1_2);
            }
        });
        return rootView;
    }
    private void ShowMenu(final Button btnMonth_item1) {
        PopupMenu popupMenu = new PopupMenu(getLayoutInflater().getContext(), btnMonth_item1);

        for (int i = 1; i <= 12; i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, String.valueOf(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                btnMonth_item1.setText(item.getTitle());
                date="2018-"+btnMonth.getText().toString().trim()+"-"+btnDay.getText().toString().trim();
                date_item_1="2018-"+btnMonth_item1_2.getText().toString().trim()+"-"+btnDay_item1_2.getText().toString().trim();
//                Toast.makeText(getLayoutInflater().getContext(),date,Toast.LENGTH_LONG).show();
//                Toast.makeText(getLayoutInflater().getContext(),date_item_1,Toast.LENGTH_LONG).show();
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&date1=" +date+"&date2="+date_item_1);                mStatisticsController.addImage(mLv_statis);
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });
        popupMenu.show();

    }
    private void ShowMenu1(final Button btnMonth_item1) {
        PopupMenu popupMenu = new PopupMenu(getLayoutInflater().getContext(), btnMonth_item1);

        for (int i = 1; i <= 31; i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, String.valueOf(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                btnMonth_item1.setText(item.getTitle());
                date="2018-"+btnMonth.getText().toString().trim()+"-"+btnDay.getText().toString().trim();
                date_item_1="2018-"+btnMonth_item1_2.getText().toString().trim()+"-"+btnDay_item1_2.getText().toString().trim();
//                Toast.makeText(getLayoutInflater().getContext(),date,Toast.LENGTH_LONG).show();
//                Toast.makeText(getLayoutInflater().getContext(),date_item_1,Toast.LENGTH_LONG).show();
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&date1=" +date+"&date2="+date_item_1);                mStatisticsController.addImage(mLv_statis);
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });
        popupMenu.show();

    }
    public void init(View rootView) {
        btnDay = rootView.findViewById(R.id.btnDay);
        btnMonth = rootView.findViewById(R.id.btnMonth);
        btnDay_item1_2 = rootView.findViewById(R.id.btnDay_item1);
        btnMonth_item1_2 = rootView.findViewById(R.id.btnMonth_item1);
        mLv_statis = rootView.findViewById(R.id.lv_statis);
        mStatisticsController = new StatisticsController(getContext());
    }
}
