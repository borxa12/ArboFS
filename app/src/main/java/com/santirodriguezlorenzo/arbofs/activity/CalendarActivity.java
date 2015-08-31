package com.santirodriguezlorenzo.arbofs.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.santirodriguezlorenzo.arbofs.R;

public class CalendarActivity extends ActionBarActivity {

    private WebView webViewCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.calendar);

        webViewCalendar = (WebView) findViewById(R.id.web_view_calendar);
        webViewCalendar.getSettings().setJavaScriptEnabled(true);
        webViewCalendar.getSettings().setLoadWithOverviewMode(true);
        webViewCalendar.getSettings().setUseWideViewPort(true);

        try {
            // load the url
            webViewCalendar.loadUrl("http://www.ligametropolitana.com/calendario.php?cat=306");
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
