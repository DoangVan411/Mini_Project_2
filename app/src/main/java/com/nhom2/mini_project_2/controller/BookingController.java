package com.nhom2.mini_project_2.controller;

import android.content.Context;

import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.model.entity.TicketEntity;
import com.nhom2.mini_project_2.model.repository.AuthRepository;
import com.nhom2.mini_project_2.model.repository.TicketRepository;

import java.util.List;

public class BookingController {
    private final AuthRepository authRepository;
    private final TicketRepository ticketRepository;

    public BookingController(Context context) {
        this.authRepository = new AuthRepository(context);
        this.ticketRepository = new TicketRepository(context);
    }

    public boolean isLoggedIn() {
        return authRepository.isLoggedIn();
    }

    public long bookTicket(long showtimeId, String seatNumber) {
        return ticketRepository.bookTicket(showtimeId, seatNumber);
    }

    public List<TicketEntity> getMyTickets() {
        return ticketRepository.getMyTickets();
    }

    public ShowtimeEntity getShowtime(long showtimeId) {
        return ticketRepository.getShowtime(showtimeId);
    }

    public List<String> getBookedSeatNumbers(long showtimeId) {
        return ticketRepository.getBookedSeatNumbers(showtimeId);
    }
}
