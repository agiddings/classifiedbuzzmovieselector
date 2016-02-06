package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserAlreadyExistsException;

/**
 * Created by steven on 2/5/16.
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    public UserManager() {} // empty for now

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
        } else if (users.get(email) != null) {
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
        User user = users.get(email);
        return user != null && user.checkPassword(pass);
    }
}
