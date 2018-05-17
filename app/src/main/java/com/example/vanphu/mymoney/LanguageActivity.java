package com.example.vanphu.mymoney;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private ListView lv_language;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
        String language = sp.getString("key_language", "");
        if(language.equals("")){
            language = "en";
        }
        setLanguage(language);
        setContentView(R.layout.activity_language);
        setTitle("");
        init();
        lv_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(i==0){
                    setLanguage("vn");
                }else if(i==1){
                    setLanguage("en");
                }else if(i==2){
                    setLanguage("ja");
                }else if(i==3){
                    setLanguage("zh");
                }
            }
        });
    }

    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_language", language);
        editor.commit();
        finish();
    }
    public void init(){
        lv_language=findViewById(R.id.lv_language);
        arrayList=new ArrayList<>();
        arrayList.add("Tieng Viet");
        arrayList.add("English");
        arrayList.add("Japan");
        arrayList.add("China");
        arrayList.add("Korean");
        ArrayAdapter adapter=new ArrayAdapter(LanguageActivity.this,android.R.layout.simple_list_item_1,arrayList);
        lv_language.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pass) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
