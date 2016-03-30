package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidEmailException;
import classified.classifiedbuzzmovieselector.model.Exceptions.InvalidMajorException;
import classified.classifiedbuzzmovieselector.model.Exceptions.UserDoesNotExistException;
import classified.classifiedbuzzmovieselector.model.UserManager;

/**
 * Created by Allie on 2/8/2016.
 */
public class ProfileActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText name = (EditText) findViewById(R.id.newName);
        EditText major = (EditText) findViewById(R.id.newMajor);
        EditText info = (EditText) findViewById(R.id.newInfo);
        name.setText(UserManager.getLoggedUser().getName());
        major.setText(UserManager.getLoggedUser().getMajor());
        info.setText(UserManager.getLoggedUser().getInfo());
    }


    /**
     * define behavior after a user clicks on cancel profile button
     *
     * @param v The view, profile activity
     */
    public void onCancelProfileButtonPressed(View v) {
        Log.d("ProfileActivity", "Cancel profile edits button was pressed.");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * define behavior after a user clicks on profile button
     *
     * @param v THe view, profile activity
     */
    public void onProfileButtonPressed(View v) {
        Log.d("ProfileActivity", "Profile button was pressed.");
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
            } else {
                if (!(password1.getText().toString().equals(password2.getText().toString())) || password1.getText().equals("")) {
                    message = "Update failed: Passwords did not match.";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast output = Toast.makeText(context, message, duration);
                    output.show();
                }
                try {
                    UserManager um = LoginActivity.getManager();
                    um.updateUser(LoginActivity.getUser().getEmail(), name.getText().toString(),
                            email.getText().toString(), password1.getText().toString(),
                            major.getText().toString(), info.getText().toString());
                } catch(UserDoesNotExistException e) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast output = Toast.makeText(context, e.getMessage(), duration);
                    output.show();
                } catch(InvalidEmailException e) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast output = Toast.makeText(context, e.getMessage(), duration);
                    output.show();
                } catch(InvalidMajorException e) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast output = Toast.makeText(context, e.getMessage(), duration);
                    output.show();
                }
                //update storage
                Gson gson = new Gson();
                UserManager userManager = new UserManager();
                String json = gson.toJson(userManager.getAllUsers());
                try {
                    FileOutputStream fos = openFileOutput("users.txt", Context.MODE_PRIVATE);
                    fos.write(json.getBytes());
                    fos.close();
                } catch (IOException e) {
                    Log.d("REGISTRATION ACTIVITY", e.toString());
                }
                message = "Profile update succeeded.";
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                // Shows a message to the user
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast output = Toast.makeText(context, message, duration);
                output.show();
            }
        }
    }
}
