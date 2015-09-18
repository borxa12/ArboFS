package com.santirodriguezlorenzo.arbofs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.adapter.TemplateAdapter;
import com.santirodriguezlorenzo.arbofs.adapter.TemplateCardsAdapter;
import com.santirodriguezlorenzo.arbofs.application.ArboFSApplication;
import com.santirodriguezlorenzo.arbofs.db.ArboFsDB;
import com.santirodriguezlorenzo.arbofs.db.ArboFsSQLHelper;
import com.santirodriguezlorenzo.arbofs.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TemplateActivity extends ActionBarActivity {//implements MenuItemCompat.OnActionExpandListener{

    private List<Player> players;
    private String nome;
    private TemplateAdapter adapter;

    // Vistas
    private ListView listPlayers;
    private SearchView searchView;
    private MenuItem searchItem;
    private TextView txtNoResults;

    // Action bar
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.template);

        listPlayers = (ListView) findViewById(R.id.list_players);
        txtNoResults = (TextView) findViewById(R.id.txt_no_results);

        ArboFsSQLHelper helper = new ArboFsSQLHelper(this);

        Cursor cursor = helper.loadAllPlayers();
        startManagingCursor(cursor);
        players = new ArrayList<Player>();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Player player = populatePlayer(cursor);
                    players.add(player);
                }while(cursor.moveToNext());
            }

            //cursor.close();
        }

        adapter = new TemplateAdapter(this, players);
        //TemplateCardsAdapter adapter = new TemplateCardsAdapter(this, players);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player player = (Player) parent.getAdapter().getItem(position);
                Intent intent = new Intent(TemplateActivity.this, PlayerActivity.class);
                intent.putExtra(PlayerActivity.PARAM_PLAYER, new Gson().toJson(player));
                startActivity(intent);
                //Toast.makeText(TemplateActivity.this, player.getDescriptionEs(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_template, menu);
        searchItem = menu.findItem(R.id.action_search);
        //searchItem.expandActionView();
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.onActionViewExpanded();
        searchView.setQuery(nome, true);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                players.clear();
                ArboFsSQLHelper helper = new ArboFsSQLHelper(TemplateActivity.this);
                if (!newText.isEmpty()) {

                    Cursor cursor = helper.loadPlayersByName(newText);
                    if (cursor != null) {
                        //cursor.moveToFirst();
                        while (cursor.moveToNext()) {
                            Player player = populatePlayer(cursor);
                            players.add(player);
                        }
                        if (players.size() == 0) {
                            listPlayers.setVisibility(View.GONE);
                            txtNoResults.setVisibility(View.VISIBLE);
                        } else {
                            listPlayers.setVisibility(View.VISIBLE);
                            txtNoResults.setVisibility(View.GONE);
                            adapter = new TemplateAdapter(TemplateActivity.this, players);
                            listPlayers.setAdapter(adapter);
                        }

                    }
                    cursor.close();
                } else {

                    Cursor cursor = helper.loadAllPlayers();
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            Player player = populatePlayer(cursor);
                            players.add(player);
                        }
                        if (players.size() == 0) {
                            listPlayers.setVisibility(View.GONE);
                            txtNoResults.setVisibility(View.VISIBLE);
                        } else {
                            listPlayers.setVisibility(View.VISIBLE);
                            txtNoResults.setVisibility(View.GONE);
                            adapter = new TemplateAdapter(TemplateActivity.this, players);
                            listPlayers.setAdapter(adapter);
                        }
                    }
                }
                return true;
            }
        });
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        /*if (searchItem != null) {
            MenuItemCompat.setOnActionExpandListener(searchItem, this);
            MenuItemCompat.setActionView(searchItem, searchView);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_search:
                MenuItemCompat.expandActionView(item);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Player populatePlayer(Cursor cursor){
        try{
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.NAME));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.FULL_NAME));
            String idName = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.ID_NAME));
            String birthdate = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.BIRTHDATE));
            String height = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.HEIGHT));
            String dorsal = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.DORSAL));
            String nationality = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.NATIONALITY));
            String nickname = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.NICKNAME));
            String position = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.POSITION));
            String descriptionEs = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.DESCRIPTION_ES));
            String descriptionGl = cursor.getString(cursor.getColumnIndexOrThrow(ArboFsDB.ArboFs.DESCRIPTION_GL));

            return new Player(id, name, fullName, idName, birthdate, height, dorsal, nationality, nickname, position, descriptionEs, descriptionGl);


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*@Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getText(R.string.template));
        ArboFSApplication.hideKeyboard(this);
        players.clear();
        searchView.setQuery("", true);
        ArboFsSQLHelper helper = new ArboFsSQLHelper(TemplateActivity.this);
        Cursor cursor = helper.loadAllPlayers();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Player player = populatePlayer(cursor);
                players.add(player);
            }
            adapter = new TemplateAdapter(TemplateActivity.this, players);
            listPlayers.setAdapter(adapter);
        }
        return true;
    }*/
}
