package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

/**
 * Created by steven on 2/5/16.
 */
public class User {
    String name;
    String email;
    String password;
    String major;
    String info;
    boolean isLocked;
    boolean isBanned;

    //Add user info, major from profile section

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        isLocked = false;
        isBanned = false;
    }

    public boolean checkPassword(String entry) {
        return password.equals(entry);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void lockUser() {
        isLocked = true;
    }

    public void  unlockUser() {
        isLocked = false;
    }

    public void banUser() {
        isBanned = false;
    }

    public void unbanUser() {
        isBanned = true;
    }

    public String toString() {
        return name + " " + email + " " + password;
    }
}
