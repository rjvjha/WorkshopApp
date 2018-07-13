package com.example.rjvjha.workshop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupScreenFragment extends Fragment {

    private EditText mNameField;
    private EditText mUserNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mCpasswordField;
    private Button mSignupButton;
    private Button mResetButton;
    private List<String> mValidUserData;




    public SignupScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_screen, container, false);
        setHasOptionsMenu(true);

        // code to move layouts up when soft keyboard is shown
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initialiseMemberVariables(view);

        // set onClick listeners
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRegistrationForm()){
                    // registration form is valid
                    if(hasPasswordMatched()){
                        // Register user details
                       if(AuthUtils.registerUser(getContext(), mValidUserData)){
                           Toast.makeText(getContext(),R.string.success_registration_feedback,
                                   Toast.LENGTH_SHORT).show();
                           // navigate to Login Screen
                           openFragmentScreen(new LoginScreenFragment());

                       }

                    } else{
                        // clear existing text in fields
                        mPasswordField.getText().clear();
                        mCpasswordField.getText().clear();
                        // Toast feedback
                        Toast.makeText(getContext(),
                                R.string.passwords_mismatch_feedback,
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                } else{
                    // Prompt the user to enter correct details
                    Toast.makeText(getContext(),
                            R.string.missing_info_feedback,
                            Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
        return view;
    }

    //  Modular code to initialise member variables
    private void initialiseMemberVariables(View view){
        mNameField = view.findViewById(R.id.name_edit_text);
        mUserNameField = view.findViewById(R.id.username_edit_text);
        mEmailField = view.findViewById(R.id.email_edit_text);
        mPasswordField = view.findViewById(R.id.first_password_edit_text);
        mCpasswordField = view.findViewById(R.id.confirm_password_edit_text);
        mSignupButton = view.findViewById(R.id.signup_button);
        mResetButton = view.findViewById(R.id.reset_button);
        mValidUserData = new ArrayList<>(5);

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

    // private helper method to check sanity of user-input
    private boolean validateRegistrationForm(){
        String name = mNameField.getText().toString().trim();
        String userName = mUserNameField.getText().toString().trim();
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        String cPassword = mCpasswordField.getText().toString().trim();
        String [] enteredData = new String[]{name, userName, email, password, cPassword};
        boolean isValid = false;

        for(String val : enteredData){
            if(!TextUtils.isEmpty(val)){
                mValidUserData.add(val);
                isValid = true;

            } else{
                // ignore user input
                mValidUserData.clear();
                isValid = false;
                break;
            }

        }
        return isValid;

    }
    // private helper method to check password mismatch
    private boolean hasPasswordMatched(){
        String password = mPasswordField.getText().toString().trim();
        String cPassword = mCpasswordField.getText().toString().trim();
        // returns true if password matches
        return TextUtils.equals(password, cPassword);
    }

    // private helper method to clear text in registration form
    private void resetForm(){
        mNameField.getText().clear();
        mUserNameField.getText().clear();
        mEmailField.getText().clear();
        mPasswordField.getText().clear();
        mCpasswordField.getText().clear();

    }

    // private helper method to open required fragment
    private void openFragmentScreen(final Fragment fragment){

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }




}
