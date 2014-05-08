package com.example.bijlicut2.src;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bijlicut2.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by pgoyal on 08/05/14.
 */

public class NavigationDrawerListViewAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fv = inflater.inflate(R.layout.layout_navigation_drawer_list_view_object, viewGroup, false);
        ImageView navigation_icon = (ImageView) fv.findViewById(R.id.navigation_icon);
        TextView navigation_title = (TextView) fv.findViewById(R.id.navigation_title);
        switch (i) {
            case 0:
                navigation_title.setText(R.string.title_section1);
                navigation_icon.setImageResource(R.drawable.bulb_on);
                break;
            case 1:
                navigation_title.setText(R.string.title_section2);
                navigation_icon.setImageResource(R.drawable.bulb_on);
                break;
            case 2:
                navigation_title.setText(R.string.title_section3);
                navigation_icon.setImageResource(R.drawable.bulb_on);
                break;
            case 3:
                navigation_title.setText(R.string.title_section4);
                navigation_icon.setImageResource(R.drawable.bulb_on);
                break;
            case 4:
                navigation_title.setText(R.string.title_section5);
                navigation_icon.setImageResource(R.drawable.bulb_on);
                break;
        }
        return fv;
    }
}
