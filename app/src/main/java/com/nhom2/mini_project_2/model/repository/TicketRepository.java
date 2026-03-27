package com.nhom2.mini_project_2.model.repository;

import android.content.Context;

import com.nhom2.mini_project_2.model.dao.ShowtimeDao;
import com.nhom2.mini_project_2.model.dao.TicketDao;
import com.nhom2.mini_project_2.model.database.AppDatabase;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.model.entity.TicketEntity;
import com.nhom2.mini_project_2.model.entity.UserEntity;

import java.util.Collections;
import java.util.List;

public class TicketRepository {
    private final TicketDao ticketDao;
    private final ShowtimeDao showtimeDao;
    private final AuthRepository authRepository;

    public TicketRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.ticketDao = db.ticketDao();
        this.showtimeDao = db.showtimeDao();
        this.authRepository = new AuthRepository(context);
    }

    public UserEntity getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    public ShowtimeEntity getShowtime(long showtimeId) {
        return showtimeDao.findById(showtimeId);
    }

    public long bookTicket(long showtimeId, String seatNumber) {
        UserEntity user = getCurrentUser();
        if (user == null) {
            return -1;
        }

        ShowtimeEntity showtime = showtimeDao.findById(showtimeId);
        if (showtime == null) {
            return -2;
        }

        String normalizedSeat = seatNumber.trim().toUpperCase();
        if (ticketDao.countByShowtimeAndSeat(showtimeId, normalizedSeat) > 0) {
            return -3;
        }

        TicketEntity ticket = new TicketEntity(
                user.id,
                showtimeId,
                normalizedSeat,
                System.currentTimeMillis()
        );

        try {
            return ticketDao.insert(ticket);
        } catch (Exception ignored) {
            return -4;
        }
    }

    public List<TicketEntity> getMyTickets() {
        UserEntity user = getCurrentUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return ticketDao.findByUserId(user.id);
    }

    public List<String> getBookedSeatNumbers(long showtimeId) {
        return ticketDao.findSeatNumbersByShowtime(showtimeId);
    }
}
