package com.nhom2.mini_project_2.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.entity.ShowtimeEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ShowtimeAdapter extends RecyclerView.Adapter<ShowtimeAdapter.ShowtimeViewHolder> {
    private final List<ShowtimeEntity> showtimes = new ArrayList<>();
    private final int movieDurationMinutes;
    private final Context context;

    public interface OnShowtimeClickListener {
        void onShowtimeClick(ShowtimeEntity showtime);
    }

    private final OnShowtimeClickListener onShowtimeClickListener;

    public ShowtimeAdapter(Context context, int movieDurationMinutes, OnShowtimeClickListener onShowtimeClickListener) {
        this.context = context;
        this.movieDurationMinutes = movieDurationMinutes;
        this.onShowtimeClickListener = onShowtimeClickListener;
    }

    public void setShowtimes(List<ShowtimeEntity> showtimeList) {
        showtimes.clear();
        if (showtimeList != null) {
            showtimes.addAll(showtimeList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowtimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_showtime, parent, false);
        return new ShowtimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowtimeViewHolder holder, int position) {
        ShowtimeEntity showtime = showtimes.get(position);

        long startMs = showtime.startTimeEpochMs;
        long endMs = startMs;
        if (movieDurationMinutes > 0) {
            endMs = startMs + (movieDurationMinutes * 60L * 1000L);
        }

        String start = formatTime(startMs);
        String end = formatTime(endMs);

        holder.tvTimeRange.setText(context.getString(R.string.showtime_time_range_template, start, end));
        holder.tvPrice.setText(context.getString(R.string.showtime_price_template, showtime.price));

        holder.itemView.setOnClickListener(v -> {
            if (onShowtimeClickListener != null) {
                onShowtimeClickListener.onShowtimeClick(showtime);
            }
        });
    }

    private String formatTime(long epochMs) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date(epochMs));
    }

    @Override
    public int getItemCount() {
        return showtimes.size();
    }

    static class ShowtimeViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTimeRange;
        final TextView tvPrice;

        ShowtimeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTimeRange = itemView.findViewById(R.id.tvTimeRange);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}

