package com.example.bijlicut2.src;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.bijlicut2.R;

import java.util.ArrayList;

public class SearcheableActivity2 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("searcheable activity", "on create called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_searcheable);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);

        this.setListAdapter(adapter);
    }

    public void doMySearch(String query) {
        Log.e("searcheable activity", "doMySearch called");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searcheable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("onNew Intent", "is called");
        super.onNewIntent(intent);
    }
}
