package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/12/16.
 */
public class Admin extends User {


    /**
     *
     * Constructor a new Admin with a name, an email and password
     *
     * @param name the name of the admin
     * @param email the email address of the admin
     * @param password the password of the admin
     */
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    /**
     *
     * Unlock a specific user
     *
     * @param user the user to be unlocked
     */
    public void  unlockUser(User user) {
        user.isLocked = false;
    }

    /**
     *
     * ban a specific user
     *
     * @param user the user to be banned
     */
    public void banUser(User user) {
        user.isBanned = true;
    }

    /**
     *
     * unban a specific user
     *
     * @param user the user to be unbanned
     */
    public void unbanUser(User user) {
        user.isBanned = false;
    }
}
