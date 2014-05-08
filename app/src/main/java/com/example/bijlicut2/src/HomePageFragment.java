package com.example.bijlicut2.src;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.io.Serializable;
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

    private JSONObject jsonObject1;
    private Button detailedInfo;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private API api;
    private Thread myThread;


    public static HomePageFragment newInstance(String param1) {
        HomePageFragment fragment = new HomePageFragment();
        fragment.setHasOptionsMenu(true);
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

        detailedInfo = (Button)v.findViewById(R.id.detailedInfo);
        detailedInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailedInfoActivity.class);
                intent.putExtra("result", jsonObject1.toString());
                startActivity(intent);
            }
        });
        detailedInfo.setEnabled(false);

        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                JSONObject jsonObject = api.getJSONFromUrl("http://10.0.2.2:8888/iReporter/index.php", params);
                final JSONObject jsonObject = api.getJSONFromUrl("http://172.30.5.106:8888/iReporter/index.php", params);
//                final JSONObject jsonObject = api.getJSONFromUrl("http://192.168.0.105:8888/iReporter/index.php", params);
                    jsonObject1 = jsonObject;

                try {
                    final JSONArray resultArray = jsonObject.getJSONArray("result");
                    JSONObject result = resultArray.getJSONObject(0);
                    Log.d("result", result.toString());
                    final String cutTillObject = result.getString("cut_till");
                    final String cutFromObject = result.getString("cut_from");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            detailedInfo.setEnabled(true);
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
        getActivity().setTitle("Home");
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
