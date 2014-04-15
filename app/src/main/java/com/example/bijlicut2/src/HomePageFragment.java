package com.example.bijlicut2.src;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bijlicut2.R;
import com.example.bijlicut2.database.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HomePageFragment extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private API api;
    private Thread myThread;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            api = new API();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("command", "stream"));
        final View v = inflater.inflate(R.layout.fragment_home_page, container, false);

        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = api.getJSONFromUrl("http://10.0.2.2:8888/iReporter/index.php", params);
                try {
                    JSONArray resultArray = jsonObject.getJSONArray("result");
                    JSONObject result = resultArray.getJSONObject(0);
                    Log.d("result", result.toString());
                    final String cutTillObject = result.getString("cut_till");
                    final String cutFromObject = result.getString("cut_from");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView cutFromView;
                            cutFromView = (TextView)v.findViewById(R.id.cut_from);
                            cutFromView.setText(cutFromObject);
                            TextView cutTillView;
                            cutTillView = (TextView)v.findViewById(R.id.cut_till);
                            cutTillView.setText(cutTillObject);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("json object", jsonObject.toString());
            }
        });
        myThread.start();

        ListView listView = (ListView)v.findViewById(R.id.homePageList);
        listView.setAdapter(new HomePageListViewAdapter());
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
