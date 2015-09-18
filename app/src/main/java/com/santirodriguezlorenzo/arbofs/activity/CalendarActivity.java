package com.santirodriguezlorenzo.arbofs.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.santirodriguezlorenzo.arbofs.R;

public class CalendarActivity extends ActionBarActivity {

    private WebView webViewCalendar;
    private RelativeLayout loadingContent;

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

        loadingContent = (RelativeLayout) findViewById(R.id.loading_content);
        webViewCalendar = (WebView) findViewById(R.id.web_view_calendar);

        webViewCalendar.getSettings().setJavaScriptEnabled(true);
        webViewCalendar.getSettings().setLoadWithOverviewMode(true);
        webViewCalendar.getSettings().setUseWideViewPort(true);
        webViewCalendar.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webViewCalendar.setScrollbarFadingEnabled(false);

        try {
            // load the url
            webViewCalendar.loadUrl("http://www.ligametropolitana.com/calendario.php?cat=344");
            loadingContent.setVisibility(View.GONE);
            webViewCalendar.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.webViewCalendar.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                Log.e("URL", url);
                view.loadUrl(url);
                return true;
            }
        });
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
