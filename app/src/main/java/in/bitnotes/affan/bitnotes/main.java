package in.bitnotes.affan.bitnotes;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , SearchView.OnQueryTextListener{
    private static final String TAG = "RecyclerViewExample";
    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update(index);
            }
        });
        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        progressBar.setVisibility(View.VISIBLE);
        // Downloading data from below url
        feedsList = new ArrayList<>();
        String url = "http://bitnotes.in/v1/abouts";
        new AsyncHttpTask().execute(url);
        if(!isNetworkAvailable())
            Snackbar.make(this.mRecyclerView, "No network connection. Connect to network and try again.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(main.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                //Toast.makeText(main.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public class AsyncHttpTask2 extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult2(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(main.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                //Toast.makeText(main.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public class AsyncHttpTask1 extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult1(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(main.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                //Toast.makeText(main.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public class AsyncHttpTask3 extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult3(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(main.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                //Toast.makeText(main.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public class AsyncHttpTask4 extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult4(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(main.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                //Toast.makeText(main.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void parseResult4(String result) {
        try {
            feedsList.clear();
            JSONObject response = new JSONObject(result);
            //Assignment
            JSONArray assignments = response.optJSONArray("qpapers");
            JSONArray Athumb = response.optJSONArray("images");
            JSONArray Aviews = response.optJSONArray("views");
            JSONArray Aid = response.optJSONArray("publications");
            for (int i = 0; i < assignments.length(); i++) {
                //Adding Assignment
                FeedItem item = new FeedItem();
                JSONObject assignment = assignments.optJSONObject(i);
                item.setTitle(assignment.optString("examname"));
                item.setId(Aid.getString(i));
                item.setViews(Aviews.get(i).toString()+" Views");
                item.setThumbnail(Athumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/qpapers/"+assignment.optInt("id"));
                feedsList.add(item);
            }
        }catch(Exception e)
        {
            Toast.makeText(main.this,"ERROR",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    private void parseResult3(String result) {
        try {
            feedsList.clear();
            JSONObject response = new JSONObject(result);
            //Assignment
            JSONArray assignments = response.optJSONArray("examnotes");
            JSONArray Athumb = response.optJSONArray("images");
            JSONArray Aviews = response.optJSONArray("views");
            JSONArray Aid = response.optJSONArray("publications");
            for (int i = 0; i < assignments.length(); i++) {
                //Adding Assignment
                FeedItem item = new FeedItem();
                JSONObject assignment = assignments.optJSONObject(i);
                item.setTitle(assignment.optString("topic"));
                item.setId(Aid.getString(i));
                item.setViews(Aviews.get(i).toString()+" Views");
                item.setThumbnail(Athumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/examnotes/"+assignment.optInt("id"));
                feedsList.add(item);
            }
        }catch(Exception e)
        {
            Toast.makeText(main.this,"ERROR",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    private void parseResult2(String result) {
        try {
            feedsList.clear();
            JSONObject response = new JSONObject(result);
            //Assignment
            JSONArray assignments = response.optJSONArray("assignments");
            JSONArray Athumb = response.optJSONArray("images");
            JSONArray Aviews = response.optJSONArray("views");
            JSONArray Aid = response.optJSONArray("publications");
            for (int i = 0; i < assignments.length(); i++) {
                //Adding Assignment
                FeedItem item = new FeedItem();
                JSONObject assignment = assignments.optJSONObject(i);
                item.setTitle(assignment.optString("topic"));
                item.setId(Aid.getString(i));
                item.setViews(Aviews.get(i).toString()+" Views");
                item.setThumbnail(Athumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/assignments/"+assignment.optInt("id"));
                feedsList.add(item);
            }
        }catch(Exception e)
        {
            Toast.makeText(main.this,"ERROR",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        }

    private void parseResult1(String result) {
        try {
                feedsList.clear();
                JSONObject response = new JSONObject(result);
                //Practical
                JSONArray practicals = response.optJSONArray("practicals");
                JSONArray Pthumb = response.optJSONArray("images");
                JSONArray Pviews = response.optJSONArray("views");
                JSONArray Pid = response.optJSONArray("publications");
                for (int i = 0; i < practicals.length(); i++) {
                    //Adding Practical
                    JSONObject practical = practicals.optJSONObject(i);
                    FeedItem item = new FeedItem();
                    item.setTitle(practical.optString("title"));
                    item.setId(Pid.getString(i));
                    item.setViews(Pviews.get(i).toString() + " Views");
                    item.setThumbnail(Pthumb.get(i).toString());
                    item.setUrl("http://bitnotes.in/v1/practicals/"+practical.optInt("id"));
                    feedsList.add(item);
                }
            } catch (JSONException e) {
                Toast.makeText(main.this,"ERROR",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
    }
    private void parseResult(String result) {
        try {
            feedsList.clear();
            JSONObject response = new JSONObject(result);
            //Practical
            JSONArray practicals = response.optJSONArray("practicals");
            JSONArray Pthumb = response.optJSONArray("practicalimages");
            JSONArray Pviews = response.optJSONArray("practicalviews");
            JSONArray Pid = response.optJSONArray("practicalpublications");
            //ExamNotes
            JSONArray notes = response.optJSONArray("examnotes");
            JSONArray Nthumb = response.optJSONArray("examnoteimages");
            JSONArray Nviews = response.optJSONArray("examnoteviews");
            JSONArray Nid = response.optJSONArray("examnotepublications");
            //Assignment
            JSONArray assignments = response.optJSONArray("assignments");
            JSONArray Athumb = response.optJSONArray("assignmentimages");
            JSONArray Aviews = response.optJSONArray("assignmentviews");
            JSONArray Aid = response.optJSONArray("assignmentpublications");
            //QPaper
            JSONArray QPapers = response.optJSONArray("qpapers");
            JSONArray Qthumb = response.optJSONArray("qpaperimages");
            JSONArray Qviews = response.optJSONArray("qpaperviews");
            JSONArray Qid = response.optJSONArray("qpaperpublications");

            for (int i = 0; i < practicals.length(); i++) {
                //Adding Practical
                JSONObject practical = practicals.optJSONObject(i);
                FeedItem item = new FeedItem();
                item.setTitle(practical.optString("title"));
                item.setId(Pid.getString(i));
                item.setViews(Pviews.get(i).toString()+" Views");
                item.setThumbnail(Pthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/practicals/"+practical.optInt("id"));
                feedsList.add(item);
                //Adding BitnotesService
                JSONObject note = notes.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(note.optString("topic"));
                item.setId(Nid.getString(i));
                item.setViews(Nviews.get(i).toString()+" Views");
                item.setThumbnail(Nthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/examnotes/"+note.optInt("id"));
                feedsList.add(item);
                //Adding Assignment
                JSONObject assignment = assignments.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(assignment.optString("topic"));
                item.setId(Aid.getString(i));
                item.setViews(Aviews.get(i).toString()+" Views");
                item.setThumbnail(Athumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/assignments/"+assignment.optInt("id"));
                feedsList.add(item);
                //Adding QPaper
                JSONObject QPaper = QPapers.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(QPaper.optString("examname"));
                item.setId(Qid.getString(i));
                item.setViews(Qviews.get(i).toString()+" Views");
                item.setThumbnail(Qthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/qpapers/"+QPaper.optInt("id"));
                feedsList.add(item);
            }
        } catch (JSONException e) {
            Toast.makeText(main.this,"ERROR",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent myIntent = new Intent(this, searchView.class);
        myIntent.putExtra("url", "http://bitnotes.in/v1/result?search="+query); //Optional parameters
        myIntent.putExtra("title", "Search result for '"+query+"'"); //Optional parameters
        this.startActivity(myIntent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return true;
    }
    SearchView searchView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicksome/Up  on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    int index;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", index);
    }
    private void update(int index){
        if(!mSwipeRefreshLayout.isRefreshing()) {
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
        }
        if(!isNetworkAvailable()) {
            Snackbar.make(this.mRecyclerView, "No network connection. Connect to network and try again.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }else
            if (index == 0) {
                this.index = 0;
                // Downloading data from below url
                String url = "http://bitnotes.in/v1/abouts";
                new AsyncHttpTask().execute(url);

            } else if (index == 1) {
                this.index = 1;
                String url = "http://bitnotes.in/v1/practicals";
                new AsyncHttpTask1().execute(url);

            } else if (index == 2) {
                this.index = 2;
                String url = "http://bitnotes.in/v1/assignments";
                new AsyncHttpTask2().execute(url);

            } else if (index == 3) {
                this.index = 3;
                String url = "http://bitnotes.in/v1/examnotes";
                new AsyncHttpTask3().execute(url);

            }else if (index == 4) {
                this.index = 4;
                String url = "http://bitnotes.in/v1/qpapers";
                new AsyncHttpTask4().execute(url);

            }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        index = savedInstanceState.getInt("index");
        update(index);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            while (!feedsList.isEmpty())
                adapter.delete(0);
            index = 0;
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            // Downloading data from below url
            String url = "http://bitnotes.in/v1/abouts";
            new AsyncHttpTask().execute(url);

        } else if (id == R.id.nav_gallery) {
            while (!feedsList.isEmpty())
                adapter.delete(0);
            index = 1;
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            String url = "http://bitnotes.in/v1/practicals";
            new AsyncHttpTask1().execute(url);

        } else if (id == R.id.nav_slideshow) {
            while (!feedsList.isEmpty())
                adapter.delete(0);
            index = 2;
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            String url = "http://bitnotes.in/v1/assignments";
            new AsyncHttpTask2().execute(url);

        } else if (id == R.id.nav_manage) {
            while (!feedsList.isEmpty())
                adapter.delete(0);
            index = 3;
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            String url = "http://bitnotes.in/v1/examnotes";
            new AsyncHttpTask3().execute(url);

        } else if (id == R.id.nav_share) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bitnotes.in"));
            startActivity(browserIntent);

        } else if (id == R.id.nav_send) {
            Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
            }

        }else if (id == R.id.nav_manag) {
            while (!feedsList.isEmpty())
                adapter.delete(0);
            index = 4;
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            String url = "http://bitnotes.in/v1/qpapers";
            new AsyncHttpTask4().execute(url);

        }
        if(!isNetworkAvailable())
            Snackbar.make(this.mRecyclerView, "No network connection. Connect to network and try again.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
