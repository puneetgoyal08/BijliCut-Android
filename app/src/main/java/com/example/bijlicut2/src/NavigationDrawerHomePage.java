package com.example.bijlicut2.src;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.bijlicut2.R;

public class NavigationDrawerHomePage extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    SimpleCursorAdapter mCursorAdapter;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_home_page);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

//        if(getIntent().getAction().equals(Intent.ACTION_VIEW)){
//            Intent countryIntent = new Intent(this, CountryActivity.class);
//            countryIntent.setData(getIntent().getData());
//            startActivity(countryIntent);
//            finish();
//        }else
//        final Intent intent = getIntent();
//        final String action = intent.getAction();
//        if (action!=null) {
//            if(action.equals(Intent.ACTION_SEARCH)){ // If this activity is invoked, when user presses "Go" in the Keyboard of Search Dialog
//                String query = intent.getStringExtra(SearchManager.QUERY);
//            }
//        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        if(position == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, HomePageFragment.newInstance("alksd"), "HomePageFragment")
                    .commit();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.container, HomePageFragment.newInstance("alksd"))
//                    .commit();
        }
    else {
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("onQueryText Submit", query);
                System.out.println("search query submit");
//                getActivity().onSearchRequested();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("OnQueryTextChange", newText);
                System.out.println("tap");
                return false;
            }
        });
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
        if (item.getTitle().equals("Search")) {
            Log.d("onoptionsitemselected", "search is selected and on search requested method is called");
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_navigation_drawer_home_page, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((NavigationDrawerHomePage) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

//    @Override
//    public boolean onSearchRequested() {
//        Bundle appData = new Bundle();
//        Log.e("search", "on search requested method is called");
//        startSearch(null, false, appData, false);
//        return true;
//    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        setIntent(intent);
//        handleIntent(intent);
//        super.onNewIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            doSearch(query);
//        }
//        Log.d("handleIntent", "handle intent method is called");
//    }
//
//    private void doMySearch(String query) {
//        HomePageFragment homePageFragment = (HomePageFragment)getFragmentManager().findFragmentByTag("HomePageFragment");
//        ListView suggestionList = (ListView)homePageFragment.getView().findViewById(R.id.suggestionList);
//        ArrayList<String> suggestions = new ArrayList<String>(5);
//        mCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
//                android.R.layout.simple_list_item_1,
//                null,
//                new String[] {SearchManager.SUGGEST_COLUMN_TEXT_1},
//                new int[] { android.R.id.text1 },
//                0
//        );
//        suggestionList.setAdapter(mCursorAdapter);
//
//        homePageFragment.query();
//    }

//    private void doSearch(String query) {
//        HomePageFragment homePageFragment = (HomePageFragment)getFragmentManager().findFragmentByTag("HomePageFragment");
//        ListView suggestionList = (ListView)homePageFragment.getView().findViewById(R.id.suggestionList);
//        ArrayList<String> suggestions = new ArrayList<String>(5);
//        mCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
//                android.R.layout.simple_list_item_1,
//                null,
//                new String[] {SearchManager.SUGGEST_COLUMN_TEXT_1},
//                new int[] { android.R.id.text1 },
//                0
//        );
//        suggestionList.setAdapter(mCursorAdapter);
//
//        Bundle data = new Bundle();
//        data.putString("query", query);
//
//        // Invoking onCreateLoader() in non-ui thread
//        getSupportLoaderManager().initLoader(1, data, this);
//    }
//
//    @Override
//    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        Uri uri = CountryContentProvider.CONTENT_URI;
//        return new android.support.v4.content.CursorLoader(getBaseContext(),uri,null, null, new String[]{bundle.getString("query")}, null);
//    }
//
//    @Override
//    public void onLoadFinished(android.support.v4.content.Loader<Cursor> cursorLoader, Cursor cursor) {
//        mCursorAdapter.swapCursor(cursor);
//    }
//
//    @Override
//    public void onLoaderReset(android.support.v4.content.Loader<Cursor> cursorLoader) {
//
//    }
