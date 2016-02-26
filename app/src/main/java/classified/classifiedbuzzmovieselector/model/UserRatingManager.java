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

    public UserRatingManager() {
    }

    public List<UserRating> getUserRatings () {
        return userRatings;
    }

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

    public static List<UserRating> getUserRatingsByUser(User user) {
        return getUserRatingsByUser(user, userRatings);
    }

    public static List<UserRating> getUserRatingsByUser(User user, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getUser() == user) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public static double getAvgMovieUserRating (Movie movie) {
        return getAvgMovieUserRating(movie, userRatings);
    }

    public static double getAvgMovieUserRating (Movie movie, List<UserRating> ratingList) {
        List<UserRating> ratings = getUserRatingsByMovie(movie, ratingList);
        int sum = 0;
        int count = 0;
        for (UserRating ur : ratings) {
            count++;
            sum += ur.getScore();
        }
        return ((double) sum)/count;
    }

    public static List<UserRating> getUserRatingsByMovie(Movie movie) {
        return getUserRatingsByMovie(movie, userRatings);
    }

    public static List<UserRating> getUserRatingsByMovie(Movie movie, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getMovie() == movie) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public static List<UserRating> getUserRatingsByMajor(String major) {
        return getUserRatingsByMajor(major, userRatings);
    }

    public static List<UserRating> getUserRatingsByMajor(String major, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getUser().getMajor().equals(major)) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public static List<UserRating> getUserRatingsByFriends(User user) {
        return getUserRatingsByFriends(user, userRatings);
    }

    public static List<UserRating> getUserRatingsByFriends(User user, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (user.getFriends().contains(ur.getUser())) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

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

    public static List<UserRating> sortRatingsLowToHigh(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return lhs.getScore() - rhs.getScore();
            }
        });
        return ratings;
    }

    public static List<UserRating> sortRatingsLowToHigh() {
        return sortRatingsLowToHigh(userRatings);
    }

    public static List<UserRating> sortRatingsHighToLow(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return rhs.getScore() - lhs.getScore();
            }
        });
        return ratings;
    }

    public static List<UserRating> sortRatingsHighToLow() {
        return sortRatingsHighToLow(userRatings);
    }
}
