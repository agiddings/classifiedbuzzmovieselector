package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;

/**
 * Created by Cole on 2/23/2016.
 */

/**
 * MovieAdapter is the controller for displaying the search results
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    /**
     * This method sets the view for the list of movies
     * @param position The position of a movie in the list
     * @param convertView The convertView
     * @param parent The parent view that has the list of movies
     * @return The view with the movie
     */
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        Movie movie = getItem(position);
        TextView year = (TextView) view.findViewById(R.id.movieLayoutYear);
        TextView name = (TextView) view.findViewById(R.id.movieLayoutName);
        //WebView poster = (WebView) view.findViewById(R.id.movieLayoutPoster);
        year.setText(Integer.toString(movie.getYear()));
        name.setText(movie.getTitle());
        //poster.loadUrl(movie.getPoster());
        return view;
    }

    /**
     * This method sets up the list of movies in the view
     * @param context The context of the list of movies
     * @param resource The number of movies
     * @param textViewResourceId The textviewresourceid
     * @param objects The list of movie objects to put in the view
     */
    public MovieAdapter(Context context, int resource, int textViewResourceId, List<Movie> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }
}