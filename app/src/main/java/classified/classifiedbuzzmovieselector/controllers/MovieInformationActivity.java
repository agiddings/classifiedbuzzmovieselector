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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;
import classified.classifiedbuzzmovieselector.model.UserRating;
import classified.classifiedbuzzmovieselector.model.UserRatingManager;

/**
 * Created by Allie Giddings on 2/26/16
 * Displays information about a specific movie to the user
 */
public class MovieInformationActivity extends AppCompatActivity {

    private Movie movie;
    private double mUserRating;
    private String mCriticsRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        int position = getIntent().getIntExtra("position", 1);
        movie = MovieManager.getMovies().get(position);
        mUserRating = UserRatingManager.getAvgMovieUserRating(movie);
        mCriticsRating = movie.getMpaa_rating();
        ((TextView) findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.movie_year)).setText(movie.getYear() + "");
        ((TextView) findViewById(R.id.critics_rating)).setText(mCriticsRating);
        ((TextView) findViewById(R.id.app_users_rating)).setText(mUserRating + "");

        ArrayList<UserRating> listOfRatings = (ArrayList<UserRating>) UserRatingManager.getUserRatingsByMovie(movie);
        ListView ratingList = (ListView) findViewById(R.id.ratingListView);
        ratingList.setAdapter(new RatingAdapter(this,R.layout.rating_layout,R.id.ratingUsername, listOfRatings));
    }

    /**
     * This goes back to the home page
     * @param v the current view
     */
    public void onCancelMovieInformationButtonPressed(View v) {
        Log.d("MOVIE INFORMATION", "Movie Information was cancelled.");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * Creates a rating for the movie
     * @param v The current view
     */
    public void onMovieRatingButtonPressed(View v) {
        Log.d("RATINGACTIVITY", "Rating button was pressed.");
        try {
            User user = UserManager.getLoggedUser();
            String comment = ((TextView) findViewById(R.id.comment)).getText().toString();
            RatingBar scoreR = (RatingBar) findViewById(R.id.ratingBar);
            float score = scoreR.getRating();
            if (user != null) {
                UserRating r = new UserRating(comment, score, movie, user);
                UserRatingManager.addUserRating(r);

                ArrayList<UserRating> listOfRatings = (ArrayList<UserRating>) UserRatingManager.getUserRatingsByMovie(movie);
                ListView ratingList = (ListView) findViewById(R.id.ratingListView);
                ratingList.setAdapter(new RatingAdapter(this, R.layout.rating_layout, R.id.ratingUsername, listOfRatings));
            }

        } catch (Exception e) {
            CharSequence message;
            message = "Rating was unsuccessful. Please try again.";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast output = Toast.makeText(context, message, duration);
            output.show();
        }
    }
}