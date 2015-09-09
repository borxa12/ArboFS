package com.santirodriguezlorenzo.arbofs.activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.adapter.TemplateAdapter;
import com.santirodriguezlorenzo.arbofs.db.ArboFsDB;
import com.santirodriguezlorenzo.arbofs.db.ArboFsSQLHelper;
import com.santirodriguezlorenzo.arbofs.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TemplateActivity extends AppCompatActivity {

    private List<Player> players;

    // Vistas
    private ListView listPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.template);

        listPlayers = (ListView) findViewById(R.id.list_players);

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

            cursor.close();
        }

        TemplateAdapter adapter = new TemplateAdapter(this, players);
        listPlayers.setAdapter(adapter);
        listPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player player = (Player) parent.getAdapter().getItem(position);
                Toast.makeText(TemplateActivity.this, player.getDescriptionEs(), Toast.LENGTH_SHORT).show();
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
}
