package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidNameException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidPasswordException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserAlreadyExistsException;
import classified.classifiedbuzzmovieselector.model.UserManager;

/**
 * Created by Allie on 2/8/2016.
 */
public class ProfileActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void onCancelProfileButtonPressed(View v) {
        Log.d("PROFILE ACTIVITY", "Cancel profile edits button was pressed.");
        Intent intent = new Intent(this, PostLoginActivity.class);
        startActivity(intent);
    }

    public void onProfileButtonPressed(View v) {
        Log.d("PROFILE ACTIVITY", "Profile button was pressed.");
        EditText name = (EditText) findViewById(R.id.newName);
        EditText email = (EditText) findViewById(R.id.newEmail);
        EditText password1 = (EditText) findViewById(R.id.newPassword1);
        EditText password2 = (EditText) findViewById(R.id.newPassword2);
        EditText major = (EditText) findViewById(R.id.newMajor);
        EditText info = (EditText) findViewById(R.id.newInfo);
        CharSequence message;
        if (!(password1.getText().toString().equals(password2.getText().toString()))) {
            message = "Profile update failed: Passwords did not match.";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast output = Toast.makeText(context, message, duration);
            output.show();
        } else {
            if (LoginActivity.getManager() == null) {
                LoginActivity.setManager(new UserManager());
            }
            message = "Profile update succeeded.";
            try {
                // Perhaps add an edit user method? And a remove user method?
                // Also need to update the information for the user.
                LoginActivity.getManager().addUser(name.getText().toString(), email.getText().toString(), password1.getText().toString());
                Intent intent = new Intent(this, PostLoginActivity.class);
                startActivity(intent);
            } catch (UserAlreadyExistsException a) {
                message = "Profile update failed: That user already exists.";
            } catch (InvalidNameException b) {
                message = "The name field is invalid.";
            } catch (InvalidEmailException c) {
                message = "The email field is invalid.";
            } catch (InvalidPasswordException d) {
                message = "The password field is invalid.";
            } finally {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast output = Toast.makeText(context, message, duration);
                output.show();
            }
        }
    }
}
