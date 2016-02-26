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

    /**
     * Constructor a movie manager that contains a list of movies
     *
     * @param json a json url
     */
    public MovieManager (String json) {
        Type collectionType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        movies = gson.fromJson(json, collectionType);
    }

    /**
     * get a list of movies
     *
     * @return a list of movies
     */
    //Needs to be static for search activity to work
    public static List<Movie> getMovies() {
        return movies;
    }

    /**
     * add a new movie to the movie list
     *
     * @param m the movie to be added
     */
    //Needs to be static for search activity to work
    public static void add(Movie m) {
        movies.add(m);
    }

    /**
     * clear all the movies in the list
     *
     */
    public static void clear(){
        movies.clear();
    }

    /**
     * get a list of movies by the best average rating
     *
     * @return a list of movies by the best average rating
     */
    public List<Movie> getBestMoviesByAvgRating() {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    /**
     * get a list of movies by the worst average rating
     *
     * @return a list of movies by the worst average rating
     */
    public List<Movie> getWorstMoviesByAvgRating() {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    /**
     *
     * get a list of best movies by major
     *
     * @param major major of the movie
     * @return a list of movies by major
     */
    public List<Movie> getBestMoviesByMajor(String major) {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    /**
     * get a list of worst movies by major
     *
     * @param major major of the movie
     * @return get a list of worst movies by major
     */
    public List<Movie> getWorstMoviesByMajor(String major) {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    /**
     * get a list of best movies by a friend's rating
     *
     * @param user a friend
     * @return a list of best movies by a friend's rating
     */
    public List<Movie> getBestMoviesByFriendRating(User user) {
        List<Movie> bestMovies = new ArrayList<>();

        return bestMovies;
    }

    /**
     * get a list of worst movies by a friend's rating
     *
     * @param user a friend
     * @return a list of worst movies by a friend's rating
     */
    public List<Movie> getWorstMoviesByFriendRating(User user) {
        List<Movie> worstMovies = new ArrayList<>();

        return worstMovies;
    }

    /**
     * sort movies in the list by release dates chronically
     *
     */
    public void sortByNewReleases() {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return rhs.getYear() - lhs.getYear();
            }
        });
    }

}
