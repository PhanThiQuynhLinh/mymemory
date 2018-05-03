package com.example.vanphu.mymoney.Tab;

/**
 * Created by VanPhu on 5/3/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vanphu.mymoney.R;

public class Tab_Login extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_login, container, false);
        return rootView;
    }
}
