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

public class Tab_Week_item_Total  extends Fragment {
    private Button mBtnWeek_item_1;
    private ListView mLv_statis;
    private StatisticsController mStatisticsController;
    private String mUrl="http://192.168.149.2/chitieu/gettienthutuan.php?";
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_week_item, container, false);
        init(rootView);
        mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&week=" + mBtnWeek_item_1.getText().toString().trim());
        mStatisticsController.addImage(mLv_statis);
        mBtnWeek_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(mBtnWeek_item_1);

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
                mStatisticsController.readJsonSpend(mUrl + "email=" + SpendActivity.mUser + "&week=" + mBtnWeek_item_1.getText().toString().trim());
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });

        popupMenu.show();
    }
    public void init(View rootView){
        mBtnWeek_item_1=rootView.findViewById(R.id.btnWeek_item_1);
        mLv_statis=rootView.findViewById(R.id.lv_statis);
        mStatisticsController=new StatisticsController(getContext());
    }
}
