package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;

/**
 * Created by coleb_000 on 2/23/2016.
 */

/**
 * MovieAdapter is the controller for displaying the search results
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    //The list of movies to display
    private List<Movie> movieList;

    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        Movie movie = (Movie)getItem(position);

        TextView year = (TextView) view.findViewById(R.id.movieLayoutYear);
        TextView name = (TextView) view.findViewById(R.id.movieLayoutName);
        WebView poster = (WebView) view.findViewById(R.id.movieLayoutPoster);

        year.setText(Integer.toString(movie.getYear()));
        name.setText(movie.getTitle());
        poster.loadUrl(movie.getPoster());

        return view;
    }

    public MovieAdapter(Context context, int resource, int textViewResourceId, List<Movie> objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @Override
    public boolean isEnabled(int position){
        return true;
    }
}
