package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import android.app.Activity;



import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;
import classified.classifiedbuzzmovieselector.model.UserRating;
import classified.classifiedbuzzmovieselector.model.UserRatingManager;

/**
 * This class is the controller for handling recommendations
 */
public class RecommendationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        movieList = (ListView) findViewById(R.id.movieResultList);
        movieList.setOnItemClickListener(this);
        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.majors_array,
                        android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                String major = (String) parent.getItemAtPosition(position);
                List<Movie> movies = MovieManager.getBestMoviesByMajor(major);
                changeView(movies);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    /**
     * This loads the list of recommendations for a particular user
     * Only loads recommendations that have 3+ stars
     * @param view Recommendation activity
     */
    public void onUserRecommendButtonPressed(View view) {
        Log.d("RecommendationActivity", "Button was pressed!");
        String user = ((TextView) findViewById(R.id.user_recommendation)).getText().toString();
        Log.d("RecommendationActivity", user);
        User u = UserManager.findUserByName(user);
        if (u != null) {
            Log.d("RecommendationActivity", "Found a user!");
            List<Movie> movies = MovieManager.getBestMoviesByFriendRating(u);
            changeView(movies);
        }
    }


    /**
     * Goes to the view to display the movies
     * @param listOfMovies The results of the recommendations, movies to show user
     */
    private void changeView(List<Movie> listOfMovies) {
        Log.d("RECOMMENDATION ACTIVITY", "Going to display results.");
        HashSet<Movie> noDuplicates = new HashSet<Movie>();
        for (Movie m : listOfMovies) {
            noDuplicates.add(m);
        }
        List<Movie> finalMovies = new ArrayList<Movie>(noDuplicates);
        MovieManager.setMovies(finalMovies);
        movieList.setAdapter(new MovieAdapter(this, R.layout.movie_layout, R.id.movieLayoutName, listOfMovies));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("RecommendationActivity", "Item was clicked.");
        Intent intent = new Intent(RecommendationActivity.this, MovieInformationActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * Goes back to home screen
     * @param v The current view
     */
    public void onBackToHome(View v){
        Log.d("Recommendationactivity", "Go to Home.");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}

