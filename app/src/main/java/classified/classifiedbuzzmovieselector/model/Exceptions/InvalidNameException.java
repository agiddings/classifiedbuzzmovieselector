package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by Allie on 2/10/2016.
 */
public class InvalidNameException extends Exception {

    /**
     * The default exception
     */
    public InvalidNameException() {
        super();
    }

    /**
     * @param message The message to be passed in with the exception.
     */
    public InvalidNameException(String message) {
        super(message);
    }
}
