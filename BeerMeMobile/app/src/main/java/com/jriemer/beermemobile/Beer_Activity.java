package com.jriemer.beermemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Beer_Activity extends AppCompatActivity {


    private TextView tvname, tvdescription, tvstyle;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_);

        tvname = findViewById(R.id.txttitle);
        tvdescription = findViewById(R.id.txtdescription);
        tvstyle = findViewById(R.id.txtstyle);
        img = findViewById(R.id.beerthumbnail);


//        receive data
        Intent intent = getIntent();
        String Name = intent.getExtras().getString("beer_name");
        String Description = intent.getExtras().getString("description");
        int image = intent.getExtras().getInt("beer_label");

//        setting values
        tvname.setText(Name);
        tvdescription.setText(Description);
        img.setImageResource(image);


    }
}
