package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.UserManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(UserManager.getLoggedUser().isAdmin()){
            Button b = (Button) findViewById(R.id.goToAdmin);
            b.setVisibility(View.VISIBLE);
        }
        getSupportActionBar().setTitle("Home");
        String welcome = "Welcome back "+UserManager.getLoggedUser().getName()+"!";
        ((TextView) findViewById(R.id.welcomeMsg)).setText(welcome);
    }


    /**
     * Logs out a user by switching to login screen
     * @param v The current view
     */
    public void onLogoutButtonClicked(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to recommendations page
     * @param v The current view
     */
    public void onRecommendationButtonClicked(View v) {
        Intent intent = new Intent(this, RecommendationActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to Edit Profile Activity
     * @param v the current view
     */
    public void onEditProfileButtonClicked(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * define behavior after a user clicks on the go to search button
     *
     * @param v
     */
    public void onGoToSearchButtonClicked(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to Admin Activity
     * @param v the current view
     */
    public void onAdminButtonClicked(View v) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}