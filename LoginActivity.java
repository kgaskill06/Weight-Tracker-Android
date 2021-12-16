package com.example.weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weighttracker.WeightDatabaseActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    // variables for initializing class reference variables
    private WeightDatabaseActivity mWeightDB;
    private TextView mError;
    private UserDAO mUserDao;
    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // load singleton of database
        mWeightDB = WeightDatabaseActivity.getInstance(getApplicationContext());
        mUserDao  = mWeightDB.userDao();

    }

    public boolean validateLogin(String username, String password) {

        List<User> userList = mUserDao.getUsers();

                // iterates through user list by size and authenticates by checking if user&password equals

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }


    public void onLoginClick(View view){
        String username = ((EditText) findViewById(R.id.usernameEdit)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEdit)).getText().toString();

        boolean isAuthenticated = validateLogin(username, password);
        if (isAuthenticated) {

            mUser = new User(username,password);
            loadWeightActivity();
        } else {
            mError.setText(R.string.error);
        }


    }

    // new user button clicked
    public void onNewUser(View view) {
        boolean userFound; // true false condition if user is found
        String username = ((EditText) findViewById(R.id.usernameEdit)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEdit)).getText().toString();

        // if user name and password is not empty, pull all users from list
        if (!username.isEmpty() && !password.isEmpty()) {
            List<User> userList = mUserDao.getUsers();
            userFound = false;

            // iterates through userList to see if username exists
            if (userList.size() > 0) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUsername().equals(username)) {
                        userFound = true;
                    }
                }
            }

            // if username is not found, create new user
            if (!userFound) {
                mUserDao.insertUser(new User(username, password));
            } else {
                mError.setText(R.string.error);
            }
        }
    }

    /*
 changes screen to Weight Activity after user is authenticated
  */
    public void loadWeightActivity() {
        Intent intent = new Intent(this, WeightActivity.class);
        intent.putExtra("user", mUser);
        startActivity(intent);
    }
}