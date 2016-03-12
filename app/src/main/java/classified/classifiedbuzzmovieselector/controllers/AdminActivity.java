package classified.classifiedbuzzmovieselector.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import classified.classifiedbuzzmovieselector.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    /**
     * Goes to home page
     * @param v the current view
     */
    public void goToHome(View v) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}
