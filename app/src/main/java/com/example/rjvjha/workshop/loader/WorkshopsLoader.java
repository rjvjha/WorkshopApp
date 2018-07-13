package com.example.rjvjha.workshop.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rjvjha.workshop.data.WorkshopContract;
import com.example.rjvjha.workshop.data.WorkshopDbHelper;


// class which queries database and loads data in background
public class WorkshopsLoader extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;
    private String selection;
    private String [] selectionArgs;

    // To be called in WorkshopsScreen Fragment
    public  WorkshopsLoader(Context context){
        super(context);
        mCursor = null;
        selection = null;
        selectionArgs = null;
    }
    // To be called in DashboardScreen Fragment
    public WorkshopsLoader(Context context, String selection,
                           String [] selectionArgs){
        super(context);
        this.mCursor = null;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            // Delivers any previously loaded data immediately
            deliverResult(mCursor);
        } else {
            // Force a new load
            forceLoad();
        }
    }


    @Override
    public Cursor loadInBackground() {
        WorkshopDbHelper dbHelper = new WorkshopDbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.query(WorkshopContract.WorkshopEntry.TABLE_NAME,
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
            return cursor;
        }
        return null;
    }

    public void deliverResult(Cursor data) {
        mCursor = data;
        super.deliverResult(data);
    }
}
