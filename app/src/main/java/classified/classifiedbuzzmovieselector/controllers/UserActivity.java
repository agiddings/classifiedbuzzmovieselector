package classified.classifiedbuzzmovieselector.controllers;

/**
 * Created by anna on 3/15/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import classified.classifiedbuzzmovieselector.model.User;


import classified.classifiedbuzzmovieselector.R;
public class UserActivity extends AppCompatActivity{

    protected boolean isLocked;
    protected boolean isBanned;
    protected boolean isAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
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
     * checks if the user account is an admin
     * @return a boolean representing whether or not the user account is an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Controls the ban button
     */
    public void onBannedPressed(View v){
        if (!isBanned) {
            isBanned = true;
        } else {
            isBanned = false;
        }
    }

    /**
     * Controls the lock button
     */
    public void onLockedPressed(View v){
       if (!isLocked) {
           isLocked = true;
       } else {
           isLocked = false;
       }
    }

    /**
     * Controls the admin button
     */
    public void onAdminPressed(View v){
        if (!isAdmin) {
            isAdmin = true;
        } else {
            isAdmin = false;
        }
    }
}
