package com.example.comp_admin.popular_movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView originalTitleTV = (TextView) findViewById(R.id.titleTextView);
        TextView ratingTV = (TextView) findViewById(R.id.ratingTextView);
        TextView releaseDateTV = (TextView) findViewById(R.id.releaseDateTextView);
        TextView overviewTV = (TextView) findViewById(R.id.overviewTextView);
        ImageView posterIV = (ImageView) findViewById(R.id.posterImageView);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Movie movie = intent.getParcelableExtra("movie");


        originalTitleTV.setText(movie.getOriginalTitle());

        ratingTV.setText(String.valueOf(movie.getVoterAverage()) + " / 10");

        Picasso.with(this)
                .load(movie.getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(posterIV);


        overviewTV.setText(movie.getOverview());

        releaseDateTV.setText(movie.getReleaseDate());
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }
}
