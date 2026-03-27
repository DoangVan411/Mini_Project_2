package com.nhom2.mini_project_2.model.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.nhom2.mini_project_2.model.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY id ASC")
    List<MovieEntity> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    MovieEntity getMovieById(long id);
}
