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

import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SpendActivity;
import com.example.vanphu.mymoney.controller.StatisticsController;

/**
 * Created by VanPhu on 5/15/2018.
 */

public class Tab_Day_Total extends Fragment {
    private Button btnDay;
    private Button btnMonth;
    private ListView mLv_statis;
    private StatisticsController mStatisticsController;
    private String mUrl = "https://vanphudhsp2015.000webhostapp.com/getngaytienthu.php?";

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_day, container, false);
        init(rootView);
        mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&ngay=" + btnDay.getText().toString().trim()+"&thang="+btnMonth.getText().toString().trim());
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
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&ngay=" + btnDay.getText().toString().trim()+"&thang="+btnMonth.getText().toString().trim());
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
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&ngay=" + btnDay.getText().toString().trim()+"&thang="+btnMonth.getText().toString().trim());
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });
        popupMenu.show();

    }
    public void init(View rootView) {
        btnDay = rootView.findViewById(R.id.btnDay);
        btnMonth = rootView.findViewById(R.id.btnMonth);
        mLv_statis = rootView.findViewById(R.id.lv_statis);
        mStatisticsController = new StatisticsController(getContext());
    }
}
