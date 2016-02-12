package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

/**
 * Created by steven on 2/5/16.
 */
public class User {
    String name;
    String email;
    String password;

    //Add user info, major from profile section

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean checkPassword(String entry) {
        return password.equals(entry);
    }

    public String toString() {
        return name + " " + email + " " + password;
    }
}
