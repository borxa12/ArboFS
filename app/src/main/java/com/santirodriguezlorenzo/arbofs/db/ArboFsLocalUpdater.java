package com.santirodriguezlorenzo.arbofs.db;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;

import com.santirodriguezlorenzo.arbofs.application.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Santi on 09/09/2015.
 */
public class ArboFsLocalUpdater extends AsyncTask<Void, Integer, Void> {

    private Activity activity;
    ProgressDialog pDialog;



    public ArboFsLocalUpdater(Activity activity) {

        this.activity = activity;
        pDialog = new ProgressDialog(activity);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(false);
        pDialog.setTitle("Actualizando base de datos");
        pDialog.setMax(62095360);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... key) {

        try {
            // Open your local db as the input stream
            InputStream myInput = activity.getAssets().open(
                    ArboFsSQLHelper.DB_NAME);


            final File dir = new File(ArboFsSQLHelper.DB_PATH);
            dir.mkdirs(); //create folders where write files
            final File file = new File(dir, ArboFsSQLHelper.DB_NAME);

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(file);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            int count = 0;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
                count += length;
                publishProgress(count);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                SharedPreferences preferences = activity.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Constants.DB_CREATE, true);
                editor.commit();
            }
        });



        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        pDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        pDialog.dismiss();
    }

}
