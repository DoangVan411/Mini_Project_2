package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.controller.BookingController;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.view.adapter.SeatAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookTicketActivity extends AppCompatActivity {
    public static final String EXTRA_SHOWTIME_ID = "extra_showtime_id";

    private BookingController bookingController;
    private SeatAdapter seatAdapter;
    private long showtimeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        bookingController = new BookingController(this);
        showtimeId = getIntent().getLongExtra(EXTRA_SHOWTIME_ID, 1L);

        if (!bookingController.isLoggedIn()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.putExtra(LoginActivity.EXTRA_REDIRECT_BOOKING, true);
            loginIntent.putExtra(LoginActivity.EXTRA_SHOWTIME_ID, showtimeId);
            startActivity(loginIntent);
            finish();
            return;
        }

        TextView tvShowtimeInfo = findViewById(R.id.tvShowtimeInfo);
        TextView tvSelectedSeat = findViewById(R.id.tvSelectedSeat);
        Button btnConfirmBook = findViewById(R.id.btnConfirmBook);
        Button btnMyTickets = findViewById(R.id.btnMyTickets);
        RecyclerView rvSeats = findViewById(R.id.rvSeats);

        seatAdapter = new SeatAdapter(generateSeats(), seat ->
            tvSelectedSeat.setText(getString(R.string.book_ticket_selected_seat, seat))
        );
        rvSeats.setLayoutManager(new GridLayoutManager(this, 6));
        rvSeats.setAdapter(seatAdapter);

        refreshBookedSeats(tvSelectedSeat);

        ShowtimeEntity showtime = bookingController.getShowtime(showtimeId);
        if (showtime == null) {
            tvShowtimeInfo.setText(getString(R.string.book_ticket_showtime_not_found, showtimeId));
        } else {
            String start = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault())
                    .format(new Date(showtime.startTimeEpochMs));
            tvShowtimeInfo.setText(getString(R.string.book_ticket_showtime_info, showtime.id, start, showtime.price));
        }

        btnConfirmBook.setOnClickListener(v -> {
            String seat = seatAdapter.getSelectedSeat();
            if (seat == null || seat.isEmpty()) {
                Toast.makeText(this, getString(R.string.book_ticket_choose_seat_required), Toast.LENGTH_SHORT).show();
                return;
            }

            long result = bookingController.bookTicket(showtimeId, seat);
            if (result > 0) {
                Toast.makeText(this, getString(R.string.book_ticket_success), Toast.LENGTH_SHORT).show();
                seatAdapter.setSelectedSeat(null);
                refreshBookedSeats(tvSelectedSeat);
                return;
            }

            if (result == -1) {
                Toast.makeText(this, getString(R.string.book_ticket_need_login), Toast.LENGTH_SHORT).show();
                return;
            }

            if (result == -2) {
                Toast.makeText(this, getString(R.string.book_ticket_showtime_missing), Toast.LENGTH_SHORT).show();
                return;
            }

            if (result == -3) {
                Toast.makeText(this, getString(R.string.book_ticket_seat_already_booked), Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, getString(R.string.book_ticket_failed), Toast.LENGTH_SHORT).show();
        });

        btnMyTickets.setOnClickListener(v ->
                startActivity(new Intent(this, MyTicketsActivity.class))
        );
    }

    private void refreshBookedSeats(TextView tvSelectedSeat) {
        List<String> bookedSeats = bookingController.getBookedSeatNumbers(showtimeId);
        seatAdapter.setBookedSeats(bookedSeats);
        tvSelectedSeat.setText(getString(R.string.book_ticket_selected_seat_none));
    }

    private List<String> generateSeats() {
        List<String> seats = new ArrayList<>();
        for (char row = 'A'; row <= 'F'; row++) {
            for (int col = 1; col <= 8; col++) {
                seats.add(row + String.valueOf(col));
            }
        }
        return seats;
    }
}
