package com.santirodriguezlorenzo.arbofs.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.fragment.ChangeFragment;
import com.santirodriguezlorenzo.arbofs.model.Player;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlayerActivity extends AppCompatActivity {

    public static String PARAM_PLAYER = "PLAYER";

    private String playerString;
    private Player player;

    // Action bar
    private ActionBar actionBar;

    // Vistas
    private TextView txtName;
    private ImageView imgPlayer;
    private TextView txtDorsal;
    private TextView txtPosition;
    private TextView txtEdad;
    private TextView txtHeight;
    private TextView txtDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        proccessParams();
        injectViews();
        coverData();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void proccessParams(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            playerString = bundle.getString(PARAM_PLAYER);
            player = new Gson().fromJson(playerString, Player.class);
        }

    }

    private void injectViews() {
        txtName = (TextView) findViewById(R.id.txt_name);
        txtDorsal = (TextView) findViewById(R.id.txt_dorsal);
        txtEdad = (TextView) findViewById(R.id.txt_edad);
        txtPosition = (TextView) findViewById(R.id.txt_position);
        txtHeight = (TextView) findViewById(R.id.txt_height);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        imgPlayer = (ImageView) findViewById(R.id.img_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void coverData(){

        if(player != null){
            txtName.setText(player.getName());
            txtDorsal.setText(player.getDorsal());
            txtPosition.setText(player.getPosition());
            int edad = calcularEdad(player.getBirthdate());
            txtEdad.setText(edad +" años");
            txtHeight.setText(player.getHeight() + " cm");
            //txtDescription.setText(player.getDescriptionEs());
            // load image
            try {
                // get input stream
                InputStream ims = getAssets().open(player.getIdName() + ".jpg");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                imgPlayer.setImageDrawable(d);
            }
            catch(IOException ex) {

            }
        }

    }

    public static Integer calcularEdad(String fecha){
        Date fechaNac=null;
        try {
            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:"+ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }

}
