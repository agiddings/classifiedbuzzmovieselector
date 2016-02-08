package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserAlreadyExistsException;

/**
 * Created by Steven on 2/5/16.
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    private User tempUser;

    public UserManager() {
        //create a temporary user for M3 demo
        tempUser = new User("John", "user@gmail.com", "pass");
    }

    //what is the method below trying to do? - Justeen
    public User findUserByEmail(String email) {
        return users.get(email);
    }

    public void addUser(String name, String email, String password)
    throws IllegalArgumentException, UserAlreadyExistsException {
        if(name == null) {
            throw new IllegalArgumentException("The name field is invalid");
        } else if (email == null || !email.matches("(.*)@(.*).(.*)")) { // rough email check
            throw new IllegalArgumentException("The email field is invalid");
        } else if (password == null) {
            throw new IllegalArgumentException("The password field is invalid");
        } else if (users.containsKey(email)) {
            throw new UserAlreadyExistsException(email + " is already registered");
        }
        User user = new User(name, email, password);
        users.put(email, user);
    }

    public String toString() {
        String returnVal = "";
        for (String s : users.keySet()) {
            returnVal += s + " " + users.get(s) + "\n";
        }
        return returnVal;
    }

    public boolean handleLoginRequest(String email, String pass) {
        //if the email is recorded before
        if (users.containsKey(email)) {
            return users.get(email).checkPassword(pass);
        } else {
            // false if email is not recorded before
            return false;
        }
    }
}
