package com.jriemer.beermemobile;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BreweryMain extends AppCompatActivity {


    private List<Brewery> lstBrewery;
    private RecyclerView myrv;
    private BreweryAdapter breweryAdapter;
    private ImageView toolbarImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        lstBrewery = new ArrayList<>();
        breweryAdapter = new BreweryAdapter(this, lstBrewery);

        myrv = findViewById(R.id.contentMain);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(breweryAdapter);

        toolbarImg = findViewById(R.id.backdrop);
        Picasso.get().load(R.drawable.breweriescropped).into(toolbarImg);

        OkHttpClient client = new OkHttpClient();
//        String url = "https://rocky-sierra-68795.herokuapp.com/breweries";
        String url = "http://43510223.ngrok.io/breweries";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    BreweryMain.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Brewery[] allBreweries = new Gson().fromJson(myResponse, Brewery[].class);
                            for (Brewery brewery : allBreweries) {
                                Brewery testBrewery = new Brewery(brewery.getId(), brewery.getBrewery_name(), brewery.getBrewery_logo(), brewery.getAddress(), brewery.getCity(), brewery.getState(), brewery.getZip(), brewery.getPhone(), brewery.getUrl());
                                lstBrewery.add(testBrewery);
                                System.out.println(testBrewery.getId());
                            }
                            breweryAdapter.notifyDataSetChanged();
                        }
                    });

                }
            }
        });
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Breweries");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}

