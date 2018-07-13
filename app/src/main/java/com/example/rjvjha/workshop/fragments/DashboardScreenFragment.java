package com.example.rjvjha.workshop.fragments;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rjvjha.workshop.MainActivity;
import com.example.rjvjha.workshop.R;
import com.example.rjvjha.workshop.Utils.AuthUtils;
import com.example.rjvjha.workshop.adapters.WorkshopsAdapter;
import com.example.rjvjha.workshop.data.WorkshopContract;
import com.example.rjvjha.workshop.loader.WorkshopsLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardScreenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView mheadlinetextView;
    private RecyclerView mRecyclerView;
    private WorkshopsAdapter mAdapter;
    private static final int sLoaderId = 2;


    public DashboardScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_screen, container, false);
        setHasOptionsMenu(true);
        initialiseMemberVariables(view);
        // set name of logged-in user
        setNameInHeadLine();

        // setting up recycler view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        // initialise and trigger loader
        getActivity().getSupportLoaderManager().initLoader(sLoaderId, null, this);


        return view;


    }

    // hide menu-items which are not required
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem dashboard = menu.findItem(R.id.dashboard_action);
        MenuItem logout = menu.findItem(R.id.logout_action);
        MenuItem signUp = menu.findItem(R.id.signup_action);
        MenuItem login = menu.findItem(R.id.login_action);

        login.setVisible(false);
        signUp.setVisible(false);
        dashboard.setVisible(false);
    }



    // Modular code to initialise member variables
    private void initialiseMemberVariables(View view){
        mheadlinetextView = view.findViewById(R.id.dashboard__headline_textView);
        mRecyclerView = view.findViewById(R.id.dashboard_recyclerView);
        mAdapter = new WorkshopsAdapter(getContext(), MainActivity.DASHBOARD_SCREEN_ID, null);

    }



    // private method to set Name of user to headline text
    private void setNameInHeadLine(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String msg = getString(R.string.dashboard_screen_headline) + " " +
                pref.getString(AuthUtils.NAME_KEY,"user");
        mheadlinetextView.setText(msg);
    }

    private List<String []> getDummyText(){
        List<String []> dummyData = new ArrayList<>(10);
        String [] dummyItem = {"A", "Android Workshop", "8 July 2018", getString(R.string.dummy_descp_text)} ;
        for(int i = 0 ; i < 10; i++){
            dummyData.add(dummyItem);
        }
        return dummyData;
    }

    @Override
    public void onResume() {
        super.onResume();
        //re-queries for all new applied Workshops
        getActivity().getSupportLoaderManager().restartLoader(sLoaderId,null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = WorkshopContract.WorkshopEntry.COL_APPLY_STATUS +"=?";
        String selArgs [] = new String[]{"1"};
        return new WorkshopsLoader(getContext(), selection, selArgs);
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
