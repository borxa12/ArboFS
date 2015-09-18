package com.santirodriguezlorenzo.arbofs.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santirodriguezlorenzo.arbofs.R;

import java.io.IOException;
import java.util.Date;

public class ContactoActivity extends AppCompatActivity {

    // Vista
    private GoogleMap mMap;
    private CardView cardContact;
    private CardView cardDirections;

    //Location
    private LocationManager mlocManager;
    private MyLocationListener mlocListener;
    private Location myLocation;
    private Location lastLocation;

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

        getMyLocation();

        cardContact = (CardView) findViewById(R.id.card_contact);
        cardDirections = (CardView) findViewById(R.id.card_directions);

        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "muebles2000fs@gmail.com", null));
                //startActivity(Intent.createChooser(emailIntent, getText(R.string.send_email)));
                startActivity(emailIntent);
            }
        });

        cardDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng contentPosition = new LatLng(42.108221, -8.303280);
                if(myLocation!=null) {
                    LatLng myPosition = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                    getDirections(myPosition, contentPosition);

                }
                else{
                    Toast.makeText(ContactoActivity.this, R.string.error_no_location, Toast.LENGTH_SHORT).show();
                }
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
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(42.108221, -8.303280))
                .title("Pabellón municipal de Arbo");
        mMap.addMarker(markerOptions);

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(42.108221,-8.303280), 15);
        mMap.animateCamera(cu);

    }


    private void axustarBBox(LatLngBounds.Builder builder, int padding, int zoom) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), metrics.widthPixels, metrics.heightPixels, padding);
        mMap.moveCamera(cameraUpdate);

        if (mMap.getCameraPosition().zoom > zoom) {
            cameraUpdate = CameraUpdateFactory.zoomTo(zoom);
            mMap.moveCamera(cameraUpdate);
        }
    }

    private void getDirections(LatLng fromPosition, LatLng toPosition){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + fromPosition.latitude + "," + fromPosition.longitude + "&daddr=" + toPosition.latitude + "," + toPosition.longitude));
        startActivity(intent);


    }

    private void getMyLocation(){
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        if(mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            lastLocation = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Date now = new Date();
            if(lastLocation!=null && lastLocation.getTime()+(1*5*60*1000)>now.getTime()	){
                myLocation = lastLocation;
            }
            else{
                //mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, (3*60*1000), 0, mlocListener);
                //mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,0,0,  mlocListener);

            }
        }
        else{
            //if(mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!=null){
            lastLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Date now = new Date();
            if(lastLocation!=null && lastLocation.getTime()+(1*5*60*1000)>now.getTime()	){
                myLocation = lastLocation;
            }
            else{
                //mlocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

                //mlocManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, mlocListener, null);
            }
            //}
        }

        if(myLocation==null){
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,  mlocListener);
            mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);
        }
    }

    private class MyLocationListener  implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if(location != null){
                mlocManager.removeUpdates(mlocListener);
                myLocation = location;


            }
        }

        @Override
        public void onProviderDisabled(String arg0) {


        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    protected void onStop (){
        super.onStop();
        mlocManager.removeUpdates(mlocListener);
    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        mlocManager.removeUpdates(mlocListener);
    }

}
