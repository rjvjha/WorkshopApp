package com.example.rjvjha.workshop.fragments;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.rjvjha.workshop.MainActivity;
import com.example.rjvjha.workshop.R;
import com.example.rjvjha.workshop.Utils.AuthUtils;
import com.example.rjvjha.workshop.adapters.WorkshopsAdapter;
import com.example.rjvjha.workshop.data.WorkshopContract;
import com.example.rjvjha.workshop.data.WorkshopDbHelper;
import com.example.rjvjha.workshop.loader.WorkshopsLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopsScreenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private WorkshopsAdapter mAdapter;
    private static final int sLoaderId = 1;


    public WorkshopsScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshops_screen, container, false);
        setHasOptionsMenu(true);
        initialiseMemberVariables(view);

        // setup recyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        // initialise and trigger loader
        getActivity().getSupportLoaderManager().initLoader(sLoaderId, null, this);


        return view;
    }

    // Modular code to initialise member variables
    private void initialiseMemberVariables(View view) {
        mRecyclerView = view.findViewById(R.id.workshop_recyclerView);
        mAdapter = new WorkshopsAdapter(getContext(), MainActivity.WORKSHOP_SCREEN_ID,this);
    }

    // hide menu-items which are not required
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem availableWorkshop = menu.findItem(R.id.available_workshop_action);
        MenuItem dashboard = menu.findItem(R.id.dashboard_action);
        MenuItem logout = menu.findItem(R.id.logout_action);

        if(AuthUtils.isUserLoggedIn(getContext())){
            menu.findItem(R.id.login_action).setVisible(false);
            menu.findItem(R.id.signup_action).setVisible(false);
        }


        availableWorkshop.setVisible(false);
        if(!AuthUtils.isUserLoggedIn(getContext())){
            dashboard.setVisible(false);
        }
        logout.setVisible(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // re-queries for all available Workshops
        getActivity().getSupportLoaderManager().restartLoader(sLoaderId,null, this);
    }

    // static method to update a workshop applied status in database
    public long registerUserInWorkshop(int id, Context context, ContentValues values){
        WorkshopDbHelper dbHelper = new WorkshopDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String rowId = String.valueOf(id);
        String selection = WorkshopContract.WorkshopEntry.COL_ID + "=?";
        String selArgs[] = new String[]{rowId};
        long rowUpdated = db.update(WorkshopContract.WorkshopEntry.TABLE_NAME,
                values,
                selection,
                selArgs);
        // restart loader
        getActivity().getSupportLoaderManager().restartLoader(sLoaderId, null, this);
        return rowUpdated;

    }

    // private helper method to open required fragment
    public void openFragmentScreen(final Fragment fragment){

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }



    // Loader Callbacks
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new WorkshopsLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data!=null){
            mAdapter.swapCursor(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }
}
