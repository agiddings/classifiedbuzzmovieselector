package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import classified.classifiedbuzzmovieselector.R;

public class PostLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
    }

    /**
     * Logouts user by switching to login screen
     * @param v The current view
     */
    public void onLogoutButtonClicked(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to Edit Profile Activity
     * @param v teh current view
     */
    public void onEditProfileButtonClicked(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
