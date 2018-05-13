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
import android.widget.Spinner;

import com.example.vanphu.mymoney.R;
import com.example.vanphu.mymoney.SpendActivity;
import com.example.vanphu.mymoney.controller.StatisticsController;


public class Tab_Month extends Fragment {
    Spinner spinner;
    Button btnMonth;
    private ListView mLv_statis;
    private StatisticsController mStatisticsController;
    String URL = "http://192.168.149.2/chitieu/getchitieumonth.php?";

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_month, container, false);
        init(rootView);


        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu(btnMonth);
            }
        });
        mStatisticsController = new StatisticsController(inflater.getContext());

        return rootView;
    }

//    public void ReadJson1(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getLayoutInflater().getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                pieChart.setData(null);
//                                dataset = new PieDataSet(null, "Dữ Liệu");
//                                JSONObject object = response.getJSONObject(i);
//                                int tien = Integer.parseInt(object.getString("giachitieu"));
//                                String tenchitieu = object.getString("tenchitieu");
//                                yValues.add(new PieEntry(tien, tenchitieu));
//                                pieChart.animateY(1000, Easing.EasingOption.EaseInCubic);
//                                sum += tien;
//                                dataset = new PieDataSet(yValues, "Dữ Liệu");
//                                dataset.setSliceSpace(3f);
//                                dataset.setSelectionShift(5f);
//                                dataset.setColors(ColorTemplate.MATERIAL_COLORS);
//
//                                PieData data = new PieData((dataset));
//                                data.setValueTextSize(15f);
//                                data.setValueTextColor(Color.RED);
//                                data.setValueFormatter(new PercentFormatter());
//                                pieChart.setData(data);
//                                pieChart.setOnChartValueSelectedListener(Tab_Month.this);
//                            } catch (Exception e) {
//                                Toast.makeText(getLayoutInflater().getContext(), "Lỗi" + e, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                        pieChart.setCenterText("Tổng chi tiêu là: " + sum);
//                        pieChart.setCenterTextSize(15f);
//                        pieChart.setCenterTextColor(Color.GRAY);
//                        pieChart.setNoDataTextColor(Color.BLUE);
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getLayoutInflater().getContext(), "Lỗi 123" + error.toString(), Toast.LENGTH_LONG).show();
////                        error();
//                    }
//                }
//        );
//        requestQueue.add(jsonArrayRequest);
//    }

    private void ShowMenu(final Button btnMonth_item1) {
        PopupMenu popupMenu = new PopupMenu(getLayoutInflater().getContext(), btnMonth_item1);

        for (int i = 1; i <= 12; i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, String.valueOf(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                btnMonth.setText(item.getTitle());
                mStatisticsController.readJsonSpend(URL + "email=" + SpendActivity.mUser + "&thang=" + btnMonth.getText().toString().trim());
                mStatisticsController.addImage(mLv_statis);
                return false;
            }
        });
        popupMenu.show();
    }

    public void init(View rootView) {
        btnMonth = rootView.findViewById(R.id.btnMonth);
        mLv_statis = rootView.findViewById(R.id.lv_statis);
    }


}
