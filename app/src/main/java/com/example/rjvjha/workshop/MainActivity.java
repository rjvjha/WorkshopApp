package com.example.rjvjha.workshop;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rjvjha.workshop.Utils.AuthUtils;
import com.example.rjvjha.workshop.Utils.FakeDataUtils;
import com.example.rjvjha.workshop.data.WorkshopDbHelper;
import com.example.rjvjha.workshop.fragments.DashboardScreenFragment;
import com.example.rjvjha.workshop.fragments.LoginScreenFragment;
import com.example.rjvjha.workshop.fragments.SignupScreenFragment;
import com.example.rjvjha.workshop.fragments.WorkshopsScreenFragment;

public class MainActivity extends AppCompatActivity {

    public static final int WORKSHOP_SCREEN_ID = 100;
    public static final int DASHBOARD_SCREEN_ID = 200 ;
    public static final String FIRST_LAUNCH_KEY = "first_launch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // insert dummy data into database
        WorkshopDbHelper dbHelper = new WorkshopDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean first_time = pref.getBoolean(FIRST_LAUNCH_KEY,true);
        // code to insert dummy data for first launch
        if(first_time){
            FakeDataUtils.insertDummyData(db);
        }


        // first screen navigation
        if(AuthUtils.isUserLoggedIn(this)){
            // user already logged-in open dashboard screen
            openFragmentScreen(new DashboardScreenFragment());
        } else{
            // otherwise open WorkshopsScreen Fragment
            openFragmentScreen(new WorkshopsScreenFragment());
        }
        firstTimeLaunch(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedId = item.getItemId();
        // open required fragment screen upon selection
        switch(selectedId){

            case R.id.login_action:
                openFragmentScreen(new LoginScreenFragment());
                break;
            case R.id.signup_action:
                openFragmentScreen(new SignupScreenFragment());
                break;
            case R.id.available_workshop_action:
                openFragmentScreen(new WorkshopsScreenFragment());
                break;
            case R.id.dashboard_action:
                openFragmentScreen(new DashboardScreenFragment());
                break;
            case R.id.logout_action:
                AuthUtils.logoutUser(this);
                openFragmentScreen(new WorkshopsScreenFragment());
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    // private helper method to open required fragment
    private void openFragmentScreen(final Fragment fragment){

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // register first-time app launch boolean in PreferenceFile
    private void firstTimeLaunch(boolean flag){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(FIRST_LAUNCH_KEY,flag);
        editor.apply();
    }
}
