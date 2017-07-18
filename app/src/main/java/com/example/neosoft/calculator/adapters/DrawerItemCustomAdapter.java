package com.example.neosoft.calculator.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neosoft.calculator.R;

import java.util.List;

/**
 * Created by neosoft on 21/6/17.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<String> {

    Context mContext;
    int layoutResourceId;
    List<String> mMenuList;

    public DrawerItemCustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        layoutResourceId = resource;
        mMenuList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        if (position == 0) {
            listItem = inflater.inflate(R.layout.layout_nav_header, parent, false);
            return listItem;
        } else {
            listItem = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView menuItem = (TextView) listItem.findViewById(R.id.tv_menu_text);
        menuItem.setText(mMenuList.get(position));
        if (position == 4) {
            menuItem.setTextColor(Color.BLACK);
            menuItem.setTextSize(18);
            menuItem.setTypeface(null, Typeface.BOLD);
            listItem.setBackgroundResource(0);
        }

        return listItem;
    }
}
