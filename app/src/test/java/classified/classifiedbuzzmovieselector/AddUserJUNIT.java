package classified.classifiedbuzzmovieselector;

import org.junit.Test;

import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidNameException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidPasswordException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;

/**
 * Created by Allie on 4/1/2016.
 */
public class AddUserJUNIT {

    @Test(expected = InvalidPasswordException.class)
    public void testAddUserInvalidPassword() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser("Allie", "a@mail.com", null);
    }

    @Test(expected = InvalidEmailException.class)
    public void testAddUserInvalidEmail1() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser("Ashwini", null, "fruit");
    }

    @Test(expected = InvalidEmailException.class)
    public void testAddUserInvalidEmail2() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser("Sam", "a22", "banana");
    }

    @Test(expected = InvalidNameException.class)
    public void testAddUserInvalidName() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser(null, "b@mail.com", "apple");
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser("Grace", "g@mail.com", "sauce");
        UserManager.addUser("Grace", "g@mail.com", "orange");
        assert(UserManager.findUserByName("Grace").equals(UserManager.findUserByEmail("g@mail.com")));
    }

    @Test
    public void testAddUser() throws UserAlreadyExistsException, InvalidPasswordException, InvalidEmailException, InvalidNameException {
        UserManager.addUser("Lauren", "l@mail.com", "pineapple");
        assert(UserManager.findUserByEmail("l@mail.com").equals(UserManager.findUserByName("Lauren")));
    }
}

