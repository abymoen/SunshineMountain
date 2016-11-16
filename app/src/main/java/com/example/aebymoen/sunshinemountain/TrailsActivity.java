package com.example.aebymoen.sunshinemountain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class TrailsActivity extends AppCompatActivity {

    private ListView listView;
    private CustomRunListViewAdapter customRunListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);

        String[] runs = new String[] {
                "RUN ONE",
                "RUN TWO",
                "RUN THREE",
                "RUN FOUR",
                "RUN FIVE",
                "RUN SIX",
                "RUN SEVEN",
                "RUN EIGHT",
        };

        String[] status = new String[] {
                "OPEN",
                "OPEN",
                "CLOSED",
                "OPEN",
                "OPEN",
                "OPEN",
                "CLOSED",
                "OPEN"
        };

        String[] difficulty = new String[] {
                "GREEN",
                "GREEN",
                "BLUE",
                "BLUE",
                "BLACK",
                "BLACK",
                "DOUBLE",
                "DOUBLE"
        };

        ArrayList<HashMap<String,String>> runList = new ArrayList<>();
        for(int i=0; i<8; i++) {
            HashMap<String,String> data = new HashMap<>();
            data.put("run", runs[i]);
            data.put("status",status[i]);
            data.put("difficulty",difficulty[i]);
            runList.add(data);
        }

        listView = (ListView) findViewById(R.id.runListView);
        customRunListViewAdapter = new CustomRunListViewAdapter(getApplicationContext(), runList);

        listView.setAdapter(customRunListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
