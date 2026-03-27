package com.nhom2.mini_project_2.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "tickets",
        foreignKeys = {
                @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = ShowtimeEntity.class, parentColumns = "id", childColumns = "showtimeId", onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("userId"),
                @Index("showtimeId"),
                @Index(value = {"showtimeId", "seatNumber"}, unique = true)
        }
)
public class TicketEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userId;
    public long showtimeId;

    @NonNull
    public String seatNumber;

    public long bookedAtEpochMs;

    public TicketEntity(long userId, long showtimeId, @NonNull String seatNumber, long bookedAtEpochMs) {
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.bookedAtEpochMs = bookedAtEpochMs;
    }
}
