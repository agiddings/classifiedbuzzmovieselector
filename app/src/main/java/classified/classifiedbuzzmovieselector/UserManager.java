package classified.classifiedbuzzmovieselector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steven on 2/5/16.
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    public UserManager() {} // empty for now

    public User findUserByEmail(String email) {
        return users.get(email);
    }

    public void addUser(String name, String email, String email2, String password, String password2)
    throws IllegalArgumentException, InputDoesNotMatchException, UserAlreadyExistsException {
        if(name == null) {
            throw new IllegalArgumentException("The name field is invalid");
        } else if (email == null || !email.matches("(.*)@(.*).(.*)")) { // rough email check
            throw new IllegalArgumentException("The email field is invalid");
        } else if (password == null) {
            throw new IllegalArgumentException("The password field is invalid");
        } else if (!email.equals(email2)){
            throw  new InputDoesNotMatchException("The emails do not match");
        } else if (!password.equals(password2)) {
            throw new InputDoesNotMatchException("The passwords do not match");
        } else if (users.get(email) != null) {
            throw new UserAlreadyExistsException(email + " is already registered");
        }
        User user = new User(name, email, email2, password, password2);
        users.put(name, user);
    }

    public boolean handleLoginRequest(String email, String pass) {
        User user = findUserByEmail(email);
        return user != null && user.checkPassword(pass);
    }
}
