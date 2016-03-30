package classified.classifiedbuzzmovieselector.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidMajorException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidNameException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidPasswordException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserDoesNotExistException;

/**
 * Created by Steven on 2/5/16.
 */
public class UserManager {
    private static final Map<String, User> users = new HashMap<>();
    private static User loggedUser;
    private static String[] majorsArray = {"Architecture", "Industrial Design", "Computational Media", "Computer Science",
        "Aerospace Engineering", "Biomedical Engineering", "Chem and Biomolecular", "Civil Engineering", "Computer Engineering",
        "Computer Science", "Electrical Engineering", "Environmental Engineering", "Industrial Engineering", "MSE",
        "Mechanical Engineering", "Nuclear Engineering", "Applied Mathematics", "Applied Physics", "Biochemistry", "Biology",
        "Chemistry", "Discrete Mathematics", "EAS", "Physics", "Psychology", "ALIS", "Economics", "Economics and International Affairs",
        "Global Economics and Modern Languages", "HTS", "International Affairs and Modern Languages", "LMC", "Public Policy",
        "Business Administration"};
    //For profile class, add a remove user and/or edit user method

    /*
     * Constructor A user manager that deals with users
     * also creates temporary user for testing
     *
     */
    public UserManager() {
        /*User u1 = new User("u1", "u1@mail.com", "u1");
        u1.isAdmin = true;
        User u2 = new User("u2", "u2@mail.com", "u2");
        User u3 = new User("u3", "u3@mail.com", "u3");
        u1.setMajor("Architecture");
        u2.setMajor("Architecture");
        u3.setMajor("Architecture");
        users.put(u1.getEmail(), u1);
        users.put(u2.getEmail(), u2);
        users.put(u3.getEmail(), u3);
        u1.addFriend(u2);
        u2.addFriend(u1);
        u1.addFriend(u3);
        u2.addFriend(u3);
        u3.addFriend(u1);
        u3.addFriend(u2);
        Movie m1 = new Movie("movie", 1900, "R", 90, 0, 0);
        Movie m2 = new Movie("movie2", 1920, "R", 90, 0, 0);
        MovieManager.add(m1);
        MovieManager.add(m2);
        UserRatingManager.addUserRating(new UserRating("wow!", 3, m1, u1));
        UserRatingManager.addUserRating(new UserRating("wow!", 3, m2, u1));
        UserRatingManager.addUserRating(new UserRating("wow2!", 5, m1, u2));
        UserRatingManager.addUserRating(new UserRating("wow2!", 5, m2, u2));
        UserRatingManager.addUserRating(new UserRating("wow3!", 1, m1, u3));
        UserRatingManager.addUserRating(new UserRating("wow3!", 1, m2, u3));*/
    }

    public UserManager (String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<User>>(){}.getType();
        Collection<User> userCollection = gson.fromJson(json, collectionType);
        for (User user : userCollection) {
            users.put(user.getEmail(), user);
        }
    }

    public static Collection<User> getAllUsers() {
        return users.values();

    }

    /*
     * find user by email address
     *
     * @param email user's email
     * @return user the user that's matched with the email
     *
     */
    public static User findUserByEmail(String email) {
        return users.get(email);
    }

    public static User findUserByName(String name) {
        for (User u : users.values()) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    /*
     * register new user
     *
     * @param name user's name
     * @param email user's email
     * @param password user's password
     *
     */
    public static void addUser(String name, String email, String password)
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
        loggedUser = user;
    }


    /*
     * update user information
     *
     * @param currentEmail user current email
     * @param name user's name
     * @param newEmail user's new email
     * @param password user's password
     * @param major user's major
     * @param info user's additional information
     *
     */
    public static void updateUser(String currentEmail, String name, String newEmail, String password, String major, String info)
    throws UserDoesNotExistException, InvalidEmailException, InvalidMajorException {
        if (!users.containsKey(currentEmail)) {
            throw new UserDoesNotExistException("User does not exist");
        }
        User toUpdate = users.get(currentEmail);
        if (name != null && name.length() != 0) {
            toUpdate.setName(name);
        }
        if (password != null && password.length() != 0) {
            toUpdate.setPassword(password);
        }
        if (major != null && major.length() != 0) {
            Boolean foundMajor = false;
            for (int i = 0; i < majorsArray.length; i++) {
                if (majorsArray[i].equals(major)) {
                    toUpdate.setMajor(major);
                    foundMajor = true;
                }
            }
            if (!foundMajor) {
                throw new InvalidMajorException("That major doesn't exist.");
            }
        }
        if (info != null && info.length() != 0) {
            toUpdate.setInfo(info);
        }

        if (newEmail != null && newEmail.length() != 0) {
            if (!newEmail.matches("(.*)@(.*).(.*)")) {
                throw new InvalidEmailException("Invalid Email");
            }

            if (users.containsKey(newEmail) && !newEmail.equals(currentEmail)) {
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
    public static boolean handleLoginRequest(String email, String pass) {
        //if the email is recorded before
        if (users.containsKey(email)) {
            User user = users.get(email);
            if (user.passwordMatched(pass) && !user.isBanned() && !user.isLocked()) {
                loggedUser = user;
                return true;
            }
        }
        return false;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void logOut() {
        loggedUser = null;
    }

    /**
     *
     * Unlock a specific user
     *
     * @param user the user to be unlocked
     */
    public static void  unlockUser(User user) {
        user.isLocked = false;
    }
    /**
     *
     * locks a specific user
     *
     * @param user the user to be locked
     */
    public static void  lockUser(User user) {
        user.isLocked = true;
    }
    /**
     *
     * ban a specific user
     *
     * @param user the user to be banned
     */
    public static void banUser(User user) {
        user.isBanned = true;
    }

    /**
     *
     * unban a specific user
     *
     * @param user the user to be unbanned
     */
    public static void unbanUser(User user) {
        user.isBanned = false;
    }

    /**
     * Makes a user an admin
     */
    public static void makeAdmin(User user) {
        user.isAdmin = true;
    }

    /**
     * Unmakes an Admin
     */
    public static void unmakeAdmin(User user) {
        user.isAdmin = false;
    }

    /**
     *
     * @return list of users
     */
    public static ArrayList<User> getUsers(){
        return new ArrayList<User>(users.values());
    }
}
