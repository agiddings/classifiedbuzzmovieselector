package classified.classifiedbuzzmovieselector.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;

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
    private static Movie selectedMovie;

    /**
     * Constructor a movie manager that contains a list of movies
     */
    public MovieManager () {
    }

    /**
     * Finds a movie given it's title and year
     * @param title Title of movie to get
     * @param year Year of movie to get
     * @return The movie
     * @throws MovieDoesNotExistException
     */
    public static Movie getMovieByTitleAndYear(String title, int year) throws MovieDoesNotExistException {
        for (Movie m : movies) {
            if (m.getYear() == year && m.getTitle().equals(title)) {
                return m;
            }
        }
        throw new MovieDoesNotExistException("This movie is not in the list");
    }

    /**
     * Gets the movie
     * @param movie The movie to get
     * @return The movie being returned
     * @throws MovieDoesNotExistException Movie passed in is not in list of movies
     */
    public static Movie getMovie(Movie movie) throws MovieDoesNotExistException {
        for (Movie m : movies) {
            if (m.equals(movie)) {
                return m;
            }
        }
        throw new MovieDoesNotExistException("This movie is not in the list");
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

    public static void setMovies(List<Movie> moviesNew) {
        movies = moviesNew;
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
    public static List<Movie> sortBestMoviesByAvgRating() {
        List<Movie> bestMovies = new ArrayList<Movie>();
        Collections.sort(bestMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return new Double(lhs.getAvgRating()).compareTo(rhs.getAvgRating());
            }
        });
        return bestMovies;
    }

    /**
     * get a list of movies by the worst average rating
     *
     * @return a list of movies by the worst average rating
     */
    public static List<Movie> sortWorstMoviesByAvgRating() {
        List<Movie> worstMovies = movies;
        Collections.sort(worstMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return new Double(lhs.getAvgRating()).compareTo(rhs.getAvgRating());
            }
        });
        return worstMovies;
    }

    public static List<Movie> getBestMoviesFromUserRatings() {
        return UserRatingManager.getBestMoviesFromUserRatings();
    }

    public static List<Movie> getWorstMoviesFromUserRatings() {
        return UserRatingManager.getWorstMoviesFromUserRatings();
    }

    /**
     *
     * get a list of best movies by major
     *
     * @param major major of the movie
     * @return a list of movies by major
     */
    public static List<Movie> getBestMoviesByMajor(String major) {
        return UserRatingManager.getBestMoviesFromUserRatings(
                UserRatingManager.getUserRatingsByMajor(major));
    }

    /**
     * get a list of worst movies by major
     *
     * @param major major of the movie
     * @return get a list of worst movies by major
     */
    public static List<Movie> getWorstMoviesByMajor(String major) {
        return UserRatingManager.getWorstMoviesFromUserRatings(
                UserRatingManager.getUserRatingsByMajor(major));
    }

    /**
     * get a list of best movies by a friend's rating
     *
     * @param user a friend
     * @return a list of best movies by a friend's rating
     */
    public static List<Movie> getBestMoviesByFriendRating(User user) {
        return UserRatingManager.getBestMoviesFromUserRatings(
                UserRatingManager.getUserRatingsByFriends(user));
    }

    /**
     * get a list of worst movies by a friend's rating
     *
     * @param user a friend
     * @return a list of worst movies by a friend's rating
     */
    public static List<Movie> getWorstMoviesByFriendRating(User user) {
        return UserRatingManager.getWorstMoviesFromUserRatings(
                UserRatingManager.getUserRatingsByFriends(user));
    }

    /**
     * sort movies in the list by release dates chronically
     *
     */
    public static void sortByNewReleases() {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return rhs.getYear() - lhs.getYear();
            }
        });
    }

    /**
     * Sets the selected movie
     * @param movie The movie passed in
     */
    public static void setSelectedMovie(Movie movie) {
        selectedMovie = movie;
    }

    /**
     * @return The selected movie
     */
    public static Movie getSelectedMovie() {
        return selectedMovie;
    }

}
