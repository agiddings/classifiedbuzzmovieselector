package classified.classifiedbuzzmovieselector.model.Exceptions;

/**
 * Created by steven on 2/26/16.
 */
public class MovieDoesNotExistException extends Exception {

    public MovieDoesNotExistException(String s) {
        super(s);
    }

    public MovieDoesNotExistException() {
        super();
    }
}
