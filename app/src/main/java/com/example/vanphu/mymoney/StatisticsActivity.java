package com.example.vanphu.mymoney;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vanphu.mymoney.controller.SpendController;
import com.example.vanphu.mymoney.tab.Tab_Day;
import com.example.vanphu.mymoney.tab.Tab_Month;
import com.example.vanphu.mymoney.tab.Tab_Month_To_Month;
import com.example.vanphu.mymoney.tab.Tab_Week;
import com.example.vanphu.mymoney.tab.Tab_Week_item;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    ArrayList<PieEntry> yValues = new ArrayList<>();
    PieDataSet dataset;
    private String mUrl1="http://192.168.149.2/chitieu/getngayhientai.php?email=";
    private int mSum=0;
    String keyMoney="đ";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pass) {
            finish();
            return true;
        } else if (id == R.id.action_day_now) {

            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.tab_day_now);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            final PieChart pieChart;
            PieDataSet dataset;
            pieChart = (PieChart) dialog.findViewById(R.id.pieChart);
            pieChart.setData(null);
            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5, 10, 5, 5);

            pieChart.setDragDecelerationFrictionCoef(0.95f);

            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setTransparentCircleRadius(31f);
            ReadJson1(mUrl1+SpendActivity.mUser,pieChart);
            Button btn_turnoff = dialog.findViewById(R.id.btn_turnoff);
            btn_turnoff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   dialog.dismiss();
                    yValues.clear();
                }
            });
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
    public void ReadJson1(String url, final PieChart pieChart){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            try{
                                JSONObject object = null;
                                object=response.getJSONObject(i);
                                int tien=Integer.parseInt(object.getString("giachitieu"));
                                String tenchitieu=object.getString("tenchitieu");
                                yValues.add(new PieEntry(tien,tenchitieu));
                                pieChart.animateY(1000, Easing.EasingOption.EaseInCubic);
                                mSum+=tien;
                                dataset=new PieDataSet(yValues,"Dữ Liệu");
                                dataset.setSliceSpace(3f);
                                dataset.setSelectionShift(5f);
                                dataset.setColors(ColorTemplate.MATERIAL_COLORS);

                                PieData data=new PieData((dataset));
                                data.setValueTextSize(20f);
                                data.setValueTextColor(Color.RED);
                                data.setValueFormatter(new PercentFormatter());

                                pieChart.setData(data);
                                pieChart.setOnChartValueSelectedListener(StatisticsActivity.this);
                            }catch (Exception e){
                                Toast.makeText(StatisticsActivity.this,"Lỗi"+e,Toast.LENGTH_LONG).show();
                            }
                        }
                        pieChart.setCenterText("Tổng chi tiêu là: "+mSum);
                        pieChart.setCenterTextSize(20f);
                        pieChart.setCenterTextColor(Color.GRAY);
                        pieChart.setNoDataTextColor(Color.BLUE);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StatisticsActivity.this,"Lỗi "+error.toString(),Toast.LENGTH_LONG).show();
//                        error();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        try{
            if (e == null)
                return;
            int intSelectedPos = (int) h.getX();
            if (intSelectedPos >= 0) {
                for (int i = 0; i < dataset.getColors().size(); i++) {

                }
                if (intSelectedPos == (int) h.getX()) {

                }
            }
            Toast.makeText(StatisticsActivity.this, "Giá Chi Tiêu: " + e.getY()+" "+ SpendController.sKeyMoney,Toast.LENGTH_LONG).show();
        }catch (Exception e1){
            Toast.makeText(StatisticsActivity.this,"Lỗi"+e.toString().trim(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new Tab_Month();
                case 1:
                    return new Tab_Month_To_Month();
                case 2:
                    return new Tab_Week();
                case 3:
                    return new Tab_Week_item();
                case 4:
                    return new Tab_Day();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 5;
        }
    }
}
