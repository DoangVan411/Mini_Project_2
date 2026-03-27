package com.nhom2.mini_project_2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<MovieEntity> movies = new ArrayList<>();
    private final OnMovieClickListener onMovieClickListener;

    public interface OnMovieClickListener {
        void onMovieClick(MovieEntity movie);
    }

    public MovieAdapter(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<MovieEntity> movieList) {
        movies.clear();
        if (movieList != null) {
            movies.addAll(movieList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movies.get(position);
        holder.tvTitle.setText(movie.title);
        holder.tvMeta.setText(movie.genre + " - " + movie.durationMinutes + " min - " + movie.ageRating);
        holder.tvDescription.setText(movie.description);
        holder.itemView.setOnClickListener(v -> onMovieClickListener.onMovieClick(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvMeta;
        final TextView tvDescription;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMeta = itemView.findViewById(R.id.tvMovieMeta);
            tvDescription = itemView.findViewById(R.id.tvMovieDescription);
        }
    }
}
