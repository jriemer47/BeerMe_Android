package com.jriemer.beermemobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstBrewery = new ArrayList<>();

        breweryAdapter = new BreweryAdapter(this, lstBrewery);

        myrv = findViewById(R.id.recyclerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(breweryAdapter);

        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:8000/breweries";
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
                                System.out.println(testBrewery);
                            }
                            breweryAdapter.notifyDataSetChanged();
                        }
                    });

                }
            }
        });
    }
}

