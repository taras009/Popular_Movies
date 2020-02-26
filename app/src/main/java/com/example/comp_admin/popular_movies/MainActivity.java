package com.example.comp_admin.popular_movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdaptor.MyClickListener {

    private MoviesAdaptor moviesAdaptor;

    private RecyclerView mRecyclerView;

    private GridLayoutManager layoutManager;

    ArrayList<Movie> imagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        moviesAdaptor = new MoviesAdaptor(imagesArray,this);
        mRecyclerView.setAdapter(moviesAdaptor);

    }

    @Override
    public void onMyItemClick(Movie item) {
        Toast.makeText(this, "Something is clicked", Toast.LENGTH_SHORT).show();
        Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);

        startActivity(detailsIntent);
    }
}
