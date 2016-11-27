package com.example.aebymoen.sunshinemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        String S = intent.getStringExtra(TrailsActivity.EXTRA_POSITION);
        printTitle(S);
    }

    public void printTitle(String S){
        TextView tv = (TextView) findViewById(R.id.mapID);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.mapview);
        switch(S){
            case "RUN ONE":
                rl.setBackgroundResource(R.drawable.runone);
                break;
            case "RUN TWO":
                rl.setBackgroundResource(R.drawable.runtwo);
                break;
            case "RUN THREE":
                rl.setBackgroundResource(R.drawable.runthree);
                break;
            case "RUN FOUR":
                rl.setBackgroundResource(R.drawable.runfour);
                break;
            case "RUN FIVE":
                rl.setBackgroundResource(R.drawable.runfive);
                break;
            case "RUN SIX":
                rl.setBackgroundResource(R.drawable.runsix);
                break;
            case "RUN SEVEN":
                rl.setBackgroundResource(R.drawable.runseven);
                break;
            case "RUN EIGHT":
                rl.setBackgroundResource(R.drawable.runeight);
                break;
            default:
                break;
        }
        tv.setText(S);
    }

    public void clickedBack(View v){
        finish();
    }
}
