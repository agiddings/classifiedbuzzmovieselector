package classified.classifiedbuzzmovieselector.model;

import java.io.Serializable;

/**
 * Created by Zhendong(Justeen) on 2/18/16.
 */
public class Movie implements Serializable {
    private String title;
    private int year;
    private String mpaa_rating;
    private int runtime; // runtime in minutes
    private double avgRating;
    //private String genre;
    //private int rating;

    /**
     * Constructor a movie
     *
     * @param title
     * @param year
     */
    public Movie(String title, int year, String mpaa_rating, int runtime, double avgRating) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
        this.avgRating = avgRating;
    }

    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year, String mpaa_rating, int runtime) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
        this.avgRating = UserRatingManager.getAvgMovieUserRating(
                new Movie(title, year, mpaa_rating, runtime, 0));
    }

    /**
     * get movie year
     *
     * @return  year of release
     *
     */
    public int getYear() {
        return year;
    }

    /**
     * set an average rating
     *
     * @param avgRating
     */
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }


    /**
     * get an average rating
     *
     * @return an average rating
     */
    public double getAvgRating() {
        return avgRating;
    }

    /**
     * get movie title
     *
     * @return movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * get MPAA rating
     *
     * @return MPAA rating for that movie
     */
    public String getMpaa_rating() {
        return mpaa_rating;
    }

    /**
     * get movie runtime
     *
     * @return movie runtime
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * compare two movies to see if they are equal
     *
     * @param movie movie compared against
     * @return  whether two movies are equal
     *
     */
    public boolean equals(Movie movie) {
        return title.equals(movie.title) && year == movie.year
                && mpaa_rating.equals(movie.mpaa_rating) && runtime == movie.runtime;
    }
}
