package com.santirodriguezlorenzo.arbofs.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Santi on 09/09/2015.
 */
public class ArboFsSQLHelper extends SQLiteOpenHelper {

    // The Android's default system path of your application database.
    public static String DB_PATH = "/data/data/com.santirodriguezlorenzo.arbofs/databases/";

    public static String DB_NAME = "arbofs.db";

    public static int DB_VERSION = 1;

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public ArboFsSQLHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        myDataBase = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        if (db.isReadOnly()) {
            db = getWritableDatabase();
        }

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA automatic_index = off;");
        }
    }

    public Cursor loadAllPlayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM players ORDER BY _id ASC", null);
        return c;
    }

}
