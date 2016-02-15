package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by anna on 2/14/16.
 */
public class UserDoesNotExistException extends Exception {

    /**
     * The default exception
     */
    public UserDoesNotExistException() {
        super();
    }

    /**
     * @param message The message to be passed in with the exception.
     */
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
