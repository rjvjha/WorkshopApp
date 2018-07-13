package com.example.rjvjha.workshop.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkshopDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "events.db";

    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 1;


    public WorkshopDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                WorkshopContract.WorkshopEntry.TABLE_NAME + "("
                + WorkshopContract.WorkshopEntry.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WorkshopContract.WorkshopEntry.COL_TITLE + " TEXT NOT NULL, "
                + WorkshopContract.WorkshopEntry.COL_DATE + " TEXT NOT NULL, "
                + WorkshopContract.WorkshopEntry.COL_DESCP + " TEXT, "
                + WorkshopContract.WorkshopEntry.COL_APPLY_STATUS + " INTEGER NOT NULL DEFAULT 0, "
                + WorkshopContract.WorkshopEntry.COL_REG_Student + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_TABLE);

    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL statement to drop table
        final String dropTableCommand = "DROP TABLE IF EXISTS " +
                WorkshopContract.WorkshopEntry.TABLE_NAME + ";";
        db.execSQL(dropTableCommand);
        onCreate(db);


    }
}
