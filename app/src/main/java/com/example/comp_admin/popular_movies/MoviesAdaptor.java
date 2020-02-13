package com.example.comp_admin.popular_movies;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdaptor extends RecyclerView.Adapter<MoviesAdaptor.MoviesViewHolder> {


    private ArrayList<Movie> mArrayOfImages;

    private MyClickListener mClickListener;

    public MoviesAdaptor(ArrayList arrayOfImages, MyClickListener listener) {

        mArrayOfImages = arrayOfImages;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        MoviesViewHolder holder = new MoviesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.bind(mArrayOfImages.get(position));

    }

    @Override
    public int getItemCount() {
        return mArrayOfImages.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView mImageView;
        Movie movieItem;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);

        }

        void bind(Movie item) {
            movieItem = item;
            mImageView.setImageResource(item.image);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onMyItemClick(movieItem);
            }
        }
    }

    public interface MyClickListener {
        void onMyItemClick(Movie item);
    }
}
