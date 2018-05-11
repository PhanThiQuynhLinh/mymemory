package com.example.vanphu.mymoney;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button btnSkip;
    private Button btnNext;
    SharedPreferences sharedPreferences;
    SharedPreferences mSharedPreferences_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.row_nitro_item);
        ImageView img_Money = dialog.findViewById(R.id.img_Money);
        Init();
        dialog.show();
        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE);
//        String mUser = sharedPreferences.getString("username", "");
        mSharedPreferences_item_1 = getSharedPreferences("datalide", MODE_PRIVATE);
        String slide = mSharedPreferences_item_1.getString("slide", "");

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmim_translate);
        if (slide.equals("")) {
            Toast.makeText(WelcomeActivity.this, "Welcome", Toast.LENGTH_LONG).show();
        } else if (!slide.equals("")) {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        SharedPreferences.Editor editor = mSharedPreferences_item_1.edit();
        editor.putString("slide", "slide");
        editor.apply();

//        set animation

        img_Money.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                declare sharedpreferences
                dialog.dismiss();
//
            }
        }, 4500);


//        if(mUser.equals("")){
//            Init();
//        }else{
//            Intent intent=new Intent(WelcomeActivity.this,SpendActivity.class);
//            intent.putExtra("User", mUser);
//            startActivity(intent);
//        }
//        list layout slides
        layouts = new int[]{
                R.layout.item_slide_1,
                R.layout.item_slide_2,
                R.layout.item_slide_3
        };
//        add login
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
        // adding bottom dots
        addBottomDots(0);

        ViewPagerAdapter viewPagerAdapter;
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    public void Init() {
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);
//        btnLogin = findViewById(R.id.btnLogin);
//        btnRegistered = (Button) findViewById(R.id.btnRegistered);
    }

    boolean twice = false;

    @Override
    public void onBackPressed() {
        if (twice) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
//        delay 3000 milis changer twice
        twice = true;
        Toast.makeText(WelcomeActivity.this, "Nhấn Thêm Lần Nữa Để Thoát", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 3000);
    }

    public void btnSkipClick(View v) {
        launchHomeScreen();
    }

    public void btnNextClick(View v) {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem();
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        TextView[] dots;
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
    }

    private int getItem() {
        return viewPager.getCurrentItem() + 1;
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        ViewPagerAdapter() {

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert layoutInflater != null;
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
