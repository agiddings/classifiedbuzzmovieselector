package classified.classifiedbuzzmovieselector.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 2/5/16.
 */
public class User {
    protected String name;
    protected String email;
    protected String password;
    protected String major;
    protected String info;
    protected boolean isLocked;
    protected boolean isBanned;
    protected int failedAttempts;
    protected List<User> friends;
    protected boolean isAdmin;

    //Add user info, major from profile section

    /**
     * Makes a new User Object.
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        friends = new ArrayList<>();
    }

    /**
     * Checks whether the entered password is correct
     * @param entry the entry being checked for equality with this user's password
     * @return a boolean representing whether entry equals this user's password
     */
    public boolean passwordMatched(String entry) {
        if (password.equals(entry)) {
            failedAttempts = 0;
            return true;
        } else {
            ++failedAttempts;
            Log.d("USER", failedAttempts + "");
            if (failedAttempts >= 5) {
                this.isLocked = true;
                Log.d("USER", "User is locked.");
            }
            return false;
        }
    }

    /**
     * Gets user's name
     * @return this user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets user's name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns user's email
     * @return a string representing the users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the user email to email
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the user major
     * @return a string representing the user major
     */
    public String getMajor() {
        return major;
    }

    /**
     * set the user major
     * @param major the new major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * get user info
     * @return a string representing user profile info
     */
    public String getInfo() {
        return info;
    }

    /**
     * set the user info
     * @param info new user info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * set the user password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * checks if the user account is locked
     * @return a boolean representing whether or not the user account is locked
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * checks if the user account is banned
     * @return a boolean representing whether or not the user account is locked
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * gets the number of failed login attempts
     * @return an int representing the number of failed login attempts
     */
    public int getFailedAttempts() {
        return failedAttempts;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public boolean removeFriend(User friend) {
        return friends.remove(friend);
    }

    public boolean equals(User user) {
        return email.equals(user.email);
    }
}
