package classified.classifiedbuzzmovieselector.model;

/**
 * Created by steven on 2/12/16.
 */
public class Admin extends User {

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void  unlockUser(User user) {
        user.isLocked = false;
    }

    public void banUser(User user) {
        user.isBanned = true;
    }

    public void unbanUser(User user) {
        user.isBanned = false;
    }
}
