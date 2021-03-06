package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.User;
import classified.classifiedbuzzmovieselector.model.UserManager;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ListView userList = (ListView) findViewById(R.id.userListView);
        ArrayList<User> listOfUsers = UserManager.getUsers();
        userList.setAdapter(new UserAdapter(this, R.layout.user_layout, R.id.username, listOfUsers));
    }

    /**
     * Goes to home page
     * @param v the current view
     */
    public void goToHome(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * This method makes the user clicked an admin, or unmakes them
     * if they were previously an admin
     * @param v The current view: AdminActivity
     */
    public void adminClickHandler(View v){
        RelativeLayout vwParentRow = (RelativeLayout)v.getParent();
        TextView name = (TextView)vwParentRow.getChildAt(0);
        Switch admin = (Switch)vwParentRow.getChildAt(3);
        boolean isAdmin = admin.isChecked();
        if (isAdmin) {
            UserManager.makeAdmin(UserManager.findUserByName(name.getText().toString()));
        } else {
            UserManager.unmakeAdmin(UserManager.findUserByName(name.getText().toString()));
        }
    }

    /**
     * This method locks or unlocks a user depending on their previous
     * status as unlocked or locked
     * @param v The current view: AdminActivity
     */
    public void lockedClickHandler(View v){
        RelativeLayout vwParentRow = (RelativeLayout)v.getParent();
        TextView name = (TextView)vwParentRow.getChildAt(0);
        Switch locked = (Switch)vwParentRow.getChildAt(2);
        boolean isLocked = locked.isChecked();
        if (isLocked) {
            UserManager.lockUser(UserManager.findUserByName(name.getText().toString()));
        } else {
            UserManager.unlockUser(UserManager.findUserByName(name.getText().toString()));
        }
    }

    /**
     * This method bans or unbans a user depending on if they were
     * previously banned or unbanned.
     * @param v The current view, AdminActivity
     */
    public void bannedClickHandler(View v){
        RelativeLayout vwParentRow = (RelativeLayout)v.getParent();
        TextView name = (TextView)vwParentRow.getChildAt(0);
        Switch banned = (Switch)vwParentRow.getChildAt(1);
        boolean isBanned = banned.isChecked();
        if (isBanned) {
            UserManager.banUser(UserManager.findUserByName(name.getText().toString()));
        } else {
            UserManager.unbanUser(UserManager.findUserByName(name.getText().toString()));
        }
    }
}
