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
import classified.classifiedbuzzmovieselector.model.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.UserManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        UserManager manager = new UserManager();
        Log.d("LOGIN ACTIVITY", manager.toString());
        EditText emailBox = (EditText) findViewById(R.id.loginEmail);
        EditText passBox = (EditText) findViewById(R.id.loginPassword);
        //For testing purposes
        try {
            manager.addUser("test", "test@gmail.com", "pass");
        } catch(UserAlreadyExistsException e){
            Log.d("LOGIN ACTIVITY","Error adding");
        }

        CharSequence text;
        if (manager.handleLoginRequest(emailBox.getText().toString(), passBox.getText().toString())) {
            Log.d("LoginActivity", "Login Successful");

            text = "Login Successful";

            //should go to login Screen
            Intent intent = new Intent(this, PostLoginActivity.class);
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

    public void onRegistrationLinkPressed(View v){
        Log.d("LOGIN ACTIVITY", "Registration Link Pressed");

        //should go to registration page
        Intent intent = new Intent(this, RegistrationActivity.class);
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
