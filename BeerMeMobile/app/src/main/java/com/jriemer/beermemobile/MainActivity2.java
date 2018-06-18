package com.jriemer.beermemobile;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity2 extends AppCompatActivity {

    public RelativeLayout beersearch;
    public RelativeLayout brewerysearch;
    public RelativeLayout randomsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        beersearch = findViewById(R.id.beer_search);
        beersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeerPage();
            }
        });

        brewerysearch = findViewById(R.id.brewery_search);
        brewerysearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreweryPage();
            }
        });

//        randomsearch = findViewById(R.id.random_brewery);
//        randomsearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openRandom();
//            }
//        });

    }

    public void openBeerPage() {
        Intent intent = new Intent(this, BeerMain.class);
        startActivity(intent);
    }

    public void openBreweryPage() {
        Intent intent = new Intent(this, BreweryMain.class);
        startActivity(intent);
    }

//    public void openRandom() {
//        Intent intent = new Intent(this, Random_Brewery.class);
//        startActivity(intent);
//    }
}
