package in.bitnotes.affan.bitnotes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class showPage extends AppCompatActivity {

    WebView webView;
    String htmlText="";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_page);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url"); //if it's a string you stored.

        if(url.contains("practical"))
            this.setTitle("Practical");
        else if(url.contains("examnotes"))
            this.setTitle("Exam Note");
        else if (url.contains("assignments"))
            this.setTitle("Assignment");
        else if (url.contains("qpapers"))
            this.setTitle("Question Paper");

        webView = (WebView) findViewById(R.id.webview);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setAppCacheEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        progressBar.setVisibility(View.VISIBLE);
        webView.setKeepScreenOn(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(showPage.this);
                    mProgress.show();
                }
                mProgress.setMessage("Loading...");
                if (progress == 100) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });
        if (isNetworkAvailable())
            new AsyncHttpTask().execute(url);
        else
            Toast.makeText(showPage.this, "No network connection!", Toast.LENGTH_LONG).show();
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
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);

            if (result != 1) {
                Toast.makeText(showPage.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void parseResult(final String result) {
        try {
            final JSONObject response = new JSONObject(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(getTitle().equals("Practical"))
                            setTitle("Practical | " + new JSONObject(result).optJSONObject("practical").optString("title"));
                        else if(getTitle().equals("Exam Note"))
                            setTitle("Exam Note | " + new JSONObject(result).optJSONObject("examnote").optString("topic"));
                        else if(getTitle().equals("Assignment"))
                            setTitle("Assignment | " + new JSONObject(result).optJSONObject("assignment").optString("topic"));
                        else if(getTitle().equals("Question Paper"))
                            setTitle("Question Paper | " + new JSONObject(result).optJSONObject("qpaper").optString("examname"));

                    }catch (Exception e){
                        Log.e("Error >>>>",e.toString());
                    }
                }
            });

            JSONArray img = response.optJSONArray("images");
            for (int i=0;i<img.length();i++)
                htmlText = htmlText+"<img alt=\"Page No "+i+"\" src=\""+img.optString(i)+"\" width=\"100%\"/><BR>";
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadDataWithBaseURL(null,"<body style='margin: 0; padding: 0'>"+htmlText+"</body>","text/html","UTF-8","about:blank");
                }
            });

        } catch (Exception e) {
            Log.e("Error >>>>>>>>>>>>",""+e.getMessage());
            Toast.makeText(showPage.this, "Failed to fetch data! :"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
