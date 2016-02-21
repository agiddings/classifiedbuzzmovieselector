package classified.classifiedbuzzmovieselector.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public void addUserRating(UserRating ur) {
        int index = userRatings.indexOf(ur);
        if (index < 0) {
            userRatings.add(ur);
        } else {
            userRatings.get(index).setComment(ur.getComment());
            userRatings.get(index).setScore(ur.getScore());
        }
        ur.getMovie().setAvgRating(getAvgMovieUserRating(ur.getMovie()));
    }

    public List<UserRating> getUserRatingsByUser(User user) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (ur.getUser() == user) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public double getAvgMovieUserRating (Movie movie) {
        List<UserRating> ratings = getUserRatingsByMovie(movie);
        int sum = 0;
        int count = 0;
        for (UserRating ur : ratings) {
            count++;
            sum += ur.getScore();
        }
        return ((double) sum)/count;
    }

    public List<UserRating> getUserRatingsByMovie(Movie movie) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (ur.getMovie() == movie) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public List<UserRating> getUserRatingsByMajor(String major) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (ur.getUser().getMajor().equals(major)) {
                returnVal.add(ur);
            }
        }
        return returnVal;
    }

    public List<UserRating> getUserRatingsByFriends(User user) {
        List<UserRating> returnVal = new ArrayList<>();
        for (UserRating ur : userRatings) {
            if (user.getFriends().contains(ur.getUser())) {
                returnVal.add(ur);
            }
        }
        return returnVal;
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
        return sortRatingsHighToLow(userRatings);
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
