package com.santirodriguezlorenzo.arbofs.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.config.Constants;
import com.santirodriguezlorenzo.arbofs.db.ArboFsLocalUpdater;
import com.santirodriguezlorenzo.arbofs.fragment.ChangeFragment;
import com.santirodriguezlorenzo.arbofs.fragment.InitFragment;
import com.santirodriguezlorenzo.arbofs.fragment.MenuFragment;

public class MainActivity extends ActionBarActivity implements ChangeFragment {

    public static final int INIT_IDENTIFIER = 0;

    private MenuFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Actualizando dicionario");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setMax(100);

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        boolean isDatabaseCreate = preferences.getBoolean(Constants.DB_CREATE, false);
        if (!isDatabaseCreate){
            updateDatabase();
        }


        mNavigationDrawerFragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return true;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChangeFragment(int identifier, boolean addToBackStack, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (identifier) {
            case INIT_IDENTIFIER:
                fragmentManager.beginTransaction().replace(R.id.container, new InitFragment()).commit();
                break;

        }
    }

    private void updateDatabase() {
            try {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                new ArboFsLocalUpdater(this).execute();
            }catch (Exception e) {
                Log.e("Main", e.getMessage());
            }

    }
}
