package classified.classifiedbuzzmovieselector.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.UserRating;

/**
 * Created by coleb_000 on 3/6/2016.
 */
public class RatingAdapter extends ArrayAdapter<UserRating> {

    /**
     * This method sets the view for the list of ratings
     * @param position The position of a rating in the list
     * @param convertView The convertView
     * @param parent The parent view that has the list of ratings
     * @return The view with the rating
     */
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        UserRating rating = getItem(position);
        TextView username = (TextView) view.findViewById(R.id.ratingUsername);
        TextView major = (TextView) view.findViewById(R.id.ratingMajor);
        TextView comment = (TextView) view.findViewById(R.id.ratingComment);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingRatingBar);
        username.setText(rating.getUser().getName());
        major.setText(rating.getUser().getMajor());
        comment.setText((rating.getComment()));
        ratingBar.setRating(rating.getScore());
        return view;
    }

    /**
     * This method sets up the list of ratings in the view
     * @param context The context of the list of ratings
     * @param resource The number of ratings
     * @param textViewResourceId The textviewresourceid
     * @param objects The list of rating objects to put in the view
     */
    public RatingAdapter(Context context, int resource, int textViewResourceId, List<UserRating> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }
}
