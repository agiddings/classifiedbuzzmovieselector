package classified.classifiedbuzzmovieselector.model;

import java.util.HashMap;
import java.util.Map;

import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidNameException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidPasswordException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserDoesNotExistException;

/**
 * Created by Steven on 2/5/16.
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    private User tempUser;

    //For profile class, add a remove user and/or edit user method

    public UserManager() {
        //create a temporary user for M3 demo
        tempUser = new User("John", "user@gmail.com", "pass");
        users.put("user@gmail.com", tempUser);
    }

    //what is the method below trying to do? - Justeen
    // it returns the user with the inputted email - Steven
    public User findUserByEmail(String email) {
        return users.get(email);
    }

    public void addUser(String name, String email, String password)
    throws InvalidPasswordException, InvalidEmailException, InvalidNameException, UserAlreadyExistsException {
        if(name == null) {
            throw new InvalidNameException();
        } else if (email == null || !email.matches("(.*)@(.*).(.*)")) { // rough email check
            throw new InvalidEmailException();
        } else if (password == null) {
            throw new InvalidPasswordException();
        } else if (users.containsKey(email)) {
            throw new UserAlreadyExistsException(email + " is already registered");
        }
        User user = new User(name, email, password);
        users.put(email, user);
    }

    public void updateUser(String currentEmail, String name, String newEmail, String password, String major, String info)
    throws UserDoesNotExistException, InvalidEmailException {
        if(!users.containsKey(currentEmail) ) {
            throw new UserDoesNotExistException("User does not exist");
        }
        User toUpdate = users.get(currentEmail);
        if (name != null && name.length() != 0) {
            toUpdate.setName(name);
        }
        if ( password != null && password.length() != 0) {
            toUpdate.setPassword(password);
        }
        if ( major != null && major.length() != 0) {
            toUpdate.setMajor(major);
        }
        if (info != null && info.length() != 0) {
            toUpdate.setInfo(info);
        }

        if (newEmail != null && newEmail.length() != 0) {
            if (!newEmail.matches("(.*)@(.*).(.*)")) {
                throw new InvalidEmailException("Invalid Email");
            }
            if (users.containsKey(newEmail)) {
                throw new InvalidEmailException("Email already exists");
            }
            toUpdate.setEmail(newEmail);
            users.remove(currentEmail);
            users.put(newEmail, toUpdate);
        }
    }


    /*
     * log in user when the login button is pressed
     *
     * @param email email address user provides
     * @param pass password user provides
     * @return whether log in successful
     *
     */
    public boolean handleLoginRequest(String email, String pass) {
        //if the email is recorded before
        if (users.containsKey(email)) {
            User user = users.get(email);
            return !user.isBanned() && !user.isLocked() && user.passwordMatched(pass);
        } else {
            // false if email is not recorded before
            return false;
        }
    }
}
