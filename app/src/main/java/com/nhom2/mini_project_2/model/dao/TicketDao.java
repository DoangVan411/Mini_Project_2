package com.nhom2.mini_project_2.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nhom2.mini_project_2.model.entity.TicketEntity;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(TicketEntity ticket);

    @Query("SELECT COUNT(*) FROM tickets WHERE showtimeId = :showtimeId AND seatNumber = :seatNumber")
    int countByShowtimeAndSeat(long showtimeId, String seatNumber);

    @Query("SELECT seatNumber FROM tickets WHERE showtimeId = :showtimeId")
    List<String> findSeatNumbersByShowtime(long showtimeId);

    @Query("SELECT * FROM tickets WHERE userId = :userId ORDER BY bookedAtEpochMs DESC")
    List<TicketEntity> findByUserId(long userId);
}
