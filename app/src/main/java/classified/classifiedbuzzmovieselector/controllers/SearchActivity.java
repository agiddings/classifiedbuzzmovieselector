package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.net.URL;
import java.util.Scanner;

import classified.classifiedbuzzmovieselector.R;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity{
    final String KEY = "yedukp76ffytfuy24zsqk7f5";

    public static JSONObject urlToJson(String s) throws Exception {
        URL url = new URL(s);
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while(scan.hasNext())
            str += scan.nextLine();
        scan.close();
        JSONObject obj = new JSONObject(str);

        return obj;
    }

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
    }
}
