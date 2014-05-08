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
import org.json.JSONObject;

/**
 * Created by pgoyal on 06/05/14.
 */
public class DetailedInfoListViewAdapter extends BaseAdapter {

    private JSONArray resultArray;
    @Override
    public int getCount() {
        return this.resultArray.length();
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
        View fv = inflater.inflate(R.layout.layout_detailed_info_list_object, viewGroup, false);
        TextView cutFromLabel = (TextView) fv.findViewById(R.id.cut_from_label);
        TextView cutTillLabel = (TextView) fv.findViewById(R.id.cut_till_label);
        try {
            cutFromLabel.setText((resultArray.getJSONObject(i)).getString("cut_from"));
            cutTillLabel.setText((resultArray.getJSONObject(i)).getString("cut_till"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fv;
    }

    public void setResultArray(JSONArray resultArray) {
        this.resultArray = resultArray;
    }
}
