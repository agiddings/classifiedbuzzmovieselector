package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by Allie on 3/27/2016.
 */
public class InvalidMajorException extends Exception {

    /**
     * The default exception
     */
    public InvalidMajorException() {super(); }

    /**
     * Throws the exception with a message
     * @param message The message to be displayed with the error message
     */
    public InvalidMajorException(String message) {super(message); }
}
