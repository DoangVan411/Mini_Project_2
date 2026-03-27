package com.nhom2.mini_project_2.model.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;

import java.util.List;

@Dao
public interface ShowtimeDao {

    @Query("SELECT * FROM showtimes WHERE movieId = :movieId AND theaterId = :theaterId ORDER BY startTimeEpochMs ASC")
    List<ShowtimeEntity> getShowtimesByMovieAndTheater(long movieId, long theaterId);
}

