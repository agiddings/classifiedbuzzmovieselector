package classified.classifiedbuzzmovieselector.model;

/**
 * Created by Zhendong(Justeen) on 2/18/16.
 */
public class Movie {
    private String title;
    private int year;
    private String mpaa_rating;
    private int runtime; // runtime in minutes
    //private String genre;
    //private int rating;


    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year, String mpaa_rating, int runtime, int audience_score, int critic_score) {
        this.title = title;
        this.year = year;
        this.mpaa_rating = mpaa_rating;
        this.runtime = runtime;
    }

    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @return  year of release
     *
     */
    public int getMovieYear(Movie title) {
        return title.year;
    }

    public int getYear() {
        return year;
    }

    public boolean equals(Movie movie) {
        return title.equals(movie.title) && year == movie.year
                && mpaa_rating.equals(movie.mpaa_rating) && runtime == movie.runtime;
    }
}
