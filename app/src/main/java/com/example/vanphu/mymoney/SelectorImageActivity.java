package com.example.vanphu.mymoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.example.vanphu.mymoney.controller.ImageController;

public class SelectorImageActivity extends AppCompatActivity {
    private GridView mGrid_Avatar;
    private ImageController mImageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.selectorimg_activity);
        init();
        // add gridview
        mImageController.addImage(mGrid_Avatar);
    }

    public void init() {
        mGrid_Avatar = findViewById(R.id.grid_Avatar);
        mImageController = new ImageController(this);
    }

    public void btnSkipClick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
