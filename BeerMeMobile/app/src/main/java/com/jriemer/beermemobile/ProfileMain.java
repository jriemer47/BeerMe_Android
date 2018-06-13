package com.jriemer.beermemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProfileMain extends AppCompatActivity {

    private List<Beer> userBeers;
    private BeerAdapter beerAdapter;
    private RecyclerView myrv;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);

        profileButton = findViewById(R.id.button2);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });


        userBeers = new ArrayList<>();
        beerAdapter = new BeerAdapter(this, userBeers);
        myrv = findViewById(R.id.recyclerview_id);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(beerAdapter);


        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:8000/users/1/beers";
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

                if(response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    ProfileMain.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Beer[] faveBeers = new Gson().fromJson(myResponse, Beer[].class);
                            for (Beer beer : faveBeers) {
                                Beer profileBeer = new Beer(beer.getId(), beer.getBeer_name(), beer.getStyle(), beer.getAbv(), beer.getIbu(), beer.getDescription(), beer.getBeer_label());
                                System.out.println(beer.getBeer_label());
                                userBeers.add(profileBeer);
                            }
                            beerAdapter.notifyDataSetChanged();
                        }
                    });


                }
            }
        });

    }

    public void openSearch() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}
