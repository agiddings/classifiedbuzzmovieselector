package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/20/16.
 */
public class UserRating {

    private String comment;
    private int score;
    private Movie movie;
    private User user;

    public UserRating (String comment, int score, Movie movie, User user) {
        this.comment = comment;
        this.score = score;
        this.movie = movie;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

    public boolean equals(UserRating ur) {
        return user.equals(ur.user) && movie.equals(ur.movie);
    }
}
