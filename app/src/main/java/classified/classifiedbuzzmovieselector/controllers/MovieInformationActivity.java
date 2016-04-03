package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classified.classifiedbuzzmovieselector.R;
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
    private RatingAdapter myAdapter;
    private List<UserRating> listOfRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        double mUserRating;
        String mCriticsRating;
        int position = getIntent().getIntExtra("position", 1);
        movie = MovieManager.getMovies().get(position);
        mUserRating = UserRatingManager.getAvgMovieUserRating(movie);
        mCriticsRating = movie.getMpaa_rating();
        ((TextView) findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.movie_year)).setText(movie.getYear() + "");
        ((TextView) findViewById(R.id.critics_rating)).setText(mCriticsRating);
        ((TextView) findViewById(R.id.app_users_rating)).setText(mUserRating + "");
        listOfRatings = (ArrayList<UserRating>) UserRatingManager.getUserRatingsByMovie(movie);
        ListView ratingList = (ListView) findViewById(R.id.ratingListView);
        myAdapter = new RatingAdapter(this,R.layout.rating_layout,R.id.ratingUsername, listOfRatings);
        ratingList.setAdapter(myAdapter);
        //getSupportActionBar().setTitle("Movie Detail");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        return true;
    }

    /**
     * This goes back to the home page
     * @param v the current view
     */
    public void onCancelMovieInformationButtonPressed(View v) {
        Log.d("MovieInfoActivity", "Movie Information was cancelled.");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * Creates a rating for the movie
     * @param v The current view
     */
    public void onMovieRatingButtonPressed(View v) {
        Log.d("MovieInfoActivity", "Rating button was pressed.");
        try {
            User user = UserManager.getLoggedUser();
            String comment = ((TextView) findViewById(R.id.comment)).getText().toString();
            RatingBar scoreR = (RatingBar) findViewById(R.id.ratingBar);
            float score = scoreR.getRating();
            if (user != null) {
                UserRating r = new UserRating(comment, score, movie, user);
                UserRatingManager.addUserRating(r);
                listOfRatings.add(r);
                myAdapter.notifyDataSetChanged();
            }
            Gson gson = new Gson();
            UserRatingManager userRatingManager = new UserRatingManager();
            String json = gson.toJson(userRatingManager.getUserRatings());
            try {
                FileOutputStream fos = openFileOutput("ratings.txt", Context.MODE_PRIVATE);
                fos.write(json.getBytes());
                fos.close();
            } catch (IOException e) {
                Log.d("MOVIE_INFORMATION_ACT", e.toString());
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