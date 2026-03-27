package com.nhom2.mini_project_2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.model.entity.TheaterEntity;

import java.util.ArrayList;
import java.util.List;

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder> {
    private final List<TheaterEntity> theaters = new ArrayList<>();

    public interface OnTheaterClickListener {
        void onTheaterClick(TheaterEntity theater);
    }

    private final OnTheaterClickListener onTheaterClickListener;

    public TheaterAdapter(OnTheaterClickListener onTheaterClickListener) {
        this.onTheaterClickListener = onTheaterClickListener;
    }

    public void setTheaters(List<TheaterEntity> theaterList) {
        theaters.clear();
        if (theaterList != null) {
            theaters.addAll(theaterList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theater, parent, false);
        return new TheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheaterViewHolder holder, int position) {
        TheaterEntity theater = theaters.get(position);
        holder.tvTheaterName.setText(theater.name);
        holder.tvTheaterAddress.setText(theater.address);
        holder.itemView.setOnClickListener(v -> onTheaterClickListener.onTheaterClick(theater));
    }

    @Override
    public int getItemCount() {
        return theaters.size();
    }

    static class TheaterViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTheaterName;
        final TextView tvTheaterAddress;

        TheaterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTheaterName = itemView.findViewById(R.id.tvTheaterName);
            tvTheaterAddress = itemView.findViewById(R.id.tvTheaterAddress);
        }
    }
}
