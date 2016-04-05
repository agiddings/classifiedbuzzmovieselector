package classified.classifiedbuzzmovieselector;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserRating;
import classified.classifiedbuzzmovieselector.model.UserRatingManager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Steven on 4/1/2016.
 */
public class StevenJUnit {

    MovieManager movieManager = new MovieManager();
    Movie movie = new Movie("movie", 2000, "R", 90, 0, 50);
    Movie movie2 = new Movie("movie2", 20000, "R", 900, 0, 500);
    Movie movie3 = new Movie("movie3", 200000, "R", 9000, 0, 5000);

    private UserRatingManager userRatingManager = new UserRatingManager();
    User u1 = new User("u1", "u1@u1.com", "u1");
    User u2 = new User("u2", "u2@u2.com", "u2");


    UserRating userRating = new UserRating("wow", 4, movie, u1);
    UserRating userRating2 = new UserRating("wow2", 5, movie, u1);

    UserRating userRating3 = new UserRating("woow", 1, movie, u2);
    UserRating userRating4 = new UserRating("woow2", 2, movie, u2);

    UserRating userRating5 = new UserRating("woow", 1, movie2, u2);
    UserRating userRating6 = new UserRating("woow", 1, movie2, u1);

    UserRating userRating7 = new UserRating("woow", 1, movie3, u1);

    @Test
    public void testMovieDoesNotExistInMovieManager() {
        UserRatingManager.clear();
        UserRatingManager.addUserRating(userRating7);
        List<UserRating> expected = new ArrayList<>();
        expected.add(userRating7);
        assertEquals(expected, UserRatingManager.getUserRatings());
    }

    @Test
    public void testSingleAdd() {
        MovieManager.add(movie);
        MovieManager.add(movie2);
        UserRatingManager.clear();
        UserRatingManager.addUserRating(userRating);
        List<UserRating> expected = new ArrayList<>();
        expected.add(userRating);
        assertEquals( expected, UserRatingManager.getUserRatings());
    }

    @Test
    public void testMultipleAdds() {
        MovieManager.add(movie);
        MovieManager.add(movie2);
        UserRatingManager.clear();
        UserRatingManager.addUserRating(userRating);
        UserRatingManager.addUserRating(userRating3);
        UserRatingManager.addUserRating(userRating5);
        UserRatingManager.addUserRating(userRating6);
        List<UserRating> expected = new ArrayList<>();
        expected.add(userRating);
        expected.add(userRating3);
        expected.add(userRating5);
        expected.add(userRating6);
        assertEquals(expected, UserRatingManager.getUserRatings());
    }

    @Test
    public void testUpdateMovieRating() {
        MovieManager.add(movie);
        MovieManager.add(movie2);
        UserRatingManager.clear();
        UserRatingManager.addUserRating(userRating);
        UserRatingManager.addUserRating(userRating3);
        List<UserRating> expected = new ArrayList<>();
        expected.add(userRating);
        expected.add(userRating3);
        try {
            assertTrue(MovieManager.getMovie(movie).getAvgRating() == 2.5);
        } catch (MovieDoesNotExistException e) {
            assertTrue("test failed", false);
        }
    }

    @Test
    public void testUpdateAdds() {
        MovieManager.add(movie);
        MovieManager.add(movie2);
        UserRatingManager.clear();
        UserRatingManager.addUserRating(userRating);
        UserRatingManager.addUserRating(userRating2);
        UserRatingManager.addUserRating(userRating3);
        UserRatingManager.addUserRating(userRating4);
        List<UserRating> expected = new ArrayList<>();
        expected.add(userRating2);
        expected.add(userRating4);
        assertEquals(expected, UserRatingManager.getUserRatings());
    }

    private void printList(List<UserRating> list) {
        System.out.print("[ ");
        if (list.size() > 0 && list.get(0) != null) {
            System.out.print(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                System.out.print( ", " + list.get(i));
            }
        }
        System.out.println(" ]");
    }

}
