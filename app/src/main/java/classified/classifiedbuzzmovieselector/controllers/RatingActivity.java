package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Allie Giddings
 * This is the controller for rating that connects activity_rating.xml to UserRating and UserRatingManager
 */
public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    /**
     * Creates a rating for the movie
     * @param v The current view
     */
    public void onRatingButtonPressed(View v) {
        Log.d("RATINGACTIVITY", "Rating button was pressed.");
        //TODO:
        //Get rating (int) from user
        //Get user info
        //Get movie the rating goes with
        //Pass information to UserRating to create a rating
        //Add rating to userRatingManager
        CharSequence message;
        message = "Rating was unsuccessful. Please try again.";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast output = Toast.makeText(context, message, duration);
        output.show();
    }

    /**
     * Goes back to search results, cancels creation of the rating
     * @param v The current view
     */
    public void onRatingCancelledButtonPressed(View v) {
        Log.d("RATING ACTIVITY", "Rating was cancelled.");
        RecyclerView recList = (RecyclerView) findViewById(R.id.movieResultList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        MovieAdapter ma = new MovieAdapter(MovieManager.getMovies());
        recList.setAdapter(ma);
    }

}
