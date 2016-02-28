package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;

/**
 * Created by steven on 2/20/16.
 */
public class UserRatingManager {

    private static final List<UserRating> userRatings = new ArrayList<>();

    /**
     * makes a new UserRatingManager
     */
    public UserRatingManager() {
    }

    /**
     * returns the list of userRatings
     * @return
     */
    public List<UserRating> getUserRatings () {
        return userRatings;
    }

    /**
     * adds a new rating to the system
     * @param ur the new UserRating
     */
    public static void addUserRating(UserRating ur) {
        int index = userRatings.indexOf(ur);
        if (index < 0) {
            userRatings.add(ur);
        } else {
            userRatings.get(index).setComment(ur.getComment());
            userRatings.get(index).setScore(ur.getScore());
        }
        try {
            MovieManager.getMovie(ur.getMovie()).setAvgRating(getAvgMovieUserRating(ur.getMovie()));
        } catch (MovieDoesNotExistException e) {
            //this literally cannot happen
            Log.e("USER_RATING_MANAGER", "YELL AT STEVE");
        }

    }

    /**
     * get UserRatings made by user
     * @param user the user
     * @return all ratings made by user
     */
    public List<UserRating> getUserRatingsByUser(User user) {
        return getUserRatingsByUser(user, userRatings);
    }

    /**
     * get user ratings made by User from the ratingList
     * @param user the user
     * @param ratingList the ratingList
     * @return List of user ratings made by User from the ratingList
     */
    public static List<UserRating> getUserRatingsByUser(User user, List<UserRating> ratingList) {

        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getUser() == user) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    /**
     * gets the avg UserRating score for a movie
     * @param movie the movie
     * @return double avg score
     */
    public static double getAvgMovieUserRating (Movie movie) {
        return getAvgMovieUserRating(movie, userRatings);
    }

    /**
     * gets the avg UserRating score for a movie in ratingList
     * @param movie the Movie
     * @param ratingList the rating List
     * @return double avg score
     */
    public static double getAvgMovieUserRating (Movie movie, List<UserRating> ratingList) {
        List<UserRating> ratings = getUserRatingsByMovie(movie, ratingList);
        int sum = 0;
        int count = 0;
        for (UserRating ur : ratings) {
            count++;
            sum += ur.getScore();
        }
        if(count > 0) {
            return ((double) sum) / count;
        } else {
            return 0;
        }
    }

    /**
     * gets user ratings by Movie
     * @param movie the movie
     * @return List of user ratings by Movie
     */
    public static List<UserRating> getUserRatingsByMovie(Movie movie) {
        return getUserRatingsByMovie(movie, userRatings);
    }

    /**
     * gets user ratings by movie in rating list
     * @param movie the movie
     * @param ratingList List of User ratings by movie
     * @return List of UserRatings by movie
     */
    public static List<UserRating> getUserRatingsByMovie(Movie movie, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getMovie() == movie) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    /**
     * gets UserRatings by major
     * @param major the major
     * @return List of UserRatings by major
     */
    public static List<UserRating> getUserRatingsByMajor(String major) {
        return getUserRatingsByMajor(major, userRatings);
    }

    /**
     * gets user ratings by major in rating list
     * @param major the major
     * @param ratingList List of User ratings by movie
     * @return List of UserRatings by major
     */
    public static List<UserRating> getUserRatingsByMajor(String major, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getUser().getMajor().equals(major)) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    /**
     * gets UserRatings by firends
     * @param user the user
     * @return List of UserRatings by friends
     */
    public static List<UserRating> getUserRatingsByFriends(User user) {
        return getUserRatingsByFriends(user, userRatings);
    }

    /**
     * gets user ratings by firends in rating list
     * @param user the user
     * @param ratingList List of User ratings by movie
     * @return List of UserRatings by friends
     */
    public static List<UserRating> getUserRatingsByFriends(User user, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (user.getFriends().contains(ur.getUser())) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    /**
     * gets the best movies from a specific list of User Ratings
     * @param ratingList the ratings
     * @return a sorted list of movies by rating
     */
    public static List<Movie> getBestMoviesfromUserRatings(List<UserRating> ratingList) {
        Set<Movie> moviesSet = new HashSet<>();
        List<Movie> movies;
        for (UserRating rating : ratingList) {
            moviesSet.add(rating.getMovie());
        }
        movies = new ArrayList<>(moviesSet);
        //TODO Sort movies by rating

        return movies;
    }

    /**
     * sorts ratings high to low
     * @param ratings the ratings
     * @return a list of ratings
     */
    public static List<UserRating> sortRatingsLowToHigh(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return lhs.getScore() - rhs.getScore();
            }
        });
        return ratings;
    }

    /**
     * sorts all ratings high to low
     * @return the sorted ratings
     */
    public static List<UserRating> sortRatingsLowToHigh() {
        return sortRatingsLowToHigh(userRatings);
    }

    /**
     * sorts ratings low to high
     * @param ratings the ratings
     * @return a list of ratings
     */

    public static List<UserRating> sortRatingsHighToLow(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return rhs.getScore() - lhs.getScore();
            }
        });
        return ratings;
    }

    /**
     * sorts all ratings low to high
     * @return the sorted ratings
     */
    public static List<UserRating> sortRatingsHighToLow() {
        return sortRatingsHighToLow(userRatings);
    }
}
