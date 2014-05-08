package com.example.bijlicut2.src;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import com.example.bijlicut2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailedInfoActivity extends Activity {

    private JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("result"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailed_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detailed_info, container, false);
            ListView listView = (ListView)rootView.findViewById(R.id.detailedInfoList);
            DetailedInfoListViewAdapter detailedInfoListViewAdapter = new DetailedInfoListViewAdapter();
            DetailedInfoActivity detailedInfoActivity = ((DetailedInfoActivity)getActivity());
            JSONObject jsonObject1 = detailedInfoActivity.getJsonObject();

            try {
                detailedInfoListViewAdapter.setResultArray(jsonObject1.getJSONArray("result"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listView.setAdapter(detailedInfoListViewAdapter);
            return rootView;
        }
    }

}
