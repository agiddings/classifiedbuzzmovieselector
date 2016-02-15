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
 * Created by Allie on 2/5/2016.
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
    }

    /**
     * @param v The view, for registration activity
     */
    public void onCancelRegistrationButtonPressed(View v) {
        Log.d("REGISTRATION ACTIVITY", "Cancel registration button was pressed.");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * @param v The view, for registration activity
     */
    public void onRegisterButtonPressed(View v) {
        Log.d("REGISTRATION ACTIVITY", "Registration button was pressed.");
        EditText name = (EditText) findViewById(R.id.editName);
        EditText email = (EditText) findViewById(R.id.editEmail);
        EditText password1 = (EditText) findViewById(R.id.editPassword1);
        EditText password2 = (EditText) findViewById(R.id.editPassword2);
        CharSequence message;
        if (!(password1.getText().toString().equals(password2.getText().toString()))) {
            message = "Registration failed: Passwords did not match.";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast output = Toast.makeText(context, message, duration);
            output.show();
        } else {
            if (LoginActivity.getManager() == null) {
                LoginActivity.setManager(new UserManager());
            }
            message = "Registration succeeded.";
            try {
                LoginActivity.getManager().addUser(name.getText().toString(), email.getText().toString(), password1.getText().toString());

                //sets current user
                LoginActivity.setUser(LoginActivity.getManager().findUserByEmail(email.getText().toString()));

                Intent intent = new Intent(this, PostLoginActivity.class);
                startActivity(intent);
            } catch (UserAlreadyExistsException a) {
                message = "Registration failed: That user already exists.";
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
