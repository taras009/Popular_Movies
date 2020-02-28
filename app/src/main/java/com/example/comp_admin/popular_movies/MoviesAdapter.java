package com.example.comp_admin.popular_movies;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.*;
import android.view.ViewGroup;

import com.squareup.picasso.*;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final Movie[] mMovies;
    private final Context mContext;


    public MoviesAdapter(Context mContext, Movie[] mMovies) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(ImageView view) {
            super(view);
            mImageView = view;
        }
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ImageView view = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(mMovies[position].getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into((ImageView) holder.mImageView.findViewById(R.id.image_view));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("movie", mMovies[position]);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (mMovies == null || mMovies.length == 0) {
            return -1;
        }
        return mMovies.length;
    }
}
