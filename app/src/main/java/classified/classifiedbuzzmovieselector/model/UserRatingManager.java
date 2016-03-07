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

    /**
     * This is the list of userRatings
     */
    private static final List<UserRating> userRatings = new ArrayList<>();

    /**
     * Everything is static so it's not instantiated
     */
    public UserRatingManager() {
    }

    /**
     * This is a getter
     * @return Returns the list of all user ratings
     */
    public List<UserRating> getUserRatings () {
        return userRatings;
    }

    /**
     * Adds a new user rating
     * @param ur The user rating to add to the list
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
            Log.d("USER_RATING_MANAGER", ur.getScore() + "");
            Log.d("USER_RATING_MANAGER", MovieManager.getMovie(ur.getMovie()).getAvgRating() + "");
        } catch (MovieDoesNotExistException e) {
            //this literally cannot happen
            Log.e("USER_RATING_MANAGER", "YELL AT STEVE");
        }
    }

    /**
     * This method gets all the user ratings of a specific user
     * @param user The user it gets ratings from
     * @return The list of ratings the user has
     */
    public static List<UserRating> getUserRatingsByUser(User user) {
        return getUserRatingsByUser(user, userRatings);
    }

    /**
     * This method gets the user ratings from a passed in list of ratings
     * @param user The user you're getting ratings for
     * @param ratingList The list of ratings it checks through
     * @return The list of user ratings
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
     * This calculates the average user rating for a movie
     * @param movie The movie getting an average rating for
     * @return The average rating
     */
    public static double getAvgMovieUserRating (Movie movie) {
        return getAvgMovieUserRating(movie, userRatings);
    }

    /**
     * This calculates the average user rating for a movie
     * @param movie The movie getting an average rating for
     * @param ratingList The list of user ratings
     * @return The average rating
     */
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

    /**
     * Gets the list of user ratings for a movie
     * @param movie The movie getting ratings for
     * @return A list of user ratings
     */
    public static List<UserRating> getUserRatingsByMovie(Movie movie) {
        return getUserRatingsByMovie(movie, userRatings);
    }

    /**
     * Gets the list of user ratings for a movie
     * @param movie The movie getting ratings for
     * @param ratingList The list of user ratings
     * @return A list of user ratings
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
     * Gets the list of user ratings for a major
     * @param major The major getting ratings for
     * @return A list of user ratings
     */
    public static List<UserRating> getUserRatingsByMajor(String major) {
        return getUserRatingsByMajor(major, userRatings);
    }

    /**
     * Gets the list of user ratings for a major
     * @param major The major getting ratings for
     * @param ratingList The list of ratings
     * @return A list of user ratings
     */
    public static List<UserRating> getUserRatingsByMajor(String major, List<UserRating> ratingList) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : ratingList) {
            if (ur.getUser().getMajor() != null) {
                if (ur.getUser().getMajor().equals(major)) {
                    returnVal.add(ur);
                }
            }
        }
        return returnVal;
    }

    /**
     * Gets the list of user ratings for a user
     * @param user The user getting ratings for
     * @return A list of user ratings
     */
    public static List<UserRating> getUserRatingsByFriends(User user) {
        return getUserRatingsByFriends(user, userRatings);
    }

    /**
     * Gets the list of user ratings for a user
     * @param user The user getting ratings for
     * @param ratingList The list of ratings
     * @return A list of user ratings
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

    public static List<Movie> getBestMoviesFromUserRatings() {
        return getBestMoviesFromUserRatings(userRatings);
    }

    /**
     * Gets a list of movies from a list of user ratings
     * @param ratingList The list of user ratings
     * @return The list of movies
     */
    public static List<Movie> getBestMoviesFromUserRatings(List<UserRating> ratingList) {
        Set<Movie> moviesSet = new HashSet<>();
        List<Movie> movies;
        for (UserRating rating : ratingList) {
            moviesSet.add(rating.getMovie());
        }
        for (Movie m : moviesSet) {
            m.setAvgRating(getAvgMovieUserRating(m));
        }

        movies = new ArrayList<>(moviesSet);
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return new Double(lhs.getAvgRating()).compareTo(rhs.getAvgRating());
            }
        });

        return movies;
    }


    public static List<Movie> getWorstMoviesFromUserRatings() {
        return getWorstMoviesFromUserRatings(userRatings);
    }

    /**
     * Gets a list of movies from a list of user ratings
     * @param ratingList The list of user ratings
     * @return The list of movies
     */
    public static List<Movie> getWorstMoviesFromUserRatings(List<UserRating> ratingList) {
        Set<Movie> moviesSet = new HashSet<>();
        List<Movie> movies;
        for (UserRating rating : ratingList) {
            moviesSet.add(rating.getMovie());
        }
        for (Movie m : moviesSet) {
            m.setAvgRating(getAvgMovieUserRating(m));
        }

        movies = new ArrayList<>(moviesSet);
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return new Double(rhs.getAvgRating()).compareTo(lhs.getAvgRating());
            }
        });

        return movies;
    }
    /**
     * Sorts the ratings from low to high to display
     * @param ratings The ratings to sort
     * @return The list of user ratings
     */
    public static List<UserRating> sortRatingsLowToHigh(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return ((int)lhs.getScore() - (int)rhs.getScore());
            }
        });
        return ratings;
    }

    /**
     * Sorts ratings low to high
     * @return The list of user ratings, sorted
     */
    public static List<UserRating> sortRatingsLowToHigh() {
        return sortRatingsLowToHigh(userRatings);
    }

    /**
     * Sorts the ratings from high to low to display
     * @param ratings The ratings to sort
     * @return The list of user ratings
     */
    public static List<UserRating> sortRatingsHighToLow(List<UserRating> ratings) {
        Collections.sort(ratings, new Comparator<UserRating>() {
            @Override
            public int compare(UserRating lhs, UserRating rhs) {
                return (int)rhs.getScore() - (int)lhs.getScore();
            }
        });
        return ratings;
    }

    /**
     * Sorts the ratings from high to low to display
     * @return The list of user ratings
     */
    public static List<UserRating> sortRatingsHighToLow() {
        return sortRatingsHighToLow(userRatings);
    }
}
