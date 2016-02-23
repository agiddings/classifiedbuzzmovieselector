package classified.classifiedbuzzmovieselector.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by steven on 2/19/16.
 */
public class MovieManager {

    //Made the list of movies static so SearchActivity can add items to it, access it
    //We can try redoing how this is done later, but for now please, please do not change
    //Possibly all of the methods/everything will be made static. We'll have to decide later
    //Or just change the constructor so if does exactly what search activity is currently
    //doing to add movies.
    private static List<Movie> movies = new ArrayList<Movie>();
    private static final Gson gson = new Gson();

    public MovieManager (String json) {
        Type collectionType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        movies = gson.fromJson(json, collectionType);
    }

    //Needs to be static for search activity to work
    public static List<Movie> getMovies() {
        return movies;
    }

    //Needs to be static for search activity to work
    public static void add(Movie m) {
        movies.add(m);
    }

    public List<Movie> getBestMoviesByAvgRating() {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    public List<Movie> getWorstMoviesByAvgRating() {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    public List<Movie> getBestMoviesByMajor(String major) {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    public List<Movie> getWorstMoviesByMajor(String major) {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    public List<Movie> getBestMoviesByFriendRating(User user) {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    public List<Movie> getWorstMoviesByFriendRating(User user) {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    public void sortByNewReleases() {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return rhs.getYear() - lhs.getYear();
            }
        });
    }

}
