package com.example.vanphu.mymoney.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanphu.mymoney.R;

import java.util.ArrayList;

/**
 * Created by VanPhu on 5/13/2018.
 */

public class Tab_Week extends Fragment {
    Spinner spinner;
    Spinner spinner_item_1;
    public static String sWeek_item_1="";
    public static String sWeek_item_2="";
    Button btn_statistics;
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_week, container, false);
        init(rootView);
        ArrayAdapter<String> adapter = null;
        adapter=new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.week));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner_item_1.setAdapter(adapter);
        btn_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inflater.getContext(),spinner.getSelectedItem().toString().trim()+"-"+spinner_item_1.getSelectedItem().toString().trim(),Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }
    public void init(View rootView){
        spinner=rootView.findViewById(R.id.spinner);
        spinner_item_1=rootView.findViewById(R.id.spinner_item_1);
        btn_statistics=rootView.findViewById(R.id.btn_statistics);
    }
}
