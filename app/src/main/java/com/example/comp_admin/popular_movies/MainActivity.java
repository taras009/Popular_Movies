package com.example.comp_admin.popular_movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MoviesAdaptor moviesAdaptor;

    private RecyclerView mRecyclerView;

    private GridLayoutManager layoutManager;

    ArrayList<Movie> imagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesArray = new ArrayList<>();
        imagesArray.add(new Movie(R.drawable.cupcake));
        imagesArray.add(new Movie(R.drawable.donut));
        imagesArray.add(new Movie(R.drawable.eclair));
        imagesArray.add(new Movie(R.drawable.froyo));
        imagesArray.add(new Movie(R.drawable.gingerbread));
        imagesArray.add(new Movie(R.drawable.honeycomb));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this,5);
        mRecyclerView.setLayoutManager(layoutManager);

        moviesAdaptor = new MoviesAdaptor(imagesArray);
        mRecyclerView.setAdapter(moviesAdaptor);

    }
}
