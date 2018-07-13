package com.example.rjvjha.workshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rjvjha.workshop.R;
import com.example.rjvjha.workshop.Utils.AuthUtils;

/**
 * This class provides user-interface for user login
 */
public class LoginScreenFragment extends Fragment {

    public static final String LOG_TAG = LoginScreenFragment.class.getSimpleName();
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mSignupButton;




    public LoginScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_screen, container, false);
        setHasOptionsMenu(true);
        initialiseMemberVariables(view);

        // code to move layouts up when soft keyboard is shown
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // set onClick listeners on buttons
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUsernameField.getText().toString().trim();
                String password = mPasswordField.getText().toString().trim();

                if(isInputValid(userName, password)){

                    boolean status = AuthUtils.checkLoginCredentials(getContext(),
                            userName,
                            password);

                    if(status){
                        // Successful login -navigate to Dashboard Screen
                        openFragmentScreen(new DashboardScreenFragment());
                    }else{
                        Toast.makeText(getContext(),
                                R.string.failed_attempt_feedback,
                                Toast.LENGTH_SHORT).show();
                        Log.d(LOG_TAG,"Entered credentials: " +userName + " " + password);
                    }
                }

            }
        });


        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new SignUp action
                // navigate to sign up fragment
                openFragmentScreen(new SignupScreenFragment());
            }
        });


        return view;
    }

    // hide menu-items which are not required
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem availableWorkshop = menu.findItem(R.id.available_workshop_action);
        MenuItem dashboard = menu.findItem(R.id.dashboard_action);
        MenuItem logout = menu.findItem(R.id.logout_action);
        MenuItem signUp = menu.findItem(R.id.signup_action);
        MenuItem login = menu.findItem(R.id.login_action);

        login.setVisible(false);
        signUp.setVisible(false);
        availableWorkshop.setVisible(false);
        dashboard.setVisible(false);
        logout.setVisible(false);
    }

    // Modular code to initialise member variables
    private void initialiseMemberVariables(View view){
        mUsernameField = view.findViewById(R.id.username_edit_text);
        mPasswordField = view.findViewById(R.id.password_edit_text);
        mLoginButton = view.findViewById(R.id.login_button);
        mSignupButton = view.findViewById(R.id.signup_button);

    }

    // private helper method to check valid input from user
    private boolean isInputValid(String user, String pwd){
        // return true if valid input
        return  (!TextUtils.isEmpty(user) &&
                !TextUtils.isEmpty(pwd));

    }

    // helper method to open required fragment
    public void openFragmentScreen(final Fragment fragment){

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }



}
