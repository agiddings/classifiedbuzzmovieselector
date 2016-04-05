package classified.classifiedbuzzmovieselector;

import org.junit.Before;
import org.junit.Test;

import classified.classifiedbuzzmovieselector.model.Exceptions.MovieDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Cole Bowers on 4/3/2016.
 *
 * Tests for getMovieByTitleAndYear(String title, int year) in MovieManager.java
 * Method shown for convenience
 *
 * public static Movie getMovieByTitleAndYear(String title, int year) throws MovieDoesNotExistException {
 *    if(title == null) {
 *        throw new IllegalArgumentException("Invalid Title passed in");
 *    }
 *    for (Movie m : movies) {
 *        if (m.getYear() == year && m.getTitle().equals(title)) {
 *            return m;
 *        }
 *    }
 *    throw new MovieDoesNotExistException("This movie is not in the list");
 * }



 */
public class ColeFindMovieTests {

    @Before
    public void setup(){
        Movie m1 = new Movie("One", 2001);
        Movie m2 = new Movie("Two", 2002);
        Movie m3 = new Movie("Three", 2003);
        Movie m4 = new Movie("One", 2004);

        MovieManager.add(m1);
        MovieManager.add(m2);
        MovieManager.add(m3);
        MovieManager.add(m4);
    }

    //Branch Coverage Tests

    //Enters the first conditional checking for  valid arguments
    @Test(expected = IllegalArgumentException.class)
    public void testTitleNull() throws Exception{
        String s = null;
        MovieManager.getMovieByTitleAndYear(s, 0);
    }



    //returns form within the for loop, checks to make sure it returns the correct movie
    @Test
    public void testMovie() throws Exception {
        Movie found = MovieManager.getMovieByTitleAndYear("Three", 2003);
        assert(found.equals(new Movie("Three" , 2003)));
    }

    //completes for loop without finding movie
    @Test(expected = MovieDoesNotExistException.class)
    public void testMovieDoesNotExist() throws Exception {
        MovieManager.getMovieByTitleAndYear("four", 0);
    }

    //Additional Tests

    @Test(expected = MovieDoesNotExistException.class)
    public void testNameNotYear() throws Exception {
        MovieManager.getMovieByTitleAndYear("One", 2005);
    }

    @Test
    public void testMovieSameTitle() throws Exception {
        Movie found = MovieManager.getMovieByTitleAndYear("One", 2004);
        assert(found.equals(new Movie("One", 2004)));
    }


}
