package com.example.aebymoen.sunshinemountain.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aebymoen.sunshinemountain.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alex on 2016-11-19.
 */

public class CustomChairListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HashMap<String,String>> mData;
    private static LayoutInflater inflater = null;

    public CustomChairListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        mContext = context;
        mData = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        CustomChairListViewAdapter.ViewHolder viewHolder;
        View view = convertView;
        HashMap<String, String> data = new HashMap<>();
        data = mData.get(i);

        if(convertView == null) {
            view = inflater.inflate(R.layout.chairrow, null);
            viewHolder = new CustomChairListViewAdapter.ViewHolder();
            viewHolder.chairName = (TextView) view.findViewById(R.id.chairNameTextView);
            viewHolder.status = (TextView) view.findViewById(R.id.chairStatusTextView);
            if(data.get("status").equals("CLOSED")) {
                viewHolder.status.setTextColor(Color.BLACK);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (CustomChairListViewAdapter.ViewHolder) view.getTag();
        }

        viewHolder.chairName.setText(data.get("run"));
        viewHolder.status.setText(data.get("status"));
        return view;
    }

    static class ViewHolder {
        TextView chairName;
        TextView status;
    }
}
