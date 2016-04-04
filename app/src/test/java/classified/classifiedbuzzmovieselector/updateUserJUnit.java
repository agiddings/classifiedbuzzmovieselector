package classified.classifiedbuzzmovieselector;

import org.junit.Before;
import org.junit.Test;

import classified.classifiedbuzzmovieselector.model.Exceptions.UserDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidMajorException;

import classified.classifiedbuzzmovieselector.model.UserManager;

/**
 * Created by Anna Laura on 4/1/2016.
 */
public class updateUserJUnit {

    private UserManager anna;
    @Before
    public void makeUser() {
        if (anna == null) {
             anna = new UserManager();
        }
        try {
            anna.addUser("Anna", "a@gmail.com", "password");
        }
        catch(Exception e){

        }

    }

    @Test(expected = InvalidEmailException.class)
    public void testUpdateUserInvalidEmail1()  throws UserDoesNotExistException, InvalidEmailException, InvalidMajorException {
        try {
            UserManager.updateUser(
                    "a@gmail.com", "Anna", "a@gmail.com" , "password" , "Computer Science", "2nd year");
        } catch(Exception e) {

        }
    }

    @Test(expected = InvalidEmailException.class)
    public void testUpdateUserInvalidEmail2()  throws UserDoesNotExistException, InvalidEmailException, InvalidMajorException {
        try {
            UserManager.updateUser("a@gmail.com", "Anna", "asdf" , "password" , "Computer Science", "2nd year");
        } catch(Exception e) {

        }
    }

    @Test(expected = InvalidMajorException.class)
    public void testUpdateUserInvalidMajor() throws UserDoesNotExistException, InvalidEmailException, InvalidMajorException {
        try {
            UserManager.updateUser("a@gmail.com", "Anna", "a@gmail.com" , "password" , "Fashion", "2nd year");
        } catch(Exception e) {

        }
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testUpdateUserAlreadyExists() throws UserDoesNotExistException, InvalidEmailException, InvalidMajorException{
        try {
            UserManager.updateUser("asf@gmail.com", "Anna", "sfkn@gmail.com" , "password" , "Computer Science", "2nd year");
        } catch(Exception e) {

        }
        assert(UserManager.findUserByEmail("a@gmail.com").equals(UserManager.findUserByEmail("asf@mail.com")));
    }

}
