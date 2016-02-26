package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity{
    final String KEY = "yedukp76ffytfuy24zsqk7f5";
    final String rottenTomatoesURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=%s&page_limit=%d&page=1&apikey=%s";
    final int pagelimit = 10;
    SearchView search;
    //for testing 0223 - Justeen
    //TextView MovieYear;
    //TextView MovieName;
    static JSONArray movies;

    RequestQueue queue;

    /**
     * Gets the list of movies from the JSON request
     * @return The movies from the result of the search
     */
    public static JSONArray getJSONArray() {
        return movies;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //MovieYear = (TextView) findViewById(R.id.movieYear);
        //MovieName = (TextView) findViewById(R.id.movieName);

        //connect search view to this controller
        search = (SearchView) findViewById(R.id.searchMovieView);
        search.setQueryHint("movie?");

        //setOnQueryTextFocusChangeListener
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                String click;
                if (hasFocus) {
                    click = "user clicked on search";
                } else {
                    click = "user withdraw from search";
                }
                Toast status = Toast.makeText(getBaseContext(),
                        String.valueOf(click),
                        Toast.LENGTH_SHORT);
                status.show();
            }
        });

        //setOnQueryTextListener, so it detects users input
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String userInput) {
                Log.d("SEARCH ACTIVITY", userInput);
                Toast input = Toast.makeText(getBaseContext(), "Searching " + userInput,
                        Toast.LENGTH_SHORT);
                input.show();
                SearchButtonPressed(userInput);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //	Toast.makeText(getBaseContext(), newText,
                //Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    /**
     * define behavior after a user clicks on search button with input
     *
     * @param userInput
     */
    public void SearchButtonPressed(String userInput) {

        userInput = userInput.replaceAll(" ", "+");
        //so the search query(userInput in this case) should pass to the url string below
        String url = String.format(
                rottenTomatoesURL,
                userInput,
                pagelimit, KEY);

        //test with "deadpool" above see if it come up


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SEARCH ACTIVITY", "Request Recieved.");

                        try {
                            movies = resp.getJSONArray("movies");
                            Log.d("SEARCH ACTIVITY", "get movies");
                            JSONObject current = null;
                            MovieManager.clear();
                            for (int i = 0; i < movies.length(); i++) {
                                current = movies.getJSONObject(i);
                                Log.d("SEARCH ACTIVITY", String.format("got Movie %d",i));
                                String runtime = current.getString("runtime");
                                int runtimeInt = 0;
                                if(!runtime.equals("")){
                                   runtimeInt = Integer.parseInt(runtime);
                                }
                                Movie m = new Movie(current.get("title").toString(),
                                        Integer.parseInt(current.get("year").toString()),
                                        current.getString("mpaa_rating"),
                                        runtimeInt,
                                        current.getJSONObject("ratings").getInt("audience_score"),
                                        current.getJSONObject("ratings").getInt("critics_score")

                                );
                                Log.d("SEARCH ACTIVITY", String.format("made Movie %d",i));
                                MovieManager.add(m);

                                //display to text movie year for testing - Justeen
                                //MovieName.setText("Movie name: " + m.getTitle());
                                //MovieYear.setText("Movie Year: " + String.valueOf(m.getYear()));
                            }
                            changeView(MovieManager.getMovies());



                        } catch(NullPointerException e){
                            Log.d("SEARCH ACTIVITY", "Null Error.");
                        }catch(JSONException e) {
                            Log.d("SEARCH ACTIVITY", "JSON Error.");
                        } catch (Exception e) {
                            Log.d("SEARCH ACTIVITY", "Error.");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SEARCH ACTIVITY", "Request Error.");
                    }
                });
        queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }


    /**
     *
     * Event when the cancel search button is pressed
     *
     * @param v The view, for search activity
     */
    public void onCancelSearchButtonPressed(View v) {
        Log.d("SEARCH ACTIVITY", "Cancel search button was pressed.");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Searches for new releases
     * @param v current view
     */
    public void onSearchNewReleases(View v) {
        String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=%d&page=1&country=us&apikey=%s", pagelimit, KEY);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject resp) {
                    Log.d("SEARCH ACTIVITY", "Request Recieved.");
                    //resp is the response JSON Obj
                    try {
                        JSONArray movies = resp.getJSONArray("movies");
                        JSONObject current = null;
                        MovieManager.clear();
                        for (int i = 0; i < movies.length(); i++) {
                            current = movies.getJSONObject(i);
                            //Initializes each movie from search to add to a list of movies
                            Movie m = new Movie(current.get("title").toString(),
                                    Integer.parseInt(current.get("year").toString()),
                                    current.getString("mpaa_rating"),
                                    current.getInt("runtime"),
                                    current.getJSONObject("ratings").getInt("audience_score"),
                                    current.getJSONObject("ratings").getInt("critics_score")

                            );
                            MovieManager.add(m);
                            //display to text movie year for testing - Cole
                            //MovieName.setText("Movie name: " + m.getTitle());
                            //MovieYear.setText("Movie Year: " + String.valueOf(m.getYear()));
                        }
                        //Goes to display the movies
                        changeView(MovieManager.getMovies());
                    } catch(JSONException e) {
                        Log.d("SEARCH ACTIVITY", "JSON Error.");
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("SEARCH ACTIVITY", "Request Error.");
                }
            });
        queue =  Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }

    /**
     * Searches for new DVD releases
     * @param v current view
     */
    public void onSearchNewDVDs(View v) {
        String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=%d&page=1&country=us&apikey=%s", pagelimit, KEY);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject resp) {
                    Log.d("SEARCH ACTIVITY", "Request Recieved.");
                    //resp is the response JSON Obj
                    //TODO Put info in movie objects
                    //then add to view
                    try {
                        MovieManager.clear();
                        JSONArray movies = resp.getJSONArray("movies");
                        JSONObject current = null;
                        for (int i = 0; i < movies.length(); i++) {
                            current = movies.getJSONObject(i);
                            //Initializes a movie to add to the list of results
                            Movie m = new Movie(current.get("title").toString(),
                                    Integer.parseInt(current.get("year").toString()),
                                    current.getString("mpaa_rating"),
                                    current.getInt("runtime"),
                                    current.getJSONObject("ratings").getInt("audience_score"),
                                    current.getJSONObject("ratings").getInt("critics_score")
                                        );
                            MovieManager.add(m);

                            //display to text movie year for testing - Cole
                            //MovieName.setText("Movie name: " + m.getTitle());
                            //MovieYear.setText("Movie Year: " + String.valueOf(m.getYear()));
                        }
                        //Goes to display the movies
                        changeView(MovieManager.getMovies());
                    } catch(JSONException e) {
                        Log.d("SEARCH ACTIVITY", "JSON Error.");
                    };
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("SEARCH ACTIVITY", "Request Error.");
                }
            });
        queue =  Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }

    /**
     * Goes to the view to display the movies
     * @param movieList The results of the search, movies to show user
     */
    private void changeView(List<Movie> movieList) {
        RecyclerView recList = (RecyclerView) findViewById(R.id.movieResultList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        MovieAdapter ma = new MovieAdapter(movieList);
        recList.setAdapter(ma);
    }

    /**
     * Navigate back to the home page
     *
     * @param v
     */
    public void onBackButtonPressed(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }



    /**
     * Navigates to the movie information page
     * @param v The view the user sees
     */
    public void onMovieInformationButtonPressed(View v) {
        try {
            String title = ((TextView)(findViewById(R.id.movieLayoutName))).toString();
            int year = Integer.parseInt(((TextView)findViewById(R.id.movieLayoutYear)).toString());
            Movie x = MovieManager.getMovieByTitleAndYear(title, year);
            MovieManager.setSelectedMovie(x);
        } catch (MovieDoesNotExistException e) {
            Log.d("SEARCH ACTIVITY", "Movie does not exist.");
        }
        Intent intent = new Intent(this, MovieInformationActivity.class);
        startActivity(intent);
    }
}
