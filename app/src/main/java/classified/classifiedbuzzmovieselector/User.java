package classified.classifiedbuzzmovieselector;

import java.util.InputMismatchException;

/**
 * Created by steven on 2/5/16.
 */
public class User {
    String name;
    String email;
    String password;

    public User(String name, String email, String email2, String password, String password2) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean checkPassword(String entry) {
        return password.equals(entry);
    }
}
