package classified.classifiedbuzzmovieselector.model;

import java.io.Serializable;

/**
 * Created by Zhendong(Justeen) on 2/18/16.
 */
public class Movie implements Serializable {
    private String title;
    private int year;
    private String mpaaRating;
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
    public Movie(String title, int year, String mpaaRating, int runtime, double avgRating, int audienceScore) {
        this.title = title;
        this.year = year;
        this.mpaaRating = mpaaRating;
        this.runtime = runtime;
        this.avgRating = avgRating;
        this.ratings = new Rating(audienceScore);
    }

    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year, String mpaaRating, int runtime, int audienceScore) {
        this.title = title;
        this.year = year;
        this.mpaaRating = mpaaRating;
        this.runtime = runtime;
        this.avgRating = UserRatingManager.getAvgMovieUserRating(
                new Movie(title, year, mpaaRating, runtime, 0, audienceScore));
        this.ratings = new Rating(audienceScore);
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
    public String getMpaaRating() {
        return mpaaRating;
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
        return ratings.audienceScore;
    }

    /**
     * compare two movies to see if they are equal!
     *
     * @param movie movie compared against
     * @return  whether two movies are equal
     *
     */
    public boolean equals(Object movie) {
        if (movie instanceof Movie) {
            return (this.title.equals(((Movie) movie).title)) && (this.year == ((Movie) movie).year);
        }
        return false;
    }

    /**
     * Hashcode method
     */
    public int hashCode() {
        return year;
    }

    private class Rating {

        private int audienceScore;

        public Rating(int audienceScore) {
            this.audienceScore = audienceScore;
        }
    }

}