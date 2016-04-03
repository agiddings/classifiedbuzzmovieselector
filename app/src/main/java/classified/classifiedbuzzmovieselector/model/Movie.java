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
    private Rating ratings;
    //private String genre;
    //private int rating;
    private String poster;


    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }
    /**
     * Constructor a movie
     *
     * @param title
     * @param year
     */
    public Movie(String title, int year, String mpaa_rating, int runtime, double avgRating, int audience_score) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
        this.avgRating = avgRating;
        this.ratings = new Rating(audience_score);
    }

    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year, String mpaa_rating, int runtime, int audience_score) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
        this.avgRating = UserRatingManager.getAvgMovieUserRating(
                new Movie(title, year, mpaa_rating, runtime, 0));
        this.ratings = new Rating(audience_score);
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

    public void setPoster(String poster){
        this.poster = poster;
    }
    /**
     * Gets poster link
     * @return poster
     */
    public String getPoster(){
        return poster;
    }

    /**
     * @return An int representing the average rating of the movie
     */
    public int getAudienceScore() {
        return ratings.audience_score;
    }

    /**
     * compare two movies to see if they are equal!
     *
     * @param movie movie compared against
     * @return  whether two movies are equal
     *
     */
    public boolean equals(Movie movie) {
        return (this.title.equals(movie.title)) && (this.year == movie.year);
    }

    /**
     * Hashcode method
     */
    public int hashCode() {
        return year;
    }

    private class Rating {

        private int audience_score;

        public Rating(int audience_score) {
            this.audience_score = audience_score;
        }
    }

}