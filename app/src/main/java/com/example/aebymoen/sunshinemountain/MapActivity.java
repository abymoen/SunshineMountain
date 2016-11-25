package com.example.aebymoen.sunshinemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        int pos = Integer.parseInt(intent.getStringExtra(TrailsActivity.EXTRA_POSITION));
        TextView tv = (TextView) findViewById(R.id.mapID);
        printTitle(tv, pos);
    }

    public void printTitle(TextView tv, int pos){
        String[] runs = new String[]{
            "RUN ONE",
            "RUN TWO",
            "RUN THREE",
            "RUN FOUR",
            "RUN FIVE",
            "RUN SIX",
            "RUN SEVEN",
            "RUN EIGHT"
        };
        tv.setText(runs[pos]);
    }
}
