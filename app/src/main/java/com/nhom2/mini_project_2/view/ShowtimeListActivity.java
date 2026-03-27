package com.nhom2.mini_project_2.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.database.AppDatabase;
import com.nhom2.mini_project_2.model.entity.MovieEntity;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.view.adapter.ShowtimeAdapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;

public class ShowtimeListActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    public static final String EXTRA_MOVIE_TITLE = "extra_movie_title";
    public static final String EXTRA_THEATER_ID = "extra_theater_id";
    public static final String EXTRA_THEATER_NAME = "extra_theater_name";

    private int durationMinutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime_list);

        long movieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, -1);
        String movieTitle = getIntent().getStringExtra(EXTRA_MOVIE_TITLE);
        long theaterId = getIntent().getLongExtra(EXTRA_THEATER_ID, -1);
        String theaterName = getIntent().getStringExtra(EXTRA_THEATER_NAME);

        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            movieTitle = "Phim đã chọn";
        }
        if (theaterName == null || theaterName.trim().isEmpty()) {
            theaterName = "Rạp đã chọn";
        }

        TextView tvScheduleTitle = findViewById(R.id.tvScheduleTitle);
        RecyclerView rvShowtimes = findViewById(R.id.rvShowtimes);
        TextView tvEmpty = findViewById(R.id.tvEmptyShowtimes);

        tvScheduleTitle.setText(getString(R.string.schedule_title, movieTitle, theaterName));

        MovieEntity movie = null;
        if (movieId > 0) {
            movie = AppDatabase.getInstance(this).movieDao().getMovieById(movieId);
        }

        durationMinutes = (movie != null) ? movie.durationMinutes : 0;

        List<ShowtimeEntity> showtimes = null;
        if (movieId > 0 && theaterId > 0) {
            showtimes = AppDatabase.getInstance(this).showtimeDao().getShowtimesByMovieAndTheater(movieId, theaterId);
        }

        ShowtimeAdapter showtimeAdapter = new ShowtimeAdapter(this, durationMinutes, this::onShowtimeSelected);
        rvShowtimes.setLayoutManager(new LinearLayoutManager(this));
        rvShowtimes.setAdapter(showtimeAdapter);

        showtimeAdapter.setShowtimes(showtimes);

        if (showtimes == null || showtimes.isEmpty()) {
            tvEmpty.setVisibility(android.view.View.VISIBLE);
        } else {
            tvEmpty.setVisibility(android.view.View.GONE);
        }
    }

    private void onShowtimeSelected(ShowtimeEntity showtime) {
        long startMs = showtime.startTimeEpochMs;
        long endMs = startMs;
        if (durationMinutes > 0) {
            endMs = startMs + (durationMinutes * 60L * 1000L);
        }

        String start = formatTime(startMs);
        String end = formatTime(endMs);

        Toast.makeText(
                this,
                getString(R.string.showtime_selected_toast, start, end),
                Toast.LENGTH_SHORT
        ).show();
    }

    private String formatTime(long epochMs) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date(epochMs));
    }
}

