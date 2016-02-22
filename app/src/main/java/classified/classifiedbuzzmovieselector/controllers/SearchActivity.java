package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity{
    final String KEY = "yedukp76ffytfuy24zsqk7f5";
    final int pagelimit = 10;
    SearchView search;

    RequestQueue queue;


    //for test purpose by Justeeeeen
    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                // TODO Auto-generated method stub
                Toast input = Toast.makeText(getBaseContext(), userInput,
                        Toast.LENGTH_SHORT);
                input.show();

                //move from onSearchButtonPressed to here | Begin

                String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=%s&page_limit=%d&page=1&apikey=%s",
                        userInput,
                        pagelimit, KEY);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SEARCH ACTIVITY", "Request Recieved.");

                        //resp is the response JSON Obj
                        //TODO Put info in movie objects
                        //then add to view
                        //Movie m = new Movie(resp.)

                        try {
                            JSONArray movies = resp.getJSONArray("movies");
                            JSONObject current = null;
                            for (int i = 0; i < movies.length(); i++) {
                                current = movies.getJSONObject(i);
                                Movie m = new Movie(current.get("title").toString(),
                                        Integer.parseInt(current.get("year").toString()),
                                        current.getString("mpaa_rating"),
                                        current.getInt("runtime"),
                                        current.getJSONObject("ratings").getInt("audience_score"),
                                        current.getJSONObject("ratings").getInt("critics_score")

                                );
                                MovieManager.add(m);
                            }
                            changeView(MovieManager.getMovies());
                        } catch (Exception e) {
                            Log.d("SEARCH ACTIVITY", "JSON Error.");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SEARCH ACTIVITY", "Request Error.");
                    }
                });

                //comment these out temporarily on 0202 by justeen
                //queue = Volley.newRequestQueue(this);
                //queue.add(jsObjRequest);
                //move from onSearchButtonPressed to here | End
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText,
                //Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
     * @param movies The results of the search, movies to show user
     */
    private void changeView(List<Movie> movies) {
        Intent intent = new Intent(this, listItemActivity.class);
        intent.putExtra("Movies", movies.toString());
        startActivity(intent);
    }
}
