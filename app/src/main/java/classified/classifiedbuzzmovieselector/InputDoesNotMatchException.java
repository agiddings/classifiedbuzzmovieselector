package classified.classifiedbuzzmovieselector;

import java.util.InputMismatchException;

/**
 * Created by steven on 2/5/16.
 */
public class InputDoesNotMatchException extends Exception {

    public InputDoesNotMatchException() {
        super();
    }

    public InputDoesNotMatchException(String message) {
        super(message);
    }
}
