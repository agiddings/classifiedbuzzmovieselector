package classified.classifiedbuzzmovieselector.model;
import com.google.gson.annotations.SerializedName;

import android.provider.ContactsContract;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by Zhendong(Justeen) on 2/18/16.
 */
public class Movie implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private int year;
    @SerializedName("mpaa_rating")
    private String mpaa_rating;
    @SerializedName("runtime")
    private String runtime; // runtime in minutes
    private double avgRating;
    @SerializedName("ratings")
    private Rating ratings;
    //private String genre;
    //private int rating;
    @SerializedName("posters")
    private Posters posters;

    /**
     * Constructor for a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year, String mpaa_rating, String runtime, int audience_score, int critics_score) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
        this.ratings = new Rating(audience_score, critics_score);
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
    public String getRuntime() {
        return runtime;
    }

    public int getAudienceScore() {
        return ratings.audience_score;
    }

    public int getCriticsScore() {
        return ratings.critics_score;
    }

    public String getThumbnail() {
        return posters.thumbnail;
    }

    public String getProfile() {
        return posters.profile;
    }

    public String getDetailed() {
        return posters.detailed;
    }

    public String getOriginal() {
        return posters.original;
    }

    public String toString() {
        return title + " " + year + " " + ratings.audience_score + " " + posters.original;
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

    private class Posters {
        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("profile")
        private String profile;
        @SerializedName("detailed")
        private String detailed;
        @SerializedName("original")
        private String original;

        public Posters(String thumbnail, String profile, String detailed, String original) {
            this.thumbnail = thumbnail;
            this.profile = profile;
            this.detailed = detailed;
            this.original = original;
        }
    }

    private class Rating {
        @SerializedName("audience_score")
        private int audience_score;
        @SerializedName("critics_score")
        private int critics_score;

        public Rating(int audience_score, int critics_score) {
            this.audience_score = audience_score;
            this.critics_score = critics_score;
        }


    }

}
