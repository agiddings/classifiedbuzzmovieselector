package classified.classifiedbuzzmovieselector.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;


/**
 * Created by Zhendong(Justeen) on 2/20/16.
 */
public class listItemActivity extends AppCompatActivity {
    private String listTitle;
    private int year;
    private boolean mTwoPane;

    private ArrayList<Movie> states;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
    }
}
