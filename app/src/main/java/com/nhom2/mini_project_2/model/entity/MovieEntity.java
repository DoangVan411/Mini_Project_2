package com.nhom2.mini_project_2.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String title;

    @NonNull
    public String genre;

    public int durationMinutes;

    @NonNull
    public String ageRating;

    @NonNull
    public String description;

    public MovieEntity(@NonNull String title, @NonNull String genre, int durationMinutes, @NonNull String ageRating, @NonNull String description) {
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.ageRating = ageRating;
        this.description = description;
    }
}
