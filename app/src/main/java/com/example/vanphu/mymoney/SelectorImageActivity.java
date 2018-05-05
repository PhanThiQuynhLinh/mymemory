package com.example.vanphu.mymoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.vanphu.mymoney.Controller.ImageController;

public class SelectorImageActivity extends AppCompatActivity {
    GridView grid_Avatar;
    ImageController imageController;
    Button btn_Previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.selectorimg_activity);
        init();
        // add gridview
        imageController.addImage(grid_Avatar);
    }

    public void init() {
        grid_Avatar = findViewById(R.id.grid_Avatar);
        btn_Previous = findViewById(R.id.btn_Previous);
        imageController = new ImageController(this);
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
