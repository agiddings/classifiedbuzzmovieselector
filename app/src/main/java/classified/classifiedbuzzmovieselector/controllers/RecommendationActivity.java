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
import java.util.List;
import android.app.Activity;



import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.UserRating;
import classified.classifiedbuzzmovieselector.model.UserRatingManager;

/**
 * This class is the controller for handling recommendations
 */
public class RecommendationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
    }

    /**
     * This method will find the way the user wants to get their
     * recommendations, and then get the recommendations, and
     * then call changeview to display them to the user.
     */
    public void onGetRecommendationsButtonPressed() {
        List<Movie> movies = new ArrayList<Movie>();
        List<UserRating> recommendations = new ArrayList<UserRating>();
        //if (user) {
            //recommendations = UserRatingManager.getUserRatingsByUser(User );
        //} else if (major) {
            //recommendations = UserRatingManager.getUserRatingsByMajor(Major);
        //} else if (best/worst) {
            //movies = MovieManager.getBestMoviesByAvgRating();
        //}
        movies = UserRatingManager.getBestMoviesfromUserRatings(recommendations);
        changeView(movies);
    }

    /**
     *
     * Event when the cancel search button is pressed
     *
     * @param v The view, for recommendation activity
     */
    public void onCancelRecommendationButtonPressed(View v) {
        Log.d("RECOMMENDATION ACTIVITY", "Cancel recommendation button was pressed.");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to the view to display the movies
     * @param listOfMovies The results of the search, movies to show user
     */
    private void changeView(List<Movie> listOfMovies) {
        Log.d("RECOMMENDATION ACTIVITY", "Going to display results.");
        movieList.setAdapter(new MovieAdapter(this, R.layout.movie_layout, R.id.movieLayoutName, listOfMovies));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("RECOMMENDATION ACTIVITY", "Item was clicked.");
        Intent intent = new Intent(RecommendationActivity.this, MovieInformationActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.majors_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });*/
}

