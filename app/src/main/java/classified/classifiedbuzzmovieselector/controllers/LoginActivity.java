package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;

public class LoginActivity extends AppCompatActivity {

    /**
     * Only one manager instance is created
     */
    private static UserManager manager = new UserManager();

    /**
     * Make manager accessible to other classes
     */
    protected static UserManager getManager() {
        return manager;
    }

    /**
     * @param usermanager Can set manager if not already existing in other classes
     */
    protected static void setManager(UserManager usermanager) {
        manager = usermanager;
    }

    /**
     * So we know who the current user is
     */
    private static User user;

    /**
     * to access user form other classes
     */
    protected static User getUser() {
        return user;
    }

    /**
     * @param newUser set user form registration page
     */
    protected static void setUser(User newUser) {
        user = newUser;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * Handles login for the user.
     * @param v The current view
     */
    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        Log.d("LOGIN ACTIVITY", manager.toString());
        EditText emailBox = (EditText) findViewById(R.id.loginEmail);
        EditText passBox = (EditText) findViewById(R.id.loginPassword);

        CharSequence text;
        if (manager.handleLoginRequest(emailBox.getText().toString(), passBox.getText().toString())) {
            Log.d("LoginActivity", "Login Successful");
            text = "Login Successful";
            manager = new UserManager();
            user = manager.findUserByEmail(emailBox.getText().toString());

            //Goes to postlogin Screen
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        } else {
            text = "Login Failed";
            Log.d("LoginActivity", "Login Failed");
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    /**
     * Directs user to the registration page
     * @param v The current view seen by the user
     */
    public void onRegistrationLinkPressed(View v){
        Log.d("LOGIN ACTIVITY", "Registration Link Pressed");

        //Goes to registration page
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onForgotPasswordLinkPressed(View v){
        Intent intent = new Intent(this, UnableLoginActivity.class);
        startActivity(intent);
    }



    //Makes this the first screen that appears
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MYAPP**", "Pausing the opening screen");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("**MYAPP**", "Resuming the opening screen");
    }
}
