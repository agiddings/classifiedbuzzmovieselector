package classified.classifiedbuzzmovieselector.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Movie> getMovies() {
        return movies;
    }

//    public void sortByRating() {
//        Collections.sort(movies, new Comparator<Movie>() {
//            @Override
//            public int compare(Movie lhs, Movie rhs) {
//                return lhs.getRating().compareTo(rhs.getRating());
 //           }
 //       });
 //   }

    public void sortByMajorRating(String major) {

    }

    public void sortByFriendRating(User user) {

    }

    public void sortByNewReleases() {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {
                return rhs.getYear() - lhs.getYear();
            }
        });
    }
}
