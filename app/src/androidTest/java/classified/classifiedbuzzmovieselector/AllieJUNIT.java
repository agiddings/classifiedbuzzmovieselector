package classified.classifiedbuzzmovieselector;

import org.junit.Test;

import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidNameException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidPasswordException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.UserManager;

/**
 * Created by Allie on 4/1/2016.
 */
public class AllieJUNIT {

    @Test(expected = InvalidPasswordException.class)
    public void testAddUserInvalidPassword() {
        try {
            UserManager.addUser("Allie", "a@mail.com", null);
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidEmailException.class)
    public void testAddUserInvalidEmail1() {
        try {
            UserManager.addUser("Ashwini", null, "fruit");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidEmailException.class)
    public void testAddUserInvalidEmail2() {
        try {
            UserManager.addUser("Sam", "a22", "banana");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidNameException.class)
    public void testAddUserInvalidName() {
        try {
            UserManager.addUser(null, "b@mail.com", "apple");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddUserAlreadyExists() {
        try {
            UserManager.addUser("Grace", "g@mail.com", "sauce");
            UserManager.addUser("Grace", "g@mail.com", "orange");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {
        try {
            UserManager.addUser("Lauren", "l@mail.com", "pineapple");
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
