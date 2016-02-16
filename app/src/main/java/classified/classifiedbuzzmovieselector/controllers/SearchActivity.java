package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import classified.classifiedbuzzmovieselector.R;

/**
 * Created by Allie on 2/16/2016.
 */
public class SearchActivity extends AppCompatActivity{

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
        //Call methods to search for movies
    }
}
