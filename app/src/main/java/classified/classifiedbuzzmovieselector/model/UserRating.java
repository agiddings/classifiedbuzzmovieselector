package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/20/16.
 */
public class UserRating {

    private String comment;
    private float score;
    private Movie movie;
    private String userEmail;

    public UserRating (String comment, float score, Movie movie, User user) {
        this.comment = comment;
        this.score = score;
        this.movie = movie;
        this.userEmail = user.getEmail();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return UserManager.findUserByEmail(userEmail);
    }

    public boolean equals(Object ur) {
        if (ur instanceof Object) {
            return userEmail.equals(((UserRating) ur).userEmail) && movie.equals(((UserRating) ur).movie);
        }
        return false;
    }
}
