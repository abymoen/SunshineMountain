package com.example.aebymoen.sunshinemountain;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SkierLandingActivity extends AppCompatActivity {

    ImageButton trails;
    ImageButton weather;
    ImageButton transportation;
    ImageButton hotels;
    ImageButton prices;
    ImageButton contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skier_landing);

        trails = (ImageButton) findViewById(R.id.trailButton);
        trails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SkierLandingActivity.this, TrailsActivity.class);
                startActivity(i);
            }
        });

        weather = (ImageButton) findViewById(R.id.weatherButton);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SkierLandingActivity.this, WeatherActivity.class);
                startActivity(i);
            }
        });
    }
}
