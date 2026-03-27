package com.nhom2.mini_project_2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    public interface OnSeatClickListener {
        void onSeatClicked(String seatNumber);
    }

    private final List<String> seats = new ArrayList<>();
    private final Set<String> bookedSeats = new HashSet<>();
    private final OnSeatClickListener onSeatClickListener;
    private String selectedSeat;

    public SeatAdapter(List<String> seats, OnSeatClickListener onSeatClickListener) {
        if (seats != null) {
            this.seats.addAll(seats);
        }
        this.onSeatClickListener = onSeatClickListener;
    }

    public void setBookedSeats(List<String> seatNumbers) {
        bookedSeats.clear();
        if (seatNumbers != null) {
            for (String seat : seatNumbers) {
                if (seat != null) {
                    bookedSeats.add(seat.trim().toUpperCase());
                }
            }
        }
        if (selectedSeat != null && bookedSeats.contains(selectedSeat)) {
            selectedSeat = null;
        }
        notifyDataSetChanged();
    }

    public void setSelectedSeat(String seatNumber) {
        selectedSeat = seatNumber;
        notifyDataSetChanged();
    }

    public String getSelectedSeat() {
        return selectedSeat;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        String seat = seats.get(position);
        boolean isBooked = bookedSeats.contains(seat);
        boolean isSelected = seat.equals(selectedSeat);

        holder.tvSeat.setText(seat);
        holder.itemView.setEnabled(!isBooked);

        if (isBooked) {
            holder.tvSeat.setBackgroundTintList(ContextCompat.getColorStateList(holder.itemView.getContext(), R.color.seat_booked));
            holder.tvSeat.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
        } else if (isSelected) {
            holder.tvSeat.setBackgroundTintList(ContextCompat.getColorStateList(holder.itemView.getContext(), R.color.seat_selected));
            holder.tvSeat.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
        } else {
            holder.tvSeat.setBackgroundTintList(ContextCompat.getColorStateList(holder.itemView.getContext(), R.color.seat_available));
            holder.tvSeat.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black));
        }

        holder.itemView.setOnClickListener(v -> {
            if (isBooked) {
                return;
            }
            selectedSeat = seat;
            notifyDataSetChanged();
            if (onSeatClickListener != null) {
                onSeatClickListener.onSeatClicked(seat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seats.size();
    }

    static class SeatViewHolder extends RecyclerView.ViewHolder {
        final TextView tvSeat;

        SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeat = itemView.findViewById(R.id.tvSeatNumber);
        }
    }
}
