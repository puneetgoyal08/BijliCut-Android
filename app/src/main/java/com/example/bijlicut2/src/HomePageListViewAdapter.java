package com.example.bijlicut2.src;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bijlicut2.R;

/**
 * Created by pgoyal on 14/04/14.
 */
public class HomePageListViewAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return 2;
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
        View fv = inflater.inflate(R.layout.layout_home_page_list_view, viewGroup, false);
        ImageView leftImageView = (ImageView) fv.findViewById(R.id.leftImage);
        ImageView rightImageView = (ImageView) fv.findViewById(R.id.rightImage);
        TextView textView = (TextView) fv.findViewById(R.id.textView);
        if (i==0) {
            leftImageView.setImageResource(R.drawable.info);
            textView.setText(R.string.homepage_table_1);
        } else {
            leftImageView.setImageResource(R.drawable.love);
            textView.setText(R.string.homepage_table_2);
        }
        return fv;
    }
}
