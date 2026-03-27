package com.nhom2.mini_project_2.model.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nhom2.mini_project_2.model.entity.TheaterEntity;

import java.util.List;

@Dao
public interface TheaterDao {
    @Query("SELECT DISTINCT t.* " +
            "FROM theaters t " +
            "INNER JOIN showtimes s ON s.theaterId = t.id " +
            "WHERE s.movieId = :movieId " +
            "ORDER BY t.name ASC")
    List<TheaterEntity> getTheatersByMovieId(long movieId);
}
