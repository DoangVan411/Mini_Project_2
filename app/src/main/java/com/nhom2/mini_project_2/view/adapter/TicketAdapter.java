package com.nhom2.mini_project_2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;
import com.nhom2.mini_project_2.model.entity.TicketEntity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private final List<TicketEntity> tickets = new ArrayList<>();
    private final DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault());
    private final ShowtimeResolver showtimeResolver;

    public interface ShowtimeResolver {
        ShowtimeEntity findById(long showtimeId);
    }

    public TicketAdapter(ShowtimeResolver showtimeResolver) {
        this.showtimeResolver = showtimeResolver;
    }

    public void setTickets(List<TicketEntity> newTickets) {
        tickets.clear();
        if (newTickets != null) {
            tickets.addAll(newTickets);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        TicketEntity ticket = tickets.get(position);
        ShowtimeEntity showtime = showtimeResolver.findById(ticket.showtimeId);

        holder.tvTicketId.setText(holder.itemView.getContext().getString(R.string.ticket_item_header, ticket.id));
        holder.tvSeat.setText(holder.itemView.getContext().getString(R.string.ticket_item_seat, ticket.seatNumber));
        holder.tvBookedAt.setText(holder.itemView.getContext().getString(
                R.string.ticket_item_booked_time,
                dateTimeFormat.format(new Date(ticket.bookedAtEpochMs))
        ));

        if (showtime == null) {
            holder.tvShowtime.setText(holder.itemView.getContext().getString(R.string.ticket_item_showtime_id, ticket.showtimeId));
        } else {
            String startAt = dateTimeFormat.format(new Date(showtime.startTimeEpochMs));
            holder.tvShowtime.setText(holder.itemView.getContext().getString(
                    R.string.ticket_item_showtime_time,
                    showtime.id,
                    startAt,
                    showtime.price
            ));
        }
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTicketId;
        final TextView tvShowtime;
        final TextView tvSeat;
        final TextView tvBookedAt;

        TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTicketId = itemView.findViewById(R.id.tvTicketId);
            tvShowtime = itemView.findViewById(R.id.tvTicketShowtime);
            tvSeat = itemView.findViewById(R.id.tvTicketSeat);
            tvBookedAt = itemView.findViewById(R.id.tvTicketBookedAt);
        }
    }
}
