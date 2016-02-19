package classified.classifiedbuzzmovieselector.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 2/19/16.
 */
public class MovieManager {
    private static List<Movie> movies;
    private static final Gson gson = new Gson();

    public MovieManager (String json) {
        Type collectionType = new TypeToken<ArrayList<Movie>>(){}.getType();
        movies = gson.fromJson(json, collectionType);
    }

    
}
