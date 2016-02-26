package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import classified.classifiedbuzzmovieselector.R;

/**
 * Created by Allie Giddings on 2/26/16
 * Displays information about a specific movie to the user
 */
public class MovieInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
    }

    /**
     * Navigates to the rating activity so a user can rate a movie
     * @param v The current view the user sees
     */
    public void onRatingButtonPressed(View v) {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }
}
