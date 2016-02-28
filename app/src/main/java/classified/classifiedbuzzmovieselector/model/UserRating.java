package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/20/16.
 */
public class UserRating {

    private String comment;
    private int score;
    private Movie movie;
    private User user;

    /**
     * makes a UserRating
     * @param comment String comment on movie
     * @param score int score for movie
     * @param movie movie being rated
     * @param user user writing the rating
     */
    public UserRating (String comment, int score, Movie movie, User user) {
        this.comment = comment;
        this.score = score;
        this.movie = movie;
        this.user = user;
    }

    /**
     * get the comment
     * @return String comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * set the comment
     * @param comment the new comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * get ther score
     * @return int score
     */
    public int getScore() {
        return score;
    }

    /**
     * set the score
     * @param score int new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * get the movie
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * get the user
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * checks ur for equality with this rating
     * @param ur
     * @return a boolean representing whether this is equal to ur
     */
    public boolean equals(UserRating ur) {
        return user.equals(ur.user) && movie.equals(ur.movie);
    }
}
