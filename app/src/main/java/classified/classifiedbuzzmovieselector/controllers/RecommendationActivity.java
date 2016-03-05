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

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;

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
        List<Movie> recommendations = new ArrayList<Movie>();
        changeView(recommendations);
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
        movieList.setAdapter(new MovieAdapter(this, R.layout.movie_layout, R.id.movieLayoutName, listOfMovies));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SEARCHACTIVITY", "Item was clicked.");
        Intent intent = new Intent(RecommendationActivity.this, MovieInformationActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
