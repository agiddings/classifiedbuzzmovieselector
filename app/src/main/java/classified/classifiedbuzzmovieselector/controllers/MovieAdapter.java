package classified.classifiedbuzzmovieselector.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    //The list of movies to display
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieHolder, int i) {
        Movie ci = movieList.get(i);
        movieHolder.vName.setText(ci.getTitle());
        movieHolder.vYear.setText(String.valueOf(ci.getYear()));
        movieHolder.vPoster.loadUrl(ci.getPoster());
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.movie_layout, viewGroup, false);

        return new MovieViewHolder(itemView);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

            protected TextView vName;
            protected TextView vYear;
            protected WebView vPoster;

            public MovieViewHolder(View v) {
                super(v);
                vName =  (TextView) v.findViewById(R.id.movieLayoutName);
                vYear = (TextView)  v.findViewById(R.id.movieLayoutYear);
                vPoster = (WebView) v.findViewById(R.id.movieLayoutPoster);
            }
    }
}
