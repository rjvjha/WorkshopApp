package com.example.rjvjha.workshop.Utils;

/* This class provide static methods to do user authentication operations*/

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.rjvjha.workshop.data.WorkshopContract;
import com.example.rjvjha.workshop.data.WorkshopDbHelper;

import java.util.List;

public class AuthUtils {

    // constants keys for saving user data into preference file
    public static final String NAME_KEY = "name";
    public static final String USER_NAME_KEY = "user_name";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String LOGGED_IN_KEY = "is_user_logged_in";
    public static final String NEW_USER_SIGN_UP = "new_sign_up";



    // private constructor
    private AuthUtils(){
    }

    public static boolean isUserLoggedIn(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(LOGGED_IN_KEY,false);
    }


    synchronized public static boolean checkLoginCredentials(Context context,
                                                             String username,
                                                             String password){
        SharedPreferences prefs
                = PreferenceManager.getDefaultSharedPreferences(context);
        // get saved user credentials
        String regUserName = prefs.getString(USER_NAME_KEY,"user");
        String regUserPassword = prefs.getString(PASSWORD_KEY,"abc123");

        if (TextUtils.equals(regUserName, username)
                && TextUtils.equals(regUserPassword, password)){
            // Successful Login
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(LOGGED_IN_KEY, true);
            editor.apply();
            return true;
        }
        return false;
    }

    synchronized public static void logoutUser(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(LOGGED_IN_KEY, false);
        editor.apply();

    }

    synchronized public static boolean registerUser(Context context, List<String> userData){
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        boolean newSignUp = prefs.getBoolean(NEW_USER_SIGN_UP, false);

        if(newSignUp){
            // clear existing user credentials and data
            editor.clear();
            editor.apply();
            SQLiteDatabase db = new WorkshopDbHelper(context).getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS,
                    WorkshopContract.WorkshopEntry.APPLIED_FALSE);
            db.update(WorkshopContract.WorkshopEntry.TABLE_NAME,
                    cv,
                    null,
                    null);
        }

        if(userData!= null && !userData.isEmpty()){
            editor.putBoolean(NEW_USER_SIGN_UP, true);

            editor.putString(NAME_KEY, userData.get(0));
            editor.putString(USER_NAME_KEY,userData.get(1));
            editor.putString(EMAIL_KEY, userData.get(2));
            editor.putString(PASSWORD_KEY, userData.get(3));
            editor.apply();

            // user sign up successful
            return true;
        }



        return false;

    }
}
