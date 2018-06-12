//package com.jriemer.beermemobile;
//
//import android.support.design.widget.AppBarLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TableLayout;
//
//public class test extends AppCompatActivity {
//
//    private TableLayout tablayout;
//    private AppBarLayout appBarLayout;
//    private ViewPager viewPager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        tablayout = findViewById(R.id.tablayout_id);
//        appBarLayout = findViewById(R.id.appbarid);
//
//
//
//        viewPager = findViewById(R.id.viewpager_id);
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//
////        Add Fragments
//
//        adapter.AddFragment(new FragmentExplore(), "Explore");
//        adapter.AddFragment(new FragmentQuiz(), "Quiz");
//        adapter.AddFragment(new FragmentTest(), "Test");
//
////        Adapter Set up
//
//        viewPager.setAdapter(adapter);
//        tablayout.setupWithViewPager(viewPager);
//
//
//
//    }
//}
