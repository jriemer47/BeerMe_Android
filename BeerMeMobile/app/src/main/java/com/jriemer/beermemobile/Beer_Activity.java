package com.jriemer.beermemobile;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

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
        String Name = intent.getExtras().getString("Name");
        String Style = intent.getExtras().getString("Style");
        String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Image");

        System.out.println(image);

//        setting values
        tvname.setText(Name);
        tvstyle.setText(Style);
        tvdescription.setText(Description);
        Picasso.get().load(image).into(img);
    }
}
