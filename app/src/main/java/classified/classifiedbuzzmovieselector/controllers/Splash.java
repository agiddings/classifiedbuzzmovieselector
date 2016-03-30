package classified.classifiedbuzzmovieselector.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import classified.classifiedbuzzmovieselector.R;

/**
 * Created by justeen on 3/30/16.
 */
public class Splash extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class));
                finish();
            }
        }, 1000);
    }
}