package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.database.AppDatabase;
import com.nhom2.mini_project_2.model.entity.TheaterEntity;
import com.nhom2.mini_project_2.view.ShowtimeListActivity;
import com.nhom2.mini_project_2.view.adapter.TheaterAdapter;

import java.util.List;

public class TheaterListActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    public static final String EXTRA_MOVIE_TITLE = "extra_movie_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_list);

        long movieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, -1);
        String movieTitle = getIntent().getStringExtra(EXTRA_MOVIE_TITLE);

        TextView tvTitle = findViewById(R.id.tvTheaterTitle);
        RecyclerView rvTheaters = findViewById(R.id.rvTheaters);
        TextView tvEmpty = findViewById(R.id.tvEmptyTheaters);

        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            movieTitle = "phim da chon";
        }

        // Java lambda yêu cầu biến capture phải là "effectively final"
        final long finalMovieId = movieId;
        final String finalMovieTitle = movieTitle;

        tvTitle.setText("Rap dang chieu: " + movieTitle);

        TheaterAdapter theaterAdapter = new TheaterAdapter(
                theater -> onTheaterSelected(finalMovieId, finalMovieTitle, theater)
        );
        rvTheaters.setLayoutManager(new LinearLayoutManager(this));
        rvTheaters.setAdapter(theaterAdapter);

        List<TheaterEntity> theaters = AppDatabase.getInstance(this).theaterDao().getTheatersByMovieId(movieId);
        theaterAdapter.setTheaters(theaters);

        if (theaters == null || theaters.isEmpty()) {
            tvEmpty.setText("Phim nay hien chua co rap chieu");
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
    }

    private void onTheaterSelected(long movieId, String movieTitle, TheaterEntity theater) {
        Intent intent = new Intent(this, ShowtimeListActivity.class);
        intent.putExtra(ShowtimeListActivity.EXTRA_MOVIE_ID, movieId);
        intent.putExtra(ShowtimeListActivity.EXTRA_MOVIE_TITLE, movieTitle);
        intent.putExtra(ShowtimeListActivity.EXTRA_THEATER_ID, theater.id);
        intent.putExtra(ShowtimeListActivity.EXTRA_THEATER_NAME, theater.name);
        startActivity(intent);
    }
}
