package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import classified.classifiedbuzzmovieselector.R;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity{
    final String KEY = "yedukp76ffytfuy24zsqk7f5";

    RequestQueue queue =  Volley.newRequestQueue(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
     *
     *  Event when the search button is pressed
     *
     * @param v The view, for search activity
     */
    public void onSearchButtonPressed(View v) {
        Log.d("SEARCH ACTIVITY", "Search button was pressed.");
        //Get text from text box that user is inputting
        //Check to see if Movie is in database
            //If so display info about movie
            //If not display message to the user saying the movie was not located.


        String userinput = "";//TODO fill this in when GUI is complete
        int pagelimit = 10;

        String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=%s&page_limit=%d&page=1&apikey=%s",userinput, pagelimit, KEY);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SEARCH ACTIVITY", "Request Recieved.");
                        //resp is the response JSON Obj
                        //TODO Put info in movie objects
                        //then add to view
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SEARCH ACTIVITY", "Request Error.");
                    }
                });
        queue.add(jsObjRequest);

    }

    /**
     * Searches for new releases
     * @param v current view
     */
    public void onSearchNewRealeases(View v) {
        int pagelimit = 10;
        String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=%d&page=1&country=us&apikey=y%s", pagelimit, KEY);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SEARCH ACTIVITY", "Request Recieved.");
                        //resp is the response JSON Obj
                        //TODO Put info in movie objects
                        //then add to view
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SEARCH ACTIVITY", "Request Error.");
                    }
                });
        queue.add(jsObjRequest);
    }

    /**
     * Searches for new DVD releases
     * @param v current view
     */
    public void onSearchNewDVDs(View v) {
        int pagelimit = 10;
        String url = String.format("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=%d&page=1&country=us&apikey=%s", pagelimit, KEY);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("SEARCH ACTIVITY", "Request Recieved.");
                        //resp is the response JSON Obj
                        //TODO Put info in movie objects
                        //then add to view
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SEARCH ACTIVITY", "Request Error.");
                    }
                });
        queue.add(jsObjRequest);

    }
}