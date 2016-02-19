package classified.classifiedbuzzmovieselector.model;

/**
 * Created by Zhendong(Justeen) on 2/18/16.
 */
public class Movie {
    private String title;
    private int year;
    //private String genre;
    //private int rating;


    /**
     * Constructor a movie with relevant information
     *
     * @param title movie title
     * @param year year of release
     *
     */
    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
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

}
