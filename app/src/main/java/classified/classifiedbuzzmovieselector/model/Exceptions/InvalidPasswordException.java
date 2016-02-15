package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by Allie on 2/10/2016.
 */
public class InvalidPasswordException extends Exception{

    /**
     * The default exception
     */
    public InvalidPasswordException() { super();}

    /**
     * @param message The message to be passed in with the exception.
     */
    public InvalidPasswordException(String message) { super(message);}
}
