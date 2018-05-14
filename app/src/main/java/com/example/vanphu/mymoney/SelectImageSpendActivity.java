package com.example.vanphu.mymoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.vanphu.mymoney.controller.ImageController;
import com.example.vanphu.mymoney.controller.ImageSpendController;

public class SelectImageSpendActivity extends AppCompatActivity {
    private GridView mGrid_Avatar;
    private ImageSpendController mImageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_image_spend);
        init();
        // add gridview
        mImageController.addImage(mGrid_Avatar);
    }
    public void init() {
        mGrid_Avatar = findViewById(R.id.grid_Avatar);
        mImageController = new ImageSpendController(this);
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
