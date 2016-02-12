package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

/**
 * Created by steven on 2/5/16.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String major;
    private String info;
    private boolean isLocked;
    private boolean isBanned;
    private int failedAttempts;

    //Add user info, major from profile section

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        isLocked = false;
        isBanned = false;
        failedAttempts = 0;
    }

    public boolean checkPassword(String entry) {
        if (password.equals(entry)) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;
            return false;
        }
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

    public int getFailedAttempts() {
        return failedAttempts;
    }
}
