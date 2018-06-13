package com.jriemer.beermemobile;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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

public class BeerMain extends AppCompatActivity {

    private List<Beer> lstBeer;
    private RecyclerView myrv;
    private BeerAdapter beerAdapter;
    private ImageView toolbarImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
////////////////////

        lstBeer = new ArrayList<>();
        beerAdapter = new BeerAdapter(this, lstBeer);

        myrv = findViewById(R.id.contentMain);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(beerAdapter);



//        use picasso to load top photo
        toolbarImg = findViewById(R.id.backdrop);
        Picasso.get().load(R.drawable.beerscropped).into(toolbarImg);


        OkHttpClient client = new OkHttpClient();

        String url = "http://10.0.2.2:8000/beers";

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

                    BeerMain.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Beer[] allBeers = new Gson().fromJson(myResponse, Beer[].class);
                            for (Beer beer : allBeers) {
                                Beer testBeer = new Beer(beer.getId(), beer.getBeer_name(), beer.getStyle(), beer.getAbv(), beer.getIbu(), beer.getDescription(), beer.getBeer_label());
                                System.out.println(beer.getBeer_label());
                                lstBeer.add(testBeer);
                            }
                            beerAdapter.notifyDataSetChanged();
                        }
                    });

                }
            }
        });
    }

    /////////////////////////////////////////////////////////////////
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
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


}
