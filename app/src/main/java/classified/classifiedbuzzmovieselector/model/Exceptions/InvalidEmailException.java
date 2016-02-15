package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by Allie on 2/10/2016.
 */
public class InvalidEmailException extends Exception {

    /**
     * The default exception implementation
     */
    public InvalidEmailException() {super();}

    /**
     * @param message The message to be passed in with the exception.
     */
    public InvalidEmailException(String message) { super(message); }
}
