package com.example.aebymoen.sunshinemountain.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.aebymoen.sunshinemountain.R;

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

        ViewHolderItem viewHolderItem;
        View view = convertView;

        if(convertView == null) {
            view = inflater.inflate(R.layout.runrow, null);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.runName = (TextView) view.findViewById(R.id.runNameTextView);
            viewHolderItem.status = (TextView) view.findViewById(R.id.runStatusTextView);
            viewHolderItem.diffPic = (ImageView) view.findViewById(R.id.runDiffImageView);
            view.setTag(viewHolderItem);
        } else {
            viewHolderItem = (ViewHolderItem) view.getTag();
        }

        HashMap<String, String> data = new HashMap<>();
        data = mData.get(position);
        viewHolderItem.runName.setText(data.get("run"));

        viewHolderItem.status.setText(data.get("status"));
        if(data.get("status") != null) {
            if(data.get("status").equals("CLOSED")) {
                viewHolderItem.status.setTextColor(Color.BLACK);
            }

            if(data.get("difficulty").equals("GREEN")) {
                viewHolderItem.diffPic.setImageResource(R.drawable.greentriangle);
            } else if(data.get("difficulty").equals("BLUE")) {
                viewHolderItem.diffPic.setImageResource(R.drawable.bluesquare);
            } else if(data.get("difficulty").equals("BLACK")) {
                viewHolderItem.diffPic.setImageResource(R.drawable.blackdiamond);
            } else if(data.get("difficulty").equals("DOUBLE")) {
                viewHolderItem.diffPic.setImageResource(R.drawable.doubleblackdiamond);
            }
        }
        return view;
    }

    static class ViewHolderItem {
        TextView runName;
        TextView status;
        ImageView diffPic;
    }
}


