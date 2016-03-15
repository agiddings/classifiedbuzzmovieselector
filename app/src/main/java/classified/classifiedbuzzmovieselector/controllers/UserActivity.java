package classified.classifiedbuzzmovieselector.controllers;

/**
 * Created by anna on 3/15/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import classified.classifiedbuzzmovieselector.R;
public class UserActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
    }

    public void onBannedPressed(View v){
        boolean set = false;
        // temp var until fixed
        boolean isBanned = false;
        while (set = false) {
            if (isBanned ) {
                isBanned = false;
                set = true;
            }
            if (!isBanned) {
                isBanned = true;
                set = true;
            }
        }
    }
    public void onLockedPressed(View v){
        boolean set = false;
        // temp var until fixed
        boolean isLocked = false;
        while (set = false) {
            if (isLocked ) {
                isLocked = false;
                set = true;
            }
            if (!isLocked) {
                isLocked = true;
                set = true;
            }
        }
    }
    public void onAdminPressed(View v){
        boolean set = false;
        // temp var until fixed
        boolean isAdmin = false;
        while (set = false) {
            if (isAdmin) {
                isAdmin = false;
                set = true;
            }
            if (!isAdmin) {
                isAdmin = true;
                set = true;
            }
        }
    }

}
