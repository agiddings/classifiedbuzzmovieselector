package classified.classifiedbuzzmovieselector;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

/**
 * Created by Allie on 2/5/2016.
 */
public class RegistrationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
    }

    public void onRegistrationButtonPressed(View v) {
        Log.d("REGISTRATION ACTIVITY", "Registration button was pressed");
        UserManager usermanager = new UserManager();
        EditText name = (EditText) findViewById(R.id.editName);
        EditText email = (EditText) findViewById(R.id.editEmail);
        EditText password1 = (EditText) findViewById(R.id.editPassword1);
        EditText password2 = (EditText) findViewById(R.id.editPassword2);
        CharSequence message;
        if (!password1.equals(password2)) {
            message = "Registration failed: Passwords did not match.";
        } else {
            message = "Registration succeeded";
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast output = Toast.makeText(context, message, duration);
        output.show();
    }
}
