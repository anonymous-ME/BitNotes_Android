package in.bitnotes.affan.bitnotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class searchView extends AppCompatActivity {


    private static final String TAG = "RecyclerViewExample";
    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_view);
            Intent intent = getIntent();
            String url = intent.getStringExtra("url"); //if it's a string you stored.
            this.setTitle(intent.getStringExtra("title"));
            // Initialize recycler view
            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
            mSwipeRefreshLayout.setEnabled(false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            progressBar.setVisibility(View.VISIBLE);
            // Downloading data from below url
            feedsList = new ArrayList<>();
            new AsyncHttpTask().execute(url);
            if (!isNetworkAvailable())
                Snackbar.make(this.mRecyclerView, "No network connection. Connect to network and try again.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }catch (Exception e){
            Toast.makeText(searchView.this,"ERROR",Toast.LENGTH_LONG).show();
        }
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

            if (result == 1) {
                adapter = new MyRecyclerAdapter(searchView.this, feedsList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(searchView.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
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
            FeedItem item;
            for (int i = 0; i < practicals.length(); i++) {
                //Adding Practical
                JSONObject practical = practicals.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(practical.optString("title"));
                item.setId(Pid.getString(i));
                item.setViews(Pviews.get(i).toString() + " Views");
                item.setThumbnail(Pthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/practicals/" + practical.optInt("id"));
                feedsList.add(item);
            }
            for (int i = 0; i < notes.length(); i++) {
                //Adding BitnotesService
                JSONObject note = notes.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(note.optString("topic"));
                item.setId(Nid.getString(i));
                item.setViews(Nviews.get(i).toString() + " Views");
                item.setThumbnail(Nthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/examnotes/" + note.optInt("id"));
                feedsList.add(item);
            }
            for (int i = 0; i < assignments.length(); i++) {
                //Adding Assignment
                JSONObject assignment = assignments.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(assignment.optString("topic"));
                item.setId(Aid.getString(i));
                item.setViews(Aviews.get(i).toString() + " Views");
                item.setThumbnail(Athumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/assignments/" + assignment.optInt("id"));
                feedsList.add(item);
            }
            for (int i = 0; i < QPapers.length(); i++) {
                //Adding QPaper
                JSONObject QPaper = QPapers.optJSONObject(i);
                item = new FeedItem();
                item.setTitle(QPaper.optString("examname"));
                item.setId(Qid.getString(i));
                item.setViews(Qviews.get(i).toString() + " Views");
                item.setThumbnail(Qthumb.get(i).toString());
                item.setUrl("http://bitnotes.in/v1/qpapers/" + QPaper.optInt("id"));
                feedsList.add(item);
            }
        } catch (Exception e1) {
            Toast.makeText(searchView.this,"ERROR",Toast.LENGTH_LONG).show();
            e1.printStackTrace();
        }
    }
}
