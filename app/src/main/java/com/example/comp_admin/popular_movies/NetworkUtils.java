package com.example.comp_admin.popular_movies;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_URL =
            "http://api.themoviedb.org/3/movie/popular?api_key=";
    private static final String API ="0ab43a289a53f6841fe0eca514954bf8";

    public static URL buildUrl() {
        Uri buildUri = Uri.parse(MOVIE_URL)
                .buildUpon()
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        Log.v(TAG, "Uri builT " + url);

        return  url;
    }
}
