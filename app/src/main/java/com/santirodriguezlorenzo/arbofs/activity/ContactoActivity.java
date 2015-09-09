package com.santirodriguezlorenzo.arbofs.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.santirodriguezlorenzo.arbofs.R;

public class ContactoActivity extends AppCompatActivity {

    private String message, subject;
    private EditText editTextMessage,editTextSubject;
    private LinearLayout btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.contact);

        editTextMessage = (EditText) findViewById(R.id.edit_text_message);
        editTextSubject = (EditText) findViewById(R.id.edit_text_subject);
        btnSend = (LinearLayout) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = editTextMessage.getText().toString();
                subject = editTextSubject.getText().toString();

                if(!message.isEmpty() || !subject.isEmpty()){
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"muebles2000fs@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, subject);
                    i.putExtra(Intent.EXTRA_TEXT   , message);
                    try {
                        startActivity(Intent.createChooser(i, getText(R.string.send_email)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactoActivity.this, getText(R.string.no_clients_email_installed), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(message.isEmpty()){
                        editTextMessage.setError(getText(R.string.error_empty_message));
                    }
                    if(subject.isEmpty()){
                        editTextSubject.setError(getText(R.string.error_empty_subject));
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_location:
                Toast.makeText(ContactoActivity.this, "Ver mapa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_navegation:
                Toast.makeText(ContactoActivity.this, "Como llegar", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
