package com.example.comp_admin.popular_movies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_URL =
            "http://api.themoviedb.org/3/movie";
    private static final String API =" ";

    public static URL buildUrl(String[] params) throws MalformedURLException  {
        Uri buildUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendPath(params[0])
                .appendQueryParameter("api_key", API)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        Log.v(TAG, "Uri built " + url);

        return  url;
    }
    public static String getResponseFromHttpUrl(URL url)throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
