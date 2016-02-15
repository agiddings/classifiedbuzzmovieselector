package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by steven on 2/6/16.
 */
public class InputsDoNotMatchException extends Exception {

    /**
     * The default exception
     */
    public InputsDoNotMatchException() {
        super();
    }

    /**
     * @param message The message to be passed along with exception
     */
    public InputsDoNotMatchException(String message) {
        super(message);
    }
}
