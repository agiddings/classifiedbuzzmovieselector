package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ITEMDETAILACTIVIYT", "oncreate");
        setContentView(R.layout.activity_item_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        List<Movie> m = MovieManager.getMovies();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ITEMDETAILACTIVITY", "Onoptionsitemselected");
        int id = item.getItemId();
        if (id == android.R.id.home) {
            new Intent(this, listItemActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
