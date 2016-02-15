package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by anna on 2/14/16.
 */
public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException() {
        super();
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
