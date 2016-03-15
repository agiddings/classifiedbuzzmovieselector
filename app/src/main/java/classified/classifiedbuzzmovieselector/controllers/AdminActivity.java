package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
}
