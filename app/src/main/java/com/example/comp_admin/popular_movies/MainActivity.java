package com.example.comp_admin.popular_movies;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private final int NUM_OF_COLUMNS = 2;
    private final String POPULAR_QUERY = "popular";
    private final String TOP_RATED_QUERY = "top_rated";
    Movie[] movies;
    MoviesAdapter mImageAdapter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);


        mLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Spinner sortSpinner = findViewById(R.id.sort_spinner);
        final int currentSelection = sortSpinner.getSelectedItemPosition();


        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentSelection == i) {
                    // If most popular was selected
                    new FetchDataAsyncTask().execute(POPULAR_QUERY);
                } else {
                    // If top rated was selected
                    new FetchDataAsyncTask().execute(TOP_RATED_QUERY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }


    public Movie[] makeMoviesDataToArray(String moviesJsonResults) throws JSONException {

        final String RESULTS = "results";
        final String ORIGINAL_TITLE = "original_title";
        final String POSTER_PATH = "poster_path";
        final String OVERVIEW = "overview";
        final String VOTER_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";


        JSONObject moviesJson = new JSONObject(moviesJsonResults);
        JSONArray resultsArray = moviesJson.getJSONArray(RESULTS);


        movies = new Movie[resultsArray.length()];


        for (int i = 0; i < resultsArray.length(); i++) {

            movies[i] = new Movie();


            JSONObject movieInfo = resultsArray.getJSONObject(i);


            movies[i].setOriginalTitle(movieInfo.getString(ORIGINAL_TITLE));
            movies[i].setPosterPath(movieInfo.getString(POSTER_PATH));
            movies[i].setOverview(movieInfo.getString(OVERVIEW));
            movies[i].setVoterAverage(movieInfo.getDouble(VOTER_AVERAGE));
            movies[i].setReleaseDate(movieInfo.getString(RELEASE_DATE));
        }
        return movies;
    }

    public class FetchDataAsyncTask extends AsyncTask<String, Void, Movie[]> {
        public FetchDataAsyncTask() {
            super();
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            String movieSearchResults = null;

            try {
                URL url = NetworkUtils.buildUrl(params);
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(url);

                if (movieSearchResults == null) {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }

            try {
                return makeMoviesDataToArray(movieSearchResults);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Movie[] movies) {
            mImageAdapter = new MoviesAdapter(getApplicationContext(), movies);
            mRecyclerView.setAdapter(mImageAdapter);
        }
    }

}
