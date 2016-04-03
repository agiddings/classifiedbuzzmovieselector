package classified.classifiedbuzzmovieselector;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;
import dalvik.annotation.TestTargetClass;

/**
 * Created by coleb_000 on 4/1/2016.
 */
public class ColesJUnit {
    private static final int TIMEOUT = 200;

    @Before
    public void setup(){
        Movie m1 = new Movie("One", 2001);
        Movie m2 = new Movie("Two", 2002);
        Movie m3 = new Movie("Three", 2003);;
        Movie m4 = new Movie("One", 2004);;
        MovieManager.add(m1);
        MovieManager.add(m2);
        MovieManager.add(m3);
        MovieManager.add(m4);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testTitleNull() throws Exception {
        String s = null;
        MovieManager.getMovieByTitleAndYear(s,0);
    }

    @Test(timeout = TIMEOUT, expected = MovieDoesNotExistException.class)
    public void testMovieDoesNotExist() throws Exception {
        MovieManager.getMovieByTitleAndYear("four",0);
    }

    @Test(timeout = TIMEOUT)
    public void testMovie() {
        try {
            Movie found = MovieManager.getMovieByTitleAndYear("Three", 2003);
            assertEquals(found, new Movie("Three", 2003));
        } catch(MovieDoesNotExistException e) {

        }
    }

    @Test(timeout = TIMEOUT)
    public void testMovieSameTitle() throws Exception {
        Movie found = MovieManager.getMovieByTitleAndYear("One", 2004);
        assertEquals(found, new Movie("One", 2004));
    }

    @Test(timeout = TIMEOUT, expected = MovieDoesNotExistException.class)
    public void testNameNotYear() throws Exception {
        Movie found = MovieManager.getMovieByTitleAndYear("One", 2005);
    }

}
