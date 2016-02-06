package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/5/16.
 */
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
