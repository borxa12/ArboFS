package com.santirodriguezlorenzo.arbofs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.santirodriguezlorenzo.arbofs.R;

public class RatingActivity extends ActionBarActivity{

    private WebView webViewRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.rating);

        webViewRating = (WebView) findViewById(R.id.web_view_rating);
        webViewRating.getSettings().setJavaScriptEnabled(true);
        webViewRating.getSettings().setLoadWithOverviewMode(true);
        webViewRating.getSettings().setUseWideViewPort(true);

        try {
            // load the url
            webViewRating.loadUrl("http://www.ligametropolitana.com/division.php?cat=306");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
