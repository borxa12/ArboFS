package com.santirodriguezlorenzo.arbofs.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.fragment.ChangeFragment;
import com.santirodriguezlorenzo.arbofs.fragment.InitFragment;
import com.santirodriguezlorenzo.arbofs.fragment.MenuFragment;

public class MainActivity extends ActionBarActivity implements ChangeFragment {

    public static final int INIT_IDENTIFIER = 0;

    private MenuFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


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
}
