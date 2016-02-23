package classified.classifiedbuzzmovieselector.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;

import classified.classifiedbuzzmovieselector.R;
import classified.classifiedbuzzmovieselector.model.Movie;
import classified.classifiedbuzzmovieselector.model.MovieManager;

/**
 * Created by Allie on 2/23/2016.
 */
public class ItemDetailFragment extends Fragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "Title";

    /**
     * The dummy content this fragment is presenting.
     */
    private Movie mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("ITEMDETAILFRAGMENT", "Oncreate");

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            try {
                mItem = (Movie) SearchActivity.getJSONArray().getJSONObject(0).get(ARG_ITEM_ID);
            } catch (JSONException e) {
                Log.d("ItemDetailFragment", "Json error");
            }

            Activity activity = this.getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ITEMDETAILFRAGMENT", "onCreateView");
        View rootView = inflater.inflate(R.layout.content_item_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.content)).setText(mItem.toString());
        }

        return rootView;
    }
}
