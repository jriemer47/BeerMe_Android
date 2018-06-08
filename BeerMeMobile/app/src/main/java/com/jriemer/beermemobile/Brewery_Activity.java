package com.jriemer.beermemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Brewery_Activity extends AppCompatActivity {

    private TextView tvname, tvcity, tvstate, tvaddress, tvphone, tvurl;
    private ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_);


        tvname = findViewById(R.id.txtname);
        tvcity = findViewById(R.id.txtcity);
        tvstate = findViewById(R.id.txtstate);
        tvaddress =findViewById(R.id.txtstate);
        tvphone = findViewById(R.id.txtphone);
        tvurl = findViewById(R.id.txturl);
        img = findViewById(R.id.brewerythumbnail);

//        receive the data
        Intent intent = getIntent();
        String Name = intent.getExtras().getString("brewery_name");
        String City = intent.getExtras().getString("city");
        String State = intent.getExtras().getString("state");
        String Address = intent.getExtras().getString("address");
        String Phone = intent.getExtras().getString("phone");
        String Url = intent.getExtras().getString("url");
        int image = intent.getExtras().getInt("beer_label");


//        setting the values

        tvname.setText(Name);
        tvcity.setText(City);
        tvstate.setText(State);
        tvaddress.setText(Address);
        tvphone.setText(Phone);
        tvurl.setText(Url);
        img.setImageResource(image);
    }
}
