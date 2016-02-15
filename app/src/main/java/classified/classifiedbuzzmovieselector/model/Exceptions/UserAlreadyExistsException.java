package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by steven on 2/5/16.
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * The default exception
     */
    public UserAlreadyExistsException() {
        super();
    }

    /**
     * @param message The message to be passed in with the exception.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
