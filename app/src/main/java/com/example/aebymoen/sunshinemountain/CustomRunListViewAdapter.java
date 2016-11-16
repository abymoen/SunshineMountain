package com.example.aebymoen.sunshinemountain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alex on 2016-11-15.
 */

public class CustomRunListViewAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<HashMap<String,String>> mData;
    private static LayoutInflater inflater = null;

    public CustomRunListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        mContext = context;
        mData = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(convertView == null) {
            view = inflater.inflate(R.layout.runrow, null);
            TextView run = (TextView) view.findViewById(R.id.runNameTextView);
            TextView status = (TextView) view.findViewById(R.id.runStatusTextView);
            HashMap<String, String> data = new HashMap<>();
            data = mData.get(position);
            run.setText(data.get("run"));
            status.setText(data.get("status"));
        }

        return view;
    }
}
