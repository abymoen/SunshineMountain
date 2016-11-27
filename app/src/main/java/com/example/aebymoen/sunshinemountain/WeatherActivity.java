package com.example.aebymoen.sunshinemountain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WeatherActivity extends AppCompatActivity {

    private Button hourly, current, weekly;
    private ImageView weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        hourly = (Button) findViewById(R.id.hourlyButton);
        current = (Button) findViewById(R.id.currentButton);
        weekly = (Button) findViewById(R.id.weeklyButton);
        weather = (ImageView) findViewById(R.id.weatherImage);

        hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtons(current, weekly, hourly);
                setButton(hourly);
                weather.setImageResource(R.drawable.hourlyweather);
            }
        });

        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtons(current, weekly, hourly);
                setButton(current);
                weather.setImageResource(R.drawable.currentweather);
            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtons(current, weekly, hourly);
                setButton(weekly);
                weather.setImageResource(R.drawable.weeklyweather);
            }
        });
    }

    public void setButtons(Button weekly, Button current, Button hourly) {
        weekly.setBackground(getResources().getDrawable(R.drawable.roundcorners));
        current.setBackground(getResources().getDrawable(R.drawable.roundcorners));
        hourly.setBackground(getResources().getDrawable(R.drawable.roundcorners));
    }

    public void setButton(Button clicked) {
        clicked.setBackground(getResources().getDrawable(R.drawable.clickedbutton));
    }
}
