package classified.classifiedbuzzmovieselector.model;

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
            isLocked = ++failedAttempts >= 5;
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

    public int getFailedAttempts() {
        return failedAttempts;
    }
}
