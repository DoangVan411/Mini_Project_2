package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.database.AppDatabase;
import com.nhom2.mini_project_2.model.entity.MovieEntity;
import com.nhom2.mini_project_2.view.adapter.MovieAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoLogin = findViewById(R.id.btnGoLogin);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        MovieAdapter movieAdapter = new MovieAdapter(this::openTheaterListByMovie);

        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(movieAdapter);

        movieAdapter.setMovies(AppDatabase.getInstance(this).movieDao().getAllMovies());

        btnGoLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class))
        );
    }

    private void openTheaterListByMovie(MovieEntity movie) {
        Intent intent = new Intent(this, TheaterListActivity.class);
        intent.putExtra(TheaterListActivity.EXTRA_MOVIE_ID, movie.id);
        intent.putExtra(TheaterListActivity.EXTRA_MOVIE_TITLE, movie.title);
        startActivity(intent);
    }
}