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

public class Tab_Month_to_Month_Total extends Fragment {
    private String mMonth_item1 = "1";
    private String mMonth_item2 = "6";
    private ListView mLv_statis;
    private Button mBtnMonth_item1, mBtnMonth_item2, btn;
    private StatisticsController mStatisticsController;
    private String mUrl = "http://192.168.149.2/chitieu/getchitieutienthucacthang.php?";

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_month_to_month, container, false);
//        call Init
        init(rootView);
//        call class login
        mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&thang1=" + mBtnMonth_item1.getText().toString().trim() + "&thang2=" + mBtnMonth_item2.getText().toString().trim());
        mStatisticsController.addImage(mLv_statis);
        mBtnMonth_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(mBtnMonth_item1);

            }
        });
        mBtnMonth_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(mBtnMonth_item2);
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
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&thang1=" + mBtnMonth_item1.getText().toString().trim() + "&thang2=" + mBtnMonth_item2.getText().toString().trim());
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });

        popupMenu.show();
    }

    public void init(View rootView) {
        mBtnMonth_item1 = rootView.findViewById(R.id.btnMonth_item1);
        mBtnMonth_item2 = rootView.findViewById(R.id.btnMonth_item2);
        mLv_statis = rootView.findViewById(R.id.lv_statis);
        mStatisticsController = new StatisticsController(getContext());

    }
}
