package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.controller.BookingController;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.model.entity.TicketEntity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyTicketsActivity extends AppCompatActivity {
    private BookingController bookingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        bookingController = new BookingController(this);
        if (!bookingController.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        TextView tvTicketList = findViewById(R.id.tvTicketList);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        List<TicketEntity> tickets = bookingController.getMyTickets();
        if (tickets.isEmpty()) {
            tvTicketList.setText(getString(R.string.my_tickets_empty));
        } else {
            StringBuilder builder = new StringBuilder();
            DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault());

            for (TicketEntity ticket : tickets) {
                ShowtimeEntity showtime = bookingController.getShowtime(ticket.showtimeId);
                String showtimeInfo;
                if (showtime == null) {
                    showtimeInfo = getString(R.string.my_tickets_showtime_id, ticket.showtimeId);
                } else {
                    showtimeInfo = getString(
                            R.string.my_tickets_showtime_time,
                            showtime.id,
                            dateTimeFormat.format(new Date(showtime.startTimeEpochMs))
                    );
                }

                builder.append(getString(R.string.my_tickets_ticket_header, ticket.id))
                        .append("\n")
                        .append(showtimeInfo)
                        .append("\n")
                        .append(getString(R.string.my_tickets_seat, ticket.seatNumber))
                        .append("\n")
                        .append(getString(R.string.my_tickets_booked_time, dateTimeFormat.format(new Date(ticket.bookedAtEpochMs))))
                        .append("\n\n");
            }
            tvTicketList.setText(builder.toString());
        }

        btnBackHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
