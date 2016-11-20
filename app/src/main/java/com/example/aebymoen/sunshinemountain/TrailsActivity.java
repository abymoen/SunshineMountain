package com.example.aebymoen.sunshinemountain;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aebymoen.sunshinemountain.adapters.CustomChairListViewAdapter;
import com.example.aebymoen.sunshinemountain.adapters.CustomRunListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class TrailsActivity extends AppCompatActivity {

    private ListView listView;
    private ListView chairListView;
    private CustomRunListViewAdapter customRunListViewAdapter;
    private CustomChairListViewAdapter customChairListViewAdapter;
    Button chairFilter, runFilter;
    private AlertDialog.Builder dialog;
    private RadioGroup chairFilterGroup, runFilterGroup;
    private RadioButton chairFilterSelected, runFilterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails);
        chairFilter = (Button) findViewById(R.id.chairliftFilterButton);
        runFilter = (Button) findViewById(R.id.runsFilterButton);
        filterAllChairs();
        filterAllRuns();

        chairFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chairFilterPopup();
            }
        });

        runFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runFilterPopup();
            }
        });
    }

    public void runFilterPopup() {
        dialog = new AlertDialog.Builder(TrailsActivity.this);
        dialog.setTitle("Run Filter");
        dialog.setView(R.layout.runfilter);
        final AlertDialog runOptions = dialog.create();
        runOptions.show();

        Button ok = (Button) runOptions.findViewById(R.id.chairFilterOkButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox green = (CheckBox) runOptions.findViewById(R.id.filterGreen);
                CheckBox blue = (CheckBox) runOptions.findViewById(R.id.filterBlue);
                CheckBox black = (CheckBox) runOptions.findViewById(R.id.filterBlack);
                CheckBox doubleBlack = (CheckBox) runOptions.findViewById(R.id.filterDouble);
                ArrayList<String> checked = new ArrayList<String>();
                String runFilterString;

                if(green.isChecked()) checked.add("GREEN");
                if(blue.isChecked()) checked.add("BLUE");
                if(black.isChecked()) checked.add("BLACK");
                if(doubleBlack.isChecked()) checked.add("DOUBLE");

                runFilterGroup = (RadioGroup) runOptions.findViewById(R.id.chairFilterOptionsStatus);
                int selected = runFilterGroup.getCheckedRadioButtonId();
                runFilterSelected = (RadioButton) runOptions.findViewById(selected);

                if(runFilterGroup.getCheckedRadioButtonId() == -1 &&
                        !(green.isChecked()) && !(blue.isChecked()) && !(black.isChecked()) && !(doubleBlack.isChecked())) {
                    runOptions.cancel();
                    return;
                } else if(runFilterGroup.getCheckedRadioButtonId() != -1 &&
                        !(green.isChecked()) && !(blue.isChecked()) && !(black.isChecked()) && !(doubleBlack.isChecked())) {
                    checked.add("GREEN");
                    checked.add("BLUE");
                    checked.add("BLACK");
                    checked.add("DOUBLE");
                } else if(runFilterGroup.getCheckedRadioButtonId() == -1 &&
                        (green.isChecked()) || (blue.isChecked()) || (black.isChecked()) || (doubleBlack.isChecked())) {
                    if(green.isChecked()) checked.add("GREEN");
                    if(blue.isChecked()) checked.add("BLUE");
                    if(black.isChecked()) checked.add("BLACK");
                    if(doubleBlack.isChecked()) checked.add("DOUBLE");
                    filterRuns(checked, "BOTH");
                    runOptions.cancel();
                    return;
                }

                if(runFilterSelected.getText().equals("OPEN ONLY")) {
                     runFilterString = "OPEN";
                } else if(runFilterSelected.getText().equals("CLOSED ONLY")){
                    runFilterString = "CLOSED";
                }else {
                    runFilterString = "BOTH";
                }
                filterRuns(checked, runFilterString);
                runOptions.cancel();
            }
        });
    }

    public void filterRuns(ArrayList<String> checked, String runFilter) {
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
            if(checked.contains(difficulty[i]) && (runFilter.equals(status[i]) || runFilter.equals("BOTH"))){
                HashMap<String,String> data = new HashMap<>();
                data.put("difficulty",difficulty[i]);
                data.put("run", runs[i]);
                data.put("status",status[i]);
                runList.add(data);
            }
        }

        listView = (ListView) findViewById(R.id.runListView);
        customRunListViewAdapter = new CustomRunListViewAdapter(getApplicationContext(), runList);
        listView.setAdapter(customRunListViewAdapter);
    }

    public void chairFilterPopup() {
        dialog = new AlertDialog.Builder(TrailsActivity.this);
        dialog.setTitle("Chairlift Filter");
        dialog.setView(R.layout.chair_filter);
        final AlertDialog chairOptions = dialog.create();
        chairOptions.show();
        Button ok = (Button) chairOptions.findViewById(R.id.chairFilterOkButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chairFilterGroup = (RadioGroup) chairOptions.findViewById(R.id.chairFilterOptions);
                int selected = chairFilterGroup.getCheckedRadioButtonId();
                chairFilterSelected = (RadioButton) chairOptions.findViewById(selected);
                if(chairFilterSelected.getText().equals("BOTH")) {
                    filterAllChairs();
                    chairOptions.cancel();
                } else if(chairFilterSelected.getText().equals("OPEN ONLY")) {
                    filterOpenChairs();
                    chairOptions.cancel();
                } else {
                    filterClosedChairs();
                    chairOptions.cancel();
                }
            }
        });
    }

    public void filterClosedChairs() {
        String[] lifts = new String[] {
                "LIFT THREE",
                "LIFT SEVEN",
        };
        String[] liftStatus = new String[] {
                "CLOSED",
                "CLOSED",
        };
        final ArrayList<HashMap<String,String>> chairList = new ArrayList<>();
        for(int i=0; i<2; i++) {
            HashMap<String,String> dataChairs = new HashMap<>();
            dataChairs.put("run", lifts[i]);
            dataChairs.put("status",liftStatus[i]);
            chairList.add(dataChairs);
        }

        chairListView = (ListView) findViewById(R.id.chairListView);
        customChairListViewAdapter = new CustomChairListViewAdapter(getApplicationContext(), chairList);
        chairListView.setAdapter(customChairListViewAdapter);
    }

    public void filterAllChairs() {
        String[] lifts = new String[] {
                "LIFT ONE",
                "LIFT TWO",
                "LIFT THREE",
                "LIFT FOUR",
                "LIFT FIVE",
                "LIFT SIX",
                "LIFT SEVEN",
                "LIFT EIGHT"
        };
        String[] liftStatus = new String[] {
                "OPEN",
                "OPEN",
                "CLOSED",
                "OPEN",
                "OPEN",
                "OPEN",
                "CLOSED",
                "OPEN"
        };
        final ArrayList<HashMap<String,String>> chairList = new ArrayList<>();
        for(int i=0; i<8; i++) {
            HashMap<String,String> dataChairs = new HashMap<>();
            dataChairs.put("run", lifts[i]);
            dataChairs.put("status",liftStatus[i]);
            chairList.add(dataChairs);
        }

        chairListView = (ListView) findViewById(R.id.chairListView);
        customChairListViewAdapter = new CustomChairListViewAdapter(getApplicationContext(), chairList);
        chairListView.setAdapter(customChairListViewAdapter);
    }

    public void filterAllRuns() {
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
    }

    public void filterOpenChairs() {
        String[] lifts = new String[] {
                "LIFT ONE",
                "LIFT TWO",
                "LIFT FOUR",
                "LIFT FIVE",
                "LIFT SIX",
                "LIFT EIGHT"
        };
        String[] liftStatus = new String[] {
                "OPEN",
                "OPEN",
                "OPEN",
                "OPEN",
                "OPEN",
                "OPEN"
        };
        final ArrayList<HashMap<String,String>> chairList = new ArrayList<>();
        for(int i=0; i<6; i++) {
            HashMap<String,String> dataChairs = new HashMap<>();
            dataChairs.put("run", lifts[i]);
            dataChairs.put("status",liftStatus[i]);
            chairList.add(dataChairs);
        }

        chairListView = (ListView) findViewById(R.id.chairListView);
        customChairListViewAdapter = new CustomChairListViewAdapter(getApplicationContext(), chairList);
        chairListView.setAdapter(customChairListViewAdapter);
    }
}
