package com.jriemer.beermemobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Brewery_Activity extends AppCompatActivity {

    private TextView tvname, tvcity, tvstate, tvaddress, tvphone, tvurl;
    private ImageView breweryImg;
    private List<Beer> lstBreweryBeer;
    private BeerAdapter beerAdapter;
    private RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_);

        lstBreweryBeer = new ArrayList<>();
        beerAdapter = new BeerAdapter(this, lstBreweryBeer);
        myrv = findViewById(R.id.contentMain);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(beerAdapter);


        tvname = findViewById(R.id.txtname);
        tvcity = findViewById(R.id.txtcity);
        tvstate = findViewById(R.id.txtstate);
        tvaddress = findViewById(R.id.txtaddress);
        tvphone = findViewById(R.id.txtphone);
        tvurl = findViewById(R.id.txturl);
        breweryImg = findViewById(R.id.brewerythumbnail);

//        receive the data
        Intent intent = getIntent();
        int ID = intent.getExtras().getInt("ID");
        String Name = intent.getExtras().getString("Name");
        String City = intent.getExtras().getString("City");
        String State = intent.getExtras().getString("State");
        String Address = intent.getExtras().getString("Address");
        String Phone = intent.getExtras().getString("Phone");
        String Url = intent.getExtras().getString("Url");
        String BreweryImage = intent.getExtras().getString("Image");
        System.out.println(Name);

//        setting the values
        tvname.setText(Name);
        tvcity.setText(City);
        tvstate.setText(State);
        tvaddress.setText(Address);
        tvphone.setText(Phone);
        tvurl.setText(Url);
        Picasso.get().load(BreweryImage).into(breweryImg);


        OkHttpClient client = new OkHttpClient();
//        String url = "https://rocky-sierra-68795.herokuapp.com/breweries/" + ID + "/beers";
        String url = "http://43510223.ngrok.io/breweries/" + ID + "/beers";

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

                    Brewery_Activity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Beer[] breweryBeers = new Gson().fromJson(myResponse, Beer[].class);
                            for (Beer beer : breweryBeers) {
                                Beer breweryBeerTest = new Beer(beer.getId(), beer.getBeer_name(), beer.getStyle(), beer.getAbv(), beer.getIbu(), beer.getDescription(), beer.getBeer_label());
                                lstBreweryBeer.add(breweryBeerTest);
                            }
                            beerAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}
