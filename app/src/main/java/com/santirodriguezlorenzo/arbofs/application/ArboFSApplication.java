package com.santirodriguezlorenzo.arbofs.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Santi on 16/09/2015.
 */
public class ArboFSApplication extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();

    }

    public static Context getAppContext() {
        return context;
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        //View v = ((Activity) ctx).getCurrentFocus();
        View w = ((Activity) ctx).findViewById(android.R.id.content).getRootView();
        if (w == null){
            return;
        }

        inputManager.hideSoftInputFromWindow(w.getWindowToken(), 0);
    }
}
