package com.example.admin.campusonphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activty);
        // create views
        TextView placesName =  findViewById(R.id.place_name_detail);
        ImageView placesImage =  findViewById(R.id.placesImage_detail);

        // set the view values from key pair of the intent
        placesName.setText(getIntent().getStringExtra("place_name"));

        // we are using Glide library to handle images
        Glide.with(this).load(getIntent()
                .getIntExtra("image_resource", 0)).into(placesImage);

    }
}
