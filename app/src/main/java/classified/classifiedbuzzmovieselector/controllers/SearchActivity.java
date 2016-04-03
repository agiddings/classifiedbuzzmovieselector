package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final static String KEY = "yedukp76ffytfuy24zsqk7f5";
    final static String rottenTomatoesURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=%s&page_limit=%d&page=1&apikey=%s";
    final static int pagelimit = 15;
    private SearchView search;
    public static JSONArray movies;

    private RequestQueue queue;
    private ListView movieList;

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

        movieList = (ListView) findViewById(R.id.movieResultList);
        movieList.setOnItemClickListener(this);
        //connect search view to this controller
        search = (SearchView) findViewById(R.id.searchMovieView);
        search.setQueryHint("movie?");
        //setOnQueryTextFocusChangeListener
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
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
                return false;
            }
        });
    }

    /**
     * define behavior after a user clicks on search button with input
     * @param userInput The input the user typed into the search box
     */
    public void SearchButtonPressed(String userInput) {
        userInput = userInput.replaceAll(" ", "+");
        //so the search query(userInput in this case) should pass to the url string below
        String url = String.format(
                rottenTomatoesURL,
                userInput,
                pagelimit, KEY);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SearchActivity", "Request Recieved.");

                        try {
                            movies = resp.getJSONArray("movies");
                            Log.d("SearchActivity", "get movies");
                            JSONObject current = null;
                            MovieManager.clear();
                            for (int i = 0; i < movies.length(); i++) {
                                current = movies.getJSONObject(i);
                                Log.d("SearchActivity", String.format("got Movie %d",i));
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
                                Log.d("SearchActivity", String.format("made Movie %d",i));
                                m.setPoster(current.getJSONObject("posters").getString("thumbnail"));
                                MovieManager.add(m);
                            }
                            changeView(MovieManager.getMovies());
                        } catch (NullPointerException e) {
                            Log.d("SearchActivity", "Null Error.");
                        } catch (JSONException e) {
                            Log.d("Searchactivity", "JSON Error.");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SearchActivity", "Request Error.");
                    }
                });
        queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
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
                    Log.d("SearchActivity", "Request Recieved.");
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
                            m.setPoster(current.getJSONObject("posters").getString("thumbnail"));
                            MovieManager.add(m);
                        }
                        changeView(MovieManager.getMovies());
                    } catch(JSONException e) {
                        Log.d("SearchActivity", "JSON Error.");
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("SearchActivity", "Request Error.");
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
                    Log.d("SearchActivity", "Request Recieved.");
                    //resp is the response JSON Obj
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
                            m.setPoster(current.getJSONObject("posters").getString("thumbnail"));
                            MovieManager.add(m);
                        }
                        changeView(MovieManager.getMovies());
                    } catch(JSONException e) {
                        Log.d("SearchActivity", "JSON Error.");
                    };
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("SearchActivity", "Request Error.");
                }
            });
        queue =  Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }

    /**
     * Goes to the view to display the movies
     * @param listOfMovies The results of the search, movies to show user
     */
    private void changeView(List<Movie> listOfMovies) {
        movieList.setAdapter(new MovieAdapter(this, R.layout.movie_layout, R.id.movieLayoutName, listOfMovies)); //?movieLayoutname
    }

    /**
     * Navigate back to the home page
     * @param v The view, search activity
     */
    public void onBackButtonPressed(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SearchActivity", "Item was clicked.");
        Intent intent = new Intent(SearchActivity.this, MovieInformationActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}