package classified.classifiedbuzzmovieselector;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        UserManager manager = new UserManager();
        EditText emailBox = (EditText) findViewById(R.id.loginEmail);
        EditText passBox = (EditText) findViewById(R.id.loginPassword);

        CharSequence text;
        if (manager.handleLoginRequest(emailBox.getText().toString(), passBox.getText().toString())) {
            //TODO Go to postLogin Screen
            //This method might go in the postLogin screen with the following:
            // Log.d("**MYAPP**", "Login button pressed!");
            //Intent intent = new Intent(this, LoginActivity.class);
            //startActivity(intent);

            text = "Login Successful";

        } else {
            text = "Login Failed";
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    public void onRegistrationLinkPressed(View v){
        Log.d("LOGIN ACTIVITY", "Registration Link Pressed");
        //TODO GO to registration screen
        //This method might go in the registration screen with the following:
        //Log.d("**MYAPP**", "Registration Link pressed!");
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    }
}
