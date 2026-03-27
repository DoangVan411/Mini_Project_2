package com.nhom2.mini_project_2.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "showtimes",
        foreignKeys = {
                @ForeignKey(entity = MovieEntity.class, parentColumns = "id", childColumns = "movieId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = TheaterEntity.class, parentColumns = "id", childColumns = "theaterId", onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("movieId"),
                @Index("theaterId")
        }
)
public class ShowtimeEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long movieId;
    public long theaterId;
    public long startTimeEpochMs;
    public int price;

    public ShowtimeEntity(long movieId, long theaterId, long startTimeEpochMs, int price) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.startTimeEpochMs = startTimeEpochMs;
        this.price = price;
    }
}
