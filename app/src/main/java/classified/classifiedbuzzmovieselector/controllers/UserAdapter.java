package classified.classifiedbuzzmovieselector.controllers;

/**
 * Created by coleb_000 on 3/15/2016.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import java.util.List;
import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.User;

/**
 * Created by coleb_000 on 2/23/2016.
 * MovieAdapter is the controller for displaying the search results
 */
public class UserAdapter extends ArrayAdapter<User> {

    /**
     * This method sets the view for the list of users
     * @param position The position of a users in the list
     * @param convertView The convertView
     * @param parent The parent view that has the list of users
     * @return The view with the users
     */
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        User user = getItem(position);
        Boolean isAdmin = user.isAdmin();
        Boolean isLocked = user.isLocked();
        Boolean isBanned = user.isBanned();
        TextView name = (TextView) view.findViewById(R.id.username);
        Switch banned = (Switch) view.findViewById(R.id.Banned);
        Switch admin = (Switch) view.findViewById(R.id.Admin);
        Switch locked = (Switch) view.findViewById(R.id.Locked);
        name.setText(user.getName());
        admin.setChecked(isAdmin);
        banned.setChecked(isBanned);
        locked.setChecked(isLocked);
        return view;
    }

    /**
     * This method sets up the list of users in the view
     * @param context The context of the list of users
     * @param resource The number of users
     * @param textViewResourceId The textviewresourceid
     * @param objects The list of user objects to put in the view
     */
    public UserAdapter(Context context, int resource, int textViewResourceId, List<User> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }
}

